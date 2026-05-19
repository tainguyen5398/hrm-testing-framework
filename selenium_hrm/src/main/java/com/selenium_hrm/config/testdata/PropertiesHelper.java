package com.selenium_hrm.config.testdata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties properties;

    private PropertiesHelper() {}

    public static Properties loadAllFiles() {
        properties = new Properties();
        loadFromResource("/config/config.properties");
        loadFromResource("/testdata/properties/API.properties");
        return properties;
    }

    private static void loadFromResource(String resourcePath) {
        try (InputStream input = PropertiesHelper.class.getResourceAsStream(resourcePath)) {
            if (input != null) {
                Properties tempProp = new Properties();
                tempProp.load(input);
                properties.putAll(tempProp);
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not load: " + resourcePath);
        }
    }

    public static void setDefaultFile() {
        loadAllFiles();
    }

    public static String getValue(String key) {
        if (properties == null) {
            loadAllFiles();
        }
        return properties.getProperty(key);
    }

    public static String getValue(String key, String defaultValue) {
        if (properties == null) {
            loadAllFiles();
        }
        return properties.getProperty(key, defaultValue);
    }

    public static void setValue(String key, String keyValue) {
        if (properties == null) {
            loadAllFiles();
        }
        properties.setProperty(key, keyValue);
    }
}
