# Naming Conventions - Selenium HRM Automation

## 1. Introduction

### 1.1 Purpose
Naming conventions đảm bảo code nhất quán, dễ đọc và dễ bảo trì. Tài liệu này định nghĩa các quy tắc đặt tên cho tất cả components trong Selenium HRM automation framework.

### 1.2 Principles
- **Clarity**: Tên phải mô tả rõ ràng mục đích
- **Consistency**: Cùng pattern cho cùng loại elements
- **Simplicity**: Đơn giản nhưng đủ thông tin
- **Standardization**: Tuân thủ Java conventions + project-specific rules

---

## 2. Java Naming Conventions

### 2.1 Class Naming

```java
// ✅ ĐÚNG: PascalCase, descriptive nouns

// Page Objects
public class LoginPage { }
public class EmployeeManagementPage { }
public class LeaveRequestPage { }
public class DashboardPage { }
public class PayrollPage { }

// Elements (3-layer POM)
public class LoginPageElements { }
public class EmployeePageElements { }
public class LeavePageElements { }

// Actions (3-layer POM)
public class LoginActions { }
public class EmployeeActions { }
public class LeaveActions { }

// Base Classes
public abstract class BasePage { }
public class BaseUI { }
public class BaseAPI { }

// Helper Classes
public class ActionHelper { }
public class WaitHelper { }
public class ElementHelper { }
public class VerificationHelper { }
public class CaptureHelper { }
public class ExcelHelper { }
public class PropertiesHelper { }

// Test Classes
public class LoginTests { }
public class EmployeeTests { }
public class LeaveRequestTests { }
public class SmokeTests { }
public class RegressionTests { }
public class LoginUITests { }
public class EmployeeAPITests { }

// Config Classes
public class ConfigHelper { }
public class ConfigReader { }

// Factory Classes
public class DriverManager { }
public class DriverManagerFactory { }

// Listener Classes
public class TestListener { }
public class SuiteListener { }

// Exception Classes
public class ElementNotFoundException { }
public class PageLoadException { }
public class InvalidDataException { }

// ❌ SAI: Abbreviations, unclear
public class LgnPg { }
public class EMPg { }
public class Test1 { }
public class Utils { }
public class Helper { }
```

### 2.2 Interface Naming

```java
// ✅ ĐÚNG: PascalCase, often with 'I' prefix or descriptive noun

public interface PageObject { }
public interface Searchable { }
public interface Filterable { }
public interface Sortable { }
public interface Pageable { }
public interface Exportable { }

// ❌ SAI
public interface IPage { }
public interface Page { }
```

### 2.3 Enum Naming

```java
// ✅ ĐÚNG: PascalCase

public enum BrowserType {
    CHROME,
    FIREFOX,
    EDGE,
    SAFARI
}

public enum Environment {
    LOCAL,
    DEV,
    QA,
    STAGING,
    PRODUCTION
}

public enum TestStatus {
    PASSED,
    FAILED,
    SKIPPED,
    BROKEN
}

public enum LeaveType {
    ANNUAL,
    SICK,
    CASUAL,
    MATERNITY,
    PATERNITY,
    UNPAID
}

public enum EmployeeStatus {
    ACTIVE,
    INACTIVE,
    PROBATION,
    TERMINATED
}

public enum Gender {
    MALE,
    FEMALE,
    OTHER,
    PREFER_NOT_TO_SAY
}
```

### 2.4 Method Naming

