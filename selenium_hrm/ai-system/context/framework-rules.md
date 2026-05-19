# Framework Rules - Selenium HRM Automation

## 1. Overview

### 1.1 Purpose
Framework rules định nghĩa các nguyên tắc, quy tắc và hướng dẫn bắt buộc tuân thủ khi làm việc với Selenium HRM automation framework. Những rules này đảm bảo:
- Consistency trong codebase
- Maintainability cao
- Scalability cho future growth
- Reliability trong test execution

### 1.2 Scope
| Area | Coverage |
|------|----------|
| **Code Structure** | Package organization, class naming, method conventions |
| **Page Object Model** | POM implementation rules, element management |
| **Test Classes** | Test method structure, data management |
| **Helper Utilities** | Usage guidelines for existing utilities |
| **Reporting** | Logging, screenshot, attachment rules |
| **Execution** | Test configuration, parallel execution |

### 1.3 Enforcement
- ✅ **MUST**: Bắt buộc phải tuân thủ
- ⚠️ **SHOULD**: Nên tuân thủ (strong recommendation)
- 💡 **CAN**: Tùy chọn, best practice

---

## 2. Project Structure Rules

### 2.1 Package Organization

```java
// BẮT BUỘC: Tuân thủ package structure
com.selenium_hrm
├── config/                 // Configuration classes
│   ├── ConfigHelper.java
│   ├── logs/               // Logging utilities
│   ├── media/              // Media capture (screenshots, videos)
│   ├── reports/            // Report managers
│   └── testdata/           // Data handling utilities
├── factory/                // Driver factory
├── listeners/              // TestNG listeners
├── utils/                  // Helper utilities
└── [module]/               // Optional: feature-specific packages

// TEST PACKAGE
com.selenium_hrm.ui
├── base/                   // Base classes
├── pages/                  // Page Object classes
│   └── [module]/           // Grouped by module
├── components/             // Reusable UI components
└── tests/                  // Test classes
    └── [module]/           // Grouped by module
```

### 2.2 Directory Structure

```
src/
├── main/
│   └── java/
│       └── com/selenium_hrm/
│           ├── config/
│           │   ├── ConfigHelper.java      ✅
│           │   ├── logs/Log.java          ✅
│           │   ├── media/CaptureHelper.java ✅
│           │   ├── reports/
│           │   │   ├── ExtentReportManager.java     ✅
│           │   │   ├── ExtentReportTestManager.java ✅
│           │   │   └── AllureReportManager.java     ✅
│           │   └── testdata/
│           │       ├── PropertiesHelper.java ✅
│           │       └── ExcelHelper.java      ✅
│           ├── factory/DriverManager.java    ✅
│           ├── listeners/TestListener.java   ✅
│           └── utils/
│               ├── ActionHelper.java        ✅
│               ├── ElementHelper.java       ✅
│               ├── WaitHelper.java          ✅
│               └── VerificationHelper.java   ✅
│
├── test/
│   ├── java/
│   │   └── com/selenium_hrm/ui/
│   │       ├── base/
│   │       │   ├── BaseUI.java              ✅
│   │       │   └── BasePage.java            ✅
│   │       ├── pages/
│   │       │   ├── LoginPage.java          ✅
│   │       │   ├── LoginPageElements.java   ✅
│   │       │   └── LoginActions.java        ✅
│   │       ├── components/
│   │       │   └── CommonComponents.java    ✅
│   │       └── tests/
│   │           ├── LoginTests.java         ✅
│   │           └── ModuleTests.java         ✅
│   └── resources/
│       ├── config/
│       │   ├── config.properties           ✅
│       │   ├── local.properties             ✅
│       │   ├── qa.properties               ✅
│       │   └── staging.properties           ✅
│       ├── suites/
│       │   ├── SuiteLoginTest.xml          ✅
│       │   ├── SuiteSmokeTest.xml          ✅
│       │   └── SuiteRegressionTest.xml     ✅
│       ├── testdata/
│       │   └── [Module]Data.xlsx           ✅
│       └── drivers/
│           └── chromedriver.exe             ✅
│
├── extentReports/                 // Auto-generated
├── logs/                         // Auto-generated
├── screenshots/                  // Auto-generated
└── test-output/                 // TestNG output
```

---

## 3. Naming Conventions

### 3.1 Class Naming

```java
// ✅ ĐÚNG: Clear, descriptive names
public class LoginPage extends BasePage { }
public class EmployeeManagementPage extends BasePage { }
public class LeaveRequestActions { }
public class TestListener implements ITestListener { }
public class DriverManager { }

// ❌ SAI: Abbreviations, unclear names
public class LgnPage { }
public class EMPage { }
public class TestClass1 { }
```

### 3.2 Method Naming

```java
// ✅ ĐÚNG: Verb + Object pattern
public void clickLoginButton() { }
public void enterUsername(String username) { }
public void selectRoleFromDropdown(String role) { }
public void verifyPageTitle(String expected) { }
public boolean isElementDisplayed(By locator) { }
public void uploadFile(String filePath) { }

// ❌ SAI: Unclear, inconsistent
public void login() { }
public void setUser() { }
public void submit() { }
```

### 3.3 Variable Naming

```java
// ✅ ĐÚNG: Descriptive, camelCase
private String defaultUsername;
private String validPassword;
private WebDriver driver;
private LoginPage loginPage;

// ❌ SAI: Single letters, Hungarian notation
private String sUser;
private String sPass;
private WebDriver d;
```

### 3.4 Locator Naming

