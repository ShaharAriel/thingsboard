package converter;

import bridge.TelitMsg;
import com.devicewise.tr50.api.DwOpenAlarm;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.google.gson.JsonObject;
import converter.base.Converter;
import model.DwCommand;
import org.thingsboard.client.tools.RestClient;
import org.thingsboard.server.common.data.alarm.Alarm;
import org.thingsboard.server.common.msg.TbMsg;

public class TelitConverter {

    private final RestClient mRestClient;

    public TelitConverter() {
        mRestClient = new RestClient("http://localhost:8080");

    }


    public static TelitMsg to(TbMsg tbMsg) {
        return null;
    }

    public Object from(DwCommand telitMsg) {

        switch (telitMsg.getCommand()) {

            case DwOpenCommands.CMD_API_ALARM_PUBLISH:
                Alarm alarm = new Alarm();
                alarm.setStartTs(telitMsg.getParams().getTs());
                mRestClient.createAlarm(alarm);
                break;

            case DwOpenCommands.CMD_API_PROPERTY_PUBLISH:
                mRestClient.publishTelemetry(telitMsg.getParams().thingKey, ValueToTbFormat(telitMsg));
                break;

            case DwOpenCommands.CMD_API_THING_ATTR_SET:
                break;

            case DwOpenCommands.CMD_API_THING_CREATE:
                break;

            case DwOpenCommands.CMD_API_THING_DELETE:
                break;

            case DwOpenCommands.CMD_API_THING_TAG_ADD:
                break;

            case DwOpenCommands.CMD_API_THING_TAG_DELETE:
                break;
        }
        return null;
    }

    private String ValueToTbFormat(DwCommand telitMsg) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(telitMsg.getParams().getKey(),telitMsg.getParams().getValue());
        return jsonObject.getAsString();
    }
}