```java
// ✅ ĐÚNG: camelCase, verb + object/noun

// Click actions
public void clickLoginButton() { }
public void clickSubmitButton() { }
public void clickAddEmployeeButton() { }
public void clickApproveButton() { }
public void clickDeleteIcon() { }

// Input actions
public void enterUsername(String username) { }
public void enterPassword(String password) { }
public void enterEmail(String email) { }
public void enterSearchTerm(String term) { }
public void typeInComment(String comment) { }

// Select actions
public void selectRole(String role) { }
public void selectDepartment(String department) { }
public void selectLeaveType(LeaveType type) { }
public void selectDate(String date) { }

// Navigation
public void navigateToLoginPage() { }
public void navigateToDashboard() { }
public void navigateToEmployeePage() { }
public void goBack() { }
public void goForward() { }
public void refresh() { }

// Getters (prefix: get/is/has/can)
public String getUsername() { }
public String getPageTitle() { }
public boolean isLoggedIn() { }
public boolean isElementDisplayed(By locator) { }
public boolean isButtonEnabled() { }
public boolean hasErrorMessage() { }
public boolean canSubmit() { }

// Setters
public void setUsername(String username) { }
public void setPassword(String password) { }

// Queries (return collection/object)
public List<WebElement> findAllEmployees() { }
public WebElement findEmployeeByName(String name) { }
public int getEmployeeCount() { }
public String getErrorMessage() { }
public String getCurrentUrl() { }

// Actions
public void login(String username, String password) { }
public void logout() { }
public void searchEmployee(String name) { }
public void filterByDepartment(String department) { }
public void sortByName() { }
public void exportToExcel() { }
public void uploadFile(String path) { }

// Wait
public void waitForPageLoad() { }
public void waitForElementVisible(By locator) { }
public void waitForAjaxComplete() { }
public void waitForUrlContains(String url) { }
public void waitForTextPresent(By locator, String text) { }

// Assert/Verify
public void assertPageTitle(String expected) { }
public void verifyElementPresent(By locator) { }
public void verifyTextEquals(String actual, String expected) { }

// ❌ SAI: Unclear, inconsistent
public void login() { }
public void clickBtn() { }
public void submit() { }
public void doLogin() { }
public void performClick() { }
```

### 2.5 Variable Naming

```java
// ✅ ĐÚNG: camelCase, descriptive

// Primitives
private String username;
private String password;
private String emailAddress;
private String firstName;
private String lastName;
private int age;
private double salary;
private boolean isActive;
private int employeeCount;

// Objects
private WebDriver driver;
private WebElement element;
private LoginPage loginPage;
private EmployeePage employeePage;
private List<WebElement> elements;
private Map<String, String> dataMap;
private Set<String> allowedRoles;
private Properties configProperties;

// Collections
private List<String> employeeNames;
private List<Employee> employeeList;
private Map<String, Employee> employeeMap;
private Set<String> selectedIds;
private List<WebElement> tableRows;
private List<Map<String, String>> testDataRows;

// Constants (values don't change)
private static final int DEFAULT_TIMEOUT = 30;
private static final int MAX_RETRY = 3;
private static final String DEFAULT_URL = "/dashboard";
private static final By BTN_SUBMIT = By.id("submitBtn");

// Enums
private BrowserType browser;
private Environment environment;
private LeaveType leaveType;
private EmployeeStatus status;

// Page Objects
private LoginPage loginPage;
private DashboardPage dashboardPage;
private EmployeePage employeePage;

// Test data
private String validUsername;
private String invalidPassword;
private Employee testEmployee;
private LeaveRequest testLeaveRequest;

// ❌ SAI: Single letters, Hungarian notation
private String s;
private String sUser;
private int i;
private int nCount;
private WebDriver d;
private boolean bFlag;
private String strName;
private int intAge;
```

### 2.6 Constant Naming

