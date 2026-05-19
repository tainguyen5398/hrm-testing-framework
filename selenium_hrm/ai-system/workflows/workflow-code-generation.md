# Workflow: Code Generation

## 1. Overview

### 1.1 Purpose
Workflow tạo code (Code Generation) là bước thứ tư trong QA process, thực hiện việc implement automation code từ test cases đã được thiết kế. Workflow này hướng dẫn QA Engineer implement code tuân thủ coding standards và framework rules để đảm bảo:

- **Standards Compliance**: 100% code tuân thủ coding standards
- **Framework Adherence**: Đúng POM structure và helper patterns
- **Quality Output**: Clean, maintainable, scalable code
- **Complete Coverage**: Tất cả test cases được implement
- **Documentation**: Đầy đủ comments và documentation

### 1.2 Objectives

| # | Objective | Success Criteria |
|---|-----------|-----------------|
| 1 | Complete Implementation | 100% test cases có automation code |
| 2 | Standards Compliance | 100% code pass linting/inspection |
| 3 | Framework Alignment | Đúng 3-layer POM structure |
| 4 | Test Data Ready | Tất cả test data files được tạo |
| 5 | Peer Reviewed | 100% code đã được review |

### 1.3 Workflow Position

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                     AI AUTOMATION PIPELINE - WORKFLOW 4                      │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   INPUT:                                                                    │
│   ├── testcases.md (từ Workflow 2: Test Case Design)                        │
│   ├── automation-plan.md (từ Workflow 3: Automation Planning)                │
│   ├── coding-standards.md (từ AI Context)                                   │
│   ├── framework-rules.md (từ AI Context)                                   │
│   └── naming-convention.md (từ AI Context)                                  │
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │                     CODE GENERATION                               │     │
│   │  ┌─────────────────────────────────────────────────────────┐   │     │
│   │  │ 1. Setup & Preparation                                 │   │     │
│   │  │ 2. Generate Page Objects                               │   │     │
│   │  │ 3. Generate Test Classes                               │   │     │
│   │  │ 4. Prepare Test Data                                  │   │     │
│   │  │ 5. Implement API Tests (if applicable)                 │   │     │
│   │  │ 6. Run & Validate                                     │   │     │
│   │  │ 7. Code Review & Refine                              │   │     │
│   │  └─────────────────────────────────────────────────────────┘   │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│                                                                             │
│   OUTPUT:                                                                   │
│   ├── Page Objects (Elements, Actions, Page classes)                        │
│   ├── Test Classes (UI + API tests)                                         │
│   ├── Test Data Files (Excel, JSON, Properties)                             │
│   └── Generated Source Code (Java files)                                      │
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │              ▶ NEXT WORKFLOW: Code Review                        │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 2. Inputs & Prerequisites

### 2.1 Required Inputs

| # | Input | Format | Source | Priority |
|---|-------|--------|--------|----------|
| 1 | Test Case Suite | Markdown/Excel | Workflow 2: Test Case Design | Required |
| 2 | Automation Plan | Markdown | Workflow 3: Automation Planning | Required |
| 3 | Coding Standards | Document | AI Context | Required |
| 4 | Framework Rules | Document | AI Context | Required |
| 5 | Naming Convention | Document | AI Context | Required |
| 6 | Locator Strategy | Document | AI Context | Required |
| 7 | Test Data Files | Excel/JSON | Workflow 2: Test Case Design | Required |

### 2.2 Prerequisites Checklist

```
PRE-WORKFLOW CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

□ Development environment configured (Java 21, Maven)
□ IDE configured (IntelliJ/Eclipse with formatting)
□ Framework skeleton created (BaseUI, BasePage, Helpers)
□ Test data files prepared
□ Locators identified and documented
□ Module-by-module implementation order planned

```

---

## 3. Step 1: Setup & Preparation

### 3.1 Project Structure Verification

```markdown
## Project Structure Verification

Verify the following structure exists before generating code:

src/
├── main/
│   └── java/
│       └── com/selenium_hrm/
│           ├── config/
│           │   ├── ConfigHelper.java
│           │   ├── logs/Log.java
│           │   ├── media/CaptureHelper.java
│           │   ├── reports/
│           │   └── testdata/
│           ├── factory/DriverManager.java
│           ├── listeners/TestListener.java
│           └── utils/
│               ├── ActionHelper.java
│               ├── ElementHelper.java
│               ├── WaitHelper.java
│               └── VerificationHelper.java
│
├── test/
│   ├── java/
│   │   └── com/selenium_hrm/ui/
│   │       ├── base/
│   │       │   ├── BaseUI.java
│   │       │   └── BasePage.java
│   │       └── pages/
│   └── resources/
│       ├── config/
│       ├── suites/
│       └── testdata/

```

### 3.2 Implementation Planning

