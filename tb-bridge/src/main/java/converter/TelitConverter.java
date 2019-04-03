package converter;

import bridge.TelitMsg;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.helpers.TimeFormatter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import model.BridgeConfig;
import model.DwCommand;
import org.thingsboard.client.tools.RestClient;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.msg.TbMsg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class TelitConverter {

    private static final String DEFAULT_TYPE = "Default";
    private static final String STATE_SUFFIX = "state";
    private final BridgeConfig mConfig;

    private RestClient mRestClient;

    public TelitConverter(BridgeConfig config) {

        mConfig = config;
        try {
            URL url = new URL(config.getSchema(), config.getHost(), config.getPort(), "");
            mRestClient = new RestClient(url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


    public static TelitMsg to(TbMsg tbMsg) {
        return null;
    }

    public String from(DwCommand telitMsg) {

        try {
            if (!mRestClient.isLogin())
                mRestClient.login(mConfig.getUserName(), mConfig.getPassword());

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }


        String response = null;

        switch (telitMsg.getCommand()) {

            case DwOpenCommands.CMD_API_ALARM_PUBLISH:
                response = mRestClient.publishTelemetry(
                        telitMsg.getParams().getThingKey(),
                        ValueToTbFormat(telitMsg, STATE_SUFFIX));
                break;
            case DwOpenCommands.CMD_API_PROPERTY_PUBLISH:
            case DwOpenCommands.CMD_API_THING_ATTR_SET:

                response = mRestClient.publishTelemetry(
                        telitMsg.getParams().getThingKey(),
                        ValueToTbFormat(telitMsg));
                break;

            case DwOpenCommands.CMD_API_THING_CREATE:

                String key = telitMsg.getParams().getKey();

                Device device = new Device();
                device.setName(key);
                device.setType(DEFAULT_TYPE);
                Device deviceRes = mRestClient.createDevice(device);

                DeviceId id = deviceRes.getId();
                mRestClient.updateDeviceCredentials(id, key);

                break;

            case DwOpenCommands.CMD_API_THING_DELETE:
                break;

            case DwOpenCommands.CMD_API_THING_TAG_ADD:
                break;

            case DwOpenCommands.CMD_API_THING_TAG_DELETE:
                break;
        }

        return response;
    }

    private String ValueToTbFormat(DwCommand telitMsg) {
        return ValueToTbFormat(telitMsg, "");
    }

    private String ValueToTbFormat(DwCommand telitMsg, String suffix) {
        JsonObject jsonObject = new JsonObject();

        String ts = telitMsg.getParams().getTs();
        String key = telitMsg.getParams().getKey();
        Date date = null;
        if (!suffix.isEmpty())
            suffix = "_" + suffix;

        if (ts != null) {

            date = TimeFormatter.toDate(ts);

            if (date != null) {

                jsonObject.addProperty("ts", date.getTime());
                JsonObject elm = new JsonObject();
                elm.addProperty(key + suffix, telitMsg.getParams().getValue());
                jsonObject.add("values", elm);

            }
        }
        if (date == null) {
            jsonObject.addProperty(key + suffix, telitMsg.getParams().getValue());
        }

        return jsonObject.toString();
    }


    public void resetToken() {
        mRestClient.resetToken();
    }

    public static List<DwCommand> JsonToCmd(String json) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.reader().readTree(json);

        if (rootNode.has("cmd")) {
            rootNode = rootNode.get("cmd");
        }
        List<DwCommand> commands = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            String fieldName = String.valueOf(i);
            if (rootNode.has(fieldName)) {

                DwCommand dwCommand = getCommand(mapper, rootNode, fieldName);
                commands.add(dwCommand);
                log.info("prop value:{}", dwCommand.getParams().getValue());
            }

        }

        if (commands.isEmpty()) {
            commands.add(getCommand(mapper, rootNode));
            log.info("prop value:{}", commands.get(0).getParams().getValue());
        }

        return commands;
    }

    private static DwCommand getCommand(ObjectMapper mapper, JsonNode node, String fieldName) throws IOException {
        JsonNode jsonNode = node.get(fieldName);
        return getCommand(mapper, jsonNode);
    }

    private static DwCommand getCommand(ObjectMapper mapper, JsonNode jsonNode) throws IOException {

        String value = mapper.writeValueAsString(jsonNode);

        return mapper.readValue(value, DwCommand.class);
    }
}
