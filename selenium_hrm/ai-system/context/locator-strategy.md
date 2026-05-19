# Locator Strategy - Selenium HRM Automation

## 1. Overview

### 1.1 Purpose
Locator strategy định nghĩa cách tìm và định vị elements trên web pages một cách hiệu quả, stable và maintainable. Chiến lược này giúp giảm flaky tests và dễ dàng bảo trì khi application thay đổi.

### 1.2 Goals
- **Stability**: Locators không bị break khi UI thay đổi nhẹ
- **Readability**: Locators dễ hiểu và maintain
- **Performance**: Locators tìm kiếm nhanh
- **Uniqueness**: Mỗi element được identify một cách duy nhất
- **Robustness**: Không phụ thuộc vào DOM position

### 1.3 Reference
- Framework Rules: `framework-rules.md`
- Coding Standards: `coding-standards.md`
- Naming Convention: `naming-convention.md`

---

## 2. Locator Priority Hierarchy

### 2.1 Priority Order

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                        LOCATOR PRIORITY MATRIX                              │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   PRIORITY 1: data-testid     [★★★★★] Best - Developer-defined, stable    │
│   PRIORITY 2: data-cy         [★★★★☆] Good - Cypress-style testing ID     │
│   PRIORITY 3: data-qa         [★★★★☆] Good - QA-specific attribute        │
│   PRIORITY 4: id              [★★★☆☆] Good - Unique HTML identifier        │
│   PRIORITY 5: name            [★★★☆☆] Good - Form element name            │
│   PRIORITY 6: css             [★★☆☆☆] Fair - Performance, readable        │
│   PRIORITY 7: xpath           [★☆☆☆☆] Last - Flexible but fragile         │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 2.2 When to Use Each Locator

| Locator Type | Best For | Avoid When |
|--------------|----------|------------|
| **data-testid** | All elements | Element doesn't have this attribute |
| **data-cy** | Cross-framework testing | Only for Cypress compatibility |
| **id** | Unique, static elements | Element has dynamic ID |
| **name** | Form inputs | Multiple elements with same name |
| **css** | Complex patterns, speed | Deep nested structures |
| **xpath** | Complex conditions, dynamic elements | Simple patterns (use CSS instead) |

---

## 3. Preferred Locators

### 3.1 data-testid (RECOMMENDED)

**Ưu điểm:**
- Developer-defined cho testing
- Không thay đổi khi UI refactor
- Rõ ràng, mục đích testing
- Ít bị ảnh hưởng bởi CSS changes

**Implementation:**
```java
// HTML: <button data-testid="login-submit-btn">Login</button>

// Java
private static final By BTN_LOGIN_SUBMIT = By.cssSelector("[data-testid='login-submit-btn']");
private static final By BTN_LOGIN_SUBMIT_ALT = By.xpath("//*[@data-testid='login-submit-btn']");

// Best practice: Use CSS selector for data attributes
private static final By BTN_LOGIN = By.cssSelector("[data-testid='login-btn']");
private static final By TXT_EMAIL = By.cssSelector("[data-testid='email-input']");
private static final By TXT_PASSWORD = By.cssSelector("[data-testid='password-input']");
private static final By LBL_WELCOME = By.cssSelector("[data-testid='welcome-message']");
```

**Request to Developer:**
```
When creating HTML elements, please add data-testid attributes:
- Format: data-testid="{module}-{element}-{action}"
- Examples:
  * data-testid="login-username-input"
  * data-testid="login-password-input"
  * data-testid="login-submit-btn"
  * data-testid="employee-add-btn"
  * data-testid="employee-name-cell"
  * data-testid="leave-approve-btn"
```

---

### 3.2 data-cy (Cypress-style)

**Ưu điểm:**
- Standard trong Cypress ecosystem
- Clear testing purpose
- Tương thích cross-platform

**Implementation:**
```java
// HTML: <input data-cy="username" type="text" />

// Java
private static final By TXT_USERNAME = By.cssSelector("[data-cy='username']");
private static final By TXT_PASSWORD = By.cssSelector("[data-cy='password']");
private static final By BTN_SUBMIT = By.cssSelector("[data-cy='submit-btn']");
```

---

### 3.3 data-qa (QA-specific)

**Implementation:**
```java
// HTML: <div data-qa="dashboard-widget" class="widget">

// Java
private static final By WIDGET_DASHBOARD = By.cssSelector("[data-qa='dashboard-widget']");
private static final By WIDGET_EMPLOYEE_LIST = By.cssSelector("[data-qa='employee-list-widget']");
```

---

## 4. Standard HTML Attributes

### 4.1 ID Locator

**Ưu điểm:**
- HTML spec: ID phải unique
- Direct native support của WebDriver
- Fast lookup

**Khi nào sử dụng:**
- Element có static, unique ID
- ID không generated dynamically