```java
// ✅ ĐÚNG: Type prefix + descriptive name
private static final By TXT_USERNAME = By.id("username");
private static final By TXT_PASSWORD = By.id("password");
private static final By BTN_LOGIN = By.cssSelector(".btn-login");
private static final By LBL_ERROR_MESSAGE = By.xpath("//div[@class='error']");
private static final By CHK_REMEMBER_ME = By.name("remember");
private static final By LNK_FORGOT_PASSWORD = By.linkText("Forgot password?");

// ❌ SAI: Generic names
private By input1;
private By button;
private By error;
```

### 3.5 Test Method Naming

```java
// ✅ ĐÚNG: Test_{Feature}_{Scenario}_{ExpectedResult}
@Test
public void testLogin_WithValidCredentials_ShouldRedirectToDashboard() { }

@Test
public void testLogin_WithInvalidPassword_ShouldShowErrorMessage() { }

@Test
public void testEmployee_CreateNewEmployee_WithValidData_ShouldSuccess() { }

@Test
public void testLeaveRequest_WithInsufficientBalance_ShouldShowWarning() { }

// ❌ SAI: Unclear names
@Test
public void testLogin() { }

@Test
public void test1() { }

@Test
public void loginSuccess() { }
```

### 3.6 Test Data File Naming

```
// ✅ ĐÚNG: Module_DataType.xlsx
LoginTestData.xlsx
EmployeeTestData.xlsx
LeaveRequestData.xlsx
PayrollTestData.xlsx

// ❌ SAI: Generic names
TestData.xlsx
Data.xlsx
Input.xlsx
```

---

## 4. Page Object Model Rules

### 4.1 POM Structure

```java
// 3-LAYER POM STRUCTURE (BẮT BUỘC)

/*
 * LAYER 1: ELEMENTS (Locators)
 * File: {PageName}Elements.java
 * Chỉ chứa locators, KHÔNG có logic
 */
public class LoginPageElements {
    private static final By TXT_USERNAME = By.id("username");
    private static final By TXT_PASSWORD = By.id("password");
    private static final By BTN_LOGIN = By.cssSelector(".btn-login");
    private static final By LBL_ERROR = By.xpath("//div[@class='error']");
}

/*
 * LAYER 2: ACTIONS (Business operations)
 * File: {PageName}Actions.java
 * Chứa các action methods gọi đến Elements
 */
public class LoginActions {
    private LoginPageElements elements = new LoginPageElements();
    
    public void login(String username, String password) {
        DriverManager.getDriver().findElement(elements.TXT_USERNAME).sendKeys(username);
        DriverManager.getDriver().findElement(elements.TXT_PASSWORD).sendKeys(password);
        DriverManager.getDriver().findElement(elements.BTN_LOGIN).click();
    }
}

/*
 * LAYER 3: PAGE (Facade - kết hợp Elements + Actions)
 * File: {PageName}Page.java
 * Page class chính sử dụng cả Elements và Actions
 */
public class LoginPage extends BasePage {
    private LoginPageElements elements = new LoginPageElements();
    private LoginActions actions = new LoginActions();
    
    public void login(String username, String password) {
        actions.login(username, password);
    }
    
    public boolean isErrorDisplayed() {
        return isElementDisplayed(elements.LBL_ERROR);
    }
}
```

### 4.2 BasePage Rules

```java
// ✅ ĐÚNG: BasePage nên delegate đến helper utilities
public abstract class BasePage {
    
    // Action delegation
    protected void click(By locator) {
        ActionHelper.click(locator);
    }
    
    protected void setText(By locator, String text) {
        ActionHelper.setText(locator, text);
    }
    
    protected String getText(By locator) {
        return ElementHelper.getText(locator);
    }
    
    // Wait delegation
    protected void waitForElementVisible(By locator) {
        WaitHelper.waitForVisibility(locator);
    }
    
    protected void waitForElementClickable(By locator) {
        WaitHelper.waitForClickable(locator);
    }
    
    // Verification delegation
    protected boolean isElementDisplayed(By locator) {
        return VerificationHelper.isDisplayed(locator);
    }
    
    protected void verifyTextEquals(String actual, String expected) {
        VerificationHelper.assertEquals(actual, expected);
    }
}

// ❌ SAI: KHÔNG implement logic trong BasePage
public abstract class BasePage {
    // ❌ KHÔNG làm như thế này
    protected WebElement findElement(By locator) {
        return DriverManager.getDriver().findElement(locator);
    }
}
```

### 4.3 BaseUI Rules

```java
// ✅ ĐÚNG: BaseUI cho test classes
@Listeners(TestListener.class)
public class BaseUI {
    
    private static final String CONFIG_PATH = "src/test/resources/config/";
    
    @BeforeClass
    public void setUpClass() {
        ConfigHelper.init();
        PropertiesHelper.loadAllFiles(CONFIG_PATH);
        ExtentReportManager.initReports();
    }
    
    @BeforeMethod
    public void setUp() {
        createDriver();
        ExtentReportTestManager.createTest();
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            CaptureHelper.captureScreenshot(result.getName());
            ExtentReportTestManager.logFailure(result.getThrowable().getMessage());
        }
        DriverManager.quit();
    }
    
    @AfterClass
    public void tearDownClass() {
        ExtentReportManager.flush();
    }
    
    private void createDriver() {
        WebDriver driver = DriverManagerFactory.initDriver(
            ConfigHelper.getBrowser(),
            ConfigHelper.isHeadless()
        );
        DriverManager.setDriver(driver);
    }
}
```

---

## 5. Test Class Rules

### 5.1 Test Method Structure

