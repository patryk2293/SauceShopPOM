package com.sauceshop.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

    private static Properties properties = new Properties();
    private static String localizationOfConfigFile = "src/main/config.properties";

    private static String readPropertiesFile(String localization, String property) {
        try {
            InputStream inputStream = new FileInputStream(localization);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is something wrong with the file. Does it exist? " +
                    "File location: " + localization);
        }
        return properties.getProperty(property);
    }

    public static String getURL() {
        return readPropertiesFile(localizationOfConfigFile, "URL");
    }

    public static String getUser() {
        return readPropertiesFile(localizationOfConfigFile, "user");
    }

    public static String getPassword() {
        return readPropertiesFile(localizationOfConfigFile, "password");
    }

}