```java
// ✅ ĐÚNG: Stable ID
private static final By TXT_USERNAME = By.id("username");
private static final By TXT_PASSWORD = By.id("password");
private static final By BTN_LOGIN = By.id("loginButton");
private static final By FORM_LOGIN = By.id("loginForm");

// ❌ TRÁNH: Dynamic ID
// HTML: <input id="field-12345" />
// ID nay co the thay doi moi lan load page
private static final By TXT_FIELD = By.id("field-12345"); // ❌ Fragile
```

**Dynamic ID Pattern:**
```java
// Nếu phải dùng dynamic ID, kết hợp với contains
private By getDynamicFieldByPrefix(String prefix) {
    return By.cssSelector("[id^='" + prefix + "']");
}

// Sử dụng
By fieldId = getDynamicFieldByPrefix("field-");
// Sẽ match: field-12345, field-67890, etc.
```

---

### 4.2 Name Locator

**Ưu điểm:**
- Thường có cho form elements
- Stable cho form inputs
- Hợp lệ cho multiple elements cùng name

```java
// ✅ ĐÚNG: Name for form elements
private static final By TXT_EMAIL = By.name("email");
private static final By TXT_PASSWORD = By.name("password");
private static final By TXT_SEARCH = By.name("searchQuery");
private static final By CHK_REMEMBER = By.name("rememberMe");

// ✅ ĐÚNG: First element với multiple same name
private static final By RADIO_OPTION = By.name("gender");
```

---

### 4.3 CSS Selector

**Ưu điểm:**
- Performance tốt hơn XPath
- Dễ đọc, dễ hiểu
- Hỗ trợ nhiều pattern

**CSS Selector Patterns:**

```java
// ============ 1. Class Selector ============
private static final By BTN_PRIMARY = By.cssSelector(".btn-primary");
private static final By FORM_LOGIN = By.cssSelector(".login-form");
private static final By INPUT_ERROR = By.cssSelector(".input-error");

// ============ 2. ID Selector ============
private static final By TXT_USERNAME = By.cssSelector("#username");
private static final By FORM_MAIN = By.cssSelector("#mainForm");

// ============ 3. Attribute Selector ============
private static final By TXT_EMAIL = By.cssSelector("input[type='text']");
private static final By TXT_PASSWORD = By.cssSelector("input[type='password']");
private static final By BTN_SUBMIT = By.cssSelector("button[type='submit']");
private static final By LINK_LOGOUT = By.cssSelector("a[href='/logout']");

// ============ 4. Attribute Contains ============
private static final By DIV_MODAL = By.cssSelector("[class*='modal']");
private static final By INPUT_SEARCH = By.cssSelector("[placeholder*='Search']");
private static final By BTN_ACTION = By.cssSelector("[class*='btn-action']");

// ============ 5. Attribute Starts With ============
private static final By LBL_ERROR = By.cssSelector("[class^='error-']");
private static final By COL_STATUS = By.cssSelector("[id^='status-']");

// ============ 6. Attribute Ends With ============
private static final By IMG_ICON = By.cssSelector("[src$='.png']");
private static final By LNK_PDF = By.cssSelector("[href$='.pdf']");

// ============ 7. Exact Match ============
private static final By TXT_FIRSTNAME = By.cssSelector("input.input-field.form-control");

// ============ 8. Multiple Classes ============
private static final By BTN_LOGIN = By.cssSelector("button.btn.btn-primary.btn-lg");

// ============ 9. Direct Child ============
private static final By ROW_FIRST = By.cssSelector("table > tbody > tr:first-child");
private static final By HEADER_TITLE = By.cssSelector("div.container > h1");

// ============ 10. Descendant ============
private static final By CELL_IN_TABLE = By.cssSelector("table tbody tr td");
private static final By LINK_IN_NAV = By.cssSelector("nav a");

// ============ 11. Nth Child ============
private static final By ROW_SECOND = By.cssSelector("table tbody tr:nth-child(2)");
private static final By MENU_ITEM_THIRD = By.cssSelector("ul.menu li:nth-child(3)");
```

---

### 4.4 XPath Selector

**Ưu điểm:**
- Linh hoạt nhất
- Hỗ trợ text content
- Điều hướng DOM linh động

**Khi nào dùng XPath:**
- Complex conditions
- Text-based selection
- Parent-to-child traversal
- Relative positioning

