package com.selenium_hrm.config.testdata;

import java.io.File;

public class SystemHelper {
public static String getCurrentDir() {
    return System.getProperty("user.dir") +  File.separator;
    }
}
