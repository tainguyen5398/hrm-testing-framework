# Coding Standards - Selenium HRM Automation

## 1. Introduction

### 1.1 Purpose
Coding standards định nghĩa các quy tắc và hướng dẫn về cách viết code Java trong Selenium HRM automation framework. Mục tiêu là đảm bảo:
- **Consistency**: Code style thống nhất trong toàn bộ project
- **Readability**: Code dễ đọc, dễ hiểu cho tất cả team members
- **Maintainability**: Code dễ sửa đổi, mở rộng
- **Reusability**: Code có thể tái sử dụng, giảm duplicate
- **Reliability**: Code hoạt động đúng và ổn định

### 1.2 Scope
| Area | Description |
|------|-------------|
| Java Code Style | Formatting, naming, structure |
| Selenium Best Practices | WebDriver usage, element interactions |
| Test Organization | Test structure, data management |
| Error Handling | Exception patterns, logging |
| Documentation | Javadoc, comments |

### 1.3 Reference
- Framework Rules: `framework-rules.md`
- Naming Convention: `naming-convention.md`
- Locator Strategy: `locator-strategy.md`

---

## 2. Code Formatting

### 2.1 Indentation & Spacing

```java
// ✅ ĐÚNG: 4 spaces cho indentation (KHÔNG dùng tabs)
public class LoginPage extends BasePage {
    
    private static final By TXT_USERNAME = By.id("username");
    private static final By TXT_PASSWORD = By.id("password");
    private static final By BTN_LOGIN = By.id("loginButton");
    
    public void login(String username, String password) {
        waitForElementVisible(TXT_USERNAME);
        setText(TXT_USERNAME, username);
        setText(TXT_PASSWORD, password);
        click(BTN_LOGIN);
    }
}

// ❌ SAI: Tab hoặc inconsistent spacing
public class LoginPage extends BasePage {
    private static final By TXT_USERNAME = By.id("username");
        private static final By TXT_PASSWORD = By.id("password");
    public void login(String username, String password) {
        waitForElementVisible(TXT_USERNAME);
    setText(TXT_USERNAME, username);
    }
}
```

### 2.2 Line Length

```java
// ✅ ĐÚNG: Tối đa 120 ký tự per line
// Split long lines với proper indentation
public void verifyEmployeeDetails(
        String name,
        String email,
        String department,
        String position
) {
    // Implementation
}

// ❌ SAI: Line quá dài, phải scroll ngang
public void verifyEmployeeDetails(String name, String email, String department, String position) {
// Implementation
}
```

### 2.3 Braces

```java
// ✅ ĐÚNG: K&R style braces (1TBS - One True Brace Style)
public void login(String username, String password) {
    if (isValidCredentials(username, password)) {
        performLogin(username, password);
    } else {
        showErrorMessage();
    }
}

// ✅ ĐÚNG: Always use braces (even for single statements)
if (isEnabled) {
    click(element);
}

// ❌ SAI: Stroustrup style hoặc missing braces
public void login(String username, String password)
{
    if (isValidCredentials(username, password))
        performLogin(username, password);
    else
        showErrorMessage();
}

// ❌ SAI: No braces
if (isEnabled)
    click(element);  // Risky!
```

### 2.4 Blank Lines

```java
// ✅ ĐÚNG: Blank lines để phân tách logical groups
public class EmployeePage extends BasePage {
    
    // Locators
    private static final By TXT_NAME = By.id("employeeName");
    private static final By TXT_EMAIL = By.id("employeeEmail");
    private static final By BTN_SUBMIT = By.id("submitBtn");
    
    // Constructor
    public EmployeePage() {
        super();
    }
    
    // Public methods
    public void createEmployee(EmployeeData data) {
        setText(TXT_NAME, data.getName());
        setText(TXT_EMAIL, data.getEmail());
        click(BTN_SUBMIT);
    }
    
    // Private methods
    private boolean validateEmail(String email) {
        return email.contains("@");
    }
}

// ❌ SAI: Không có blank lines
public class EmployeePage extends BasePage {
    private static final By TXT_NAME = By.id("employeeName");
    private static final By TXT_EMAIL = By.id("employeeEmail");
    public EmployeePage() { super(); }
    public void createEmployee(EmployeeData data) { setText(TXT_NAME, data.getName()); }
}
```

### 2.5 Imports

```java
// ✅ ĐÚNG: Organized imports (static first, then alphabetical)
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// ❌ SAI: Unorganized imports
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.By;
import static org.testng.Assert.assertEquals;
```

---

## 3. Java Language Rules

### 3.1 Access Modifiers