```java
// ✅ ĐÚNG: UPPER_SNAKE_CASE

// Configuration
private static final String CONFIG_FILE_PATH = "src/test/resources/config/";
private static final String TEST_DATA_PATH = "src/test/resources/testdata/";
private static final String REPORT_PATH = "extentReports/";
private static final String SCREENSHOT_PATH = "screenshots/";

// Timeouts
private static final int DEFAULT_TIMEOUT = 30;
private static final int PAGE_LOAD_TIMEOUT = 60;
private static final int IMPLICIT_WAIT = 20;
private static final int EXPLICIT_WAIT = 30;
private static final int POLLING_INTERVAL = 500; // milliseconds

// URLs
private static final String BASE_URL = "https://hrm.example.com";
private static final String LOGIN_URL = BASE_URL + "/login";
private static final String DASHBOARD_URL = BASE_URL + "/dashboard";
private static final String API_BASE_URL = "https://api.hrm.example.com";

// Credentials
private static final String DEFAULT_USERNAME = "admin";
private static final String DEFAULT_PASSWORD = "admin123";
private static final String ADMIN_USERNAME = "admin";
private static final String ADMIN_PASSWORD = "Admin@123";

// API Endpoints
private static final String API_LOGIN = "/auth/login";
private static final String API_EMPLOYEES = "/api/v1/employees";
private static final String API_LEAVE = "/api/v1/leave";

// Test data
private static final String VALID_EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
private static final int MIN_PASSWORD_LENGTH = 8;
private static final int MAX_PASSWORD_LENGTH = 20;

// ❌ SAI: Not uppercase, unclear
private static final String configfile = "config.properties";
private static final int timeout = 30;
private static final String url = "https://example.com";
```

### 2.7 Parameter Naming

```java
// ✅ ĐÚNG: camelCase, descriptive

public void login(String username, String password) { }
public void createEmployee(String firstName, String lastName, String email) { }
public void searchEmployee(String searchTerm) { }
public void filterByDepartment(String departmentName) { }
public void selectDateRange(LocalDate startDate, LocalDate endDate) { }
public void uploadFile(String filePath, String fileName) { }
public List<Employee> getEmployeesByStatus(EmployeeStatus status) { }
public void assertElementText(By locator, String expectedText) { }

// ❌ SAI: Single letters, unclear
public void login(String u, String p) { }
public void create(String n, String e) { }
public void search(String s) { }
```

---

## 3. Selenium-Specific Naming

### 3.1 Locator Naming