```markdown
## Implementation Order (by Priority)

### Sprint 1: Foundation + Authentication

┌────────────────────────────────────────────────────────────────────────────┐
│ IMPLEMENTATION SEQUENCE                                                     │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  WEEK 1: Framework Setup                                                   │
│  ├── [ ] Verify BaseUI setup                                              │
│  ├── [ ] Verify BasePage setup                                             │
│  ├── [ ] Verify all helpers work correctly                                 │
│  └── [ ] Create sample test to validate setup                             │
│                                                                             │
│  WEEK 2: Authentication Module                                            │
│  ├── [ ] LoginPageElements.java                                           │
│  ├── [ ] LoginActions.java                                                │
│  ├── [ ] LoginPage.java                                                   │
│  ├── [ ] LoginTests.java                                                  │
│  ├── [ ] LoginAPITests.java (if API exists)                               │
│  └── [ ] LoginTestData.xlsx                                               │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

### Implementation Priority Matrix

| Module | Priority | Page Objects | Test Cases | Test Data | Estimated Hours |
|--------|----------|--------------|------------|-----------|----------------|
| Authentication | P0 | 3 classes | 15 tests | 1 file | 30 hours |
| Employee Mgmt | P0 | 9 classes | 45 tests | 3 files | 80 hours |
| Leave Mgmt | P1 | 9 classes | 35 tests | 2 files | 65 hours |
| Attendance | P1 | 6 classes | 25 tests | 1 file | 45 hours |
| Payroll | P1 | 6 classes | 20 tests | 2 files | 40 hours |
| Reports | P2 | 3 classes | 25 tests | 1 file | 30 hours |

```

---

## 4. Step 2: Generate Page Objects

### 4.1 Page Objects Generation Pattern

```java
/*
 * =============================================================================
 * PAGE OBJECTS GENERATION TEMPLATE
 * Follow this pattern for ALL page objects
 * =============================================================================
 */

/*
 * =============================================================================
 * FILE: {Module}PageElements.java
 * PURPOSE: Locators only - DO NOT add any logic here
 * LAYER: Layer 1 - Elements
 * =============================================================================
 */
package com.selenium_hrm.ui.pages.{module};

import org.openqa.selenium.By;

public class {Module}PageElements {
    
    // ========================================================================
    // FORM FIELDS
    // ========================================================================
    
    // Text inputs (TXT prefix)
    private static final By TXT_FIELD_NAME = By.id("fieldName");
    private static final By TXT_EMAIL = By.id("email");
    private static final By TXT_PHONE = By.name("phone");
    
    // Textarea inputs (TXA prefix)
    private static final By TXA_ADDRESS = By.id("address");
    private static final By TXA_DESCRIPTION = By.name("description");
    
    // ========================================================================
    // BUTTONS
    // ========================================================================
    
    // Primary buttons (BTN prefix)
    private static final By BTN_SUBMIT = By.id("submitBtn");
    private static final By BTN_SAVE = By.cssSelector(".btn-save");
    private static final By BTN_CANCEL = By.xpath("//button[contains(text(),'Cancel')]");
    private static final By BTN_DELETE = By.cssSelector("[data-testid='delete-btn']");
    private static final By BTN_EDIT = By.cssSelector("[data-testid='edit-btn']");
    private static final By BTN_ADD = By.cssSelector("[data-testid='add-btn']");
    
    // Icon buttons
    private static final By BTN_SEARCH = By.id("searchBtn");
    private static final By BTN_FILTER = By.id("filterBtn");
    private static final By BTN_CLEAR = By.id("clearBtn");
    
    // ========================================================================
    // DROPDOWNS & SELECTS
    // ========================================================================
    
    private static final By DDL_DEPARTMENT = By.id("departmentSelect");
    private static final By DDL_STATUS = By.name("status");
    private static final By DDL_ROLE = By.cssSelector(".role-dropdown");
    
    // ========================================================================
    // TABLES
    // ========================================================================
    
    private static final By TBL_DATA = By.id("dataTable");
    private static final By TBL_ROW_FIRST = By.cssSelector("table tbody tr:first-child");
    private static final By TBL_ROW_LAST = By.cssSelector("table tbody tr:last-child");
    private static final By TBL_CHECKBOX_ALL = By.id("selectAll");
    
    // ========================================================================
    // MESSAGES & LABELS
    // ========================================================================
    
    private static final By LBL_SUCCESS_MESSAGE = By.cssSelector(".alert-success");
    private static final By LBL_ERROR_MESSAGE = By.cssSelector(".alert-danger");
    private static final By LBL_PAGE_TITLE = By.xpath("//h1");
    private static final By LBL_TABLE_COUNT = By.cssSelector(".record-count");
    
    // ========================================================================
    // MODALS & DIALOGS
    // ========================================================================
    
    private static final By MDL_CONFIRM = By.cssSelector(".modal.confirm-dialog");
    private static final By MDL_CONFIRM_YES = By.xpath("//button[contains(text(),'Yes')]");
    private static final By MDL_CONFIRM_NO = By.xpath("//button[contains(text(),'No')]");
    
    // ========================================================================
    // PAGINATION
    // ========================================================================
    
    private static final By PAG_NEXT = By.cssSelector(".pagination .next");
    private static final By PAG_PREV = By.cssSelector(".pagination .previous");
    private static final By PAG_PAGE_ACTIVE = By.cssSelector(".pagination .active");
    
    // ========================================================================
    // GETTERS (for test access)
    // ========================================================================
    
    public static By getTxtFieldName() { return TXT_FIELD_NAME; }
    public static By getTxtEmail() { return TXT_EMAIL; }
    public static By getBtnSubmit() { return BTN_SUBMIT; }
    // ... add getters as needed
}
```