```java
// ✅ ĐÚNG: Sử dụng access modifier phù hợp

// Class - public for page objects
public class LoginPage extends BasePage {
    
    // Constants - private static final
    private static final By TXT_USERNAME = By.id("username");
    private static final By BTN_LOGIN = By.id("loginBtn");
    
    // Static helpers - private static
    private static WebDriver getDriver() {
        return DriverManager.getDriver();
    }
    
    // Public methods
    public void login(String username, String password) {
        getDriver().findElement(TXT_USERNAME).sendKeys(username);
        getDriver().findElement(BTN_LOGIN).click();
    }
    
    // Protected methods - for subclasses
    protected void waitForLoginComplete() {
        WaitHelper.waitForUrlContains("dashboard");
    }
}

// ❌ SAI: Overexposing hoặc underexposing
class LoginPage {  // ❌ Missing public
    static By TXT_USERNAME = By.id("username");  // ❌ Missing private
    public void login() { }  // May be OK for some cases
}
```

### 3.2 Final Usage

```java
// ✅ ĐÚNG: Sử dụng final cho constants và immutable objects

// Constants
private static final int MAX_RETRY = 3;
private static final String DEFAULT_USERNAME = "admin";
private static final By BTN_SUBMIT = By.id("submit");

// Parameters
public void login(final String username, final String password) {
    // username and password cannot be reassigned
}

// Local variables - cũng nên dùng final
public void processEmployee(EmployeeData data) {
    final String name = data.getName();
    final String email = data.getEmail();
    // ...
}

// ❌ SAI: Không dùng final cho constants
private static int MAX_RETRY = 3;  // ❌ Missing static
public static String URL = "http://test.com";  // ❌ Not final
```

### 3.3 Type Declarations

```java
// ✅ ĐÚNG: Prefer interfaces for variable types
List<String> names = new ArrayList<>();
Map<String, Integer> scores = new HashMap<>();
WebDriver driver = DriverManager.getDriver();

// ✅ ĐÚNG: ArrayList instead of LinkedList (general purpose)
List<String> list = new ArrayList<>();
Set<Integer> set = new HashSet<>();

// ❌ SAI: Concrete types as variable declarations
ArrayList<String> names = new ArrayList<>();  // ❌ Less flexible
LinkedList<String> items = new LinkedList<>();  // ❌ Usually not needed
```

### 3.4 String Handling

```java
// ✅ ĐÚNG: StringBuilder for multiple concatenations
public String buildEmployeeInfo(Employee emp) {
    StringBuilder sb = new StringBuilder();
    sb.append("Name: ").append(emp.getName())
      .append(", Email: ").append(emp.getEmail())
      .append(", Department: ").append(emp.getDepartment())
      .append(", Position: ").append(emp.getPosition());
    return sb.toString();
}

// ✅ ĐÚNG: String.format for formatted strings
public String formatMessage(String name, int count) {
    return String.format("Hello %s, you have %d notifications", name, count);
}

// ✅ ĐÚNG: String.isEmpty() thay vì length() == 0
if (username == null || username.isEmpty()) {
    throw new InvalidDataException("Username cannot be empty");
}

// ❌ SAI: String concatenation in loop
String result = "";
for (String item : items) {
    result += item + ",";  // ❌ Creates new String each iteration
}
```

### 3.5 Collections

```java
// ✅ ĐÚNG: Initialize collections with size hints when known
List<Employee> employees = new ArrayList<>(expectedSize);
Map<String, Employee> employeeMap = new HashMap<>(expectedSize);

// ✅ ĐÚNG: Use enhanced for-loop
for (Employee emp : employeeList) {
    processEmployee(emp);
}

// ✅ ĐÚNG: Check for empty collection
if (employeeList != null && !employeeList.isEmpty()) {
    // Process
}

// ✅ ĐÚNG: Use streams for complex operations
List<String> activeEmployeeNames = employees.stream()
    .filter(Employee::isActive)
    .map(Employee::getName)
    .collect(Collectors.toList());

// ❌ SAI: Primitive collection wrappers unnecessary
List<int> numbers = new ArrayList<>();  // ❌ Can't use primitive
List<Integer> numbers = new ArrayList<>();  // ✅ Correct
```

---

## 4. Class Structure

### 4.1 Class Organization