```java
// ✅ ĐÚNG: AAA Pattern (Arrange-Act-Assert)
@Test
public void testLogin_WithValidCredentials_ShouldRedirectToDashboard() {
    // ARRANGE: Prepare test data and objects
    String validUsername = "admin";
    String validPassword = "admin123";
    LoginPage loginPage = new LoginPage();
    
    // ACT: Perform the action
    loginPage.login(validUsername, validPassword);
    
    // ASSERT: Verify the result
    String expectedUrl = ConfigHelper.getDashboardUrl();
    assertEquals(DriverManager.getDriver().getCurrentUrl(), expectedUrl);
    assertTrue(loginPage.isLoggedIn());
}

// ✅ ĐÚNG: Với DataProvider
@DataProvider
public Object[][] loginData() {
    return new Object[][] {
        { "admin", "admin123", true },
        { "user", "user123", true },
        { "invalid", "invalid", false }
    };
}

@Test(dataProvider = "loginData")
public void testLogin_DataDriven(String username, String password, boolean shouldSucceed) {
    LoginPage loginPage = new LoginPage();
    
    loginPage.login(username, password);
    
    if (shouldSucceed) {
        assertTrue(loginPage.isLoggedIn());
    } else {
        assertTrue(loginPage.isErrorDisplayed());
    }
}
```

### 5.2 Test Grouping

```java
// ✅ ĐÚNG: Group tests by module and priority
@Test(groups = {"login", "smoke", "critical"})
public void testLogin_WithValidCredentials_ShouldSucceed() { }

@Test(groups = {"login", "smoke", "critical"})
public void testLogin_WithInvalidPassword_ShouldShowError() { }

@Test(groups = {"login", "regression"})
public void testLogin_WithEmptyUsername_ShouldShowValidation() { }

@Test(groups = {"employee", "regression", "p1"})
public void testEmployee_CreateNew_ShouldSuccess() { }

@Test(groups = {"employee", "regression", "p2"})
public void testEmployee_DuplicateEmail_ShouldShowWarning() { }
```

### 5.3 Test Data Management

```java
// ✅ ĐÚNG: Sử dụng DataProvider cho multiple test data
@DataProvider(name = "employeeData")
public Object[][] employeeDataProvider() {
    // Từ Excel
    return ExcelHelper.readExcel("testdata/EmployeeTestData.xlsx", "ValidData");
    
    // Hoặc từ JSON
    // return JSONHelper.readJSON("testdata/EmployeeData.json");
}

// ✅ ĐÚNG: Sử dụng DataFaker cho dynamic data
@DataProvider
public Object[][] fakerDataProvider() {
    Faker faker = new Faker();
    return new Object[][] {
        { faker.name().fullName(), faker.internet().email(), faker.phoneNumber().cellPhone() },
        { faker.name().fullName(), faker.internet().email(), faker.phoneNumber().cellPhone() }
    };
}

// ❌ SAI: Hardcode test data trong test method
@Test
public void testCreateEmployee() {
    // ❌ KHÔNG làm như thế này
    enterName("John Doe");
    enterEmail("john.doe@example.com");
    enterPhone("1234567890");
    // Hardcoded values scattered everywhere
}
```

### 5.4 Independent Tests

```java
// ✅ ĐÚNG: Mỗi test độc lập, tự setup/teardown
@Test
public void testCreateEmployee_Success() {
    // Setup - tạo data cần thiết
    EmployeeRequest request = createValidEmployeeRequest();
    
    // Act
    EmployeeResponse response = employeeService.create(request);
    
    // Assert
    assertNotNull(response.getId());
    
    // Cleanup - xóa data đã tạo
    employeeService.delete(response.getId());
}

@Test
public void testSearchEmployee_ByName() {
    // Setup - tạo employee để search
    EmployeeRequest request = createValidEmployeeRequest();
    EmployeeResponse created = employeeService.create(request);
    
    // Act
    List<EmployeeResponse> results = employeeService.searchByName(request.getName());
    
    // Assert
    assertTrue(results.size() >= 1);
    
    // Cleanup
    employeeService.delete(created.getId());
}

// ❌ SAI: Test phụ thuộc vào nhau
@Test
public void testCreateEmployee() {
    // Tạo employee
}

@Test
public void testSearchEmployee() {
    // ❌ Giả định testCreateEmployee đã chạy
    // Search employee vừa tạo ở trên
}
```

---

## 6. Locator Strategy Rules

### 6.1 Locator Priority

```java
// Độ ưu tiên: data-testid > id > name > css > xpath

// ✅ ƯU TIÊN 1: data-testid (developer-requested attributes)
private static final By BTN_SUBMIT = By.cssSelector("[data-testid='submit-btn']");
private static final By TXT_USERNAME = By.cssSelector("[data-testid='username-input']");

// ✅ ƯU TIÊN 2: data-cy (Cypress-style)
private static final By BTN_LOGIN = By.cssSelector("[data-cy='login-button']");

// ✅ ƯU TIÊN 3: data-qa
private static final By LBL_WELCOME = By.cssSelector("[data-qa='welcome-label']");

// ✅ ƯU TIÊN 4: id (unique identifier)
private static final By TXT_EMAIL = By.id("email");
private static final By TXT_PASSWORD = By.id("password");

// ✅ ƯU TIÊN 5: name attribute
private static final By TXT_SEARCH = By.name("searchQuery");

// ✅ ƯU TIÊN 6: CSS selector
private static final By BTN_SUBMIT = By.cssSelector(".btn.btn-primary.submit");
private static final By LNK_LOGOUT = By.cssSelector("a[href='/logout']");

// ⚠️ ƯU TIÊN THẤP: XPath (chỉ khi không có cách nào khác)
private static final By LBL_ERROR = By.xpath("//div[@class='alert alert-danger']");
private static final By ROW_FIRST = By.xpath("//table[@id='employeeTable']//tr[1]");
```

### 6.2 Locator Best Practices

