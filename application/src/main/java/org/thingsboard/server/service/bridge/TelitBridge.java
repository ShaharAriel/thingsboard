package org.thingsboard.server.service.bridge;

import bridge.BluetoothDiscoveryListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TelitBridge {

    @Autowired
    BluetoothDiscoveryListener listener;

    @PostConstruct
    void init() {
        listener.startInquiry();
    }


}