```java
// ============ 1. Absolute XPath (TRÁNH) ============
// ❌ Rất fragile
private static final By BTN = By.xpath("/html/body/div[1]/div[2]/form/button");

// ============ 2. Relative XPath (ƯU TIÊN) ============
// ✅ Bắt đầu từ element gần nhất có unique attribute
private static final By BTN_LOGIN = By.xpath("//button[@id='loginBtn']");
private static final By TXT_USERNAME = By.xpath("//input[@name='username']");

// ============ 3. Text Content ============
private static final By LNK_LOGOUT = By.xpath("//a[text()='Logout']");
private static final By LBL_WELCOME = By.xpath("//span[contains(text(),'Welcome')]");
private static final By LBL_ERROR = By.xpath("//div[contains(@class,'error') and contains(text(),'Invalid')]");

// ============ 4. Partial Text Match ============
private static final By BTN_SUBMIT = By.xpath("//button[contains(text(),'Submit')]");
private static final By LINK_FORGOT = By.xpath("//a[contains(text(),'Forgot')]");

// ============ 5. Multiple Attributes ============
private static final By BTN_DELETE = By.xpath("//button[@type='submit' and @class='btn-danger']");
private static final By INPUT_REQUIRED = By.xpath("//input[@type='text' and @required]");

// ============ 6. OR Conditions ============
private static final By BTN_ANY = By.xpath("//button[@id='btn' or @name='btn' or @class='btn']");

// ============ 7. NOT Conditions ============
private static final By INPUT_ENABLED = By.xpath("//input[@type='text' and not(@disabled)]");
private static final By LINK_VISIBLE = By.xpath("//a[not(contains(@style,'display:none'))]");

// ============ 8. Position-based ============
private static final By ROW_FIRST = By.xpath("//table[@id='empTable']//tr[1]");
private static final By ROW_LAST = By.xpath("//table[@id='empTable']//tr[last()]");
private static final By CELL_SECOND = By.xpath("//tr[1]//td[2]");

// ============ 9. Parent to Child ============
private static final By BTN_IN_FORM = By.xpath("//form[@id='loginForm']//button");
private static final By INPUT_IN_DIV = By.xpath("//div[@class='form-group']//input");

// ============ 10. Axis: Following Sibling ============
private static final By LABEL_AFTER_INPUT = By.xpath("//input[@id='email']/following-sibling::label");
private static final By DIV_AFTER_LABEL = By.xpath("//label[contains(text(),'Email')]/following-sibling::div");

// ============ 11. Axis: Preceding Sibling ============
private static final By CHECKBOX_BEFORE_LABEL = By.xpath("//label[contains(text(),'Remember')]/preceding-sibling::input");

// ============ 12. Axis: Ancestor ============
private static final By FORM_CONTAINING_INPUT = By.xpath("//input[@id='username']/ancestor::form");

// ============ 13. Dynamic Elements ============
private By getEmployeeRowByName(String name) {
    return By.xpath("//tr[@class='employee-row'][td[contains(text(),'" + name + "')]]");
}

private By getDeleteButtonById(String id) {
    return By.xpath("//button[@data-action='delete' and contains(@data-id,'" + id + "')]");
}
```

---

## 5. Locator Patterns

### 5.1 Login Page Locators

```java
public class LoginPageElements {
    
    // ============ Input Fields ============
    private static final By TXT_USERNAME = By.cssSelector("[data-testid='username-input']");
    private static final By TXT_PASSWORD = By.cssSelector("[data-testid='password-input']");
    private static final By TXT_USERNAME_ALT = By.id("username");
    private static final By TXT_PASSWORD_ALT = By.id("password");
    
    // ============ Buttons ============
    private static final By BTN_LOGIN = By.cssSelector("[data-testid='login-submit-btn']");
    private static final By BTN_LOGIN_ALT = By.cssSelector("button[type='submit']");
    private static final By BTN_SHOW_PASSWORD = By.cssSelector("[data-testid='toggle-password']");
    
    // ============ Links ============
    private static final By LNK_FORGOT_PASSWORD = By.xpath("//a[contains(text(),'Forgot')]");
    private static final By LNK_REGISTER = By.xpath("//a[contains(text(),'Register')]");
    
    // ============ Messages ============
    private static final By LBL_ERROR = By.cssSelector("[data-testid='error-message']");
    private static final By LBL_ERROR_ALT = By.xpath("//div[contains(@class,'alert-danger')]");
    private static final By LBL_SUCCESS = By.cssSelector("[data-testid='success-message']");
    
    // ============ Checkbox ============
    private static final By CHK_REMEMBER = By.name("rememberMe");
    
    // ============ Logo ============
    private static final By IMG_LOGO = By.cssSelector("[data-testid='app-logo']");
    
    // ============ Navigation ============
    private static final By NAV_LOGIN_TAB = By.xpath("//a[contains(@class,'nav-link') and contains(text(),'Login')]");
}
```

### 5.2 Employee Management Locators