```java
// ✅ ĐÚNG: PREFIX_ELEMENT_DESCRIPTION (all caps with underscore)

// Text/Input fields (TXT)
private static final By TXT_USERNAME = By.id("username");
private static final By TXT_PASSWORD = By.id("password");
private static final By TXT_EMAIL = By.id("email");
private static final By TXT_FIRST_NAME = By.id("firstName");
private static final By TXT_SEARCH = By.cssSelector("[data-testid='search-input']");

// Buttons (BTN)
private static final By BTN_LOGIN = By.id("loginBtn");
private static final By BTN_SUBMIT = By.cssSelector("[data-testid='submit-btn']");
private static final By BTN_SAVE = By.xpath("//button[contains(text(),'Save')]");
private static final By BTN_CANCEL = By.xpath("//button[contains(text(),'Cancel')]");
private static final By BTN_ADD = By.cssSelector("[data-testid='add-btn']");
private static final By BTN_DELETE = By.cssSelector("[data-testid='delete-btn']");
private static final By BTN_EDIT = By.cssSelector("[data-testid='edit-btn']");
private static final By BTN_APPROVE = By.cssSelector("[data-testid='approve-btn']");
private static final By BTN_REJECT = By.cssSelector("[data-testid='reject-btn']");
private static final By BTN_CLOSE = By.cssSelector("[data-testid='close-btn']");
private static final By BTN_NEXT = By.cssSelector("[data-testid='next-btn']");
private static final By BTN_PREVIOUS = By.cssSelector("[data-testid='prev-btn']");
private static final By BTN_SEARCH = By.id("searchBtn");
private static final By BTN_FILTER = By.id("filterBtn");
private static final By BTN_CLEAR = By.id("clearBtn");
private static final By BTN_RESET = By.id("resetBtn");
private static final By BTN_EXPORT = By.id("exportBtn");
private static final By BTN_DOWNLOAD = By.id("downloadBtn");
private static final By BTN_UPLOAD = By.id("uploadBtn");

// Links (LNK)
private static final By LNK_LOGOUT = By.linkText("Logout");
private static final By LNK_FORGOT_PASSWORD = By.linkText("Forgot Password?");
private static final By LNK_REGISTER = By.linkText("Register");
private static final By LNK_HOME = By.linkText("Home");
private static final By LNK_PROFILE = By.linkText("My Profile");
private static final By LNK_SETTINGS = By.linkText("Settings");
private static final By LNK_HELP = By.linkText("Help");
private static final By LNK_BACK = By.cssSelector("[data-testid='back-link']");

// Labels/Text (LBL)
private static final By LBL_WELCOME = By.id("welcomeMessage");
private static final By LBL_ERROR = By.cssSelector(".error-message");
private static final By LBL_SUCCESS = By.cssSelector(".success-message");
private static final By LBL_WARNING = By.cssSelector(".warning-message");
private static final By LBL_INFO = By.cssSelector(".info-message");
private static final By LBL_PAGE_TITLE = By.xpath("//h1");
private static final By LBL_SUBTITLE = By.xpath("//h2");
private static final By LBL_EMPLOYEE_NAME = By.xpath("//span[@class='employee-name']");
private static final By LBL_EMPLOYEE_COUNT = By.id("employeeCount");
private static final By LBL_TOTAL_RECORDS = By.cssSelector(".total-records");
private static final By LBL_PAGINATION_INFO = By.cssSelector(".pagination-info");

// Checkbox (CHK)
private static final By CHK_REMEMBER_ME = By.name("rememberMe");
private static final By CHK_SELECT_ALL = By.id("selectAll");
private static final By CHK_ACTIVE = By.name("active");
private static final By CHK_TERMS = By.id("acceptTerms");

// Radio buttons (RAD)
private static final By RAD_MALE = By.id("genderMale");
private static final By RAD_FEMALE = By.id("genderFemale");
private static final By RAD_FULL_TIME = By.id("employmentTypeFullTime");
private static final By RAD_PART_TIME = By.id("employmentTypePartTime");

// Dropdown/Select (DDL, SEL)
private static final By DDL_DEPARTMENT = By.id("departmentSelect");
private static final By DDL_POSITION = By.id("positionSelect");
private static final By DDL_ROLE = By.id("roleSelect");
private static final By DDL_STATUS = By.id("statusSelect");
private static final By DDL_LEAVE_TYPE = By.id("leaveTypeSelect");
private static final By DDL_YEAR = By.id("yearSelect");
private static final By DDL_MONTH = By.id("monthSelect");
private static final By DDL_COUNTRY = By.id("countrySelect");
private static final By DDL_STATE = By.id("stateSelect");

// Tables (TBL, ROW, COL)
private static final By TBL_EMPLOYEE_LIST = By.id("employeeTable");
private static final By TBL_LEAVE_REQUESTS = By.id("leaveTable");
private static final By TBL_PAYROLL = By.id("payrollTable");
private static final By ROW_TABLE_HEADER = By.cssSelector("table thead th");
private static final By ROW_TABLE_BODY = By.cssSelector("table tbody tr");
private static final By ROW_FIRST = By.xpath("//table//tr[1]");
private static final By ROW_LAST = By.xpath("//table//tr[last()]");
private static final By COL_NAME = By.xpath("//table//th[contains(text(),'Name')]");
private static final By COL_EMAIL = By.xpath("//table//th[contains(text(),'Email')]");

// Forms (FRM)
private static final By FRM_LOGIN = By.id("loginForm");
private static final By FRM_EMPLOYEE = By.id("employeeForm");
private static final By FRM_LEAVE_REQUEST = By.id("leaveRequestForm");
private static final By FRM_SEARCH = By.id("searchForm");

// Modal/Dialog (MDL, DLG)
private static final By MDL_CONFIRM = By.cssSelector(".modal.confirm");
private static final By MDL_ALERT = By.cssSelector(".modal.alert");
private static final By DLG_CONFIRM_DELETE = By.xpath("//div[contains(@class,'modal')]//div[contains(text(),'Delete')]");
private static final By DLG_CONFIRMATION = By.xpath("//div[contains(@class,'modal')]//div[contains(text(),'Confirm')]");

// Menu (MNU)
private static final By MNU_MAIN = By.cssSelector(".main-menu");
private static final By MNU_SIDEBAR = By.cssSelector(".sidebar-menu");
private static final By MNU_DROPDOWN = By.cssSelector(".dropdown-menu");
private static final By MNU_CONTEXT = By.cssSelector(".context-menu");

// Calendar/Date (CAL, DT)
private static final By CAL_START_DATE = By.id("startDatePicker");
private static final By CAL_END_DATE = By.id("endDatePicker");
private static final By DT_TODAY = By.cssSelector(".datepicker .today");
private static final By DT_CALENDAR = By.cssSelector(".datepicker-calendar");

// Pagination (PAG)
private static final By PAG_PREVIOUS = By.cssSelector(".pagination .previous");
private static final By PAG_NEXT = By.cssSelector(".pagination .next");
private static final By PAG_FIRST = By.cssSelector(".pagination .first");
private static final By PAG_LAST = By.cssSelector(".pagination .last");
private static final By PAG_CURRENT = By.cssSelector(".pagination .active");

// Loading (LOD)
private static final By LOD_SPINNER = By.cssSelector(".spinner");
private static final By LOD_LOADING = By.cssSelector(".loading");
private static final By LOD_OVERLAY = By.cssSelector(".loading-overlay");
private static final By LOD_PROGRESS = By.cssSelector(".progress-bar");

// Messages (MSG)
private static final By MSG_SUCCESS = By.cssSelector(".alert-success");
private static final By MSG_ERROR = By.cssSelector(".alert-danger");
private static final By MSG_WARNING = By.cssSelector(".alert-warning");
private static final By MSG_INFO = By.cssSelector(".alert-info");
private static final By MSG_TOAST = By.cssSelector(".toast-message");

// File upload (FUL)
private static final By FUL_DOCUMENT = By.id("documentUpload");
private static final By FUL_IMAGE = By.id("imageUpload");
private static final By FUL_ATTACHMENT = By.id("attachmentUpload");

// Tabs (TAB)
private static final By TAB_GENERAL = By.xpath("//a[contains(text(),'General')]");
private static final By TAB_DETAILS = By.xpath("//a[contains(text(),'Details')]");
private static final By TAB_DOCUMENTS = By.xpath("//a[contains(text(),'Documents')]");
private static final By TAB_HISTORY = By.xpath("//a[contains(text(),'History')]");

// Breadcrumb (BCM)
private static final By BCM_NAVIGATION = By.cssSelector(".breadcrumb");
private static final By BCM_HOME = By.xpath("//nav[contains(@class,'breadcrumb')]//a[contains(text(),'Home')]");

// Tooltip (TIP)
private static final By TIP_HELP = By.cssSelector("[data-toggle='tooltip']");
private static final By TIP_INFO = By.cssSelector(".tooltip");

// Pagination page numbers
private static final By PAG_PAGE_1 = By.cssSelector(".pagination a[data-page='1']");
private static final By PAG_PAGE_2 = By.cssSelector(".pagination a[data-page='2']");

// ❌ SAI: Generic, unclear
private static final By username = By.id("username");
private static final By button = By.id("btn");
private static final By error = By.cssSelector(".error");
private static final By input = By.tagName("input");
```