```java
// ✅ STANDARD CLASS STRUCTURE
package com.selenium_hrm.ui.pages;

public class LoginPage extends BasePage {
    
    // ============ 1. STATIC CONSTANTS ============
    // Locators - organized by type
    private static final By TXT_USERNAME = By.id("username");
    private static final By TXT_PASSWORD = By.id("password");
    private static final By BTN_LOGIN = By.id("loginBtn");
    private static final By LBL_ERROR = By.cssSelector(".error-message");
    
    // Configuration
    private static final int DEFAULT_TIMEOUT = 30;
    private static final String DEFAULT_URL = "/dashboard";
    
    // ============ 2. INSTANCE VARIABLES ============
    private final LoginActions actions;
    private final LoginPageElements elements;
    
    // ============ 3. CONSTRUCTORS ============
    public LoginPage() {
        this.elements = new LoginPageElements();
        this.actions = new LoginActions();
    }
    
    // ============ 4. PUBLIC METHODS ============
    // High-level business operations
    public void login(String username, String password) {
        Log.info("Attempting login with username: " + username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        waitForLoginComplete();
    }
    
    public boolean isLoginSuccessful() {
        return isElementDisplayed(LBL_WELCOME);
    }
    
    public String getErrorMessage() {
        if (isElementDisplayed(LBL_ERROR)) {
            return getText(LBL_ERROR);
        }
        return null;
    }
    
    // ============ 5. PRIVATE METHODS ============
    // Low-level element interactions
    private void enterUsername(String username) {
        waitForElementVisible(TXT_USERNAME);
        setText(TXT_USERNAME, username);
    }
    
    private void enterPassword(String password) {
        waitForElementVisible(TXT_PASSWORD);
        setText(TXT_PASSWORD, password);
    }
    
    private void clickLoginButton() {
        waitForElementClickable(BTN_LOGIN);
        click(BTN_LOGIN);
    }
    
    private void waitForLoginComplete() {
        WaitHelper.waitForUrlContains(DEFAULT_URL);
    }
}
```

### 4.2 Class Size

```java
// ✅ RECOMMENDED: Classes should be focused
// Max ~300-500 lines per class
// If more, consider splitting

// ❌ VIOLATION: God Class - too many responsibilities
public class EmployeePage {
    // 1000+ lines handling login, employee, leave, payroll...
}

// ✅ FIX: Split into focused classes
public class LoginPage { /* Login related */ }
public class EmployeePage { /* Employee related */ }
public class LeavePage { /* Leave related */ }
public class PayrollPage { /* Payroll related */ }
```

### 4.3 Method Size

```java
// ✅ RECOMMENDED: Methods should be small and focused
// Max ~30-50 lines per method

// ❌ VIOLATION: Long method
public void processEmployee(String name, String email, String dept) {
    // 100 lines doing everything
}

// ✅ FIX: Small, focused methods
public void processEmployee(EmployeeData data) {
    validateData(data);
    createEmployeeRecord(data);
    sendNotification(data);
    logCompletion(data);
}

private void validateData(EmployeeData data) {
    if (data.getName() == null || data.getName().isEmpty()) {
        throw new InvalidDataException("Name is required");
    }
    validateEmail(data.getEmail());
    validateDepartment(data.getDepartment());
}
```

---

## 5. Method Design

### 5.1 Method Naming

```java
// ✅ ĐÚNG: Verb + Object/Result pattern

// Actions
public void clickLoginButton() { }
public void enterUsername(String username) { }
public void selectRoleFromDropdown(String role) { }
public void uploadDocument(String filePath) { }
public void scrollToElement(By locator) { }

// Queries
public boolean isElementDisplayed(By locator) { }
public String getText(By locator) { }
public int getElementCount(By locator) { }
public boolean isPageLoaded() { }

// Business operations
public void login(String username, String password) { }
public Employee createEmployee(EmployeeData data) { }
public void approveLeaveRequest(String requestId) { }
public void generatePayroll(int month, int year) { }

// ❌ SAI: Unclear names
public void login() { }  // What to login?
public void click() { }  // Click what?
public void submit() { }  // Submit what?
public void process() { }  // Process what?
```

### 5.2 Method Parameters

```java
// ✅ ĐÚNG: Max 3-4 parameters, use objects for more
public void createEmployee(String name, String email, String dept, String position) {
    // OK for 4 parameters
}

// ✅ ĐÚNG: Use DTO/Model for many parameters
public void createEmployee(EmployeeRequest request) {
    String name = request.getName();
    String email = request.getEmail();
    // ...
}

// ❌ SAI: Too many parameters
public void createEmployee(String name, String email, String phone, 
    String address, String city, String state, String zip, 
    String department, String position, String manager, double salary) {
    // ❌ Too many!
}

// ✅ FIX: Use request object
public void createEmployee(EmployeeRequest request) {
    // ✅ Clean
}

public class EmployeeRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String department;
    private String position;
    private String manager;
    private double salary;
    // Getters and setters
}
```

### 5.3 Return Values

