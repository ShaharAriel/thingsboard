package com.ucontrol.bridge;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.DwCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DwToTbTest {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNodes = mapper.createObjectNode();
        ObjectNode cmd = jsonNodes.putObject("cmd");
        ObjectNode cmd1 = cmd.putObject("1");
        cmd1.put("command", "property.publish");
        ObjectNode params = cmd1.putObject("params");
        params.put("thingKey", "mtng");
        params.put("key", "mtng");
        params.put("value", 0.3);
        params.put("ts", "2014-08");
        params.put("corrId", "2014-08");
        ObjectNode cmd2 = cmd.putObject("2");
        ObjectNode command2 = cmd2.put("command", "alarm.publish");
        ObjectNode params2 = command2.putObject("params");
        params2.put("thingKey", "mtng");
        params2.put("key", "mtng");
        params2.put("value", 0.3);
        params2.put("ts", "2014-08");
        params2.put("corrId", "2014-08");

        String json = mapper.writeValueAsString(jsonNodes);
        System.out.println(json);

        List<DwCommand> commands = JsonToCmd(json);
    }

    private static List<DwCommand> JsonToCmd(String json) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode objectNode = mapper.reader().readTree(json);
        JsonNode node = objectNode.get("cmd");
        List<DwCommand> commands = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            String fieldName = String.valueOf(i);
            if (node.has(fieldName)) {

                DwCommand dwCommand = getCommand(mapper, node, fieldName);
                commands.add(dwCommand);

                System.out.println(dwCommand.getParams().getValue());
            }
        }
        return commands;
    }

    private static DwCommand getCommand(ObjectMapper mapper, JsonNode node, String fieldName) throws IOException {
        JsonNode jsonNode = node.get(fieldName);
        String value = mapper.writeValueAsString(jsonNode);

        return mapper.readValue(value, DwCommand.class);
    }
}