```java
public class EmployeePageElements {
    
    // ============ Page Header ============
    private static final By LBL_PAGE_TITLE = By.xpath("//h1[contains(text(),'Employee')]");
    private static final By LBL_SUBTITLE = By.xpath("//h2[contains(text(),'Management')]");
    
    // ============ Search & Filter ============
    private static final By TXT_SEARCH = By.cssSelector("[data-testid='employee-search-input']");
    private static final By TXT_SEARCH_ALT = By.xpath("//input[@placeholder='Search employees']");
    private static final By DROPDOWN_DEPARTMENT = By.cssSelector("[data-testid='department-filter']");
    private static final By DROPDOWN_STATUS = By.cssSelector("[data-testid='status-filter']");
    private static final By BTN_SEARCH = By.cssSelector("[data-testid='search-btn']");
    private static final By BTN_CLEAR_FILTER = By.cssSelector("[data-testid='clear-filter-btn']");
    
    // ============ Table ============
    private static final By TABLE_EMPLOYEE = By.id("employeeTable");
    private static final By TABLE_HEADER = By.cssSelector("#employeeTable thead th");
    private static final By TABLE_ROW = By.cssSelector("#employeeTable tbody tr");
    private static final By TABLE_ROW_FIRST = By.xpath("//table[@id='employeeTable']//tbody/tr[1]");
    
    // Column locators
    private static final By COL_NAME = By.xpath("//table[@id='employeeTable']//td[2]");
    private static final By COL_EMAIL = By.xpath("//table[@id='employeeTable']//td[3]");
    private static final By COL_DEPARTMENT = By.xpath("//table[@id='employeeTable']//td[4]");
    private static final By COL_STATUS = By.xpath("//table[@id='employeeTable']//td[5]");
    
    // ============ Action Buttons ============
    private static final By BTN_ADD_EMPLOYEE = By.cssSelector("[data-testid='add-employee-btn']");
    private static final By BTN_ADD_EMPLOYEE_ALT = By.xpath("//button[contains(@class,'btn-add')]");
    
    // Dynamic locators for table rows
    private By getEmployeeRowByName(String name) {
        return By.xpath("//table[@id='employeeTable']//tr[td[contains(text(),'" + name + "')]]");
    }
    
    private By getEditButtonByName(String name) {
        return By.xpath("//table[@id='employeeTable']//tr[td[contains(text(),'" + name + "')]]//button[@data-action='edit']");
    }
    
    private By getDeleteButtonByName(String name) {
        return By.xpath("//table[@id='employeeTable']//tr[td[contains(text(),'" + name + "')]]//button[@data-action='delete']");
    }
    
    // ============ Form Fields ============
    private static final By TXT_EMP_NAME = By.id("employeeName");
    private static final By TXT_EMP_EMAIL = By.id("employeeEmail");
    private static final By TXT_EMP_PHONE = By.id("employeePhone");
    private static final By TXT_EMP_ADDRESS = By.id("employeeAddress");
    private static final By DROPDOWN_DEPT = By.id("department");
    private static final By DROPDOWN_POSITION = By.id("position");
    private static final By DATEPICKER_HIRE_DATE = By.id("hireDate");
    private static final By TXT_SALARY = By.id("salary");
    
    // ============ Form Buttons ============
    private static final By BTN_SAVE = By.cssSelector("[data-testid='save-btn']");
    private static final By BTN_CANCEL = By.cssSelector("[data-testid='cancel-btn']");
    private static final By BTN_CLOSE_FORM = By.cssSelector("[data-testid='close-form-btn']");
    
    // ============ Messages ============
    private static final By LBL_SUCCESS_MESSAGE = By.xpath("//div[contains(@class,'alert-success')]");
    private static final By LBL_ERROR_MESSAGE = By.xpath("//div[contains(@class,'alert-danger')]");
    private static final By LBL_CONFIRM_DELETE = By.xpath("//div[contains(@class,'modal')]//div[contains(text(),'confirm')]");
    private static final By BTN_CONFIRM_DELETE = By.xpath("//button[contains(text(),'Delete')]");
    
    // ============ Pagination ============
    private static final By PAGINATION_INFO = By.cssSelector(".pagination-info");
    private static final By BTN_PAGE_PREV = By.cssSelector(".page-item.previous");
    private static final By BTN_PAGE_NEXT = By.cssSelector(".page-item.next");
    private static final By BTN_PAGE_NUMBER = By.cssSelector(".page-item.number");
}
```

### 5.3 Leave Management Locators

```java
public class LeavePageElements {
    
    // ============ Leave Request Form ============
    private static final By TAB_LEAVE_REQUESTS = By.xpath("//a[contains(text(),'Leave Requests')]");
    private static final By BTN_NEW_REQUEST = By.cssSelector("[data-testid='new-leave-request-btn']");
    
    // Form fields
    private static final By DROPDOWN_LEAVE_TYPE = By.id("leaveType");
    private static final By DATE_START = By.id("startDate");
    private static final By DATE_END = By.id("endDate");
    private static final By TXT_REASON = By.id("reason");
    private static final By FILE_ATTACHMENT = By.id("attachment");
    
    // ============ Leave Balance ============
    private static final By LBL_ANNUAL_BALANCE = By.xpath("//div[@class='leave-balance']//span[@data-type='annual']");
    private static final By LBL_SICK_BALANCE = By.xpath("//div[@class='leave-balance']//span[@data-type='sick']");
    private static final By LBL_CASUAL_BALANCE = By.xpath("//div[@class='leave-balance']//span[@data-type='casual']");
    
    // ============ Leave Table ============
    private static final By TABLE_LEAVE = By.id("leaveRequestsTable");
    private static final By TABLE_LEAVE_ROW = By.cssSelector("#leaveRequestsTable tbody tr");
    
    // Status-based locators
    private By getLeaveRowById(String id) {
        return By.xpath("//table[@id='leaveRequestsTable']//tr[@data-id='" + id + "']");
    }
    
    // Action buttons per row
    private By getApproveButtonByRow(String xpath) {
        return By.xpath(xpath + "//button[contains(@class,'btn-approve')]");
    }
    
    private By getRejectButtonByRow(String xpath) {
        return By.xpath(xpath + "//button[contains(@class,'btn-reject')]");
    }
    
    // ============ Calendar ============
    private static final By CALENDAR = By.cssSelector(".leave-calendar");
    private static final By CALENDAR_PREV_MONTH = By.cssSelector(".calendar-nav-prev");
    private static final By CALENDAR_NEXT_MONTH = By.cssSelector(".calendar-nav-next");
    private static final By CALENDAR_TODAY = By.cssSelector(".calendar-today");
    
    // Dynamic date cells
    private By getCalendarDateCell(int day) {
        return By.xpath("//div[@class='calendar']//td[@data-day='" + day + "']");
    }
    
    // ============ Messages ============
    private static final By LBL_INSUFFICIENT_BALANCE = By.xpath("//span[contains(@class,'error') and contains(text(),'Insufficient')]");
    private static final By LBL_APPROVAL_NOTIFICATION = By.cssSelector(".notification-approval");
}
```

