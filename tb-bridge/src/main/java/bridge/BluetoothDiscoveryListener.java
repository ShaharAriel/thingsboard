package bridge;


import converter.TelitConverter;
import lombok.extern.slf4j.Slf4j;
import model.BridgeConfig;
import model.DwCommand;
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
import java.util.List;

@Slf4j
public class BluetoothDiscoveryListener implements DiscoveryListener {

    private static final int UUID_COMM = 0x1101;
    private static final UUID[] UUID_SET = new UUID[1];

    static {
        UUID_SET[0] = new UUID(UUID_COMM);
    }

    private DiscoveryAgent agent;
    private TelitConverter mTelitConverter;

    public BluetoothDiscoveryListener(BridgeConfig config) {
        mTelitConverter = new TelitConverter(config);

    }

    public void startInquiry() {
        log.info("startInquiry");
        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            agent = localDevice.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, this);
        } catch (BluetoothStateException e) {
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
        log.info("deviceDiscovered:[{}]", name);

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
            receiveMessageFromDevice(url);
        }
    }

    private void receiveMessageFromDevice(String serverURL) {
        try {
            log.info("Connecting to " + serverURL);

            StreamConnection clientSession = (StreamConnection) Connector.open(serverURL);
            InputStream os = clientSession.openInputStream();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(os));

            String json;
            while ((json = inputStream.readLine()) != null) {

                log.info("bluetooth data is:{}", json);

                int beginJsonTag = json.indexOf("{");
                if (beginJsonTag > 0 && json.startsWith("AT#DWSENDR=") && json.endsWith("}")) {

                    json = json.substring(beginJsonTag);

                    log.info("bluetooth json is:{}", json);

                    List<DwCommand> telitMsg = TelitConverter.JsonToCmd(json);

                    for (DwCommand command : telitMsg) {
                        try {
                            mTelitConverter.from(command);
                            log.info("bluetooth command is:{}", command.getCommand());

                        } catch (Exception e) {
                            mTelitConverter.resetToken();
                            e.printStackTrace();
                            log.error(e.getMessage(), e);
                        }
                    }

                } else {
                    log.debug("verbose-data is:{}",
                            beginJsonTag, json.endsWith("}") ? 1 : 0, json);
                }
            }

            os.close();

            clientSession.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
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