```java
// ✅ ĐÚNG: Return appropriate types

// Return boolean for queries
public boolean isLoggedIn() {
    return isElementDisplayed(LBL_WELCOME_USER);
}

// Return object for lookups
public Employee getEmployeeById(String id) {
    return employeeRepository.findById(id);
}

// Return list for collections
public List<Employee> searchEmployees(String name) {
    return employeeService.searchByName(name);
}

// Return this for method chaining
public LoginPage enterUsername(String username) {
    setText(TXT_USERNAME, username);
    return this;
}

public LoginPage enterPassword(String password) {
    setText(TXT_PASSWORD, password);
    return this;
}

public LoginPage clickLogin() {
    click(BTN_LOGIN);
    return this;
}

// Usage - fluent API
public DashboardPage loginAsAdmin() {
    return new LoginPage()
        .enterUsername("admin")
        .enterPassword("admin123")
        .clickLogin();
}

// ❌ SAI: Returning void for query methods
public void isLoggedIn() {  // ❌ Should return boolean
    return isElementDisplayed(LBL_WELCOME);
}
```

---

## 6. Selenium Best Practices

### 6.1 WebDriver Usage

```java
// ✅ ĐÚNG: Use DriverManager consistently
public class BasePage {
    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}

// ✅ ĐÚNG: Always use helper methods, not raw WebDriver
public void click(By locator) {
    waitForElementClickable(locator);
    getDriver().findElement(locator).click();
}

public void setText(By locator, String text) {
    waitForElementVisible(locator);
    WebElement element = getDriver().findElement(locator);
    element.clear();
    element.sendKeys(text);
}

// ❌ SAI: Direct WebDriver usage without waits
public void click(By locator) {
    getDriver().findElement(locator).click();  // ❌ Race condition
}

public void setText(By locator, String text) {
    getDriver().findElement(locator).sendKeys(text);  // ❌ No clear, no wait
}
```

### 6.2 Element Interaction

```java
// ✅ ĐÚNG: Complete interaction pattern
public void typeInField(By locator, String text) {
    waitForElementVisible(locator);
    WebElement element = getDriver().findElement(locator);
    element.clear();
    element.sendKeys(text);
    Log.debug("Typed '" + text + "' in " + locator);
}

// ✅ ĐÚNG: Click with JavaScript fallback
public void click(By locator) {
    try {
        waitForElementClickable(locator);
        getDriver().findElement(locator).click();
    } catch (ElementClickInterceptedException e) {
        Log.warn("Standard click failed, trying JavaScript click");
        jsClick(locator);
    }
}

private void jsClick(By locator) {
    WebElement element = getDriver().findElement(locator);
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("arguments[0].click();", element);
}

// ✅ ĐÚNG: Hover and drag-drop
public void hoverOverElement(By locator) {
    Actions actions = new Actions(getDriver());
    WebElement element = getDriver().findElement(locator);
    actions.moveToElement(element).perform();
}

public void dragAndDrop(By source, By target) {
    Actions actions = new Actions(getDriver());
    WebElement sourceElement = getDriver().findElement(source);
    WebElement targetElement = getDriver().findElement(target);
    actions.dragAndDrop(sourceElement, targetElement).perform();
}

// ❌ SAI: Incomplete interactions
public void typeInField(By locator, String text) {
    getDriver().findElement(locator).sendKeys(text);  // ❌ No clear
}

public void click(By locator) {
    getDriver().findElement(locator).click();  // ❌ No wait, no fallback
}
```

### 6.3 Page Navigation

```java
// ✅ ĐÚNG: Proper page navigation
public class NavigationHelper {
    
    public void navigateTo(String url) {
        Log.info("Navigating to: " + url);
        getDriver().navigate().to(url);
        waitForPageLoad();
        Log.info("Page loaded: " + getDriver().getCurrentUrl());
    }
    
    public void navigateBack() {
        Log.info("Navigating back");
        getDriver().navigate().back();
        waitForPageLoad();
    }
    
    public void navigateForward() {
        Log.info("Navigating forward");
        getDriver().navigate().forward();
        waitForPageLoad();
    }
    
    public void refreshPage() {
        Log.info("Refreshing page");
        getDriver().navigate().refresh();
        waitForPageLoad();
    }
    
    private void waitForPageLoad() {
        WaitHelper.waitForPageLoaded();
    }
}

// ✅ ĐÚNG: Fluent page navigation
public LoginPage navigateToLoginPage() {
    navigateTo(ConfigHelper.getLoginUrl());
    return new LoginPage();
}

public DashboardPage navigateToDashboard() {
    navigateTo(ConfigHelper.getDashboardUrl());
    return new DashboardPage();
}
```

### 6.4 Handling Multiple Elements