---

## 6. Advanced Locator Patterns

### 6.1 Dynamic Locators

```java
public class DynamicLocators {
    
    // Pattern 1: String concatenation
    private By getEmployeeEditButton(String employeeId) {
        return By.xpath("//tr[@data-employee-id='" + employeeId + "']//button[@class='btn-edit']");
    }
    
    // Pattern 2: String.format
    private static final String ROW_TEMPLATE = "//table//tr[%d]";
    private static final String CELL_TEMPLATE = "//table//tr[%d]//td[%d]";
    
    public By getTableCell(int row, int column) {
        return By.xpath(String.format(CELL_TEMPLATE, row, column));
    }
    
    // Pattern 3: Lambda/Method reference
    private Function<String, By> employeeRowLocator = (name) -> 
        By.xpath("//tr[@class='employee']//td[contains(text(),'" + name + "')]");
    
    // Pattern 4: Builder pattern
    public By buildTableCellLocator(String tableId, int row, int column) {
        return By.xpath(String.format(
            "//table[@id='%s']//tr[%d]//td[%d]", 
            tableId, row, column
        ));
    }
    
    // Pattern 5: Composite locator
    public By getLocator(LocatorType type, String value) {
        switch (type) {
            case ID:
                return By.id(value);
            case NAME:
                return By.name(value);
            case CSS:
                return By.cssSelector(value);
            case XPATH:
                return By.xpath(value);
            case DATA_TEST_ID:
                return By.cssSelector("[data-testid='" + value + "']");
            default:
                throw new IllegalArgumentException("Unknown locator type: " + type);
        }
    }
}
```

### 6.2 Complex Table Locators

```java
public class TableLocators {
    
    // Table structure constants
    private static final String TABLE_ID = "dataTable";
    private static final By TABLE = By.id(TABLE_ID);
    
    // Column indices
    private static final int COL_CHECKBOX = 1;
    private static final int COL_NAME = 2;
    private static final int COL_EMAIL = 3;
    private static final int COL_DEPARTMENT = 4;
    private static final int COL_STATUS = 5;
    private static final int COL_ACTIONS = 6;
    
    // Base locators
    private static final By TABLE_BODY = By.cssSelector("#" + TABLE_ID + " tbody");
    private static final By TABLE_ROWS = By.cssSelector("#" + TABLE_ID + " tbody tr");
    private static final By TABLE_HEADER = By.cssSelector("#" + TABLE_ID + " thead th");
    
    // Get row by content
    public By getRowByColumnValue(int columnIndex, String value) {
        return By.xpath(String.format(
            "//table[@id='%s']//tbody//tr[td[%d][contains(text(),'%s')]]",
            TABLE_ID, columnIndex, value
        ));
    }
    
    // Get cell by row and column
    public By getCell(int rowIndex, int columnIndex) {
        return By.xpath(String.format(
            "//table[@id='%s']//tbody//tr[%d]//td[%d]",
            TABLE_ID, rowIndex, columnIndex
        ));
    }
    
    // Get action button in row
    public By getActionButton(String action, int rowIndex) {
        return By.xpath(String.format(
            "//table[@id='%s']//tbody//tr[%d]//button[@data-action='%s']",
            TABLE_ID, rowIndex, action
        ));
    }
    
    // Get checkbox in row
    public By getRowCheckbox(int rowIndex) {
        return By.xpath(String.format(
            "//table[@id='%s']//tbody//tr[%d]//input[@type='checkbox']",
            TABLE_ID, rowIndex
        ));
    }
    
    // Count rows
    public int getRowCount() {
        return DriverManager.getDriver().findElements(TABLE_ROWS).size();
    }
    
    // Find rows matching criteria
    public List<String> getColumnValues(int columnIndex) {
        List<String> values = new ArrayList<>();
        List<WebElement> rows = DriverManager.getDriver().findElements(TABLE_ROWS);
        
        for (WebElement row : rows) {
            WebElement cell = row.findElement(By.xpath(".//td[" + columnIndex + "]"));
            values.add(cell.getText());
        }
        
        return values;
    }
}
```