```java
// ✅ ĐÚNG: Unique, stable, readable
private static final By BTN_LOGIN = By.id("loginButton");
private static final By TXT_EMAIL = By.cssSelector("input[type='email']");
private static final By LBL_NAME = By.xpath("//span[@class='employee-name'][@data-id='123']");

// ❌ SAI: Fragile, unreadable, overly complex
private static final By BTN = By.xpath("//div[1]/div[2]/button[1]");  // Absolute path
private static final By INPUT = By.xpath("//input[@type='text' and @class='form-control']"); // Too generic
private static final By EL = By.xpath("//*[contains(@id, 'test')]"); // Too vague

// ✅ ĐÚNG: Relative XPath với multiple attributes
private static final By LBL_EMPLOYEE = By.xpath(
    "//span[@class='employee-name'][contains(text(), 'John')][@data-active='true']"
);

// ❌ SAI: Complex nested XPath
private static final By LBL_EMPLOYEE = By.xpath(
    "//div[@class='container']/div[@class='content']/div[@class='employee-list']/div[@class='row'][1]/span[@class='employee-name']"
);
```

### 6.3 Dynamic Locators

```java
// ✅ ĐÚNG: Parameterized locators
public By getEmployeeRowByName(String name) {
    return By.xpath("//tr[@class='employee-row'][td[contains(text(),'" + name + "')]]");
}

public By getDeleteButtonById(String id) {
    return By.cssSelector("[data-action='delete'][data-id='" + id + "']");
}

// ✅ ĐÚNG: Locator với String.format
private static final By ROW_TEMPLATE = "//table[@id='table']//tr[%d]";
private static final By cellInRow(int row, int col) {
    return By.xpath(String.format("//table[@id='table']//tr[%d]//td[%d]", row, col));
}

// ❌ SAI: Dynamic locator trong test method
@Test
public void testEmployee() {
    // ❌ KHÔNG làm như thế này
    String name = "John Doe";
    By deleteBtn = By.xpath("//button[contains(@data-name,'" + name + "')]");
}
```

---

## 7. Wait Strategy Rules

### 7.1 Wait Hierarchy

```java
// ✅ THỨ TỰ ƯU TIÊN: Explicit Wait > Thread.sleep (chỉ khi cần thiết)

// 1. Implicit Wait - Đặt 1 lần trong setup
// ConfigHelper: implicitWait = 30 giây

// 2. Explicit Wait - Ưu tiên sử dụng
WaitHelper.waitForVisibility(By.id("elementId"));
WaitHelper.waitForClickable(By.id("buttonId"));
WaitHelper.waitForElementPresent(By.xpath("//div"));

// 3. Fluent Wait - Cho elements có polling interval
WaitHelper.waitForCondition(By.id("elementId"), 
    ExpectedConditions.textToBePresentInElement(locator, "expected text"),
    10, // timeout
    2   // polling interval
);

// 4. Thread.sleep - CHỈ khi cần thiết cho animation/rendering
Thread.sleep(1000); // Animation wait
```

### 7.2 Wait Helper Usage

```java
// ✅ ĐÚNG: Sử dụng WaitHelper methods
public class LoginPage extends BasePage {
    
    public void login(String username, String password) {
        // Wait before interaction
        waitForElementVisible(TXT_USERNAME);
        waitForElementClickable(BTN_LOGIN);
        
        // Perform actions
        setText(TXT_USERNAME, username);
        setText(TXT_PASSWORD, password);
        click(BTN_LOGIN);
        
        // Wait for navigation
        waitForUrlContains("dashboard");
    }
}

// ✅ ĐÚNG: Wait for page load
public void navigateToPage(String url) {
    DriverManager.getDriver().get(url);
    waitForPageLoaded();
    waitForAjaxComplete();
}

// ❌ SAI: Không wait hoặc wait quá lâu không cần thiết
@Test
public void testWithoutWait() {
    // ❌ KHÔNG làm như thế này
    driver.findElement(By.id("btn")).click();
    driver.findElement(By.id("result")).getText(); // Có thể fail
}
```

### 7.3 Timeout Configuration

```java
// ✅ ĐÚNG: Sử dụng config cho timeouts
// config.properties
explicitWait=30
implicitWait=30
pageLoadTimeout=60
scriptTimeout=30

// ✅ ĐÚNG: Custom timeout cho specific elements
public void waitForSpinnerToDisappear() {
    WaitHelper.waitForInvisibility(By.id("spinner"), 60);
}

public void waitForDownloadComplete(String fileName, int timeoutSeconds) {
    WaitHelper.waitForCondition(
        ExpectedConditions.or(
            ExpectedConditions.not(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//download/in-progress")))),
        timeoutSeconds,
        5
    );
}
```

---

## 8. Assertion Rules

### 8.1 Assertion Hierarchy

```java
// ✅ ƯU TIÊN 1: Hard Assertions (dừng test khi fail)
Assert.assertEquals(actual, expected);
Assert.assertTrue(condition);
Assert.assertNotNull(object);
Assert.assertFalse(condition);

// ✅ ƯU TIÊN 2: Soft Assertions (tiếp tục test khi fail)
SoftAssert softAssert = new SoftAssert();
softAssert.assertEquals(actual, expected);
softAssert.assertTrue(condition);
softAssert.assertAll(); // Phải gọi cuối cùng

// ✅ ƯU TIÊN 3: Verification (log only, không fail test)
VerificationHelper.verifyEquals(actual, expected);
VerificationHelper.verifyTrue(condition, "Custom message");
```

### 8.2 Assertion Best Practices

