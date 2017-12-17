package org.perfectnumbers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final Logger logger = LogManager.getLogger(Config.class);

    private static Config ourInstance = new Config();
    private static Properties properties;

    public static Config getInstance() {
        synchronized (Config.class) {
            return ourInstance;
        }
    }

    public String getProperty(final String key) {
        return properties.getProperty(key);
    }

    private Config() {
        logger.info("Initializing config from config.properties");

        properties = new Properties();
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            logger.fatal("Loading config failed.", e);
        }

        logger.info("Finished initializing config from config.properties");
    }
}
