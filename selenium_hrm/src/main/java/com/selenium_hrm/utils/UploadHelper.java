package com.selenium_hrm.utils;

import com.aventstack.extentreports.Status;
import com.selenium_hrm.config.reports.AllureReportManager;
import com.selenium_hrm.config.reports.ExtentReportTestManager;
import com.selenium_hrm.config.logs.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class UploadHelper {

    private UploadHelper() {}

    @Step("Upload file: {0}")
    public static void uploadFileWithRobot(String filePath) {
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        KeyboardHelper.getRobot().setAutoDelay(500);
        KeyboardHelper.pressCtrlV();
        KeyboardHelper.getRobot().setAutoDelay(500);
        KeyboardHelper.pressEnter();

        Log.info("Upload file: " + filePath);
        ExtentReportTestManager.logMessage(Status.PASS, "Upload file: " + filePath);
        AllureReportManager.saveTextLog("Upload file: " + filePath);
    }

    @Step("Upload file to {0}: {1}")
    public static void uploadFileWithSendKeys(By inputLocator, String filePath) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(inputLocator);
        WaitHelper.sleep(0.5);

        ElementHelper.getElement(inputLocator).sendKeys(filePath);

        WaitHelper.sleep(0.5);
        Log.info("Upload file to " + inputLocator + ": " + filePath);
        ExtentReportTestManager.logMessage(Status.PASS, "Upload file: " + filePath);
        AllureReportManager.saveTextLog("Upload file: " + filePath);
    }

    @Step("Upload multiple files to {0}: {1}")
    public static void uploadMultipleFiles(By inputLocator, String... filePaths) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(inputLocator);
        WaitHelper.sleep(0.5);

        StringBuilder allPaths = new StringBuilder();
        for (int i = 0; i < filePaths.length; i++) {
            allPaths.append(filePaths[i]);
            if (i < filePaths.length - 1) {
                allPaths.append("\n");
            }
        }

        ElementHelper.getElement(inputLocator).sendKeys(allPaths.toString());

        WaitHelper.sleep(0.5);
        Log.info("Upload multiple files to " + inputLocator + ": " + allPaths);
        ExtentReportTestManager.logMessage(Status.PASS, "Upload multiple files: " + allPaths);
    }

    public static String getFilePath(String fileName) {
        String basePath = System.getProperty("user.dir");
        return basePath + "/src/test/resources/testdata/files/" + fileName;
    }

    public static String getFilePath(String folder, String fileName) {
        String basePath = System.getProperty("user.dir");
        return basePath + "/src/test/resources/testdata/files/" + folder + "/" + fileName;
    }
}