### 3.2 Page Object Naming

```java
// ✅ ĐÚNG: PageName + Page/PageElements/PageActions

// Main page class
public class LoginPage extends BasePage { }
public class EmployeePage extends BasePage { }
public class LeavePage extends BasePage { }
public class DashboardPage extends BasePage { }

// Elements classes (3-layer POM)
public class LoginPageElements { }
public class EmployeePageElements { }
public class LeavePageElements { }

// Actions classes (3-layer POM)
public class LoginActions { }
public class EmployeeActions { }
public class LeaveActions { }

// ❌ SAI
public class Login { }
public class Elements { }
public class Page { }
```

### 3.3 Test Class Naming

```java
// ✅ ĐÚNG: FeatureName + Tests/Tests + Type

// Basic tests
public class LoginTests { }
public class EmployeeTests { }
public class LeaveTests { }
public class PayrollTests { }

// UI-specific tests
public class LoginUITests { }
public class EmployeeUITests { }
public class LeaveUITests { }

// API-specific tests
public class LoginAPITests { }
public class EmployeeAPITests { }
public class LeaveAPITests { }

// Type-specific tests
public class SmokeTests { }
public class SanityTests { }
public class RegressionTests { }
public class E2ETests { }
public class PerformanceTests { }
public class SecurityTests { }

// ❌ SAI
public class TestLogin { }
public class Login { }
public class Test1 { }
public class Tests { }
```

