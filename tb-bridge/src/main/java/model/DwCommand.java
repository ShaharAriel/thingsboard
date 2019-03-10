package model;

/*        "{\"cmd\":{\"1\":{\"command\":\"property.publish\",\"Params\":{\"tng\":\"mtng\"}},\"2\":{\"command\":\"alarm.publish\",\"Params\":{\"tng\":\"mtng\"}}}}"
 */
public class DwCommand {

    private String command;
    private Params params;

    public String getCommand() {
        return command;
    }

    public Params getParams() {
        return params;
    }
}
