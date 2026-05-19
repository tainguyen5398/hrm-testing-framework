package com.selenium_hrm.utils;

import org.openqa.selenium.By;

public class ElementStillVisibleException extends RuntimeException {
    private final By locator;
    private final int timeout;

    public ElementStillVisibleException(String message, By locator, int timeout) {
        super(message);
        this.locator = locator;
        this.timeout = timeout;
    }

    public By getLocator() {
        return locator;
    }

    public int getTimeout() {
        return timeout;
    }
}
