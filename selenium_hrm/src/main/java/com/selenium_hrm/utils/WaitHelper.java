package com.selenium_hrm.utils;

import com.selenium_hrm.config.ConfigHelper;
import com.selenium_hrm.factory.DriverManager;
import com.selenium_hrm.config.logs.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private WaitHelper() {}

    public static int getDefaultTimeout() {
        return ConfigHelper.getDefaultTimeout();
    }

    public static int getPageLoadTimeout() {
        return ConfigHelper.getPageLoadTimeout();
    }

    public static long getPollingInterval() {
        return ConfigHelper.getPollingInterval();
    }

    public static WebDriverWait getWait(int timeout) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(getPollingInterval()));
    }

    public static WebDriverWait getWait() {
        return getWait(getDefaultTimeout());
    }

    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (1000 * seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForElementVisible(By by) {
        waitForElementVisible(by, getDefaultTimeout());
    }

    public static void waitForElementVisible(By by, int timeout) {
        try {
            getWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception error) {
            Log.error("TIMEOUT: Cannot find visible element: " + by);
            throw new ElementNotFoundException("Cannot find visible element: " + by, by, timeout);
        }
    }

    public static void waitForElementPresent(By by) {
        waitForElementPresent(by, getDefaultTimeout());
    }

    public static void waitForElementPresent(By by, int timeout) {
        try {
            getWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception error) {
            Log.error("Element does not exist: " + by);
            throw new ElementNotFoundException("Element does not exist: " + by, by, timeout);
        }
    }

    public static void waitForElementClickable(By by) {
        waitForElementClickable(by, getDefaultTimeout());
    }

    public static void waitForElementClickable(By by, int timeout) {
        try {
            getWait(timeout).until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception error) {
            Log.error("Element not clickable: " + by);
            throw new ElementNotClickableException("Element not clickable: " + by, by, timeout);
        }
    }

    public static void waitForElementInvisible(By by) {
        waitForElementInvisible(by, getDefaultTimeout());
    }

    public static void waitForElementInvisible(By by, int timeout) {
        try {
            getWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception error) {
            Log.error("Element still visible: " + by);
            throw new ElementStillVisibleException("Element still visible: " + by, by, timeout);
        }
    }

    public static void waitForURLContains(String urlText) {
        waitForURLContains(urlText, getDefaultTimeout());
    }

    public static void waitForURLContains(String urlText, int timeout) {
        try {
            getWait(timeout).until(ExpectedConditions.urlContains(urlText));
        } catch (Exception error) {
            Log.error("URL does not contain: " + urlText);
            throw new URLNotFoundException("URL does not contain: " + urlText, urlText, timeout);
        }
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = getWait(getPageLoadTimeout());
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        ExpectedCondition<Boolean> jsLoad = driver ->
                ((JavascriptExecutor) DriverManager.getDriver())
                        .executeScript("return document.readyState")
                        .toString().equals("complete");

        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        if (!jsReady) {
            Log.info("Waiting for JavaScript to load...");
            try {
                wait.until(jsLoad);
            } catch (Exception error) {
                error.printStackTrace();
                Log.error("Timeout waiting for page load (JavaScript). (" + getPageLoadTimeout() + "s)");
                throw new PageLoadException("Timeout waiting for page load (JavaScript). (" + getPageLoadTimeout() + "s)");
            }
        }
    }

    public static void waitForJQueryLoad() {
        WebDriverWait wait = getWait(getPageLoadTimeout());
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        ExpectedCondition<Boolean> jQueryLoad = driver ->
                ((Long) js.executeScript("return jQuery.active")) == 0;

        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        if (!jqueryReady) {
            Log.info("Waiting for JQuery to load...");
            try {
                wait.until(jQueryLoad);
            } catch (Exception error) {
                throw new PageLoadException("Timeout waiting for JQuery load. (" + getPageLoadTimeout() + "s)");
            }
        }
    }

    public static void waitForAngularLoad() {
        WebDriverWait wait = getWait(getPageLoadTimeout());
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        String angularScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        ExpectedCondition<Boolean> angularLoad = driver ->
                Boolean.valueOf(js.executeScript(angularScript).toString());

        boolean angularReady = Boolean.parseBoolean(js.executeScript(angularScript).toString());

        if (!angularReady) {
            Log.info("Waiting for Angular to load...");
            try {
                wait.until(angularLoad);
            } catch (Exception error) {
                throw new PageLoadException("Timeout waiting for Angular load. (" + getPageLoadTimeout() + "s)");
            }
        }
    }

    // ========== BOOLEAN WAIT METHODS ==========

    public static boolean waitForElementVisibleResult(By by, int timeout) {
        try {
            getWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public static boolean waitForElementPresentResult(By by, int timeout) {
        try {
            getWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public static boolean waitForElementClickableResult(By by, int timeout) {
        try {
            getWait(timeout).until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public static boolean waitForURLContainsResult(String urlText, int timeout) {
        try {
            getWait(timeout).until(ExpectedConditions.urlContains(urlText));
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}
