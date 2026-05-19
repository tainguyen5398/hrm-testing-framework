package com.selenium_hrm.utils;

import com.aventstack.extentreports.Status;
import com.selenium_hrm.config.reports.AllureReportManager;
import com.selenium_hrm.config.reports.ExtentReportTestManager;
import com.selenium_hrm.config.logs.Log;
import com.selenium_hrm.factory.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class VerificationHelper {

    private VerificationHelper() {}

    @Step("Get text from {0}")
    public static String getElementText(By by) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(by);
        WaitHelper.sleep(0.5);
        return ElementHelper.getText(by);
    }

    @Step("Check if element text equals '{1}'")
    public static boolean isElementTextMatch(By by, String expectedText) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(by);
        WaitHelper.sleep(0.5);

        String actualText = ElementHelper.getText(by);
        boolean matches = actualText.equals(expectedText.trim());

        Log.info("ACTUAL: " + actualText);
        Log.info("EXPECTED: " + expectedText);
        ExtentReportTestManager.logMessage(Status.INFO, "ACTUAL: " + actualText);
        ExtentReportTestManager.logMessage(Status.INFO, "EXPECTED: " + expectedText);
        AllureReportManager.saveTextLog("ACTUAL: " + actualText + " | EXPECTED: " + expectedText);

        ExtentReportTestManager.logMessage(matches ? Status.PASS : Status.FAIL,
                matches ? "Text matches expected" : "Text does not match");

        return matches;
    }

    @Step("Check if element text contains '{1}'")
    public static boolean isElementTextContains(By by, String expectedSubtext) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(by);

        String actualText = ElementHelper.getText(by);
        boolean contains = actualText.contains(expectedSubtext);

        Log.info("ACTUAL: " + actualText);
        Log.info("EXPECTED CONTAINS: " + expectedSubtext);

        return contains;
    }

    @Step("Get attribute '{1}' from {0}")
    public static String getElementAttribute(By by, String attribute) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(by);
        return ElementHelper.getAttribute(by, attribute);
    }

    @Step("Check if element attribute '{1}' equals '{2}'")
    public static boolean isElementAttributeMatch(By by, String attribute, String expectedValue) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(by);

        String actualValue = ElementHelper.getAttribute(by, attribute);
        return actualValue.trim().equals(expectedValue.trim());
    }

    @Step("Get current URL")
    public static String getCurrentURL() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    @Step("Check if URL contains '{0}'")
    public static boolean isURLContains(String expectedURL) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.sleep(0.5);

        String currentURL = DriverManager.getDriver().getCurrentUrl();
        boolean contains = currentURL.contains(expectedURL);

        Log.info("Current URL: " + currentURL);
        Log.info("Expected contains: " + expectedURL);
        ExtentReportTestManager.logMessage(Status.INFO, "Current URL: " + currentURL);
        ExtentReportTestManager.logMessage(Status.INFO, "Expected contains: " + expectedURL);
        AllureReportManager.saveTextLog("Current URL: " + currentURL);

        ExtentReportTestManager.logMessage(contains ? Status.PASS : Status.FAIL,
                contains ? "URL contains expected value" : "URL does not contain expected value");

        return contains;
    }

    @Step("Check if URL equals '{0}'")
    public static boolean isURLEquals(String expectedURL) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.sleep(0.5);

        String currentURL = DriverManager.getDriver().getCurrentUrl();
        return currentURL.equals(expectedURL);
    }

    @Step("Check if element {0} is displayed")
    public static boolean isElementDisplayed(By by) {
        WaitHelper.waitForPageLoaded();
        try {
            WaitHelper.waitForElementVisible(by, 3);
            return ElementHelper.isDisplayed(by);
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check if element {0} is NOT displayed")
    public static boolean isElementNotDisplayed(By by) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.sleep(1);
        return !ElementHelper.isDisplayed(by);
    }

    @Step("Check if element {0} is enabled")
    public static boolean isElementEnabled(By by) {
        return ElementHelper.isEnabled(by);
    }

    @Step("Check if element {0} is disabled")
    public static boolean isElementDisabled(By by) {
        return !ElementHelper.isEnabled(by);
    }

    @Step("Check if element {0} is selected")
    public static boolean isElementSelected(By by) {
        return ElementHelper.isSelected(by);
    }

    @Step("Check if element {0} is NOT selected")
    public static boolean isElementNotSelected(By by) {
        return !ElementHelper.isSelected(by);
    }

    @Step("Check if checkbox {0} is NOT checked")
    public static boolean isCheckboxNotChecked(By by) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.waitForElementVisible(by);
        return !ElementHelper.isSelected(by);
    }

    @Step("Get element count of {0}")
    public static int getElementCount(By by) {
        return ElementHelper.getElementCount(by);
    }

    @Step("Check if element count of {0} equals {1}")
    public static boolean isElementCountMatch(By by, int expectedCount) {
        int actualCount = ElementHelper.getElementCount(by);
        return actualCount == expectedCount;
    }

    @Step("Check if element count of {0} is greater than {1}")
    public static boolean isElementCountGreaterThan(By by, int minCount) {
        int actualCount = ElementHelper.getElementCount(by);
        return actualCount > minCount;
    }

    public static boolean areValuesEqual(int actual, int expected) {
        return actual == expected;
    }

    public static boolean areValuesEqual(String actual, String expected) {
        return actual != null && actual.equals(expected);
    }

    public static boolean isTrue(boolean condition) {
        return condition;
    }

    public static boolean isFalse(boolean condition) {
        return !condition;
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean isNull(Object object) {
        return object == null;
    }
}
