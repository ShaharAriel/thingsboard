package org.thingsboard.server.service.bridge;

import bridge.BluetoothDiscoveryListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TelitBridge {


    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        new BluetoothDiscoveryListener().startInquiry();
    }


}