```java
// ✅ ĐÚNG: Descriptive assertion messages
@Test
public void testLogin() {
    loginPage.login("admin", "admin123");
    
    assertEquals(
        "Dashboard URL should match after successful login",
        ConfigHelper.getDashboardUrl(),
        driver.getCurrentUrl()
    );
    
    assertTrue(
        "User should be logged in successfully",
        dashboardPage.isUserLoggedIn()
    );
}

// ✅ ĐÚNG: Multiple assertions với SoftAssert
@Test
public void testUserProfile() {
    SoftAssert softAssert = new SoftAssert();
    
    UserProfile profile = profilePage.getProfile();
    
    softAssert.assertEquals(profile.getName(), "John Doe", "Name mismatch");
    softAssert.assertEquals(profile.getEmail(), "john@example.com", "Email mismatch");
    softAssert.assertEquals(profile.getRole(), "Admin", "Role mismatch");
    softAssert.assertEquals(profile.getDepartment(), "Engineering", "Department mismatch");
    
    softAssert.assertAll(); // Quan trọng!
}

// ❌ SAI: Không có message
@Test
public void testLogin() {
    assertEquals(driver.getCurrentUrl(), expectedUrl); // ❌ Không rõ lý do
}
```

### 8.3 Custom Assertion Messages

```java
// ✅ ĐÚNG: Custom assertion với formatted message
public static void assertElementVisible(By locator) {
    WebDriver driver = DriverManager.getDriver();
    WebElement element = driver.findElement(locator);
    
    if (!element.isDisplayed()) {
        throw new AssertionError(
            String.format("Element not visible: %s (Locator: %s)", 
                getLocatorInfo(locator), locator)
        );
    }
}

public static void assertElementTextEquals(By locator, String expected) {
    String actual = ElementHelper.getText(locator);
    
    if (!actual.equals(expected)) {
        throw new AssertionError(
            String.format("Text mismatch! Expected: '%s', Actual: '%s' at %s",
                expected, actual, getLocatorInfo(locator))
        );
    }
}
```

---

## 9. Logging Rules

### 9.1 Log Level Usage

```java
// ✅ INFO: Test progress, important milestones
Log.info("Starting test: " + testName);
Log.info("Login successful with user: " + username);
Log.info("Navigating to Employee Management page");
Log.info("Employee created with ID: " + employeeId);

// ✅ DEBUG: Detailed execution info
Log.debug("Clicking element: " + locator);
Log.debug("Page title: " + pageTitle);
Log.debug("Element found at: " + element.getLocation());

// ✅ WARN: Non-critical issues, potential problems
Log.warn("Element not found on first attempt, retrying...");
Log.warn("Page load took longer than expected: " + loadTime + "ms");
Log.warn("Test data file not found, using default values");

// ✅ ERROR: Failures, exceptions
Log.error("Login failed: " + exception.getMessage());
Log.error("Element not found after " + MAX_ATTEMPTS + " attempts");
Log.error("API response error: " + response.getStatusCode());
```

### 9.2 Log Best Practices

```java
// ✅ ĐÚNG: Log at key points
@Test
public void testCreateEmployee() {
    // Start
    Log.info("=== Starting testCreateEmployee ===");
    
    try {
        // Setup
        Log.info("Preparing test data");
        EmployeeRequest request = createValidEmployeeRequest();
        
        // Action
        Log.info("Creating employee: " + request.getName());
        EmployeeResponse response = employeeService.create(request);
        Log.info("Employee created with ID: " + response.getId());
        
        // Verification
        assertNotNull(response.getId());
        assertEquals(response.getName(), request.getName());
        Log.info("Employee verification passed");
        
        // Cleanup
        Log.info("Cleaning up test data");
        employeeService.delete(response.getId());
        
        Log.info("=== testCreateEmployee PASSED ===");
        
    } catch (Exception e) {
        Log.error("testCreateEmployee FAILED: " + e.getMessage(), e);
        throw e;
    }
}

// ❌ SAI: Over-logging hoặc under-logging
@Test
public void testCreateEmployee() {
    // ❌ Over-logging: Log mọi thứ
    Log.info("Step 1: Creating request object");
    Log.info("Step 2: Setting name");
    Log.info("Step 3: Setting email");
    Log.info("Step 4: Calling API");
    Log.info("Step 5: Getting response");
    
    // ❌ Under-logging: Không log gì
    employeeService.create(request);
}
```

### 9.3 Allure Reporting Integration

```java
// ✅ ĐÚNG: Allure @Step annotations
@Step("Enter username: {0}")
public void enterUsername(String username) {
    ActionHelper.setText(TXT_USERNAME, username);
}

@Step("Enter password")
public void enterPassword(String password) {
    ActionHelper.setText(TXT_PASSWORD, password);
}

@Step("Click login button")
public void clickLogin() {
    ActionHelper.click(BTN_LOGIN);
}

@Step("Verify user is logged in")
public boolean isUserLoggedIn() {
    return VerificationHelper.isDisplayed(LBL_WELCOME_USER);
}

// ✅ ĐÚNG: Allure attachments
@Attachment(value = "Screenshot on failure", type = "image/png")
public byte[] captureScreenshot() {
    return CaptureHelper.getScreenshotAsBytes();
}

@Attachment(value = "Page Source", type = "text/html")
public byte[] capturePageSource() {
    return DriverManager.getDriver().getPageSource().getBytes();
}
```

---

## 10. Exception Handling Rules

### 10.1 Custom Exceptions

```java
// ✅ ĐÚNG: Custom exceptions cho specific errors
public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(By locator) {
        super("Element not found: " + locator);
    }
    
    public ElementNotFoundException(String message, By locator) {
        super(String.format("%s | Locator: %s", message, locator));
    }
}

public class PageLoadException extends RuntimeException {
    public PageLoadException(String pageName) {
        super("Failed to load page: " + pageName);
    }
}

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String field, String reason) {
        super(String.format("Invalid data for '%s': %s", field, reason));
    }
}
```

