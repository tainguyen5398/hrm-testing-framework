package com.selenium_hrm.utils;

import com.selenium_hrm.factory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebActions {

    private WebActions() {}

    // ========== BACKWARD COMPATIBILITY - DELEGATE TO HELPERS ==========

    public static void sleep(double seconds) {
        WaitHelper.sleep(seconds);
    }

    public static void LogConsole(Object message) {
        System.out.println(message);
    }

    // --- Element Finders (delegate to ElementHelper) ---

    public static WebElement getWebElement(By by) {
        return ElementHelper.getElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return ElementHelper.getElements(by);
    }

    public static String getText(By by) {
        return ElementHelper.getText(by);
    }

    public static String getText(By by, int timeout) {
        return ElementHelper.getText(by, timeout);
    }

    public static Boolean checkElementExist(By by) {
        return ElementHelper.isElementExist(by);
    }

    public static int getSize(By by, int number) {
        return ElementHelper.getElementCount(by) - number;
    }

    // --- Navigation (delegate to ActionHelper) ---

    public static void openURL(String URL) {
        ActionHelper.openURL(URL);
    }

    // --- Waits (delegate to WaitHelper) ---

    public static void waitForElementVisible(By by) {
        WaitHelper.waitForElementVisible(by);
    }

    public static void waitForElementVisible(By by, int timeout) {
        WaitHelper.waitForElementVisible(by, timeout);
    }

    public static void waitForElementPresent(By by) {
        WaitHelper.waitForElementPresent(by);
    }

    public static void waitForElementPresent(By by, int timeout) {
        WaitHelper.waitForElementPresent(by, timeout);
    }

    public static void waitForElementClickable(By by) {
        WaitHelper.waitForElementClickable(by);
    }

    public static void waitForElementClickable(By by, int timeout) {
        WaitHelper.waitForElementClickable(by, timeout);
    }

    public static void waitForPageLoaded() {
        WaitHelper.waitForPageLoaded();
    }

    public static void waitForJQueryLoad() {
        WaitHelper.waitForJQueryLoad();
    }

    public static void waitForAngularLoad() {
        WaitHelper.waitForAngularLoad();
    }

    // --- Actions (delegate to ActionHelper) ---

    public static void clickElement(By by) {
        ActionHelper.click(by);
    }

    public static void clickElementWithJS(By by) {
        ActionHelper.clickByJS(by);
    }

    public static void setText(By by, String value) {
        ActionHelper.setText(by, value);
    }

    public static void scrollToElement(By element) {
        ActionHelper.scrollToElement(element);
    }

    public static void scrollToElement(WebElement element) {
        ActionHelper.scrollToElement(element);
    }

    public static void scrollToPosition(int X, int Y) {
        ActionHelper.scrollToPosition(X, Y);
    }

    public static boolean moveToElement(By toElement) {
        ActionHelper.moveToElement(toElement);
        return true;
    }

    public static boolean moveToOffset(int X, int Y) {
        ActionHelper.moveToOffset(X, Y);
        return true;
    }

    public static boolean hoverElement(By by) {
        ActionHelper.hoverElement(by);
        return true;
    }

    public static boolean mouseHover(By by) {
        ActionHelper.hoverElement(by);
        return true;
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        ActionHelper.dragAndDrop(fromElement, toElement);
        return true;
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        ActionHelper.dragAndDrop(fromElement, toElement);
        return true;
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        ActionHelper.dragAndDropByOffset(fromElement, X, Y);
        return true;
    }

    public static WebElement highLightElement(By by) {
        ActionHelper.highlightElement(by);
        return ElementHelper.getElement(by);
    }

    // --- Verification Checks (return boolean, no Assert) ---

    public static String getElementText(By by) {
        return VerificationHelper.getElementText(by);
    }

    public static boolean isElementTextMatch(By by, String textValue) {
        return VerificationHelper.isElementTextMatch(by, textValue);
    }

    public static boolean isElementTextContains(By by, String expectedSubtext) {
        return VerificationHelper.isElementTextContains(by, expectedSubtext);
    }

    public static String getElementAttribute(By by, String attribute) {
        return VerificationHelper.getElementAttribute(by, attribute);
    }

    public static boolean isElementAttributeMatch(By by, String attribute, String expectedValue) {
        return VerificationHelper.isElementAttributeMatch(by, attribute, expectedValue);
    }

    public static boolean isURLContains(String myURL) {
        return VerificationHelper.isURLContains(myURL);
    }

    public static boolean isURLContains(String myURL, boolean waitForIt) {
        if (waitForIt) {
            WaitHelper.waitForURLContains(myURL);
        }
        return VerificationHelper.isURLContains(myURL);
    }

    public static String getCurrentURL() {
        return VerificationHelper.getCurrentURL();
    }

    public static boolean isElementDisplayed(By by) {
        return VerificationHelper.isElementDisplayed(by);
    }

    public static boolean isElementNotDisplayed(By by) {
        return VerificationHelper.isElementNotDisplayed(by);
    }

    public static boolean isElementEnabled(By by) {
        return VerificationHelper.isElementEnabled(by);
    }

    public static boolean isCheckboxNotChecked(By by) {
        return VerificationHelper.isCheckboxNotChecked(by);
    }

    public static int getElementCount(By by) {
        return VerificationHelper.getElementCount(by);
    }

    // --- Keyboard (delegate to KeyboardHelper) ---

    public static boolean pressENTER() {
        KeyboardHelper.pressEnter();
        return true;
    }

    public static boolean pressESC() {
        KeyboardHelper.pressEscape();
        return true;
    }

    public static boolean pressF11() {
        KeyboardHelper.pressF11();
        return true;
    }

    public static boolean pressCtrlC() {
        KeyboardHelper.pressCtrlC();
        return true;
    }

    // --- Upload (delegate to UploadHelper) ---

    public static void uploadFileRobotClass(String fileLocation) {
        UploadHelper.uploadFileWithRobot(fileLocation);
    }

    public static void uploadFileWithSendKeys(By by, String value) {
        UploadHelper.uploadFileWithSendKeys(by, value);
    }

    // --- Dropdown (delegate to ElementHelper) ---

    public static void selectValueDropdown(By by, String value) {
        ElementHelper.selectDropdownByValue(by, value);
    }

    public static void selectByVisibleTextDropdown(By by, String text) {
        ElementHelper.selectDropdownByVisibleText(by, text);
    }
}
