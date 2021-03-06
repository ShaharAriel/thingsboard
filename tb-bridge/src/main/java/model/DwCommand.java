package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*        "{\"cmd\":{\"1\":{\"command\":\"property.publish\",\"Params\":{\"tng\":\"mtng\"}},\"2\":{\"command\":\"alarm.publish\",\"Params\":{\"tng\":\"mtng\"}}}}"
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DwCommand {

    public String command;
    public Params params;

    public String getCommand() {
        return command;
    }

    public Params getParams() {
        return params;
    }
}
