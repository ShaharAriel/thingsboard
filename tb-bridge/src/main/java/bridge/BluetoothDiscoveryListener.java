package bridge;


import converter.TelitConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.thingsboard.client.tools.RestClient;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.msg.TbMsg;
import org.thingsboard.server.dao.util.mapping.JacksonUtil;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

@Slf4j
@Service
public class BluetoothDiscoveryListener implements DiscoveryListener {

    public static final int UUID_COMM = 0x1101;
    public static final UUID[] UUID_SET = new UUID[1];

    static {
        UUID_SET[0] = new UUID(UUID_COMM);
    }

    private  DiscoveryAgent agent;
    private RestClient restClient;

    public BluetoothDiscoveryListener()  {
        restClient = new RestClient("http://localhost:8080");
//        restClient.getRestTemplate().setRequestFactory(getRequestFactoryForSelfSignedCert());
        restClient.login("arielshahar8@gmail.com", "12345678");
        Device device = restClient.createDevice("mydevice2", "default");
        log.info("device:name[{}] type[{}]" , device.getName(),device.getType());

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

    public static void main(String args[]) throws Exception {
        new BluetoothDiscoveryListener();//.startInquiry();

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

    private static HttpComponentsClientHttpRequestFactory getRequestFactoryForSelfSignedCert() throws Exception {
        SSLContextBuilder builder = SSLContexts.custom();
        builder.loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true);
        SSLContext sslContext = builder.build();
        SSLConnectionSocketFactory sslSelfSigned = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
            @Override
            public void verify(String host, SSLSocket ssl) {
            }

            @Override
            public void verify(String host, X509Certificate cert) {
            }

            @Override
            public void verify(String host, String[] cns, String[] subjectAlts) {
            }

            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("https", sslSelfSigned)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

}