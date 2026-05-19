# Project Context - Selenium HRM Automation Testing System

## 1. Project Overview

### 1.1 Project Name
**Selenium HRM** - Human Resource Management System Automation Testing

### 1.2 Project Purpose
Xây dựng hệ thống automation testing toàn diện cho ứng dụng HRM, từ phân tích yêu cầu, thiết kế test case, đến tự động hóa và báo cáo. Hệ thống này cho phép QA Engineer nhập requirement và nhận đầy đủ:
- Kết quả phân tích (Analysis)
- Test case manual (Testcase)
- Kế hoạch automation (Automation Plan)
- Source code tự động (Generated Code)
- Báo cáo review (Review Report)
- Báo cáo thực thi (Execution Report)

### 1.3 Scope
| Area | Coverage |
|------|----------|
| **Web Testing** | UI Automation với Selenium WebDriver |
| **API Testing** | REST API với Rest-Assured |
| **Functional Testing** | Smoke, Sanity, Regression, End-to-End |
| **Cross-browser Testing** | Chrome, Firefox, Edge |
| **Multi-environment** | Local, QA, Staging, Production |

---

## 2. Technology Stack

### 2.1 Core Technologies
| Component | Technology | Version |
|-----------|------------|---------|
| **Language** | Java | 21 |
| **Build Tool** | Maven | - |
| **Test Framework** | TestNG | 7.4.0 |
| **Web Automation** | Selenium WebDriver | 4.35.0 |
| **API Testing** | Rest-Assured | 5.3.2 |
| **HTML Reports** | Extent Reports | 5.1.1 |
| **Test Reports** | Allure TestNG | 2.30.0 |
| **Logging** | Log4j2 | 2.24.3 |
| **Excel Handling** | Apache POI | 5.2.5 |
| **Test Data** | DataFaker | 2.5.3 |

### 2.2 Supported Browsers
- Google Chrome (default)
- Mozilla Firefox
- Microsoft Edge

### 2.3 Supported Environments
- `local` - Development local environment
- `qa` - QA/Testing environment
- `staging` - Staging/Pre-production environment

---

## 3. AI Workflow Integration

### 3.1 Complete Workflow Pipeline

```
┌─────────────────────────────────────────────────────────────────────────┐
│                         AI AUTOMATION PIPELINE                          │
└─────────────────────────────────────────────────────────────────────────┘

    [REQS] ──▶ [AI Requirement Analyst] ──▶ [analysis-result.md]
                                                              │
                                                              ▼
                                          [AI Test Designer] ──▶ [testcases.md]
                                                                          │
                                                                          ▼
                                                          [AI Automation Planner]
                                                                          │
                                                                          ▼
                                                          [automation-plan.md]
                                                                          │
                                                                          ▼
                                                          [AI Automation Engineer]
                                                                          │
                                                                          ▼
                                                          [Generated Source Code]
                                                                          │
                                                                          ▼
                                                               [AI Reviewer]
                                                                          │
                                                                          ▼
                                                            [review-report.md]
                                                                          │
                                                                          ▼
                                                                [AI Reporter]
                                                                          │
                                                                          ▼
                                                           [execution-report.md]

```

### 3.2 Role Definitions

| Role | Responsibility | Output |
|------|----------------|--------|
| **AI Requirement Analyst** | Phân tích yêu cầu, xác định scope, phân tích rủi ro | `analysis-result.md` |
| **AI Test Designer** | Thiết kế test case, xác định test data, mapping requirements | `testcases.md` |
| **AI Automation Planner** | Lập kế hoạch automation, phân chia module, estimate effort | `automation-plan.md` |
| **AI Automation Engineer** | Viết source code tự động theo framework | `Generated Source Code` |
| **AI Reviewer** | Code review, quality check, best practices verification | `review-report.md` |
| **AI Reporter** | Tổng hợp kết quả, tạo báo cáo execution | `execution-report.md` |

---

## 4. Testing Strategy

### 4.1 Testing Pyramid

```
                        ┌───────────────┐
                        │   E2E Tests   │  ← Few, Slow, High Confidence
                        │   (10-15%)    │
                        ├───────────────┤
                        │ Integration   │  ← Medium, Medium Confidence
                        │   Tests       │
                        │   (20-30%)    │
                        ├───────────────┤
                        │   API Tests   │  ← Many, Fast, Stable
                        │   (30-40%)    │
                        ├───────────────┤
                        │  Unit Tests   │  ← Most, Fastest, Isolated
                        │   (40-50%)    │
                        └───────────────┘
```