### 3.4 Test Method Naming

```java
// ✅ ĐÚNG: testFeature_Scenario_ExpectedResult

// Login tests
public void testLogin_WithValidCredentials_ShouldSucceed() { }
public void testLogin_WithInvalidPassword_ShouldShowError() { }
public void testLogin_WithEmptyUsername_ShouldShowValidation() { }
public void testLogin_WithUnregisteredUser_ShouldShowError() { }
public void testLogin_WithLockedAccount_ShouldShowError() { }
public void testLogin_RememberMe_ShouldPersistSession() { }
public void testLogout_ShouldClearSession() { }

// Employee tests
public void testEmployee_Create_WithValidData_ShouldSucceed() { }
public void testEmployee_Create_WithDuplicateEmail_ShouldShowError() { }
public void testEmployee_Create_WithMissingFields_ShouldShowValidation() { }
public void testEmployee_Edit_ShouldUpdateRecord() { }
public void testEmployee_Delete_ShouldRemoveRecord() { }
public void testEmployee_Search_ByName_ShouldReturnResults() { }
public void testEmployee_Search_NoResults_ShouldShowMessage() { }
public void testEmployee_Filter_ByDepartment_ShouldFilter() { }
public void testEmployee_Sort_ByName_ShouldSortCorrectly() { }
public void testEmployee_Pagination_ShouldNavigateCorrectly() { }

// Leave tests
public void testLeaveRequest_Create_WithValidData_ShouldSucceed() { }
public void testLeaveRequest_Approve_ShouldUpdateStatus() { }
public void testLeaveRequest_Reject_ShouldUpdateStatus() { }
public void testLeaveRequest_WithInsufficientBalance_ShouldShowError() { }
public void testLeaveRequest_OverlappingDates_ShouldShowWarning() { }
public void testLeaveBalance_ShouldCalculateCorrectly() { }

// ❌ SAI: Unclear, no pattern
public void testLogin() { }
public void loginSuccess() { }
public void test1() { }
public void createEmployee() { }
public void shouldWork() { }
```

### 3.5 Data Provider Naming

```java
// ✅ ĐÚNG: dataProvider + Description

@DataProvider(name = "validLoginData")
public Object[][] validLoginData() { }

@DataProvider(name = "invalidLoginData")
public Object[][] invalidLoginData() { }

@DataProvider(name = "employeeData")
public Object[][] employeeData() { }

@DataProvider(name = "leaveRequestData")
public Object[][] leaveRequestData() { }

@DataProvider(name = "boundaryValueData")
public Object[][] boundaryValueData() { }

@DataProvider(name = "paginationData")
public Object[][] paginationData() { }

// ❌ SAI
@DataProvider(name = "dp1")
public Object[][] dp1() { }

@DataProvider(name = "data")
public Object[][] data() { }
```

