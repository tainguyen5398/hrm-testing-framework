package com.selenium_hrm.ui.tests.login;

import com.selenium_hrm.listeners.TestListener;
import com.selenium_hrm.ui.pages.login.LoginAction;
import com.selenium_hrm.ui.pages.login.LoginPage;
import com.selenium_hrm.ui.tests.login.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.selenium_hrm.ui.base.BaseUI;

@Listeners(TestListener.class)
public class LoginTest extends BaseUI {
    private LoginPage loginPage;
    private LoginAction loginAction;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        loginAction = new LoginAction(loginPage);
        loginPage.openURL();
    }

    @Test(description = "Verify that user can login successfully with valid credentials")
    public void testLoginSuccess() {
        String username = LoginTestData.getValidUsername();
        String password = LoginTestData.getValidPassword();
        String expectedUrl = LoginTestData.getSuccessUrlPart();

        loginAction.login(username, password);
        Assert.assertTrue(loginAction.isLoginSuccessURL(expectedUrl),
                "Login failed - URL does not contain " + expectedUrl);
    }
}
