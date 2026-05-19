package com.selenium_hrm.config.media;

import com.selenium_hrm.config.ConfigHelper;
import com.selenium_hrm.factory.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureHelper {

    static String projectPath = System.getProperty("user.dir") + "/";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void captureScreenshot(ITestResult result) {
        try {
            Reporter.log("Driver for Screenshot: " + DriverManager.getDriver());
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);

            String screenshotPath = projectPath + ConfigHelper.getScreenshotPath();
            File theDir = new File(screenshotPath);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            FileHandler.copy(source, new File(screenshotPath + "/" + result.getName() + "_" + dateFormat.format(new Date()) + ".png"));
            System.out.println("Screenshot taken: " + result.getName());
            Reporter.log("Screenshot taken current URL: " + DriverManager.getDriver().getCurrentUrl(), true);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}