### 10.2 Exception Handling Pattern

```java
// ✅ ĐÚNG: Try-catch with logging
@Test
public void testDeleteEmployee() {
    Log.info("Starting delete employee test");
    
    try {
        // Create employee to delete
        EmployeeResponse employee = employeeService.create(createValidEmployee());
        Log.info("Created employee: " + employee.getId());
        
        // Delete employee
        employeeService.delete(employee.getId());
        Log.info("Deleted employee: " + employee.getId());
        
        // Verify deletion
        EmployeeResponse deleted = employeeService.getById(employee.getId());
        assertNull("Employee should be deleted", deleted);
        
    } catch (Exception e) {
        Log.error("Delete employee test failed: " + e.getMessage(), e);
        captureScreenshot();
        throw e;
    }
}

// ✅ ĐÚNG: Expected exceptions
@Test(expectedExceptions = ElementNotFoundException.class)
public void testInvalidLocator_ShouldThrowException() {
    driver.findElement(By.id("non-existent-element"));
}

// ✅ ĐÚNG: Try-finally for cleanup
@Test
public void testWithCleanup() {
    EmployeeResponse employee = null;
    try {
        employee = employeeService.create(createValidEmployee());
        // Test assertions
    } finally {
        if (employee != null) {
            employeeService.delete(employee.getId());
        }
    }
}
```

---

## 11. Test Data Rules

### 11.1 Test Data Principles

```
┌─────────────────────────────────────────────────────────────┐
│                   TEST DATA PRINCIPLES                      │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  1. ISOLATION        - Mỗi test có data riêng               │
│  2. INDEPENDENCE     - Test không phụ thuộc thứ tự          │
│  3. REUSABILITY      - Share data khi hợp lý               │
│  4. REALISM          - Data gần với production             │
│  5. ANONYMIZATION    - Không có sensitive data thật        │
│  6. TRACEABILITY     - Biết data đến từ đâu                │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

### 11.2 Test Data Sources

```java
// ✅ NGUỒN 1: Constants (static, shared)
public class TestData {
    public static final String DEFAULT_USERNAME = "admin";
    public static final String DEFAULT_PASSWORD = "admin123";
    public static final String VALID_EMAIL = "test@example.com";
}

// ✅ NGUỒN 2: Properties files (environment-specific)
PropertiesHelper helper = new PropertiesHelper();
String url = helper.get("url");
String browser = helper.get("browser");

// ✅ NGUỒN 3: Excel/JSON (test case data)
@DataProvider
public Object[][] employeeData() {
    return ExcelHelper.readExcel("testdata/EmployeeTestData.xlsx", "ValidData");
}

// ✅ NGUỒN 4: DataFaker (generated)
@DataProvider
public Object[][] fakerData() {
    Faker faker = new Faker();
    return new Object[][] {
        { faker.name().fullName(), faker.internet().email() }
    };
}

// ✅ NGUỒN 5: Database (pre-condition)
public EmployeeResponse createTestEmployee() {
    // Insert directly to DB for test setup
    return employeeRepository.save(testEmployee);
}
```

### 11.3 Test Data File Structure

```excel
// Excel file: EmployeeTestData.xlsx

// Sheet: ValidData
| Name          | Email                  | Department   | Salary   | Status |
|---------------|------------------------|--------------|----------|--------|
| John Doe      | john.doe@example.com   | Engineering  | 10000    | Active |
| Jane Smith    | jane.smith@example.com | Marketing    | 8000     | Active |
| Bob Johnson   | bob.johnson@example.com| Sales        | 7000     | Active |

// Sheet: InvalidData
| Name          | Email        | Department | Expected Error        |
|---------------|--------------|------------|-----------------------|
| AB            | invalid      |            | Name too short        |
|               | test@test.com| Engineering| Name required         |
| Test User     | invalid-email| Sales      | Invalid email format  |

// Sheet: BoundaryData
| Salary    | Expected Behavior                      |
|-----------|----------------------------------------|
| 0         | Error: Minimum salary required         |
| 1         | Success (minimum)                       |
| 1000000   | Success (maximum valid)                |
| 1000001   | Error: Exceeds maximum                 |
```

---

## 12. Configuration Rules

### 12.1 ConfigHelper Usage

```java
// ✅ ĐÚNG: Sử dụng ConfigHelper cho tất cả configuration
public class ConfigHelper {
    private static Properties properties;
    
    public static void init() {
        String env = System.getProperty("env", "local");
        String configPath = "src/test/resources/config/" + env + ".properties";
        properties = PropertiesHelper.load(configPath);
    }
    
    public static String getUrl() {
        return properties.getProperty("url");
    }
    
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
    
    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "30"));
    }
}
```

### 12.2 Environment Configuration

```properties
# local.properties
url=https://hrm.local.company.com
browser=chrome
headless=false
timeout=30
implicitWait=30
pageLoadTimeout=60
username=admin
password=admin123
dbUrl=jdbc:mysql://localhost:3306/hrm_local

# qa.properties
url=https://hrm-qa.company.com
browser=chrome
headless=true
timeout=30
implicitWait=30
pageLoadTimeout=60
username=qa_admin
password=qa_admin123
dbUrl=jdbc:mysql://qa-db.company.com:3306/hrm_qa

