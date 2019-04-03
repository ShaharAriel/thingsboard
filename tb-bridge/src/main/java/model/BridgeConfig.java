package model;

public class BridgeConfig {

    private String userName;

    private String password;

    private String schema;

    private int port;

    private String host;

    public BridgeConfig(String userName, String password, String schema, int port, String host) {
        this.userName = userName;
        this.password = password;
        this.schema = schema;
        this.port = port;
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public String getSchema() {
        return schema;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getUserName() {
        return userName;
    }
}
