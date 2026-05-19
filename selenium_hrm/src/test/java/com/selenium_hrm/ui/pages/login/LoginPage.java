package com.selenium_hrm.ui.pages.login;

import com.selenium_hrm.config.ConfigHelper;
import com.selenium_hrm.ui.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage openURL() {
        openURL(ConfigHelper.getURL());
        return this;
    }

    public LoginPage enterUsername(String username) {
        setText(LoginLocator.TXT_USERNAME, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        setText(LoginLocator.TXT_PASSWORD, password);
        return this;
    }

    public LoginPage clickLogin() {
        clickElement(LoginLocator.BTN_LOGIN);
        return this;
    }

    public boolean isLoginSuccessURL(String expectedURL) {
        waitForPageLoaded();
        return waitForURLContainsResult(expectedURL, 10);
    }
}