### 4.2 Actions Class Generation

```java
/*
 * =============================================================================
 * FILE: {Module}Actions.java
 * PURPOSE: Business-level operations using elements
 * LAYER: Layer 2 - Actions
 * =============================================================================
 */
package com.selenium_hrm.ui.pages.{module};

import com.selenium_hrm.config.DriverManager;
import com.selenium_hrm.utils.ActionHelper;
import com.selenium_hrm.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class {Module}Actions {
    
    private {Module}PageElements elements = new {Module}PageElements();
    private WebDriver driver = DriverManager.getDriver();
    
    // ========================================================================
    // FORM OPERATIONS
    // ========================================================================
    
    /**
     * Fill form with provided data
     * @param fieldName Name value
     * @param email Email value
     */
    public void fillForm(String fieldName, String email) {
        WaitHelper.waitForElementVisible(elements.getTxtFieldName());
        ActionHelper.setText(elements.getTxtFieldName(), fieldName);
        ActionHelper.setText(elements.getTxtEmail(), email);
    }
    
    /**
     * Submit form
     */
    public void submitForm() {
        ActionHelper.click(elements.getBtnSubmit());
        WaitHelper.waitForPageLoad();
    }
    
    /**
     * Select option from dropdown
     * @param dropdown Locator of dropdown
     * @param optionText Text of option to select
     */
    public void selectDropdownOption(By dropdown, String optionText) {
        ActionHelper.selectDropdownByText(dropdown, optionText);
    }
    
    /**
     * Clear field and enter new value
     * @param locator Field locator
     * @param value New value
     */
    public void clearAndEnterText(By locator, String value) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }
    
    // ========================================================================
    // TABLE OPERATIONS
    // ========================================================================
    
    /**
     * Get all rows from data table
     * @return List of WebElements representing rows
     */
    public List<WebElement> getTableRows() {
        WaitHelper.waitForElementVisible(elements.getTblData());
        return driver.findElements(By.cssSelector("table tbody tr"));
    }
    
    /**
     * Click action button in table row
     * @param rowIndex Row index (0-based)
     * @param action Edit/Delete
     */
    public void clickTableRowAction(int rowIndex, String action) {
        List<WebElement> rows = getTableRows();
        if (rowIndex < rows.size()) {
            By actionBtn = By.cssSelector("table tbody tr:nth-child(" + (rowIndex + 1) + ") .btn-" + action.toLowerCase());
            ActionHelper.click(actionBtn);
        }
    }
    
    /**
     * Select checkbox in table row
     * @param rowIndex Row index (0-based)
     */
    public void selectTableRowCheckbox(int rowIndex) {
        By checkbox = By.cssSelector("table tbody tr:nth-child(" + (rowIndex + 1) + ") input[type='checkbox']");
        ActionHelper.click(checkbox);
    }
    
    // ========================================================================
    // SEARCH & FILTER
    // ========================================================================
    
    /**
     * Search by keyword
     * @param keyword Search term
     */
    public void search(String keyword) {
        By searchInput = By.cssSelector(".search-input");
        ActionHelper.setText(searchInput, keyword);
        ActionHelper.click(elements.getBtnSearch());
        WaitHelper.waitForAjaxComplete();
    }
    
    /**
     * Clear all filters
     */
    public void clearFilters() {
        ActionHelper.click(elements.getBtnClear());
        WaitHelper.waitForAjaxComplete();
    }
    
    // ========================================================================
    // PAGINATION
    // ========================================================================
    
    /**
     * Navigate to next page
     * @return true if next page exists, false otherwise
     */
    public boolean goToNextPage() {
        By nextBtn = elements.getPagNext();
        if (ActionHelper.isElementEnabled(nextBtn)) {
            ActionHelper.click(nextBtn);
            WaitHelper.waitForAjaxComplete();
            return true;
        }
        return false;
    }
    
    /**
     * Get current page number
     * @return Current page number
     */
    public int getCurrentPage() {
        String text = driver.findElement(elements.getPagPageActive()).getText();
        return Integer.parseInt(text.trim());
    }
}
```

### 4.3 Page Class Generation

