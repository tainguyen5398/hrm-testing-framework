package com.selenium_hrm.utils;

import com.aventstack.extentreports.Status;
import com.selenium_hrm.config.reports.AllureReportManager;
import com.selenium_hrm.config.reports.ExtentReportTestManager;
import com.selenium_hrm.config.logs.Log;
import com.selenium_hrm.factory.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class ActionHelper {

    private ActionHelper() {}

    @Step("Open URL: {0}")
    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        WaitHelper.sleep(0.5);
        Log.info("Open: " + url);
        ExtentReportTestManager.logMessage(Status.PASS, "Open: " + url);
        AllureReportManager.saveTextLog("Open: " + url);
        WaitHelper.waitForPageLoaded();
    }

    @Step("Click on {0}")
    public static void click(By by) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(by);
        WaitHelper.waitForElementClickable(by);
        ElementHelper.getElement(by).click();
        Log.info("Click: " + by);
        ExtentReportTestManager.logMessage(Status.PASS, "Click: " + by);
        AllureReportManager.saveTextLog("Click: " + by);
    }

    @Step("Click on {0} by JavaScript")
    public static void clickByJS(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementPresent(by);
        WebElement element = ElementHelper.getElement(by);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
        Log.info("Click by JS: " + by);
        ExtentReportTestManager.logMessage(Status.PASS, "Click by JS: " + by);
        AllureReportManager.saveTextLog("Click by JS: " + by);
    }

    @Step("Set text '{1}' on {0}")
    public static void setText(By by, String value) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(by);
        WebElement element = ElementHelper.getElement(by);
        element.clear();
        element.sendKeys(value);
        Log.info("Set Text: " + value + " on " + by);
        ExtentReportTestManager.logMessage(Status.PASS, "Set Text: " + value + " on " + by);
        AllureReportManager.saveTextLog("Set Text: " + value + " on " + by);
    }

    @Step("Submit form on {0}")
    public static void submit(By by) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(by);
        ElementHelper.getElement(by).submit();
        Log.info("Submit: " + by);
        ExtentReportTestManager.logMessage(Status.PASS, "Submit: " + by);
    }

    public static void scrollToElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", ElementHelper.getElement(by));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPosition(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + x + "," + y + ");");
    }

    public static void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(0, 0);");
    }

    public static void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void hoverElement(By by) {
        Actions action = new Actions(DriverManager.getDriver());
        action.moveToElement(ElementHelper.getElement(by)).perform();
        Log.info("Hover on: " + by);
    }

    public static void moveToElement(By by) {
        Actions action = new Actions(DriverManager.getDriver());
        action.moveToElement(ElementHelper.getElement(by))
               .release()
               .build()
               .perform();
    }

    public static void moveToOffset(int x, int y) {
        Actions action = new Actions(DriverManager.getDriver());
        action.moveByOffset(x, y).build().perform();
    }

    public static void dragAndDrop(By from, By to) {
        Actions action = new Actions(DriverManager.getDriver());
        action.dragAndDrop(ElementHelper.getElement(from), ElementHelper.getElement(to)).perform();
        Log.info("Drag and drop: " + from + " -> " + to);
    }

    public static void dragAndDropByOffset(By from, int xOffset, int yOffset) {
        Actions action = new Actions(DriverManager.getDriver());
        action.clickAndHold(ElementHelper.getElement(from))
              .pause(1)
              .moveByOffset(xOffset, yOffset)
              .release()
              .build()
              .perform();
        Log.info("Drag and drop by offset: " + from + " by (" + xOffset + ", " + yOffset + ")");
    }

    public static void doubleClick(By by) {
        Actions action = new Actions(DriverManager.getDriver());
        action.doubleClick(ElementHelper.getElement(by)).perform();
        Log.info("Double click: " + by);
    }

    public static void rightClick(By by) {
        Actions action = new Actions(DriverManager.getDriver());
        action.contextClick(ElementHelper.getElement(by)).perform();
        Log.info("Right click: " + by);
    }

    public static void highlightElement(By by) {
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("arguments[0].style.border='3px solid red'", ElementHelper.getElement(by));
            WaitHelper.sleep(0.5);
        }
    }

    public static void executeJS(String script) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(script);
    }

    public static void executeJS(String script, Object... args) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(script, args);
    }
}