### 6.3 Modal/Dialog Locators

```java
public class ModalLocators {
    
    // Modal identification
    private static final By MODAL_OVERLAY = By.cssSelector(".modal-backdrop");
    private static final By MODAL_DIALOG = By.cssSelector(".modal-dialog");
    private static final By MODAL_CONTENT = By.cssSelector(".modal-content");
    private static final By MODAL_HEADER = By.cssSelector(".modal-header");
    private static final By MODAL_BODY = By.cssSelector(".modal-body");
    private static final By MODAL_FOOTER = By.cssSelector(".modal-footer");
    
    // Modal by title
    private By getModalByTitle(String title) {
        return By.xpath("//div[contains(@class,'modal')]//h5[contains(text(),'" + title + "')]/ancestor::div[@class='modal-dialog']");
    }
    
    // Modal buttons
    private static final By BTN_MODAL_CLOSE = By.cssSelector(".modal .btn-close");
    private static final By BTN_MODAL_CANCEL = By.xpath("//div[@class='modal-footer']//button[contains(text(),'Cancel')]");
    private static final By BTN_MODAL_CONFIRM = By.xpath("//div[@class='modal-footer']//button[contains(text(),'Confirm')]");
    private static final By BTN_MODAL_SAVE = By.xpath("//div[@class='modal-footer']//button[contains(text(),'Save')]");
    
    // Check if modal is visible
    public boolean isModalVisible() {
        try {
            return DriverManager.getDriver()
                .findElement(By.cssSelector(".modal.show"))
                .isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    // Wait for modal
    public void waitForModalToAppear() {
        WaitHelper.waitForVisibility(MODAL_DIALOG);
    }
    
    public void waitForModalToDisappear() {
        WaitHelper.waitForInvisibility(MODAL_DIALOG);
    }
}
```

### 6.4 Dropdown/Select Locators

```java
public class DropdownLocators {
    
    // Standard select dropdown
    private static final By SEL_DEPARTMENT = By.id("departmentSelect");
    
    // Custom dropdown (div-based)
    private static final By DROPDOWN_CUSTOM = By.cssSelector(".custom-dropdown");
    private static final By DROPDOWN_TOGGLE = By.cssSelector(".dropdown-toggle");
    private static final By DROPDOWN_MENU = By.cssSelector(".dropdown-menu");
    
    // Dropdown options
    private static final By DROPDOWN_OPTIONS = By.cssSelector(".dropdown-menu .dropdown-item");
    
    // Option locators
    public By getDropdownOption(String text) {
        return By.xpath("//div[contains(@class,'dropdown-menu')]//span[contains(text(),'" + text + "')]");
    }
    
    public By getDropdownOptionByValue(String value) {
        return By.xpath("//div[contains(@class,'dropdown-menu')]//div[@data-value='" + value + "']");
    }
    
    // Select by index (for standard select)
    public void selectByIndex(By selectLocator, int index) {
        Select select = new Select(DriverManager.getDriver().findElement(selectLocator));
        select.selectByIndex(index);
    }
    
    // Select by text (for standard select)
    public void selectByText(By selectLocator, String text) {
        Select select = new Select(DriverManager.getDriver().findElement(selectLocator));
        select.selectByVisibleText(text);
    }
    
    // Click custom dropdown option
    public void clickDropdownOption(String optionText) {
        click(DROPDOWN_TOGGLE);
        waitForElementVisible(DROPDOWN_MENU);
        click(getDropdownOption(optionText));
    }
}
```

### 6.5 DatePicker Locators

```java
public class DatePickerLocators {
    
    private static final By DATEPICKER_INPUT = By.id("dateInput");
    private static final By DATEPICKER_CALENDAR = By.cssSelector(".datepicker-calendar");
    private static final By DATEPICKER_MONTH_YEAR = By.cssSelector(".datepicker-month-year");
    private static final By DATEPICKER_PREV = By.cssSelector(".datepicker-prev");
    private static final By DATEPICKER_NEXT = By.cssSelector(".datepicker-next");
    private static final By DATEPICKER_TODAY = By.cssSelector(".datepicker-today");
    private static final By DATEPICKER_CLEAR = By.cssSelector(".datepicker-clear");
    
    // Day cells
    private static final By DATEPICKER_DAYS = By.cssSelector(".datepicker-days td");
    private static final By DATEPICKER_MONTHS = By.cssSelector(".datepicker-months td");
    private static final By DATEPICKER_YEARS = By.cssSelector(".datepicker-years td");
    
    // Get specific date
    public By getDateCell(int day) {
        return By.xpath("//div[contains(@class,'datepicker')]//td[@data-day='" + day + "']");
    }
    
    // Get month
    public By getMonthCell(String month) {
        return By.xpath("//div[contains(@class,'datepicker-months')]//span[contains(text(),'" + month + "')]");
    }
    
    // Get year
    public By getYearCell(String year) {
        return By.xpath("//div[contains(@class,'datepicker-years')]//span[contains(text(),'" + year + "')]");
    }
    
    // Select date
    public void selectDate(int day, int month, int year) {
        click(DATEPICKER_INPUT);
        waitForElementVisible(DATEPICKER_CALENDAR);
        
        // Navigate to correct year
        navigateToYear(year);
        
        // Navigate to correct month
        navigateToMonth(month);
        
        // Click day
        click(getDateCell(day));
    }
}
```