### 4.2 Test Levels

| Level | Purpose | Entry Criteria | Exit Criteria |
|-------|---------|----------------|---------------|
| **Unit Test** | Test individual components | Code completed | All assertions pass |
| **API Test** | Verify API contracts | API deployed | Response validation pass |
| **Integration Test** | Verify system integration | Modules integrated | Data flow validated |
| **E2E Test** | Verify complete user flows | System ready | Business scenarios pass |

### 4.3 Test Types Coverage

#### 4.3.1 Functional Testing
- **Smoke Testing**: Quick verification of critical paths
- **Sanity Testing**: Verify specific functionality works
- **Regression Testing**: Ensure existing features not broken
- **End-to-End Testing**: Complete user journey validation

#### 4.3.2 Non-Functional Testing
| Type | Coverage |
|------|----------|
| **Performance** | Response time measurement |
| **Security** | Authentication, authorization, data protection |
| **Compatibility** | Cross-browser, cross-device |
| **Usability** | UI/UX validation, accessibility |

### 4.4 Test Execution Strategy

| Phase | Test Scope | Frequency | Environment |
|-------|------------|-----------|-------------|
| **Development** | Unit + API | Every commit | Local |
| **PR/Merge** | Smoke + API | Every PR | QA |
| **Release Candidate** | Regression | Weekly | QA/Staging |
| **Production** | Sanity + E2E | Pre/post deploy | Production |

---

## 5. Manual Testing Approach

### 5.1 Manual Test Case Structure

```markdown
| Field | Description |
|-------|-------------|
| Test Case ID | Unique identifier (e.g., TC-LOGIN-001) |
| Module | HRM module name |
| Feature | Feature being tested |
| Title | Short descriptive title |
| Pre-conditions | Setup requirements |
| Test Steps | Numbered action sequence |
| Test Data | Input data required |
| Expected Result | Expected outcome |
| Actual Result | (To be filled during execution) |
| Status | Pass/Fail/Blocked |
| Priority | High/Medium/Low |
| Severity | Critical/Major/Minor |
```

### 5.2 Manual Test Process

```
1. REQUIREMENT ANALYSIS
   ├── Review requirements document
   ├── Identify testable items
   ├── Clarify ambiguities with BA/PM
   └── Create traceability matrix

2. TEST DESIGN
   ├── Identify test conditions
   ├── Design test cases
   ├── Prepare test data
   ├── Review with team
   └── Get sign-off

3. TEST EXECUTION
   ├── Setup test environment
   ├── Execute test cases
   ├── Log defects
   ├── Retest fixed defects
   └── Update test results

4. TEST Closure
   ├── Analyze test results
   ├── Prepare test summary
   ├── Archive test artifacts
   └── Conduct retrospective
```

### 5.3 Test Case Design Techniques

| Technique | Description | Applicable When |
|-----------|-------------|-----------------|
| **Equivalence Partitioning** | Group inputs with similar behavior | Multiple input values |
| **Boundary Value Analysis** | Test edge cases at boundaries | Numeric/range inputs |
| **Decision Table Testing** | Test combinations of conditions | Complex business rules |
| **State Transition Testing** | Test system state changes | Workflow-based systems |
| **Pairwise Testing** | Test all pairs of parameters | Multiple parameters |
| **Use Case Testing** | Test from user scenarios | Business workflows |

---

## 6. Automation Testing Approach

### 6.1 Automation Framework Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                     AUTOMATION FRAMEWORK                        │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│  ┌──────────────┐    ┌──────────────┐    ┌──────────────┐       │
│  │   Page       │    │    Base      │    │   Helper     │       │
│  │   Objects    │◀───│    Pages     │◀───│   Utilities  │       │
│  └──────────────┘    └──────────────┘    └──────────────┘       │
│         │                   │                   │               │
│         │                   │                   │               │
│         ▼                   ▼                   ▼               │
│  ┌──────────────────────────────────────────────────────┐       │
│  │                    BASE TEST CLASS                    │       │
│  │  - Driver Management                                  │       │
│  │  - TestNG Annotations                                 │       │
│  │  - Reporting Integration                             │       │
│  └──────────────────────────────────────────────────────┘       │
│                            │                                      │
│                            ▼                                      │
│  ┌──────────────────────────────────────────────────────┐       │
│  │                    TEST CLASSES                       │       │
│  │  - Test Methods (@Test)                               │       │
│  │  - Page Object Interactions                          │       │
│  │  - Assertions                                        │       │
│  └──────────────────────────────────────────────────────┘       │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