```java
// ✅ ĐÚNG: Handling multiple elements
public List<WebElement> findElements(By locator) {
    return getDriver().findElements(locator);
}

public int getElementCount(By locator) {
    List<WebElement> elements = findElements(locator);
    Log.debug("Found " + elements.size() + " elements for " + locator);
    return elements.size();
}

public List<String> getAllEmployeeNames() {
    List<WebElement> rows = getDriver().findElements(By.xpath("//table//tr"));
    List<String> names = new ArrayList<>();
    
    for (WebElement row : rows) {
        String name = row.findElement(By.xpath(".//td[2]")).getText();
        names.add(name);
    }
    
    Log.info("Retrieved " + names.size() + " employee names");
    return names;
}

public void clickNthElement(By locator, int index) {
    List<WebElement> elements = getDriver().findElements(locator);
    if (index >= 0 && index < elements.size()) {
        elements.get(index).click();
        Log.info("Clicked element at index " + index);
    } else {
        throw new IndexOutOfBoundsException(
            "Element index " + index + " out of range for " + locator);
    }
}

// ❌ SAI: No error handling
public void clickNthElement(By locator, int index) {
    List<WebElement> elements = getDriver().findElements(locator);
    elements.get(index).click();  // ❌ No bounds check
}
```

---

## 7. Error Handling

### 7.1 Exception Types

```java
// ✅ ĐÚNG: Specific exception hierarchy

// Custom exceptions
public class AutomationException extends RuntimeException {
    public AutomationException(String message) {
        super(message);
    }
    
    public AutomationException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class ElementNotFoundException extends AutomationException {
    private final By locator;
    
    public ElementNotFoundException(By locator) {
        super("Element not found: " + locator);
        this.locator = locator;
    }
    
    public ElementNotFoundException(By locator, Throwable cause) {
        super("Element not found: " + locator, cause);
        this.locator = locator;
    }
    
    public By getLocator() {
        return locator;
    }
}

public class PageLoadException extends AutomationException {
    private final String pageName;
    
    public PageLoadException(String pageName) {
        super("Failed to load page: " + pageName);
        this.pageName = pageName;
    }
    
    public PageLoadException(String pageName, Throwable cause) {
        super("Failed to load page: " + pageName, cause);
        this.pageName = pageName;
    }
}

public class InvalidDataException extends AutomationException {
    public InvalidDataException(String field, String reason) {
        super(String.format("Invalid data for '%s': %s", field, reason));
    }
}
```

### 7.2 Try-Catch Patterns

```java
// ✅ ĐÚNG: Proper exception handling
public void safeClick(By locator) {
    try {
        waitForElementClickable(locator);
        getDriver().findElement(locator).click();
        Log.debug("Successfully clicked: " + locator);
    } catch (NoSuchElementException e) {
        Log.error("Element not found: " + locator);
        throw new ElementNotFoundException(locator, e);
    } catch (ElementClickInterceptedException e) {
        Log.warn("Click intercepted, retrying with JS: " + locator);
        jsClick(locator);
    } catch (Exception e) {
        Log.error("Unexpected error clicking: " + locator, e);
        throw new AutomationException("Failed to click element", e);
    }
}

// ✅ ĐÚNG: Try-with-resources
public void processWithResource(Path file) {
    try (BufferedReader reader = Files.newBufferedReader(file)) {
        String line;
        while ((line = reader.readLine()) != null) {
            processLine(line);
        }
    } catch (IOException e) {
        Log.error("Error processing file: " + file, e);
        throw new AutomationException("File processing failed", e);
    }
}

// ❌ SAI: Empty catch blocks
try {
    doSomething();
} catch (Exception e) {
    // ❌ Empty - hiding errors!
}

// ❌ SAI: Catching generic Exception
try {
    doSomething();
} catch (Exception e) {  // ❌ Too generic
    Log.error(e.getMessage());
}
```

### 7.3 Graceful Degradation

```java
// ✅ ĐÚNG: Graceful degradation with fallbacks
public void uploadFile(By locator, String filePath) {
    try {
        // Try standard upload first
        waitForElementVisible(locator);
        getDriver().findElement(locator).sendKeys(filePath);
        Log.info("File uploaded successfully: " + filePath);
    } catch (Exception e) {
        Log.warn("Standard upload failed, trying JavaScript: " + e.getMessage());
        try {
            // Fallback to JavaScript upload
            jsUploadFile(locator, filePath);
        } catch (Exception jsError) {
            Log.error("JavaScript upload also failed", jsError);
            throw new AutomationException("Upload failed with all methods", e);
        }
    }
}

// ✅ ĐÚNG: Retry pattern
public WebElement findElementWithRetry(By locator, int maxRetries) {
    int attempts = 0;
    while (attempts < maxRetries) {
        try {
            waitForElementPresent(locator);
            return getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            attempts++;
            Log.warn("Element not found, attempt " + attempts + "/" + maxRetries);
            if (attempts >= maxRetries) {
                throw new ElementNotFoundException(locator, e);
            }
            sleep(1000); // Wait before retry
        }
    }
    throw new ElementNotFoundException(locator);
}
```

---

## 8. Logging Standards

### 8.1 Log Levels

