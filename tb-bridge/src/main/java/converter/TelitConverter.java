package converter;

import bridge.TelitMsg;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import model.DwCommand;
import org.thingsboard.client.tools.RestClient;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.msg.TbMsg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TelitConverter {

    private static final String DEFAULT_TYPE = "Default";
    private final RestClient mRestClient;

    public TelitConverter() {
        mRestClient = new RestClient("http://localhost:8080");
    }


    public static TelitMsg to(TbMsg tbMsg) {
        return null;
    }

    public String from(DwCommand telitMsg) {

        try {
            if (!mRestClient.isLogin())
                mRestClient.login("sariel@ravtech.co.il", "1q2w3e4r");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }


        String response = null;

        switch (telitMsg.getCommand()) {

            case DwOpenCommands.CMD_API_ALARM_PUBLISH:
                response = mRestClient.publishTelemetry(
                        telitMsg.getParams().getThingKey(),
                        ValueToTbFormat(telitMsg));
                break;
            case DwOpenCommands.CMD_API_PROPERTY_PUBLISH:
            case DwOpenCommands.CMD_API_THING_ATTR_SET:

                response = mRestClient.publishTelemetry(
                        telitMsg.getParams().getThingKey(),
                        ValueToTbFormat(telitMsg));
                break;

            case DwOpenCommands.CMD_API_THING_CREATE:
                Device device = new Device();
                device.setName(telitMsg.getParams().getKey());
                device.setType(DEFAULT_TYPE);
//                device.
                response = String.valueOf(
                        mRestClient.createDevice(device).getId());
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
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(telitMsg.getParams().getKey(), telitMsg.getParams().getValue());
        return jsonObject.toString();
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