```java
/*
 * =============================================================================
 * FILE: {Module}Page.java
 * PURPOSE: Facade combining Elements and Actions
 * LAYER: Layer 3 - Page (Facade)
 * =============================================================================
 */
package com.selenium_hrm.ui.pages.{module};

import com.selenium_hrm.ui.base.BasePage;

public class {Module}Page extends BasePage {
    
    private {Module}PageElements elements = new {Module}PageElements();
    private {Module}Actions actions = new {Module}Actions();
    
    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================
    
    public {Module}Page() {
        super();
    }
    
    // ========================================================================
    // DELEGATED ACTIONS
    // ========================================================================
    
    /**
     * Navigate to this page
     */
    public void navigate() {
        String url = getConfigValue("app.url") + "/{module}";
        getDriver().navigate().to(url);
        waitForPageLoad();
    }
    
    /**
     * Fill form with test data
     * @param fieldName Field name value
     * @param email Email value
     */
    public void fillForm(String fieldName, String email) {
        actions.fillForm(fieldName, email);
    }
    
    /**
     * Submit the form
     */
    public void submit() {
        actions.submitForm();
    }
    
    /**
     * Create new record
     * @param fieldName Field name
     * @param email Email
     */
    public void create(String fieldName, String email) {
        actions.clickAddButton();
        fillForm(fieldName, email);
        submit();
    }
    
    /**
     * Search by keyword
     * @param keyword Search term
     */
    public void search(String keyword) {
        actions.search(keyword);
    }
    
    /**
     * Edit first row in table
     * @param newFieldName New field name
     * @param newEmail New email
     */
    public void editFirstRow(String newFieldName, String newEmail) {
        actions.clickTableRowAction(0, "edit");
        fillForm(newFieldName, newEmail);
        submit();
    }
    
    /**
     * Delete first row
     */
    public void deleteFirstRow() {
        actions.clickTableRowAction(0, "delete");
        actions.confirmDelete();
    }
    
    // ========================================================================
    // VERIFICATION METHODS
    // ========================================================================
    
    /**
     * Check if success message is displayed
     * @return true if success message visible
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(elements.getLblSuccessMessage());
    }
    
    /**
     * Check if error message is displayed
     * @return true if error message visible
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(elements.getLblErrorMessage());
    }
    
    /**
     * Get success message text
     * @return Success message
     */
    public String getSuccessMessage() {
        return getText(elements.getLblSuccessMessage());
    }
    
    /**
     * Get error message text
     * @return Error message
     */
    public String getErrorMessage() {
        return getText(elements.getLblErrorMessage());
    }
    
    /**
     * Get table row count
     * @return Number of rows
     */
    public int getTableRowCount() {
        return actions.getTableRows().size();
    }
    
    /**
     * Check if table is empty
     * @return true if no rows
     */
    public boolean isTableEmpty() {
        return getTableRowCount() == 0;
    }
    
    // ========================================================================
    // WAIT METHODS
    // ========================================================================
    
    /**
     * Wait for page to load
     */
    public void waitForPageToLoad() {
        waitForElementVisible(elements.getLblPageTitle());
    }
    
    /**
     * Wait for success message to disappear
     */
    public void waitForSuccessMessageToDisappear() {
        waitForElementInvisible(elements.getLblSuccessMessage());
    }
}
```

---

## 5. Step 3: Generate Test Classes

### 5.1 Test Class Generation Pattern