```java
// ✅ TRACE: Very detailed, for debugging complex issues
Log.trace("Entering method with params: " + param1 + ", " + param2);
Log.trace("Loop iteration: " + i + ", current element: " + element);

// ✅ DEBUG: Debug information, development
Log.debug("Clicking element: " + locator);
Log.debug("Page title: " + pageTitle);
Log.debug("Element found at: " + element.getLocation());
Log.debug("Retrieved " + items.size() + " items from database");

// ✅ INFO: Important milestones, business events
Log.info("Test started: " + testName);
Log.info("User logged in: " + username);
Log.info("Employee created with ID: " + employeeId);
Log.info("Leave request approved: " + requestId);
Log.info("Navigating to page: " + pageName);

// ✅ WARN: Non-critical issues, potential problems
Log.warn("Element not found on first attempt, retrying...");
Log.warn("Page load took longer than expected: " + loadTime + "ms");
Log.warn("Test data file not found, using default values");
Log.warn("API response time exceeded threshold: " + responseTime + "ms");

// ✅ ERROR: Failures, exceptions
Log.error("Login failed: " + exception.getMessage());
Log.error("Element not found after " + MAX_ATTEMPTS + " attempts");
Log.error("API response error: " + response.getStatusCode());
Log.error("Unexpected error in test", exception);

// ✅ FATAL: Critical failures, system crashes
Log.fatal("Database connection lost");
Log.fatal("WebDriver crashed, cannot continue");
```

### 8.2 Log Messages

```java
// ✅ ĐÚNG: Descriptive log messages with context
Log.info("Attempting to login with username: " + username + 
         ", environment: " + ConfigHelper.getEnv());

Log.debug("Creating employee with name: " + name + 
          ", email: " + email + 
          ", department: " + department);

Log.warn("Retrying click on element: " + locator + 
         " (attempt " + attempt + "/" + maxRetries + ")");

Log.error("Failed to find element after " + maxRetries + 
          " attempts. Locator: " + locator);

// ✅ ĐÚNG: Log parameterization (avoid string concatenation in production)
Log.info("Processing order {} for customer {}", orderId, customerName);
Log.debug("Element {} is {} at {}", locator, state, position);

// ❌ SAI: Uninformative logs
Log.info("Processing");
Log.debug("Clicked");
Log.error("Failed");
```

### 8.3 Log Placement

```java
// ✅ ĐÚNG: Log at key points
@Test
public void testCreateEmployee() {
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
        assertNotNull(response.getId(), "Employee ID should not be null");
        assertEquals(response.getName(), request.getName(), 
            "Employee name should match");
        Log.info("Employee verification passed");
        
        // Cleanup
        Log.info("Cleaning up test data");
        employeeService.delete(response.getId());
        
        Log.info("=== testCreateEmployee PASSED ===");
        
    } catch (Exception e) {
        Log.error("=== testCreateEmployee FAILED ===", e);
        CaptureHelper.captureScreenshot("testCreateEmployee_failed");
        throw e;
    }
}

// ❌ SAI: No logs or excessive logs
@Test
public void testCreateEmployee() {
    // ❌ No logs
    employeeService.create(request);
    assertNotNull(response.getId());
}

@Test
public void testCreateEmployee() {
    // ❌ Over-logging
    Log.info("Step 1: Creating request");
    EmployeeRequest request = new EmployeeRequest();
    Log.info("Step 2: Setting name");
    request.setName(name);
    Log.info("Step 3: Setting email");
    request.setEmail(email);
    Log.info("Step 4: Calling create");
    response = employeeService.create(request);
    // ... every single line is logged
}
```

---

## 9. Comments & Documentation

### 9.1 When to Comment

```java
// ✅ TỐT: Explain WHY, not WHAT
// Using Thread.sleep here because the animation takes exactly 1.5s
// and we need to wait for it to complete before checking the result
Thread.sleep(1500);

// Retry logic for flaky network calls
// Sometimes the API returns 503 during deployment
int maxRetries = 3;
for (int i = 0; i < maxRetries; i++) {
    try {
        return api.call();
    } catch (ServiceUnavailableException e) {
        if (i == maxRetries - 1) throw e;
        Thread.sleep(1000 * (i + 1)); // Exponential backoff
    }
}

// ❌ BAD: Explain WHAT (code already shows this)
// Create a new employee object
Employee emp = new Employee();

// Loop through all employees
for (Employee emp : employees) {
    process(emp);
}
```

### 9.2 Javadoc Format