---

## 4. File Naming

### 4.1 Java Files

```java
// ✅ ĐÚNG: PascalCase, matches class name

LoginPage.java
EmployeePage.java
EmployeePageElements.java
EmployeePageActions.java
LoginTests.java
EmployeeTests.java
ActionHelper.java
WaitHelper.java
TestListener.java
DriverManager.java
ExtentReportManager.java

// ❌ SAI
loginpage.java
employee_page.java
test_login.java
Test.java
helper.java
```

### 4.2 Configuration Files

```java
// ✅ ĐÚNG: Descriptive, lowercase with hyphens

// Properties
config.properties
local.properties
qa.properties
staging.properties
production.properties

// Suite XML
suite-login.xml
suite-smoke.xml
suite-regression.xml
suite-full.xml

// Test Data
login-testdata.xlsx
employee-testdata.xlsx
leave-testdata.xlsx

// JSON
test-data.json
config.json
endpoints.json

// ❌ SAI
TestData.xlsx
data.xlsx
config.XML
LoginData.properties
```

### 4.3 Directory/Folder Naming

```java
// ✅ ĐÚNG: lowercase with hyphens or PascalCase for package-style

src/
├── main/
│   └── java/
│       └── com/
│           └── selenium_hrm/
│               ├── config/
│               ├── factory/
│               ├── listeners/
│               ├── utils/
│               └── pages/

src/
├── test/
│   ├── java/
│   │   └── com/
│   │       └── selenium_hrm/
│   │           ├── base/
│   │           ├── pages/
│   │           ├── components/
│   │           └── tests/
│   └── resources/
│       ├── config/
│       ├── suites/
│       └── testdata/

extent-reports/
screenshots/
logs/
allure-results/
test-output/

// ❌ SAI
src/main/java/com/selenium_hrm/PAGES/
src/Test/java/
TEST_DATA/
```

---

## 5. API Naming

### 5.1 Endpoint Naming

```java
// ✅ ĐÚNG: RESTful, lowercase, hyphenated

// Authentication
private static final String API_LOGIN = "/api/v1/auth/login";
private static final String API_LOGOUT = "/api/v1/auth/logout";
private static final String API_REFRESH_TOKEN = "/api/v1/auth/refresh";
private static final String API_FORGOT_PASSWORD = "/api/v1/auth/forgot-password";
private static final String API_RESET_PASSWORD = "/api/v1/auth/reset-password";

// Employees
private static final String API_EMPLOYEES = "/api/v1/employees";
private static final String API_EMPLOYEE_BY_ID = "/api/v1/employees/{id}";
private static final String API_EMPLOYEE_SEARCH = "/api/v1/employees/search";
private static final String API_EMPLOYEE_EXPORT = "/api/v1/employees/export";

// Leave
private static final String API_LEAVE_REQUESTS = "/api/v1/leave/requests";
private static final String API_LEAVE_BALANCE = "/api/v1/leave/balance";
private static final String API_LEAVE_APPROVE = "/api/v1/leave/requests/{id}/approve";
private static final String API_LEAVE_REJECT = "/api/v1/leave/requests/{id}/reject";

// Leave Types
private static final String API_LEAVE_TYPES = "/api/v1/leave/types";

// Departments
private static final String API_DEPARTMENTS = "/api/v1/departments";
private static final String API_DEPARTMENT_BY_ID = "/api/v1/departments/{id}";

// ❌ SAI
private static final String API_EMP = "/api/v1/GetEmployees";
private static final String API_EMPLOYEE_DATA = "/api/v1/getEmployeeData";
private static final String API_ACTION = "/api/v1/PerformAction";
```

### 5.2 HTTP Method Naming

