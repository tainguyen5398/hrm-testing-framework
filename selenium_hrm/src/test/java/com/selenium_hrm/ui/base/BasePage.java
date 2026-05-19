package com.selenium_hrm.ui.base;

import com.aventstack.extentreports.Status;
import com.selenium_hrm.config.reports.AllureReportManager;
import com.selenium_hrm.config.reports.ExtentReportTestManager;
import com.selenium_hrm.config.testdata.PropertiesHelper;
import com.selenium_hrm.utils.ActionHelper;
import com.selenium_hrm.utils.ElementHelper;
import com.selenium_hrm.utils.VerificationHelper;
import com.selenium_hrm.utils.WaitHelper;
import org.openqa.selenium.By;

public abstract class BasePage {

    protected String getProperty(String key) {
        return PropertiesHelper.getValue(key);
    }

    // ========== NAVIGATION ==========

    protected void openURL(String url) {
        ActionHelper.openURL(url);
    }

    // ========== ELEMENT ACTIONS ==========

    protected void setText(By by, String value) {
        ActionHelper.setText(by, value);
    }

    protected void clickElement(By by) {
        ActionHelper.click(by);
    }

    protected void clickElementByJS(By by) {
        ActionHelper.clickByJS(by);
    }

    protected void submit(By by) {
        ActionHelper.submit(by);
    }

    // ========== ELEMENT GETTERS ==========

    protected String getText(By by) {
        return ElementHelper.getText(by);
    }

    protected String getText(By by, int timeout) {
        return ElementHelper.getText(by, timeout);
    }

    protected String getAttribute(By by, String attribute) {
        return ElementHelper.getAttribute(by, attribute);
    }

    // ========== WAITS ==========

    protected void waitForElementVisible(By by) {
        WaitHelper.waitForElementVisible(by);
    }

    protected void waitForElementVisible(By by, int timeout) {
        WaitHelper.waitForElementVisible(by, timeout);
    }

    protected void waitForElementPresent(By by) {
        WaitHelper.waitForElementPresent(by);
    }

    protected void waitForElementClickable(By by) {
        WaitHelper.waitForElementClickable(by);
    }

    protected void waitForPageLoaded() {
        WaitHelper.waitForPageLoaded();
    }

    protected void waitForURL(String urlText) {
        WaitHelper.waitForURLContains(urlText);
    }

    protected boolean waitForURLContainsResult(String urlText, int timeout) {
        return WaitHelper.waitForURLContainsResult(urlText, timeout);
    }

    protected void sleep(double seconds) {
        WaitHelper.sleep(seconds);
    }

    // ========== SCROLL ==========

    protected void scrollToElement(By by) {
        ActionHelper.scrollToElement(by);
    }

    protected void scrollToTop() {
        ActionHelper.scrollToTop();
    }

    protected void scrollToBottom() {
        ActionHelper.scrollToBottom();
    }

    // ========== HOVER ==========

    protected void hoverElement(By by) {
        ActionHelper.hoverElement(by);
    }

    // ========== DRAG & DROP ==========

    protected void dragAndDrop(By from, By to) {
        ActionHelper.dragAndDrop(from, to);
    }

    // ========== VERIFICATION CHECKS (return boolean, assert in test) ==========

    protected String getElementText(By by) {
        return VerificationHelper.getElementText(by);
    }

    protected boolean isElementTextMatch(By by, String expectedText) {
        return VerificationHelper.isElementTextMatch(by, expectedText);
    }

    protected boolean isElementTextContains(By by, String expectedSubtext) {
        return VerificationHelper.isElementTextContains(by, expectedSubtext);
    }

    protected String getCurrentURL() {
        return VerificationHelper.getCurrentURL();
    }

    protected boolean isURLContains(String expectedURL) {
        return VerificationHelper.isURLContains(expectedURL);
    }

    protected boolean isURLEquals(String expectedURL) {
        return VerificationHelper.isURLEquals(expectedURL);
    }

    protected boolean isElementDisplayed(By by) {
        return VerificationHelper.isElementDisplayed(by);
    }

    protected boolean isElementNotDisplayed(By by) {
        return VerificationHelper.isElementNotDisplayed(by);
    }

    protected boolean isElementEnabled(By by) {
        return VerificationHelper.isElementEnabled(by);
    }

    protected boolean isElementDisabled(By by) {
        return VerificationHelper.isElementDisabled(by);
    }

    protected boolean isElementSelected(By by) {
        return VerificationHelper.isElementSelected(by);
    }

    protected boolean isElementNotSelected(By by) {
        return VerificationHelper.isElementNotSelected(by);
    }

    protected int getElementCount(By by) {
        return VerificationHelper.getElementCount(by);
    }

    protected boolean isElementCountMatch(By by, int expectedCount) {
        return VerificationHelper.isElementCountMatch(by, expectedCount);
    }

    protected boolean isElementCountGreaterThan(By by, int minCount) {
        return VerificationHelper.isElementCountGreaterThan(by, minCount);
    }

    // ========== ELEMENT CHECKS ==========

    protected boolean isElementVisible(By by) {
        return ElementHelper.isElementExist(by);
    }

    protected boolean isElementEnabled(By by, boolean fallback) {
        return ElementHelper.isEnabled(by);
    }

    protected int getElementCountRaw(By by) {
        return ElementHelper.getElementCount(by);
    }

    // ========== DROPDOWN ==========

    protected void selectDropdownByValue(By by, String value) {
        ElementHelper.selectDropdownByValue(by, value);
    }

    protected void selectDropdownByVisibleText(By by, String text) {
        ElementHelper.selectDropdownByVisibleText(by, text);
    }

    // ========== LOGGING ==========

    protected void logPass(String message) {
        ExtentReportTestManager.logMessage(Status.PASS, message);
        AllureReportManager.saveTextLog(message);
    }

    protected void logInfo(String message) {
        ExtentReportTestManager.logMessage(Status.INFO, message);
    }

    protected void logFail(String message) {
        ExtentReportTestManager.logMessage(Status.FAIL, message);
    }
}
