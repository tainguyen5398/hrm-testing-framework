package com.selenium_hrm.ui.pages.login;

import org.openqa.selenium.By;

public class LoginLocator {

    public static final By TXT_USERNAME = By.id("iusername");
    public static final By TXT_PASSWORD = By.name("password");
    public static final By BTN_LOGIN = By.xpath("//button[@type='submit']");
}
