package org.perfectnumbers.integration;

import org.apache.cxf.endpoint.Server;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.perfectnumbers.Config;
import org.perfectnumbers.RestServer;

import java.io.IOException;

public class BaseIntegrationTest {

    protected static String BASE_URL = "http://localhost:8080/perfectnumbers/";
    protected static CloseableHttpClient client;
    protected static Server server;

    @BeforeClass
    public static void setupClass() {
        Config config = Config.getInstance();

        server = new RestServer().initializeServer(config).start();

        client = HttpClients.createDefault();
    }

    @AfterClass
    public static void teardownClass() throws IOException {
        client.close();
        if (server.isStarted()) {
            server.stop();
        }
    }

}