```java
/*
 * =============================================================================
 * FILE: {Module}Tests.java
 * PURPOSE: Test cases for {Module} module
 * PATTERN: AAA (Arrange-Act-Assert)
 * =============================================================================
 */
package com.selenium_hrm.ui.tests.{module};

import com.selenium_hrm.ui.base.BaseUI;
import com.selenium_hrm.ui.pages.{module}.{Module}Page;
import com.selenium_hrm.config.ConfigHelper;
import com.selenium_hrm.utils.DataFakerHelper;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class {Module}Tests extends BaseUI {
    
    // ========================================================================
    // PAGE OBJECTS
    // ========================================================================
    
    private {Module}Page {module}Page;
    
    // ========================================================================
    // TEST DATA
    // ========================================================================
    
    private String testName;
    private String testEmail;
    
    // ========================================================================
    // SUITE SETUP
    // ========================================================================
    
    @BeforeClass
    public void setUpClass() {
        log.info("=== Setting up {Module} test suite ===");
        {module}Page = new {Module}Page();
    }
    
    // ========================================================================
    // TEST SETUP
    // ========================================================================
    
    @BeforeMethod
    public void setUp() {
        log.info("=== Starting new test ===");
        
        // Login (common pre-condition)
        loginAsAdmin();
        
        // Navigate to module
        {module}Page.navigate();
        {module}Page.waitForPageToLoad();
        
        // Generate unique test data
        testName = DataFakerHelper.generateName();
        testEmail = DataFakerHelper.generateEmail(testName);
    }
    
    // ========================================================================
    // TEST METHODS - POSITIVE CASES
    // ========================================================================
    
    @Test(
        description = "Verify user can create new record with valid data",
        priority = 1,
        groups = {"smoke", "create", "positive"}
    )
    @Severity(SeverityLevel.CRITICAL)
    @Severity(SeverityLevel.BLOCKER)
    public void testCreate_WithValidData_ShouldSucceed() {
        // ARRANGE
        String expectedName = "John Doe " + System.currentTimeMillis();
        String expectedEmail = "john.doe." + System.currentTimeMillis() + "@test.com";
        
        // ACT
        {module}Page.clickAddButton();
        {module}Page.fillForm(expectedName, expectedEmail);
        {module}Page.submit();
        
        // ASSERT
        Assert.assertTrue({module}Page.isSuccessMessageDisplayed(), 
            "Success message should be displayed");
        Assert.assertTrue({module}Page.getSuccessMessage().contains("created") || 
            {module}Page.getSuccessMessage().contains("success"),
            "Success message should confirm creation");
    }
    
    @Test(
        description = "Verify user can search records by name",
        priority = 2,
        groups = {"smoke", "search", "positive"}
    )
    @Severity(SeverityLevel.CRITICAL)
    public void testSearch_ByName_ShouldReturnMatchingRecords() {
        // ARRANGE
        String searchTerm = "Engineering";
        int initialCount = {module}Page.getTableRowCount();
        
        // ACT
        {module}Page.search(searchTerm);
        
        // ASSERT
        Assert.assertTrue({module}Page.getTableRowCount() <= initialCount,
            "Search should return equal or fewer records");
    }
    
    // ========================================================================
    // TEST METHODS - NEGATIVE CASES
    // ========================================================================
    
    @Test(
        description = "Verify system rejects creation with missing required field",
        priority = 10,
        groups = {"regression", "create", "negative", "validation"}
    )
    @Severity(SeverityLevel.MAJOR)
    public void testCreate_WithMissingName_ShouldShowValidationError() {
        // ARRANGE
        String emptyName = "";
        String validEmail = "test@test.com";
        
        // ACT
        {module}Page.clickAddButton();
        {module}Page.fillForm(emptyName, validEmail);
        {module}Page.submit();
        
        // ASSERT
        Assert.assertTrue({module}Page.isErrorMessageDisplayed(),
            "Error message should be displayed for missing name");
    }
    
    @Test(
        description = "Verify system rejects creation with invalid email format",
        priority = 11,
        groups = {"regression", "create", "negative", "validation"}
    )
    @Severity(SeverityLevel.MAJOR)
    public void testCreate_WithInvalidEmail_ShouldShowValidationError() {
        // ARRANGE
        String validName = "Test User";
        String invalidEmail = "not-an-email";
        
        // ACT
        {module}Page.clickAddButton();
        {module}Page.fillForm(validName, invalidEmail);
        {module}Page.submit();
        
        // ASSERT
        Assert.assertTrue({module}Page.isErrorMessageDisplayed(),
            "Error message should be displayed for invalid email");
    }
    
    @Test(
        description = "Verify system rejects creation with duplicate email",
        priority = 12,
        groups = {"regression", "create", "negative"}
    )
    @Severity(SeverityLevel.MAJOR)
    public void testCreate_WithDuplicateEmail_ShouldShowError() {
        // ARRANGE - Create first record
        String duplicateEmail = "duplicate." + System.currentTimeMillis() + "@test.com";
        {module}Page.create("First User", duplicateEmail);
        Assert.assertTrue({module}Page.isSuccessMessageDisplayed());
        
        // ACT - Try to create second with same email
        {module}Page.create("Second User", duplicateEmail);
        
        // ASSERT
        Assert.assertTrue({module}Page.isErrorMessageDisplayed(),
            "Error should be displayed for duplicate email");
    }
    
    // ========================================================================
    // TEST METHODS - BOUNDARY CASES
    // ========================================================================
    
    @Test(
        description = "Verify system handles maximum length input",
        priority = 20,
        groups = {"regression", "boundary"}
    )
    @Severity(SeverityLevel.MINOR)
    public void testCreate_WithMaxLengthInput_ShouldSucceed() {
        // ARRANGE
        String maxLengthName = "A".repeat(100); // 100 chars
        String validEmail = "maxlen@test.com";
        
        // ACT
        {module}Page.clickAddButton();
        {module}Page.fillForm(maxLengthName, validEmail);
        {module}Page.submit();
        
        // ASSERT
        Assert.assertTrue({module}Page.isSuccessMessageDisplayed() || 
            {module}Page.isErrorMessageDisplayed(),
            "System should handle max length gracefully");
    }
    
    // ========================================================================
    // TEST TEARDOWN
    // ========================================================================
    
    @AfterMethod
    public void tearDown() {
        log.info("=== Cleaning up after test ===");
        // Logout or reset state if needed
        if (getDriver() != null) {
            getDriver().manage().deleteAllCookies();
        }
    }
    
    // ========================================================================
    // HELPER METHODS
    // ========================================================================
    
    private void loginAsAdmin() {
        // Common login method
        getDriver().navigate().to(ConfigHelper.getLoginUrl());
        // ... login implementation
    }
}
```

