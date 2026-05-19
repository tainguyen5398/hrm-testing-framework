package com.selenium_hrm.utils;

import com.selenium_hrm.factory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ElementHelper {

    private ElementHelper() {}

    public static WebElement getElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static String getText(By by) {
        return getElement(by).getText().trim();
    }

    public static String getText(By by, int timeout) {
        WaitHelper.waitForElementVisible(by, timeout);
        return getElement(by).getText().trim();
    }

    public static String getAttribute(By by, String attribute) {
        return getElement(by).getAttribute(attribute);
    }

    public static String getCssValue(By by, String property) {
        return getElement(by).getCssValue(property);
    }

    public static boolean isDisplayed(By by) {
        try {
            return getElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEnabled(By by) {
        try {
            return getElement(by).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isSelected(By by) {
        try {
            return getElement(by).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public static int getElementCount(By by) {
        return getElements(by).size();
    }

    public static boolean isElementExist(By by) {
        WaitHelper.waitForPageLoaded();
        WaitHelper.sleep(1);
        return getElementCount(by) > 0;
    }

    public static void selectDropdownByValue(By by, String value) {
        new Select(getElement(by)).selectByValue(value);
    }

    public static void selectDropdownByVisibleText(By by, String text) {
        new Select(getElement(by)).selectByVisibleText(text);
    }

    public static void selectDropdownByIndex(By by, int index) {
        new Select(getElement(by)).selectByIndex(index);
    }

    public static String getFirstSelectedOption(By by) {
        return new Select(getElement(by)).getFirstSelectedOption().getText().trim();
    }

    public static List<WebElement> getAllSelectedOptions(By by) {
        return new Select(getElement(by)).getAllSelectedOptions();
    }
}