### 6.2 Page Object Model Structure

```
Page Objects/
├── base/
│   ├── BaseUI.java          # Test base - driver setup, annotations
│   └── BasePage.java        # Page base - helper delegation
├── pages/
│   ├── {Module}/
│   │   ├── {PageName}Page.java           # Main page object
│   │   ├── {PageName}Locators.java       # Locators
│   │   └── {PageName}Actions.java        # Page-specific actions
│   └── CommonPage.java                   # Shared elements
└── components/
    └── {ComponentName}.java              # Reusable UI components
```

### 6.3 Locator Strategy (Priority Order)

| Priority | Locator Type | Example | When to Use |
|----------|--------------|---------|-------------|
| 1 | `data-testid` | `By.dataTestid("login-btn")` | Preferred - dedicated for testing |
| 2 | `data-cy` | `By.dataCy("username-input")` | Cypress-style testing attributes |
| 3 | `data-qa` | `By.dataQa("submit-form")` | QA-specific attributes |
| 4 | `id` | `By.id("username")` | Unique identifier |
| 5 | `name` | `By.name("email")` | Form elements |
| 6 | `css` | `By.cssSelector(".class")` | Complex elements, performance |
| 7 | `xpath` | `By.xpath("//button")` | Last resort, when others unavailable |

### 6.4 Automation Coding Standards

```java
// CLASS NAMING
public class LoginPage extends BasePage { }
public class LoginPageLocators { }  // Locators only
public class LoginActions { }       // Business actions

// METHOD NAMING - Action Verb + Object
public void clickLoginButton() { }
public void enterUsername(String username) { }
public void selectRoleFromDropdown(String role) { }

// LOCATOR NAMING
private static final By TXT_USERNAME = By.id("username");
private static final By BTN_LOGIN = By.cssSelector(".btn-login");
private static final By LBL_ERROR_MESSAGE = By.xpath("//div[@class='error']");

// ASSERTION METHODS
public void verifyPageTitle(String expected) { }
public void verifyElementDisplayed(By locator) { }
public void verifyTextEquals(String actual, String expected) { }
```

### 6.5 Test Data Management

| Type | Storage | Access Method |
|------|---------|---------------|
| **Static Data** | Constants/Enums | Direct reference |
| **Configuration** | .properties files | PropertiesHelper |
| **Test Data** | Excel/JSON files | ExcelHelper/JSON |
| **Dynamic Data** | DataFaker | On-the-fly generation |
| **API Data** | Database/API | Direct query |

### 6.6 Reporting & Logging

```
Reporting Layers/
├── Extent Reports (HTML)
│   ├── Test-level reporting
│   ├── Screenshot on failure
│   └── Step-by-step logging
├── Allure Reports (HTML)
│   ├── Execution history
│   ├── Trend analysis
│   └── Attachment support
└── Log Files (Log4j2)
    ├── DEBUG - Detailed execution
    ├── INFO - Test progress
    ├── WARN - Warnings
    └── ERROR - Failures
```

---

## 7. Module Structure

### 7.1 Expected HRM Modules

| Module | Description | Test Priority |
|--------|-------------|---------------|
| **Login/Authentication** | User login, logout, session management | Critical |
| **Dashboard** | Overview, widgets, analytics | High |
| **Employee Management** | CRUD employees, profiles | Critical |
| **Leave Management** | Leave requests, approvals | Critical |
| **Attendance** | Clock in/out, time tracking | High |
| **Payroll** | Salary calculation, processing | Critical |
| **Recruitment** | Job postings, candidate management | Medium |
| **Performance** | Reviews, appraisals, goals | Medium |
| **Reports** | Report generation, exports | Medium |
| **Settings** | System configuration | Low |

### 7.2 Module Test Coverage Template

```markdown
## {Module Name}

### Features Covered
1. Feature 1
2. Feature 2

### Test Cases Count
| Type | Count |
|------|-------|
| Positive | XX |
| Negative | XX |
| Boundary | XX |
| Total | XX |

### Automated Cases
| Test Case ID | Status | Framework Coverage |
|--------------|--------|-------------------|
| TC-XXX-001 | ✅ Automated | Full POM |
| TC-XXX-002 | ⏳ Manual Only | - |
```