### 5.2 Data Provider Pattern

```java
/*
 * =============================================================================
 * DATA PROVIDERS
 * =============================================================================
 */

public class {Module}TestData {
    
    // ========================================================================
    // POSITIVE TEST DATA
    // ========================================================================
    
    @DataProvider(name = "validCreateData")
    public static Object[][] validCreateData() {
        return new Object[][] {
            {"John Doe", "john.doe@test.com", "Engineering", "PASS"},
            {"Jane Smith", "jane.smith@test.com", "Sales", "PASS"},
            {"Bob Wilson", "bob.wilson@test.com", "HR", "PASS"}
        };
    }
    
    @DataProvider(name = "validSearchData")
    public static Object[][] validSearchData() {
        return new Object[][] {
            {"Engineering"},
            {"Sales"},
            {"HR"},
            {"john"}
        };
    }
    
    // ========================================================================
    // NEGATIVE TEST DATA
    // ========================================================================
    
    @DataProvider(name = "invalidEmailData")
    public static Object[][] invalidEmailData() {
        return new Object[][] {
            {"", "email@test.com"},                    // Empty name
            {"Test User", ""},                         // Empty email
            {"Test User", "not-an-email"},            // Invalid format
            {"Test User", "test@"},                    // Incomplete
            {"Test User", "@test.com"},                // Missing local
            {"Test User", "test@test"},                // Missing TLD
            {"Test User", "test@.com"}                 // Invalid domain
        };
    }
    
    @DataProvider(name = "invalidNameData")
    public static Object[][] invalidNameData() {
        return new Object[][] {
            {""},                                       // Empty
            {"A"},                                      // Too short (< 2)
            {"A".repeat(101)}                          // Too long (> 100)
        };
    }
    
    // ========================================================================
    // BOUNDARY TEST DATA
    // ========================================================================
    
    @DataProvider(name = "boundaryData")
    public static Object[][] boundaryData() {
        return new Object[][] {
            // Name boundaries
            {"A", "test@test.com", "Name min boundary"},
            {"AB", "test@test.com", "Name exactly 2 chars"},
            {"A".repeat(99), "test@test.com", "Name 99 chars"},
            {"A".repeat(100), "test@test.com", "Name exactly 100 chars"},
            {"A".repeat(101), "test@test.com", "Name exceeds max"}
        };
    }
}
```

### 5.3 Integration with Test Class

```java
/*
 * USING DATA PROVIDER IN TEST
 */
@Test(
    dataProvider = "validCreateData",
    dataProviderClass = {Module}TestData.class,
    description = "Verify record creation with various valid data"
)
@Severity(SeverityLevel.CRITICAL)
public void testCreate_WithVariousValidData_ShouldSucceed(
        String name, 
        String email, 
        String department,
        String expectedResult) {
    
    // ARRANGE
    String uniqueName = name + " " + System.currentTimeMillis();
    String uniqueEmail = email.replace("@", "." + System.currentTimeMillis() + "@");
    
    // ACT
    {module}Page.create(uniqueName, uniqueEmail);
    
    // ASSERT
    Assert.assertEquals(expectedResult, "PASS");
}
```

---

## 6. Step 4: Prepare Test Data

### 6.1 Excel Test Data Structure

```excel
/* EXCEL FILE: {Module}TestData.xlsx */

/* SHEET: Create_Valid */
| Test_ID | Name | Email | Department | Expected_Result | Expected_Message |
|---------|------|-------|------------|-----------------|-----------------|
| TC001 | John Doe | john@test.com | Engineering | PASS | Record created successfully |
| TC002 | Jane Smith | jane@test.com | Sales | PASS | Record created successfully |
| TC003 | Bob Wilson | bob@test.com | HR | PASS | Record created successfully |

/* SHEET: Create_Invalid */
| Test_ID | Name | Email | Expected_Result | Expected_Error |
|---------|------|-------|-----------------|----------------|
| TC101 | | test@test.com | FAIL | Name is required |
| TC102 | Test | | FAIL | Email is required |
| TC103 | Test | invalid-email | FAIL | Invalid email format |

/* SHEET: Search */
| Test_ID | Search_Term | Expected_Match | Expected_Count_Min |
|---------|-------------|----------------|-------------------|
| TC201 | Engineering | true | 1 |
| TC202 | Nonexistent | false | 0 |
```

### 6.2 Test Data Reader

