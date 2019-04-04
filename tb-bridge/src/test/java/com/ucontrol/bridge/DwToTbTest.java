package com.ucontrol.bridge;

import com.devicewise.tr50.constants.DwOpenCommands;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import converter.TelitConverter;
import model.BridgeConfig;
import model.DwCommand;
import model.Params;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

public class DwToTbTest {

    private static String getTelitJson() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNodes = mapper.createObjectNode();
        ObjectNode cmd = jsonNodes.putObject("cmd");
        ObjectNode cmd1 = cmd.putObject("1");
        cmd1.put("command", "property.publish");
        ObjectNode params = cmd1.putObject("params");
        params.put("thingKey", "Arm_10");
        params.put("key", "temp");
        params.put("value", 1.3);
//        params.put("ts", "2014-08");
        params.put("corrId", "2019-03-10T17:36.322Z");
        ObjectNode cmd2 = cmd.putObject("2");
        ObjectNode command2 = cmd2.put("command", "alarm.publish");
        ObjectNode params2 = command2.putObject("params");
        params2.put("thingKey", "Arm_10");
        params2.put("key", "temp2");
        params2.put("value", 100);
        params2.put("ts", "2019-03-10T17:36.322Z");
        params2.put("name", "2014-08");
        params2.put("corrId", "2014-08");

        String json = mapper.writeValueAsString(jsonNodes);
        System.out.println(json);

        return json;
//        List<DwCommand> commands = JsonToCmd(json);
    }


    public static void main(String[] args) {
        TelitConverter telitConverter = new TelitConverter(getBridgeConfig());
        DwCommand telitMsg = new DwCommand();
        telitMsg.command = DwOpenCommands.CMD_API_PROPERTY_PUBLISH;
        telitMsg.params = new Params();
        telitMsg.params.setTs("2019-04-04T17:38:40Z");
        telitMsg.params.setThingKey("Arm_10");
        telitMsg.params.setKey("WU_WATER");
        telitMsg.params.setValue(11);
        telitConverter.from(telitMsg);

    }

    @Test
    public void testJson2Tb() throws JsonProcessingException {

        TelitConverter mTelitConverter = new TelitConverter(getBridgeConfig());

        Scanner scanner = new Scanner(getTelitJson());
        String data = "";

        while (!data.equals("-1")) {

            System.out.println("Enter json: ");
            data = scanner.nextLine();
            try {
                int beginIndex = data.indexOf('{');

                if (beginIndex < 0 || !data.endsWith("}")) {
                    System.out.println(String.format("Not a json:%s %d,ends %d",
                            data, beginIndex, data.endsWith("}") ? 1 : 0));
                    return;
                }

                String json = data.substring(beginIndex).trim();
                System.out.println("json:" + json);

                List<DwCommand> telitMsg = TelitConverter.JsonToCmd(json);

                for (DwCommand command : telitMsg) {

                    mTelitConverter.from(command);

                    System.out.println(command.getParams().getValue());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static BridgeConfig getBridgeConfig() {
        return new BridgeConfig("rokeha_user@gmail.com",
                "12345678", "http", 8080, "127.0.0.1");
    }

}
