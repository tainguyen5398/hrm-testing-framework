package com.selenium_hrm.utils;

public class URLNotFoundException extends RuntimeException {
    private final String urlText;
    private final int timeout;

    public URLNotFoundException(String message, String urlText, int timeout) {
        super(message);
        this.urlText = urlText;
        this.timeout = timeout;
    }

    public String getUrlText() {
        return urlText;
    }

    public int getTimeout() {
        return timeout;
    }
}
