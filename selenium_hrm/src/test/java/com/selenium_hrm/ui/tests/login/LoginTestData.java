package com.selenium_hrm.ui.tests.login;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class LoginTestData {

    private static final Gson gson = new Gson();

    @SerializedName("valid_login")
    private static final ValidLoginData validLogin = new ValidLoginData();

    @SerializedName("invalid_login")
    private static final InvalidLoginData invalidLogin = new InvalidLoginData();

    @SerializedName("expected_messages")
    private static final ExpectedMessages expectedMessages = new ExpectedMessages();

    public static String getValidUsername() {
        return validLogin.username;
    }

    public static String getValidPassword() {
        return validLogin.password;
    }

    public static String getInvalidUsername() {
        return invalidLogin.username;
    }

    public static String getInvalidPassword() {
        return invalidLogin.password;
    }

    public static String getSuccessUrlPart() {
        return expectedMessages.successUrlPart;
    }

    public static String getFailedErrorMessage() {
        return expectedMessages.failedErrorMessage;
    }

    public static LoginTestData getData() {
        return gson.fromJson(getJsonString(), LoginTestData.class);
    }

    public static String getJsonString() {
        return """
                {
                    "valid_login": {
                        "username": "admin_example",
                        "password": "123456"
                    },
                    "invalid_login": {
                        "username": "Admin",
                        "password": "wrongpassword"
                    },
                    "expected_messages": {
                        "success_url_part": "/erp/desk",
                        "failed_error_message": "Invalid"
                    }
                }
                """;
    }

    private static class ValidLoginData {
        String username = "admin_example";
        String password = "123456";
    }

    private static class InvalidLoginData {
        String username = "Admin";
        String password = "wrongpassword";
    }

    private static class ExpectedMessages {
        String successUrlPart = "/erp/desk";
        String failedErrorMessage = "Invalid";
    }
}