# staging.properties
url=https://hrm-staging.company.com
browser=chrome,firefox,edge
headless=true
timeout=30
implicitWait=30
pageLoadTimeout=60
username=staging_admin
password=staging_admin123
dbUrl=jdbc:mysql://staging-db.company.com:3306/hrm_staging
```

### 12.3 Environment Switching

```bash
# Command line execution
mvn test                              # Default: local
mvn test -Denv=qa                     # QA environment
mvn test -Denv=staging               # Staging environment
mvn test -Denv=qa -Dbrowser=firefox  # QA + Firefox
mvn test -Denv=staging -Dheadless=true # Staging + headless
```

---

## 13. Test Suite Rules

### 13.1 Suite XML Structure

```xml
<!-- Suite: Smoke Test -->
<suite name="SmokeTestSuite" parallel="tests" thread-count="2">
    <listeners>
        <listener class-name="com.selenium_hrm.listeners.TestListener"/>
    </listeners>
    
    <test name="LoginSmokeTests" verbose="2">
        <groups>
            <run>
                <include name="smoke"/>
                <include name="critical"/>
            </run>
        </groups>
        <classes>
            <class name="com.selenium_hrm.tests.LoginTests"/>
        </classes>
    </test>
    
    <test name="DashboardSmokeTests" verbose="2">
        <groups>
            <run>
                <include name="smoke"/>
                <include name="critical"/>
            </run>
        </groups>
        <classes>
            <class name="com.selenium_hrm.tests.DashboardTests"/>
        </classes>
    </test>
</suite>

<!-- Suite: Regression Test -->
<suite name="RegressionTestSuite" parallel="classes" thread-count="4">
    <listeners>
        <listener class-name="com.selenium_hrm.listeners.TestListener"/>
    </listeners>
    
    <test name="FullRegression">
        <groups>
            <run>
                <include name="regression"/>
            </run>
        </groups>
        <packages>
            <package name="com.selenium_hrm.tests.*"/>
        </packages>
    </test>
</suite>
```

### 13.2 Test Group Definitions

```java
// Group definitions
public class TestGroups {
    // By Priority
    public static final String CRITICAL = "critical";  // Must pass
    public static final String P1 = "p1";             // High priority
    public static final String P2 = "p2";             // Medium priority
    public static final String P3 = "p3";             // Low priority
    
    // By Type
    public static final String SMOKE = "smoke";        // Smoke tests
    public static final String SANITY = "sanity";      // Sanity tests
    public static final String REGRESSION = "regression"; // Regression tests
    public static final String E2E = "e2e";            // End-to-end tests
    
    // By Module
    public static final String LOGIN = "login";
    public static final String DASHBOARD = "dashboard";
    public static final String EMPLOYEE = "employee";
    public static final String LEAVE = "leave";
    public static final String PAYROLL = "payroll";
    
    // By Type
    public static final String API = "api";
    public static final String UI = "ui";
    public static final String DATABASE = "database";
}
```

---

## 14. Code Review Rules

### 14.1 Pre-Submission Checklist

```markdown
## Code Review Checklist

### Naming & Structure
- [ ] Class names follow conventions
- [ ] Method names are descriptive
- [ ] Variables are properly named
- [ ] Package structure follows rules

### POM Implementation
- [ ] Locators in separate Elements class
- [ ] Actions in separate Actions class
- [ ] Page class uses BasePage
- [ ] No hardcoded locators in test class

### Test Methods
- [ ] Follows AAA pattern
- [ ] Test data via DataProvider or test data files
- [ ] Independent (no test dependencies)
- [ ] Proper cleanup in finally/AfterMethod

### Waits & Synchronization
- [ ] Uses explicit waits (WaitHelper)
- [ ] No Thread.sleep() unless necessary
- [ ] Proper timeout configuration

### Assertions
- [ ] Descriptive assertion messages
- [ ] Uses SoftAssert when needed
- [ ] Proper error handling

### Logging
- [ ] Logs at key points
- [ ] No over/under logging
- [ ] Allure @Step annotations

### Configuration
- [ ] No hardcoded values
- [ ] Uses ConfigHelper/PropertiesHelper
- [ ] Environment-specific configs
```

### 14.2 Review Standards

```java
// ❌ REJECT: Hardcoded values
@Test
public void testLogin() {
    driver.get("https://hrm.local.com"); // ❌
    driver.findElement(By.id("username")).sendKeys("admin"); // ❌
}

// ✅ ACCEPT: Config-based values
@Test
public void testLogin() {
    driver.get(ConfigHelper.getUrl()); // ✅
    loginPage.enterUsername(ConfigHelper.getUsername()); // ✅
}

// ❌ REJECT: No waits
@Test
public void testLogin() {
    driver.findElement(By.id("btn")).click(); // ❌
    String text = driver.findElement(By.id("result")).getText(); // ❌ Race condition
}

// ✅ ACCEPT: Proper waits
@Test
public void testLogin() {
    waitForElementClickable(BTN_LOGIN);
    click(BTN_LOGIN);
    waitForElementVisible(LBL_RESULT);
    String text = getText(LBL_RESULT);
}

// ❌ REJECT: Complex unreadable locator
@Test
public void test() {
    By btn = By.xpath("/html/body/div[1]/div[2]/form/div[3]/button[2]"); // ❌ Fragile
}

// ✅ ACCEPT: Clean locator
@Test
public void test() {
    By btn = By.id("submitButton"); // ✅ Stable
}
```

---

## 15. Execution Rules

### 15.1 Maven Commands

```bash
# Basic execution
mvn test                                    # Default (local, chrome)
mvn clean test                              # Clean and test

# Environment
mvn test -Denv=qa                           # QA environment
mvn test -Denv=staging                      # Staging environment

# Browser
mvn test -Dbrowser=chrome                    # Chrome
mvn test -Dbrowser=firefox                  # Firefox
mvn test -Dbrowser=edge                     # Edge

