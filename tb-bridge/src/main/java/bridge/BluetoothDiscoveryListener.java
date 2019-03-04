package bridge;


import converter.TelitConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thingsboard.client.tools.RestClient;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.msg.TbMsg;
import org.thingsboard.server.dao.util.mapping.JacksonUtil;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Slf4j
@Service
public class BluetoothDiscoveryListener implements DiscoveryListener {

    public static final int UUID_COMM = 0x1101;
    public ArrayList<RemoteDevice> devices;
    public static final UUID[] UUID_SET = new UUID[1];

    static {
        UUID_SET[0] = new UUID(UUID_COMM);
    }

    private  DiscoveryAgent agent;
    private RestClient restClient;

    public BluetoothDiscoveryListener() {
        devices = new ArrayList<>();
        restClient = new RestClient("https://localhost");
    }

    public void startInquiry()
    {
        log.info("startInquiry");
        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            agent = localDevice.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, this);
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        new BluetoothDiscoveryListener().startInquiry();
        while (true);
    }


    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {
        String name;
        try {
            name = btDevice.getFriendlyName(false);
        } catch (Exception e) {
            name = btDevice.getBluetoothAddress();
        }
        log.info("deviceDiscovered:[{}]",name);

        if (name.equals("HC-05")) {
            log.info("searchServices: " + name);
            try {
                agent.searchServices(
                        null, UUID_SET, btDevice, this);
            } catch (BluetoothStateException e) {
                e.printStackTrace();
            }
        }

        log.info("Service search finished.");
    }


    public void inquiryCompleted(int arg0) {

    }


    public void serviceSearchCompleted(int arg0, int arg1) {

    }


    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        for (ServiceRecord aServRecord : servRecord) {
            String url = aServRecord.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            if (url == null) {
                continue;
            }
            sendMessageToDevice(url);

        }
    }

    private  void sendMessageToDevice(String serverURL) {
        try {
            log.info("Connecting to " + serverURL);

            StreamConnection clientSession = (StreamConnection) Connector.open(serverURL);

            InputStream os = clientSession.openInputStream();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(os));
            String line;
            while ((line = inputStream.readLine()) != null) {
                int beginIndex = line.indexOf('{');
                if (beginIndex > 0) {
                    String substring = line.substring(beginIndex).trim();
                    log.info("data:" + substring);
                    if(!substring.endsWith("}"))
                        return;

                    log.info("json:" + substring);
//                    String telitMsg = JacksonUtil.fromString(substring, String.class);
//                    TbMsg from = TelitConverter.from(telitMsg);

                    restClient.login("tenant@thingsboard.org", "tenant");
                    Device device = restClient.createDevice("mydevice", "default");
                    log.info("device:name[{}] type[{}]" + device.getName(),device.getType());
                }

            }

            os.close();

            clientSession.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
    }

}