---

## 7. Locator Performance

### 7.1 Performance Comparison

| Locator Type | Speed | Readability | Flexibility |
|--------------|-------|-------------|------------|
| **ID** | Fastest | Good | Low |
| **CSS Class** | Fast | Good | Medium |
| **CSS Attribute** | Fast | Good | High |
| **Name** | Fast | Good | Low |
| **XPath Position** | Medium | Poor | Medium |
| **XPath Text** | Medium | Good | High |
| **XPath Complex** | Slow | Poor | Very High |

### 7.2 Performance Best Practices

```java
// ✅ TỐT: Direct ID lookup
private static final By FORM = By.id("mainForm");

// ✅ TỐT: CSS with single attribute
private static final By BTN = By.cssSelector("button#submitBtn");

// ✅ TỐT: Short XPath with index
private static final By ROW = By.xpath("//table//tr[1]");

// ❌ CHẬM: Deep nested XPath
private static final By EL_SLOW = By.xpath(
    "/html/body/div[1]/div[2]/div[3]/form/div[1]/div[1]/input"
);

// ❌ CHẬM: Multiple DOM traversals
private static final By EL_SLOW2 = By.xpath(
    "//div[@class='container']//div[@class='form']//div[@class='field']//input[@type='text']"
);

// ✅ CẢI THIỆN: Start from nearest stable element
private static final By EL_FAST = By.cssSelector("form#mainForm input[type='text']");
```

### 7.3 Caching Locators

```java
public class LoginPage extends BasePage {
    
    // ✅ CACHE: Store element reference
    private final WebElement usernameField;
    private final WebElement passwordField;
    private final WebElement loginButton;
    
    public LoginPage() {
        // Cache locators - don't search multiple times
        this.usernameField = getDriver().findElement(By.id("username"));
        this.passwordField = getDriver().findElement(By.id("password"));
        this.loginButton = getDriver().findElement(By.id("loginBtn"));
    }
    
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
    
    // ❌ KHÔNG CACHE: Find every time
    public void loginWithoutCache(String username, String password) {
        getDriver().findElement(By.id("username")).sendKeys(username);  // Search each time
        getDriver().findElement(By.id("password")).sendKeys(password); // Search each time
        getDriver().findElement(By.id("loginBtn")).click();            // Search each time
    }
}
```

---

## 8. Locator Maintenance

### 8.1 When to Update Locators

| Trigger | Action Required |
|---------|-----------------|
| Developer changes element attribute | Update locator with new attribute |
| UI redesign | Review and update affected locators |
| Test fails with "Element not found" | Investigate and fix locator |
| Flaky test (intermittent failure) | Make locator more robust |
| Application version upgrade | Full locator review |

### 8.2 Maintenance Process

```
┌─────────────────────────────────────────────────────────────┐
│              LOCATOR MAINTENANCE PROCESS                     │
└─────────────────────────────────────────────────────────────┘

1. IDENTIFY
   └── Run test → Identify failing locator
                    │
                    ▼
2. INVESTIGATE
   └── Check Application UI → Identify new attributes
                    │
                    ▼
3. UPDATE
   └── Update Elements class with new locator
                    │
                    ▼
4. VERIFY
   └── Run test → Verify fix works
                    │
                    ▼
5. PROPAGATE
   └── Check for similar patterns → Update all affected tests
```

### 8.3 Fallback Locators

```java
public class LoginPageElements {
    
    // Primary locator (preferred)
    private static final By TXT_USERNAME = By.cssSelector("[data-testid='username-input']");
    
    // Fallback 1
    private static final By TXT_USERNAME_FB1 = By.id("username");
    
    // Fallback 2
    private static final By TXT_USERNAME_FB2 = By.name("username");
    
    // Fallback 3
    private static final By TXT_USERNAME_FB3 = By.cssSelector("input[type='text'].form-control");
    
    // Try each locator until found
    public WebElement findUsernameField() {
        WebDriver driver = DriverManager.getDriver();
        
        List<By> locators = Arrays.asList(
            TXT_USERNAME,
            TXT_USERNAME_FB1,
            TXT_USERNAME_FB2,
            TXT_USERNAME_FB3
        );
        
        for (By locator : locators) {
            try {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    Log.info("Found element with locator: " + locator);
                    return element;
                }
            } catch (NoSuchElementException e) {
                Log.debug("Locator not found: " + locator);
            }
        }
        
        throw new ElementNotFoundException(
            "Could not find username field with any known locator"
        );
    }
}
```

---

## 9. Common Patterns

### 9.1 Visibility Checks

