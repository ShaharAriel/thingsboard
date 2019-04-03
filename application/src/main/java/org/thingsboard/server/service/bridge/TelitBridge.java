package org.thingsboard.server.service.bridge;

import bridge.BluetoothDiscoveryListener;
import model.BridgeConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TelitBridge {

    @Value("${bridge.username}")
    private String userName;

    @Value("${bridge.password}")
    private String password;

    @Value("${bridge.schema}")
    private String schema;

    @Value("${bridge.port}")
    private int port;

    @Value("${bridge.host}")
    private String host;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        BridgeConfig config = new BridgeConfig(userName,password,schema,port,host);

        new BluetoothDiscoveryListener(config).startInquiry();
    }


}
