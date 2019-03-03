package bridge;


import converter.TelitConverter;
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


public class MyDiscoveryListener implements DiscoveryListener {

    public static final int UUID_COMM = 0x1101;
    private static final Object lock = new Object();
    public ArrayList<RemoteDevice> devices;

    public MyDiscoveryListener() {
        devices = new ArrayList<RemoteDevice>();
    }

    public static void main(String[] args) {

        MyDiscoveryListener listener = new MyDiscoveryListener();

        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = localDevice.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, listener);

            try {
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }


            System.out.println("Device Inquiry Completed. ");


            UUID[] uuidSet = new UUID[1];
//            uuidSet[0]=new UUID(0x1105); //OBEX Object Push service
//            uuidSet[0]=new UUID(0x0008); //OBEX Object Push service
//            uuidSet[0]=new UUID(0x000C); //OBEX Object Push service
//            uuidSet[0]=new UUID(0x0001); //OBEX Object Push service
//            uuidSet[0]=new UUID(0x0100); //OBEX Object Push service
            uuidSet[0] = new UUID(UUID_COMM); //OBEX Object Push service
//            uuidSet[0]=new UUID(0x1132); //OBEX Object Push service
//            uuidSet[0] = new UUID(0x1134); //OBEX Object Push service

            int[] attrIDs = new int[]{
                    0x0100 // Service name
            };

            for (RemoteDevice device : listener.devices) {
                String friendlyName = device.getFriendlyName(false);
                if (friendlyName.equals("HC-05")) {
                    System.out.println("searchServices: " + friendlyName);
                    agent.searchServices(
                            attrIDs, uuidSet, device, listener);
                }


                try {
                    synchronized (lock) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }


                System.out.println("Service search finished.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {
        String name;
        try {
            name = btDevice.getFriendlyName(false);
        } catch (Exception e) {
            name = btDevice.getBluetoothAddress();
        }

        devices.add(btDevice);
        System.out.println("device found: " + name);

    }


    public void inquiryCompleted(int arg0) {
        synchronized (lock) {
            lock.notify();
        }
    }


    public void serviceSearchCompleted(int arg0, int arg1) {
        synchronized (lock) {
            lock.notify();
        }
    }


    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        for (ServiceRecord aServRecord : servRecord) {
            String url = aServRecord.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            if (url == null) {
                continue;
            }
            sendMessageToDevice(url);
//            DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
//            if (serviceName != null) {
//                System.out.println("service " + serviceName.getValue() + " found " + url);
//
//                if(serviceName.getValue().equals("OBEX Object Push")){
//                    sendMessageToDevice(url);
//                }
//            } else {
//                System.out.println("service found " + url);
//            }


        }
    }

    private static void sendMessageToDevice(String serverURL) {
        try {
            System.out.println("Connecting to " + serverURL);


            StreamConnection clientSession = (StreamConnection) Connector.open(serverURL);
//            HeaderSet hsConnectReply = clientSession.connect(null);
           /* if (hsConnectReply.getResponseCode() != ResponseCodes.OBEX_HTTP_OK) {
                System.out.println("Failed to connect");
                return;
            }*/

//            HeaderSet hsOperation = clientSession.createHeaderSet();
//            hsOperation.setHeader(HeaderSet.NAME, "Hello.txt");
//            hsOperation.setHeader(HeaderSet.TYPE, "text");
//
//            Create PUT Operation
//            Operation putOperation = clientSession.put(hsOperation);

            // Send some text to server
            InputStream os = clientSession.openInputStream();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(os));
            String line;
            while ((line = inputStream.readLine()) != null) {
                int beginIndex = line.indexOf('{');
                if (beginIndex > 0) {
                    String substring = line.substring(beginIndex);
                    System.out.println("data:" + substring);

                    TelitMsg telitMsg = JacksonUtil.fromString(substring, TelitMsg.class);
                    TbMsg from = TelitConverter.from(telitMsg);
                    RestClient restClient = new RestClient();
                    Device device = restClient.createDevice("", "default");
                }

            }

            os.close();

//            putOperation.close();
//
//            clientSession.disconnect(null);

            clientSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}