```java
/*
 * TEST DATA READER
 */
package com.selenium_hrm.config.testdata;

import com.selenium_hrm.config.testdata.ExcelHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;

public class {Module}TestDataReader {
    
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/";
    private static final String FILE_NAME = "{Module}TestData.xlsx";
    
    /**
     * Read valid create data from Excel
     */
    public static List<Map<String, String>> getValidCreateData() {
        List<Map<String, String>> data = new ArrayList<>();
        Sheet sheet = ExcelHelper.getSheet(FILE_NAME, "Create_Valid");
        
        if (sheet == null) return data;
        
        // Get headers from first row
        List<String> headers = ExcelHelper.getRowAsList(sheet, 0);
        
        // Read data rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            List<String> rowData = ExcelHelper.getRowAsList(sheet, i);
            Map<String, String> rowMap = new LinkedHashMap<>();
            for (int j = 0; j < headers.size(); j++) {
                rowMap.put(headers.get(j), j < rowData.size() ? rowData.get(j) : "");
            }
            data.add(rowMap);
        }
        
        return data;
    }
    
    /**
     * Read test data as 2D array for DataProvider
     */
    public static Object[][] getValidCreateDataAsArray() {
        List<Map<String, String>> data = getValidCreateData();
        Object[][] result = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            result[i] = new Object[]{
                row.get("Name"),
                row.get("Email"),
                row.get("Department")
            };
        }
        return result;
    }
}
```

---

## 7. Step 5: Implement API Tests

### 7.1 API Test Class Pattern

```java
/*
 * =============================================================================
 * FILE: {Module}APITests.java
 * PURPOSE: API tests for {Module}
 * =============================================================================
 */
package com.selenium_hrm.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class {Module}APITests extends BaseAPITest {
    
    // ========================================================================
    // CONFIGURATION
    // ========================================================================
    
    private static final String BASE_URL = ConfigHelper.getApiBaseUrl();
    private static final String ENDPOINT = "/api/v1/{module}";
    
    // ========================================================================
    // SUITE SETUP
    // ========================================================================
    
    @BeforeClass
    public void setUpClass() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
        // Login and get token
        login();
    }
    
    // ========================================================================
    // TEST METHODS - CRUD
    // ========================================================================
    
    @Test(
        description = "Verify API returns list of records",
        priority = 1,
        groups = {"api", "smoke", "get"}
    )
    public void testGetAll_ShouldReturnList() {
        given()
            .headers(getAuthHeaders())
            .queryParam("page", 0)
            .queryParam("size", 20)
        .when()
            .get(ENDPOINT)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("success", equalTo(true))
            .body("data.content", notNullValue());
    }
    
    @Test(
        description = "Verify API can create new record",
        priority = 2,
        groups = {"api", "smoke", "create"}
    )
    public void testCreate_ShouldReturnCreatedRecord() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "API Test " + System.currentTimeMillis());
        requestBody.put("email", "api." + System.currentTimeMillis() + "@test.com");
        requestBody.put("department", "Engineering");
        
        Response response = given()
            .headers(getAuthHeaders())
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(ENDPOINT)
        .then()
            .statusCode(201)
            .contentType(ContentType.JSON)
            .body("success", equalTo(true))
            .body("data.id", notNullValue())
            .body("data.name", equalTo(requestBody.get("name")))
            .extract().response();
        
        // Store ID for cleanup
        String createdId = response.jsonPath().getString("data.id");
        testDataIds.add(createdId);
    }
    
    @Test(
        description = "Verify API rejects duplicate email",
        priority = 10,
        groups = {"api", "regression", "create", "negative"}
    )
    public void testCreate_WithDuplicateEmail_ShouldReturn409() {
        // First create
        String duplicateEmail = "duplicate." + System.currentTimeMillis() + "@test.com";
        Map<String, Object> firstBody = new HashMap<>();
        firstBody.put("name", "First User");
        firstBody.put("email", duplicateEmail);
        
        Response firstResponse = given()
            .headers(getAuthHeaders())
            .contentType(ContentType.JSON)
            .body(firstBody)
        .when()
            .post(ENDPOINT);
        
        firstResponse.then().statusCode(201);
        String createdId = firstResponse.jsonPath().getString("data.id");
        testDataIds.add(createdId);
        
        // Try duplicate
        Map<String, Object> secondBody = new HashMap<>();
        secondBody.put("name", "Second User");
        secondBody.put("email", duplicateEmail);
        
        given()
            .headers(getAuthHeaders())
            .contentType(ContentType.JSON)
            .body(secondBody)
        .when()
            .post(ENDPOINT)
        .then()
            .statusCode(409)
            .body("success", equalTo(false));
    }
}
```

---

## 8. Step 6: Run & Validate

### 8.1 Validation Checklist