```java
/**
 * Represents a login page object with methods to interact with login elements.
 * 
 * <p>This class provides methods for:</p>
 * <ul>
 *   <li>Entering user credentials</li>
 *   <li>Clicking login button</li>
 *   <li>Verifying login status</li>
 * </ul>
 * 
 * <p><b>Usage:</b></p>
 * <pre>{@code
 * LoginPage loginPage = new LoginPage();
 * loginPage.login("admin", "password123");
 * }</pre>
 * 
 * @author QA Team
 * @version 1.0
 * @since 2026-01-01
 */
public class LoginPage extends BasePage {
    
    /**
     * Performs login with the given credentials.
     * 
     * <p>This method:</p>
     * <ol>
     *   <li>Enters the username</li>
     *   <li>Enters the password</li>
     *   <li>Clicks the login button</li>
     *   <li>Waits for navigation to complete</li>
     * </ol>
     * 
     * @param username The username to login with
     * @param password The password for the user
     * @throws ElementNotFoundException if login elements are not found
     * @throws InvalidDataException if credentials are invalid
     * @see #isLoginSuccessful()
     */
    public void login(String username, String password) {
        // Implementation
    }
}
```

### 9.3 TODO Comments

```java
// ✅ ĐÚNG: TODO với description rõ ràng
// TODO(username): Refactor after API v2 is released
// TODO(username): Add more test cases for edge scenarios
// FIXME(username): Flaky on Firefox, investigate
// HACK(username): Temporary workaround for known bug HRM-1234

// ❌ SAI: Vague TODOs
// TODO: Fix this
// FIXME
// HACK
```

---

## 10. Testing Code Quality

### 10.1 Test Class Structure

```java
// ✅ STANDARD TEST CLASS
@Listeners(TestListener.class)
@Test(groups = {"login", "smoke", "critical"})
public class LoginTests extends BaseUI {
    
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    // ============ Data Providers ============
    @DataProvider
    public Object[][] validLoginData() {
        return new Object[][] {
            { "admin", "admin123" },
            { "manager", "manager123" },
            { "employee", "employee123" }
        };
    }
    
    @DataProvider
    public Object[][] invalidLoginData() {
        return new Object[][] {
            { "admin", "wrongpassword", "Invalid credentials" },
            { "invalid", "password", "User not found" },
            { "", "", "Username is required" }
        };
    }
    
    // ============ Setup ============
    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        navigateToLoginPage();
    }
    
    // ============ Tests ============
    @Test(priority = 1)
    public void testLoginPageLoaded() {
        Log.info("Verifying login page elements");
        assertTrue(loginPage.isLoginFormDisplayed(), 
            "Login form should be displayed");
        assertTrue(loginPage.isUsernameFieldVisible(), 
            "Username field should be visible");
        assertTrue(loginPage.isPasswordFieldVisible(), 
            "Password field should be visible");
        assertTrue(loginPage.isLoginButtonVisible(), 
            "Login button should be visible");
    }
    
    @Test(priority = 2, dataProvider = "validLoginData")
    public void testLogin_ValidCredentials(String username, String password) {
        Log.info("Testing login with valid credentials");
        loginPage.login(username, password);
        
        assertTrue(dashboardPage.isUserLoggedIn(), 
            "User should be logged in");
        assertEquals(dashboardPage.getCurrentUrl(), 
            ConfigHelper.getDashboardUrl(),
            "Should navigate to dashboard");
    }
    
    @Test(priority = 3, dataProvider = "invalidLoginData")
    public void testLogin_InvalidCredentials(
            String username, String password, String expectedError) {
        Log.info("Testing login with invalid credentials");
        loginPage.login(username, password);
        
        assertFalse(dashboardPage.isUserLoggedIn(), 
            "User should not be logged in");
        assertTrue(loginPage.isErrorDisplayed(), 
            "Error message should be displayed");
        assertEquals(loginPage.getErrorMessage(), expectedError,
            "Error message should match expected");
    }
    
    // ============ Cleanup ============
    @AfterMethod
    public void tearDown() {
        if (dashboardPage != null && dashboardPage.isUserLoggedIn()) {
            dashboardPage.logout();
        }
    }
}
```

### 10.2 Assertion Messages

```java
// ✅ ĐÚNG: Descriptive assertion messages
@Test
public void testEmployeeCreated() {
    EmployeeResponse response = employeeService.create(testEmployee);
    
    assertNotNull(response.getId(), 
        "Employee ID should not be null after creation");
    
    assertEquals(response.getName(), testEmployee.getName(), 
        "Created employee name should match input name");
    
    assertEquals(response.getEmail(), testEmployee.getEmail(), 
        "Created employee email should match input email");
    
    assertEquals(response.getDepartment(), testEmployee.getDepartment(), 
        "Created employee department should match input department");
    
    assertTrue(response.isActive(), 
        "Newly created employee should be active by default");
    
    assertNotNull(response.getCreatedAt(), 
        "Created employee should have creation timestamp");
    
    Log.info("Employee created successfully with ID: " + response.getId());
}

// ❌ SAI: Missing assertion messages
@Test
public void testEmployeeCreated() {
    EmployeeResponse response = employeeService.create(testEmployee);
    assertNotNull(response.getId());
    assertEquals(response.getName(), testEmployee.getName());
}
```

