package org.perfectnumbers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Config config = Config.getInstance();

        RestServer server = new RestServer();
        server.initializeServer(config).start();

        logger.info("Server ready on endpoint {}", config.getProperty("endpoint.address"));
    }

}
