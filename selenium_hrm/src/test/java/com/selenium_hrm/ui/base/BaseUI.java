package com.selenium_hrm.ui.base;

import com.selenium_hrm.config.ConfigHelper;
import com.selenium_hrm.config.testdata.PropertiesHelper;
import com.selenium_hrm.config.reports.ExtentReportTestManager;
import com.selenium_hrm.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseUI {

    private static boolean initialized = false;

    public BaseUI() {
        if (!initialized) {
            initialize();
        }
    }

    @BeforeClass
    public static void beforeClass() {
        initialize();
    }

    private static void initialize() {
        ConfigHelper.init();
        PropertiesHelper.loadAllFiles();
        initialized = true;
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browserName, Method method) {
        WebDriver driver = setupBrowser(browserName);
        DriverManager.setDriver(driver);
        ExtentReportTestManager.saveToReport(method.getName(), "Running test: " + method.getName());
    }

    public WebDriver setupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome" -> driver = initChromeDriver();
            case "firefox" -> driver = initFirefoxDriver();
            case "edge" -> driver = initEdgeDriver();
            default -> {
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
            }
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.setAcceptInsecureCerts(true);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        ChromeDriver chrome = new ChromeDriver(options);

        Map<String, Object> params = new HashMap<>();
        params.put("width", 1920);
        params.put("height", 1080);
        params.put("deviceScaleFactor", 1);
        params.put("mobile", false);
        chrome.executeCdpCommand("Emulation.setDeviceMetricsOverride", params);

        WebDriver driver = chrome;
        driver.manage().window().maximize();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        return driver;
    }

    private WebDriver initEdgeDriver() {
        System.out.println("Launching Edge browser...");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        return driver;
    }

    @AfterMethod
    public void closeDriver(ITestResult result) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
