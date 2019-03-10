package com.ucontrol.bridge;

import bridge.BluetoothDiscoveryListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import converter.TelitConverter;
import model.DwCommand;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

public class DwToTbTest {

    public static String getTelitJson() throws JsonProcessingException {

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
        new BluetoothDiscoveryListener().startInquiry();
        while (true) ;
    }

    @Test
    public void testJson2Tb() throws JsonProcessingException {
        TelitConverter mTelitConverter = new TelitConverter();

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

}