### 10.3 Test Independence

```java
// ✅ ĐÚNG: Independent tests with self-contained setup
@Test
public void testCreateEmployee_Success() {
    // ARRANGE: Create all needed test data within the test
    EmployeeRequest request = EmployeeDataBuilder.createValidRequest();
    
    // ACT
    EmployeeResponse response = employeeService.create(request);
    
    // ASSERT
    assertNotNull(response.getId());
    assertEquals(response.getName(), request.getName());
    
    // CLEANUP: Remove created data
    employeeService.delete(response.getId());
}

@Test
public void testSearchEmployee_ByName() {
    // ARRANGE: Create employee for search
    EmployeeRequest request = EmployeeDataBuilder.createValidRequest();
    request.setName("UniqueName12345"); // Unique to avoid conflicts
    EmployeeResponse created = employeeService.create(request);
    
    // ACT
    List<EmployeeResponse> results = employeeService.searchByName("UniqueName12345");
    
    // ASSERT
    assertFalse(results.isEmpty(), "Should find at least one result");
    assertTrue(results.stream().anyMatch(e -> e.getId().equals(created.getId())),
        "Created employee should appear in search results");
    
    // CLEANUP
    employeeService.delete(created.getId());
}

// ❌ SAI: Tests depending on each other
@Test
public void testCreateEmployee() {
    // Creates employee, stores in static variable
    createdId = employeeService.create(request).getId();
}

@Test
public void testSearchEmployee() {
    // ❌ Assumes testCreateEmployee ran first
    // ❌ Will fail if run alone or in different order
    List<EmployeeResponse> results = employeeService.searchById(createdId);
}
```

---

## 11. Code Review Checklist

### 11.1 Code Quality Checklist

```markdown
## Code Quality Review Checklist

### Formatting & Style
- [ ] Consistent indentation (4 spaces)
- [ ] Line length < 120 characters
- [ ] Proper braces placement (K&R style)
- [ ] Blank lines between logical sections
- [ ] Organized imports (alphabetical, grouped)

### Naming Conventions
- [ ] Classes: PascalCase (e.g., LoginPage)
- [ ] Methods: camelCase, verb-object (e.g., clickLoginButton)
- [ ] Variables: camelCase, descriptive (e.g., employeeName)
- [ ] Constants: UPPER_SNAKE_CASE (e.g., MAX_RETRY_COUNT)
- [ ] Locators: PREFIX_ELEMENT (e.g., BTN_SUBMIT, TXT_USERNAME)

### Method Design
- [ ] Single responsibility
- [ ] Max ~30-50 lines
- [ ] Clear, descriptive name
- [ ] Appropriate parameters (max 3-4)
- [ ] Proper return type

### Error Handling
- [ ] Specific exception types
- [ ] Meaningful error messages
- [ ] Proper logging
- [ ] Cleanup in finally blocks

### Selenium Best Practices
- [ ] Uses helper methods (not raw WebDriver)
- [ ] Proper waits for all interactions
- [ ] Complete interaction pattern (wait, clear, action)
- [ ] No Thread.sleep (use WaitHelper)

### Testing Standards
- [ ] AAA pattern (Arrange-Act-Assert)
- [ ] Descriptive test names
- [ ] Meaningful assertion messages
- [ ] Independent tests (self-contained setup)
- [ ] Proper cleanup/teardown

### Documentation
- [ ] Javadoc for public classes/methods
- [ ] Clear comments for non-obvious code
- [ ] No TODO without description
- [ ] No commented-out code
```

### 11.2 Common Issues to Avoid

```java
// ❌ ISSUE 1: Hardcoded values
String url = "https://hrm.test.com";  // ❌
String url = ConfigHelper.getUrl();  // ✅

// ❌ ISSUE 2: Magic numbers
for (int i = 0; i < 27; i++) {  // ❌
    // What is 27?
}
for (int i = 0; i < MAX_RETRY_COUNT; i++) {  // ✅
    // Clear!
}

// ❌ ISSUE 3: Null checks everywhere
if (list != null && !list.isEmpty()) {
    // Process
}
// Better: Initialize properly, use Optional
List<String> list = new ArrayList<>();  // Never null
if (!list.isEmpty()) {  // Simple!
}

// ❌ ISSUE 4: Returning null
public Employee getEmployee(String id) {
    if (!exists(id)) {
        return null;  // ❌ Forces null checks
    }
    return find(id);
}
// Better: Throw exception or return Optional
public Optional<Employee> getEmployee(String id) {  // ✅
    return employeeRepository.findById(id);
}

// ❌ ISSUE 5: Suppressing warnings
@SuppressWarnings("unchecked")
public void legacyMethod() {  // ❌
    // Ignores important type safety warnings
}
```

---

## 12. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer | Initial coding standards |