# Combined
mvn test -Denv=qa -Dbrowser=firefox        # QA + Firefox
mvn test -Denv=staging -Dbrowser=chrome,firefox,edge # All browsers

# Specific suite
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suites/SmokeTest.xml

# Parallel execution
mvn test -Dparallel=methods -DthreadCount=4

# Report generation
mvn test                                    # Generates Extent Report
mvn allure:serve                            # Serve Allure report
mvn allure:generate                         # Generate Allure report
```

### 15.2 Test Execution Order

```java
// @Test(priority = n) - Run in priority order
@Test(priority = 1)
public void testPrecondition() { }  // Run first

@Test(priority = 2)
public void testMainFeature() { }   // Run second

@Test(priority = 3)
public void testCleanup() { }        // Run last

// ⚠️ NOTE: priority only works within same class
// For cross-class ordering, use dependsOnMethods
@Test(dependsOnMethods = "testLogin")
public void testDashboard() { }      // Run after testLogin
```

### 15.3 Parallel Execution Rules

```java
// ✅ Suite level parallel
<suite name="ParallelSuite" parallel="tests" thread-count="4">

// ✅ Class level parallel
<suite name="ParallelSuite" parallel="classes" thread-count="4">

// ✅ Method level parallel
<suite name="ParallelSuite" parallel="methods" thread-count="4">
```

---

## 16. Maintenance Rules

### 16.1 Code Maintenance

```
┌─────────────────────────────────────────────────────────────┐
│                   MAINTENANCE SCHEDULE                       │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  DAILY                                                       │
│  ├── Review failed test reports                             │
│  ├── Fix critical/flaky tests                               │
│  └── Update test data if needed                             │
│                                                              │
│  WEEKLY                                                      │
│  ├── Review test execution metrics                          │
│  ├── Refactor unstable locators                            │
│  ├── Update documentation                                  │
│  └── Review code coverage                                   │
│                                                              │
│  MONTHLY                                                     │
│  ├── Review and update POM structure                        │
│  ├── Update framework dependencies                         │
│  ├── Review and update test data                           │
│  └── Conduct retrospective                                 │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

### 16.2 Locator Maintenance

```java
// ✅ KHI NÀO CẦN UPDATE LOCATOR
// 1. Developer thay đổi element attribute
// 2. UI redesign affecting element position
// 3. Test fail với "Element not found" error
// 4. Flaky test do locator không stable

// ✅ PROCESS
// 1. Identify failing locator
// 2. Check application UI for new attributes
// 3. Update Elements class
// 4. Run test to verify
// 5. Check for similar patterns in other tests
```

### 16.3 Framework Updates

```markdown
## Dependency Update Process

### Step 1: Research
- Check release notes
- Check breaking changes
- Verify compatibility with Java version

### Step 2: Update in pom.xml
```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>NEW_VERSION</version>  <!-- Update here -->
</dependency>
```

### Step 3: Test
- Run smoke tests
- Verify all core functionality

### Step 4: Document
- Update this document with version changes
- Note any behavior changes
```

---

## 17. Anti-Patterns

### 17.1 Prohibited Practices

```java
// ❌ CẤM 1: System.out.println thay cho Log
System.out.println("Test passed");  // ❌

// ✅ THAY: Sử dụng Log
Log.info("Test passed");  // ✅

// ❌ CẤM 2: Thread.sleep thay cho explicit wait
Thread.sleep(5000);  // ❌

// ✅ THAY: Sử dụng WaitHelper
WaitHelper.waitForVisibility(locator);  // ✅

// ❌ CẤM 3: ThreadLocal driver không properly cleaned
// KHÔNG làm điều này
public static WebDriver driver;
driver = new ChromeDriver();

// ✅ THAY: Luôn clean ThreadLocal
DriverManager.quit();  // ✅

// ❌ CẤM 4: Static WebDriver
public static WebDriver driver;  // ❌ Concurrency issues

// ✅ THAY: ThreadLocal
private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();  // ✅

// ❌ CẤM 5: Screenshot capture không có error handling
byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

// ✅ THAY: Với error handling
if (driver instanceof TakesScreenshot) {
    byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
}

// ❌ CẤM 6: Driver quit trong @BeforeMethod
@BeforeMethod
public void setup() {
    driver = new ChromeDriver();  // ❌ Create new every time
}

// ✅ THAY: Reuse driver khi có thể
@BeforeClass
public void setupClass() {
    driver = new ChromeDriver();  // ✅
}

@AfterClass
public void teardownClass() {
    driver.quit();  // ✅
```

### 17.2 Code Smells

```java
// ❌ SMELL 1: Magic numbers
driver.findElement(By.id("test")).click();
Thread.sleep(3000);
assertEquals(driver.findElements(By.className("item")).size(), 25);

// ✅ FIX 1: Named constants
private static final int EXPECTED_ITEM_COUNT = 25;
private static final int SHORT_WAIT_MS = 3000;

// ❌ SMELL 2: God Method (quá dài)
@Test
public void testEverything() {
    // 500 lines of code doing everything
}

// ✅ FIX 2: Small, focused methods
@Test
public void testLogin() { loginFlow(); verifyDashboard(); }
@Test
public void testNavigation() { navigateToEmployee(); verifyPage(); }

// ❌ SMELL 3: Comment spam
// Click button
button.click();  // ❌ Redundant comment

// ✅ FIX 3: Remove obvious comments
button.click();  // ✅

// ❌ SMELL 4: Deep nesting
if (condition1) {
    if (condition2) {
        if (condition3) {
            // deeply nested code
        }
    }
}

// ✅ FIX 4: Early return / Extract method
if (!condition1) return;
if (!condition2) return;
if (!condition3) return;
// logic here
```

---

## 18. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer | Initial framework rules |