```java
// ✅ ĐÚNG: Method names describe the operation

// GET requests
public Response getEmployees() { }
public Response getEmployeeById(String id) { }
public Response searchEmployees(String query) { }
public Response getLeaveBalance(String employeeId) { }
public Response getDepartments() { }

// POST requests
public Response createEmployee(EmployeeRequest request) { }
public Response login(LoginRequest request) { }
public Response submitLeaveRequest(LeaveRequest request) { }
public Response uploadDocument(File file) { }

// PUT requests
public Response updateEmployee(String id, EmployeeRequest request) { }
public Response updateProfile(ProfileRequest request) { }

// DELETE requests
public Response deleteEmployee(String id) { }
public Response cancelLeaveRequest(String id) { }

// PATCH requests
public Response partialUpdateEmployee(String id, Map<String, Object> updates) { }
public Response changePassword(PasswordChangeRequest request) { }
```

---

## 6. Database Naming (if applicable)

```java
// ✅ ĐÚNG: lowercase with underscores (SQL convention)

-- Tables
employees
leave_requests
leave_balances
departments
roles
permissions
audit_logs

-- Columns
employee_id
first_name
last_name
email_address
created_at
updated_at
is_active
leave_type
start_date
end_date

-- Foreign keys
fk_employee_department
fk_leave_employee
fk_employee_role

-- ❌ SAI
EmployeeTable
EmployeeData
EMP_ID
FirstName
CreateDate
IsActive
```

---

## 7. Test Data Naming

### 7.1 Excel File Structure

```
// ✅ ĐÚNG: Module + TestData.xlsx

LoginTestData.xlsx
├── ValidCredentials       // Sheet name
├── InvalidCredentials
└── EdgeCases

EmployeeTestData.xlsx
├── CreateEmployee
├── UpdateEmployee
├── DeleteEmployee
├── SearchEmployee
└── BoundaryData

LeaveTestData.xlsx
├── CreateLeaveRequest
├── ApproveLeave
├── RejectLeave
└── LeaveBalance

// ❌ SAI
TestData.xlsx
Data.xlsx
EmployeeData.xlsx
```

### 7.2 Data Column Naming

```excel
// ✅ ĐÚNG: Clear, descriptive column names

// LoginTestData
| username   | password      | expectedResult | expectedMessage          |
|------------|---------------|----------------|--------------------------|
| admin      | admin123      | SUCCESS        | Redirect to dashboard    |
| invalid    | wrongpass     | FAIL           | Invalid credentials      |
| empty      | empty         | FAIL           | Username is required     |

// EmployeeTestData
| firstName | lastName | email              | department | expectedResult |
|-----------|----------|--------------------|------------|----------------|
| John      | Doe      | john.doe@test.com  | Engineering| SUCCESS        |
| AB        | Test     | invalid-email      | Engineering| FAIL          |

// ❌ SAI
| field1 | field2 | field3 | result |
|--------|--------|--------|--------|
| val1   | val2   | val3   | res1   |
```

---

## 8. Log Message Naming

```java
// ✅ ĐÚNG: Descriptive, structured

// Test start/end
Log.info("=== Starting testLogin_WithValidCredentials_ShouldSucceed ===");
Log.info("=== testLogin_WithValidCredentials_ShouldSucceed PASSED ===");
Log.info("=== testLogin_WithValidCredentials_ShouldSucceed FAILED ===");

// Step logging
Log.info("Step 1: Navigating to login page");
Log.info("Step 2: Entering username");
Log.info("Step 3: Entering password");
Log.info("Step 4: Clicking login button");

// Action logging
Log.info("Login attempted with username: " + username);
Log.info("Employee created with ID: " + employeeId);
Log.info("Leave request submitted: " + requestId);

// Data logging
Log.info("Test data: " + testData.toString());
Log.info("API request payload: " + requestBody);

// Result logging
Log.info("Response status: " + response.getStatusCode());
Log.info("Response body: " + response.asString());

// ❌ SAI
Log.info("test");
Log.info("clicked");
Log.info("done");
Log.info("success");
```

---

## 9. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer | Initial naming conventions |