```java
// Check if element exists (doesn't throw)
public boolean isElementPresent(By locator) {
    return !DriverManager.getDriver()
        .findElements(locator)
        .isEmpty();
}

// Check if element is visible
public boolean isElementVisible(By locator) {
    try {
        WebElement element = DriverManager.getDriver().findElement(locator);
        return element.isDisplayed();
    } catch (NoSuchElementException e) {
        return false;
    }
}

// Check if element is clickable
public boolean isElementClickable(By locator) {
    try {
        WaitHelper.waitForClickable(locator, 5);
        return true;
    } catch (TimeoutException e) {
        return false;
    }
}
```

### 9.2 Wait Patterns

```java
// Wait for element to be visible
public void waitForVisible(By locator) {
    WaitHelper.waitForVisibility(locator);
}

// Wait for element to be clickable
public void waitForClickable(By locator) {
    WaitHelper.waitForClickable(locator);
}

// Wait for element to contain text
public void waitForText(By locator, String expectedText) {
    WaitHelper.waitForCondition(
        ExpectedConditions.textToBePresentInElementLocated(locator, expectedText)
    );
}

// Wait for URL to contain
public void waitForUrlContains(String urlPart) {
    WaitHelper.waitForUrlContains(urlPart);
}

// Wait for page title
public void waitForPageTitle(String expectedTitle) {
    WaitHelper.waitForTitle(expectedTitle);
}

// Wait for element to disappear
public void waitForElementToDisappear(By locator) {
    WaitHelper.waitForInvisibility(locator);
}
```

### 9.3 Action Patterns

```java
// Click with JavaScript fallback
public void jsClick(By locator) {
    WebElement element = DriverManager.getDriver().findElement(locator);
    JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
    js.executeScript("arguments[0].click();", element);
}

// Scroll into view
public void scrollIntoView(By locator) {
    WebElement element = DriverManager.getDriver().findElement(locator);
    JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
    js.executeScript("arguments[0].scrollIntoView(true);", element);
}

// Hover over element
public void hoverOver(By locator) {
    Actions actions = new Actions(DriverManager.getDriver());
    WebElement element = DriverManager.getDriver().findElement(locator);
    actions.moveToElement(element).perform();
}

// Double click
public void doubleClick(By locator) {
    Actions actions = new Actions(DriverManager.getDriver());
    WebElement element = DriverManager.getDriver().findElement(locator);
    actions.doubleClick(element).perform();
}

// Drag and drop
public void dragAndDrop(By source, By target) {
    Actions actions = new Actions(DriverManager.getDriver());
    WebElement sourceElement = DriverManager.getDriver().findElement(source);
    WebElement targetElement = DriverManager.getDriver().findElement(target);
    actions.dragAndDrop(sourceElement, targetElement).perform();
}
```

---

## 10. Anti-Patterns to Avoid

### 10.1 Fragile Locators

```java
// ❌ TRÁNH: Absolute XPath
private static final By BTN = By.xpath("/html/body/div[1]/div[2]/div[3]/button[1]");

// ✅ THAY: Relative XPath với attributes
private static final By BTN = By.xpath("//button[@id='submitBtn']");

// ❌ TRÁNH: Index-based không cần thiết
private static final By DIV = By.xpath("//div[@class='container'][1]/div[2]/div[3]");

// ✅ THAY: Use meaningful attributes
private static final By DIV = By.xpath("//div[@class='main-content']");

// ❌ TRÁNH: Text-based không chính xác
private static final By LNK = By.xpath("//a[text()='Click Here To Continue']");

// ✅ THAY: Combine with stable attributes
private static final By LNK = By.xpath("//a[@id='continueLink' and contains(text(),'Continue')]");
```

### 10.2 Slow Locators

```java
// ❌ TRÁNH: Wildcard search
private static final By EL = By.xpath("//div[contains(@class,'*')]");

// ❌ TRÁNH: //* usage
private static final By EL = By.xpath("//*[@class='container']");

// ✅ THAY: Be specific
private static final By EL = By.cssSelector("div.container");

// ❌ TRÁNH: Too many conditions
private static final By EL = By.xpath(
    "//div[@class='a'][@id='b'][@data-x='c'][@data-y='d'][@style='e']"
);

// ✅ THAY: Use sufficient conditions
private static final By EL = By.cssSelector("div.a#b");
```

### 10.3 Hard-to-Maintain Patterns

```java
// ❌ TRÁNH: Complex regex-like patterns
private static final By EL = By.xpath(
    "//div[starts-with(@id,'test') and contains(@class,'item') and not(contains(@style,'none'))]"
);

// ✅ THAY: Simpler pattern + validation
private static final By EL = By.cssSelector("[data-testid^='test-item']");

// ❌ TRÁNH: Position-based without stable parent
private static final By EL = By.xpath("//tr[1]/td[1]/div[2]/span");

// ✅ THAY: Use stable parent + position
private static final By EL = By.xpath("//table[@id='mainTable']//tr[1]//td[1]//span");
```

---

## 11. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer | Initial locator strategy |