```
CODE GENERATION VALIDATION CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

□ COMPILATION CHECK
  ├── All classes compile without errors
  ├── No missing imports
  ├── No type mismatches
  └── Maven build successful

□ CODING STANDARDS CHECK
  ├── Naming conventions followed
  ├── Proper indentation (4 spaces)
  ├── No TODO/FIXME comments left
  ├── Proper access modifiers
  └── Constants properly declared

□ FRAMEWORK COMPLIANCE CHECK
  ├── 3-layer POM structure followed
  ├── BasePage methods used correctly
  ├── Helper utilities used appropriately
  └── No direct WebDriver calls in Page classes

□ TEST COVERAGE CHECK
  ├── All test cases from design have code
  ├── All test cases are executable
  ├── Test data files ready
  └── DataProvider methods complete

□ EXECUTION CHECK
  ├── Sample tests run successfully
  ├── Screenshots captured on failure
  ├── Reports generated correctly
  └── Logs show proper information

```

### 8.2 Execution Commands

```bash
# MAVEN COMMANDS FOR VALIDATION

# 1. Compile project
mvn clean compile

# 2. Run single test class
mvn test -Dtest=LoginTests

# 3. Run specific test group
mvn test -Dgroups=smoke

# 4. Run with reports
mvn test
# Reports at: target/surefire-reports/

# 5. Generate Extent Report
mvn test -DextentReport=true

# 6. Run with specific browser
mvn test -Dbrowser=chrome -Dheadless=true

# 7. Run specific suite
mvn test -DsuiteXmlFile=src/test/resources/suites/SuiteSmokeTest.xml
```

---

## 9. Step 7: Code Review & Refine

### 9.1 Code Review Checklist

```
CODE REVIEW CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

□ STRUCTURE REVIEW
  ├── Package structure follows convention
  ├── Class organization correct
  └── No duplicate code

□ NAMING REVIEW
  ├── Classes named correctly
  ├── Methods follow verb-object pattern
  ├── Variables are descriptive
  └── Locators follow prefix convention

□ LOGIC REVIEW
  ├── No hardcoded values (use config)
  ├── Proper null handling
  ├── Appropriate wait strategies
  └── No race conditions

□ MAINTAINABILITY REVIEW
  ├── Code is DRY (Don't Repeat Yourself)
  ├── Helper methods extracted
  ├── Test data externalized
  └── Locators centralized

□ RELIABILITY REVIEW
  ├── Explicit waits used
  ├── Proper exception handling
  ├── Retry logic for flaky elements
  └── No Thread.sleep()

□ DOCUMENTATION REVIEW
  ├── Javadoc for public methods
  ├── Comments for complex logic
  └── README updated if needed

```

### 9.2 Review Comment Template

```markdown
## Code Review Comments

### Review for: {Module}Page.java

| # | Line | Type | Comment | Severity |
|---|------|------|---------|----------|
| 1 | 45 | Suggestion | Consider using String.format for URL construction | Low |
| 2 | 78 | Issue | Hardcoded wait time - use WaitHelper instead | Medium |
| 3 | 102 | Issue | Missing null check before accessing element | High |

### Resolved Issues:
- [x] Line 45: Refactored to use StringBuilder
- [x] Line 78: Replaced with WaitHelper.waitForAjaxComplete()
- [x] Line 102: Added null check

### Sign-off:
| Role | Name | Date | Status |
|------|------|------|--------|
| Author | [Name] | [Date] | Submitted |
| Reviewer | [Name] | [Date] | Approved |

```

---

## 10. Deliverables

### 10.1 Generated Files

| File Type | Location | Count | Status |
|-----------|----------|-------|--------|
| Page Elements | `src/test/java/com/selenium_hrm/ui/pages/{module}/` | 1 per module | Generated |
| Page Actions | `src/test/java/com/selenium_hrm/ui/pages/{module}/` | 1 per module | Generated |
| Page Objects | `src/test/java/com/selenium_hrm/ui/pages/{module}/` | 1 per module | Generated |
| Test Classes | `src/test/java/com/selenium_hrm/ui/tests/{module}/` | 1 per module | Generated |
| API Tests | `src/test/java/com/selenium_hrm/api/tests/{module}/` | 1 per module | Generated |
| Test Data | `src/test/resources/testdata/{module}/` | 1-3 files | Generated |

### 10.2 Output Structure

```
src/test/java/com/selenium_hrm/
├── ui/
│   ├── base/
│   │   ├── BaseUI.java
│   │   └── BasePage.java
│   ├── pages/
│   │   ├── login/
│   │   │   ├── LoginPageElements.java
│   │   │   ├── LoginActions.java
│   │   │   └── LoginPage.java
│   │   ├── employee/
│   │   │   ├── EmployeePageElements.java
│   │   │   ├── EmployeeActions.java
│   │   │   └── EmployeePage.java
│   │   └── [other modules]/
│   └── tests/
│       ├── login/
│       │   └── LoginTests.java
│       ├── employee/
│       │   └── EmployeeTests.java
│       └── [other modules]/
└── api/
    └── tests/
        ├── BaseAPITest.java
        └── [module]/
            └── {Module}APITests.java
```

---

## 11. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer | Initial workflow document |
