package com.selenium_hrm.config.reports;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.selenium_hrm.factory.DriverManager;
import io.qameta.allure.Attachment;

public class AllureReportManager {
    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    //Text attachments for Allure
    @Attachment(value = "BugImage", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