---

## 8. Defect Management

### 8.1 Defect Lifecycle

```
NEW → OPEN → IN PROGRESS → RESOLVED → VERIFIED → CLOSED
 │                            │
 └── REOPENED ────────────────┘
```

### 8.2 Defect Report Template

| Field | Description |
|-------|-------------|
| **Defect ID** | Auto-generated unique ID |
| **Title** | Short summary |
| **Description** | Detailed steps to reproduce |
| **Severity** | Critical/Major/Minor |
| **Priority** | High/Medium/Low |
| **Module** | Affected module |
| **Test Environment** | Environment where found |
| **Test Data** | Data used |
| **Expected Result** | What should happen |
| **Actual Result** | What actually happened |
| **Attachments** | Screenshots, logs |
| **Assigned To** | Developer |
| **Status** | Current status |

---

## 9. Quality Gates

### 9.1 Pre-Release Quality Criteria

| Criteria | Threshold | Metric |
|----------|-----------|--------|
| Test Coverage | ≥ 80% | Automated test coverage |
| Defect Leakage | ≤ 5% | Production defects / Total defects |
| Code Review | 100% | All code reviewed |
| Build Success | 100% | CI/CD pipeline pass |
| Test Pass Rate | ≥ 95% | Passing automated tests |

### 9.2 Automation Suitability Criteria

| Criteria | Suitable for Automation | Manual Only |
|----------|------------------------|-------------|
| **Frequency** | High (regression, daily) | One-time |
| **Stability** | Stable UI elements | Frequently changing |
| **Complexity** | Repetitive tasks | Exploratory testing |
| **Data** | Deterministic data | Complex scenarios |
| **Execution** | Multiple browsers/environments | Human judgment |

---

## 10. Deliverables

### 10.1 Per Requirement Analysis

| Deliverable | Format | Location |
|--------------|--------|----------|
| Analysis Result | Markdown | `outputs/analysis/` |
| Traceability Matrix | Excel/Markdown | `outputs/analysis/` |
| Risk Assessment | Markdown | `outputs/analysis/` |

### 10.2 Per Test Design

| Deliverable | Format | Location |
|--------------|--------|----------|
| Test Cases | Markdown/Excel | `outputs/testcase/` |
| Test Data | Excel/JSON | `outputs/testcase/` |
| Test Coverage Report | Markdown | `outputs/testcase/` |

### 10.3 Per Automation

| Deliverable | Format | Location |
|--------------|--------|----------|
| Automation Plan | Markdown | `outputs/automation/` |
| Source Code | Java | `src/test/java/` |
| Test Data Files | Excel/JSON | `src/test/resources/` |

### 10.4 Per Execution

| Deliverable | Format | Location |
|--------------|--------|----------|
| Review Report | Markdown | `outputs/review/` |
| Execution Report | HTML/Markdown | `outputs/reports/` |
| Test Logs | Log4j2 files | `logs/` |
| Screenshots | PNG | `screenshots/` |
| Extent Report | HTML | `extentReports/` |

---

## 11. Entry & Exit Criteria

### 11.1 Requirement Analysis Entry
- [ ] Requirements document received
- [ ] Clarification questions resolved
- [ ] Scope defined

### 11.2 Requirement Analysis Exit
- [ ] Analysis document completed
- [ ] All requirements mapped to test cases
- [ ] Risks identified and documented

### 11.3 Test Design Entry
- [ ] Analysis approved
- [ ] Requirements finalized
- [ ] Test environment accessible

### 11.4 Test Design Exit
- [ ] Test cases reviewed and approved
- [ ] Test data prepared
- [ ] Traceability matrix complete

### 11.5 Automation Entry
- [ ] Test cases finalized
- [ ] Framework ready
- [ ] Environment configured

### 11.6 Automation Exit
- [ ] Code follows coding standards
- [ ] Code reviewed
- [ ] All test cases automated
- [ ] Test data configured

### 11.7 Execution Entry
- [ ] Build successful
- [ ] Environment ready
- [ ] Test data loaded

### 11.8 Execution Exit
- [ ] All tests executed
- [ ] Defects logged
- [ ] Reports generated
- [ ] Test summary approved

---

## 12. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | QA Engineer | Initial version |
