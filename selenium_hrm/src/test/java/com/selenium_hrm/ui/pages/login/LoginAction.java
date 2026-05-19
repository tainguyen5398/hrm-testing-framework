package com.selenium_hrm.ui.pages.login;

public class LoginAction {
    private final LoginPage loginPage;

    public LoginAction(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public LoginAction login(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        return this;
    }

    public boolean isLoginSuccessURL(String expectedURL) {
        return loginPage.isLoginSuccessURL(expectedURL);
    }
}
