package com.selenium_hrm.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {

    private static Properties properties;
    private static String currentEnv;

    private ConfigHelper() {}

    public static void init() {
        currentEnv = System.getProperty("env", "local");
        loadConfig(currentEnv);
    }

    public static void init(String env) {
        currentEnv = env;
        loadConfig(env);
    }

    private static void loadConfig(String env) {
        properties = new Properties();
        String configFile = "/config/" + env + ".properties";

        try (InputStream input = ConfigHelper.class.getResourceAsStream(configFile)) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Warning: Could not find config file: " + configFile + ", loading default");
                loadDefaultConfig();
            }
        } catch (IOException e) {
            System.err.println("Error loading config file: " + e.getMessage());
            loadDefaultConfig();
        }
    }

    private static void loadDefaultConfig() {
        try (InputStream input = ConfigHelper.class.getResourceAsStream("/config/config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Cannot load default config file");
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot load default config file: " + e.getMessage());
        }
    }

    // ========== CONFIG GETTERS ==========

    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    public static String getValue(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key) {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : 0;
    }

    public static int getInt(String key, int defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public static String getEnv() {
        return currentEnv;
    }

    // ========== CONFIG SHORTCUTS ==========

    public static String getURL() {
        return properties.getProperty("URL", "https://hrm.anhtester.com/");
    }

    public static int getDefaultTimeout() {
        return getInt("DEFAULT_TIMEOUT", 10);
    }

    public static int getPageLoadTimeout() {
        return getInt("PAGE_LOAD_TIMEOUT", 20);
    }

    public static long getPollingInterval() {
        return getInt("POLLING_INTERVAL", 500);
    }

    public static String getBrowser() {
        return properties.getProperty("BROWSER", "chrome");
    }

    public static boolean isHeadless() {
        return getBoolean("HEADLESS", false);
    }

    public static boolean isMaximizeWindow() {
        return getBoolean("MAXIMIZE_WINDOW", true);
    }

    public static String getScreenshotPath() {
        return properties.getProperty("SCREENSHOT_PATH", "target/screenshots");
    }

    public static boolean takeScreenshotOnFailure() {
        return getBoolean("TAKE_SCREENSHOT_ON_FAILURE", true);
    }
}
