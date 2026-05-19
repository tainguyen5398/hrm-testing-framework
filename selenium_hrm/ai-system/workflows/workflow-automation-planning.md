# Workflow: Automation Planning

## 1. Overview

### 1.1 Purpose
Workflow lập kế hoạch automation (Automation Planning) là bước thứ ba trong QA process, chuẩn bị chi tiết cho việc implement automation. Workflow này hướng dẫn QA Engineer/Senior QA lập kế hoạch, estimate effort, và đưa ra các quyết định kỹ thuật để đảm bảo:

- **Optimal Resource Allocation**: Phân bổ resources hiệu quả
- **Realistic Timeline**: Timeline khả thi và đáng tin cậy
- **Technical Soundness**: Các quyết định kỹ thuật phù hợp
- **Risk Mitigation**: Lập kế hoạch cho các risks
- **Stakeholder Alignment**: Align expectations với stakeholders

### 1.2 Objectives

| # | Objective | Success Criteria |
|---|-----------|-----------------|
| 1 | Complete Planning | 100% test cases có automation plan |
| 2 | Accurate Estimation | Estimate within ±20% of actual |
| 3 | Technical Clarity | Stack, approach, framework decisions documented |
| 4 | Resource Plan | Team capacity và skills mapped |
| 5 | Risk Coverage | All risks có mitigation plan |

### 1.3 Workflow Position

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                     AI AUTOMATION PIPELINE - WORKFLOW 3                      │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   INPUT:                                                                    │
│   ├── testcases.md (từ Workflow 2: Test Case Design)                         │
│   ├── Traceability Matrix                                                  │
│   ├── Test Coverage Report                                                 │
│   └── Test Data Files                                                       │
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │                  AUTOMATION PLANNING                               │     │
│   │  ┌─────────────────────────────────────────────────────────┐   │     │
│   │  │ 1. Analyze Automation Requirements                      │   │     │
│   │  │ 2. Assess Automation Suitability                        │   │     │
│   │  │ 3. Determine Technical Approach                        │   │     │
│   │  │ 4. Create Module Breakdown & Effort Estimate            │   │     │
│   │  │ 5. Plan Sprint/Iteration Schedule                      │   │     │
│   │  │ 6. Define Infrastructure & Environment                  │   │     │
│   │  │ 7. Document Automation Plan                            │   │     │
│   │  └─────────────────────────────────────────────────────────┘   │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│                                                                             │
│   OUTPUT:                                                                   │
│   ├── automation-plan.md (Master Automation Plan)                            │
│   ├── sprint-backlog.md (Sprint-wise breakdown)                            │
│   └── effort-estimate.md (Detailed effort estimates)                       │
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │              ▶ NEXT WORKFLOW: Code Generation                    │     │
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
| 2 | Traceability Matrix | Markdown/Excel | Workflow 2: Test Case Design | Required |
| 3 | Test Coverage Report | Markdown | Workflow 2: Test Case Design | Required |
| 4 | Test Data Files | Excel/JSON | Workflow 2: Test Case Design | Required |
| 5 | Business Rules Register | Markdown | Workflow 1: Requirement Analysis | Recommended |
| 6 | Risk Register | Markdown | Workflow 1: Requirement Analysis | Recommended |
| 7 | Framework Rules | Document | AI Context | Required |
| 8 | Coding Standards | Document | AI Context | Required |

### 2.2 Prerequisites Checklist

```
PRE-WORKFLOW CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

□ Test Case Suite approved by QA Lead
□ Traceability Matrix complete (100% coverage)
□ Test data files validated
□ Framework Rules reviewed and understood
□ Coding Standards reviewed and understood
□ Team skills assessment completed
□ CI/CD environment status confirmed
□ Tool licenses/access verified (if applicable)

```

---

## 3. Step 1: Analyze Automation Requirements

### 3.1 Requirement Analysis Framework

```
AUTOMATION REQUIREMENT ANALYSIS
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│ REQUIREMENT CATEGORIZATION                                                 │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  1. FUNCTIONAL REQUIREMENTS                                                │
│     ├── Authentication flows                                               │
│     ├── CRUD operations                                                   │
│     ├── Search and filter                                                 │
│     ├── Form submissions                                                  │
│     └── Report generation                                                 │
│                                                                             │
│  2. INTEGRATION REQUIREMENTS                                              │
│     ├── API integrations                                                 │
│     ├── Database operations                                               │
│     ├── External system calls                                             │
│     └── Third-party service mocks                                         │
│                                                                             │
│  3. NON-FUNCTIONAL REQUIREMENTS                                            │
│     ├── Performance thresholds                                            │
│     ├── Security validations                                              │
│     ├── Cross-browser testing                                             │
│     └── Responsive design testing                                         │
│                                                                             │
│  4. INFRASTRUCTURE REQUIREMENTS                                           │
│     ├── Test environment access                                           │
│     ├── Test data management                                              │
│     ├── CI/CD pipeline integration                                        │
│     └── Parallel execution capability                                      │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 3.2 Test Case Classification

```markdown
## Test Case Classification Analysis

### Classification Matrix

| Classification | Criteria | Description |
|---------------|----------|-------------|
| **Automatable** | Can execute automatically | Stable UI, clear expected results |
| **Conditionally Automatable** | Needs special setup | External dependencies, time-based |
| **Manual Only** | Requires human judgment | Exploratory, UX validation |
| **Complex Automation** | High effort required | Dynamic elements, complex workflows |

### Example: Login Module Classification

| Test Case | Classification | Reason | Effort |
|-----------|---------------|--------|--------|
| TC-LOGIN-001: Valid login | Automatable | Stable UI, clear steps | Low |
| TC-LOGIN-002: Invalid password | Automatable | Clear expected results | Low |
| TC-LOGIN-003: Account lock | Automatable | Time-based but stable | Medium |
| TC-LOGIN-004: SSO login | Manual Only | Third-party dependency | N/A |
| TC-LOGIN-005: CAPTCHA handling | Manual Only | Not automatable | N/A |

```

### 3.3 Effort Factors Analysis

```
EFFORT FACTORS MATRIX
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│ COMPLEXITY FACTORS                                                          │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  LOW COMPLEXITY (1 point)          MEDIUM COMPLEXITY (2 points)           │
│  ├── Simple form submission        ├── Multi-step workflows                │
│  ├── Basic CRUD                   ├── Conditional logic                    │
│  ├── Standard dropdowns           ├── Dynamic elements                     │
│  ├── Static tables                ├── AJAX loading                        │
│  └── Clear expected results       ├── Multiple assertions                 │
│                                   ├── Date/time operations                 │
│                                   └── File upload/download                 │
│                                                                             │
│  HIGH COMPLEXITY (3 points)                                                 │
│  ├── Nested iframes                                                          │
│  ├── Complex JavaScript interactions                                        │
│  ├── Drag-and-drop operations                                               │
│  ├── Multiple browser windows                                               │
│  ├── Third-party integrations                                              │
│  ├── CAPTCHA/biometric handling                                             │
│  └── Real-time data synchronization                                         │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

EFFORT SCORE CALCULATION:
├── Effort Score = Sum of Complexity Points
├── 1-2: Low Effort (1-2 hours per test case)
├── 3-4: Medium Effort (3-5 hours per test case)
├── 5-6: High Effort (6-10 hours per test case)
└── 7+: Very High Effort (10+ hours, consider manual)

```

---

## 4. Step 2: Assess Automation Suitability

### 4.1 Automation Suitability Criteria

```
AUTOMATION SUITABILITY ASSESSMENT
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│ SUITABILITY MATRIX                                                         │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│                         HIGH SUITABILITY                                    │
│    ┌────────────────────────────────────────────────────────────────┐    │
│    │                                                                │    │
│    │  ✅ High ROI                                                   │    │
│    │     ├── Regression suites (run frequently)                     │    │
│    │     ├── Critical path tests (P0)                               │    │
│    │     ├── Smoke tests (run on every build)                       │    │
│    │     └── Data-driven tests (multiple data sets)                 │    │
│    │                                                                │    │
│    │  ✅ Stable UI                                                  │    │
│    │     ├── Established applications                               │    │
│    │     ├── Minimal UI changes                                     │    │
│    │     └── Clear element identifiers                              │    │
│    │                                                                │    │
│    │  ✅ Repeatable                                                  │    │
│    │     ├── Same result on repeated runs                           │    │
│    │     ├── Independent test cases                                  │    │
│    │     └── No manual intervention required                         │    │
│    │                                                                │    │
│    └────────────────────────────────────────────────────────────────┘    │
│                                                                             │
│                         LOW SUITABILITY                                     │
│    ┌────────────────────────────────────────────────────────────────┐    │
│    │                                                                │    │
│    │  ❌ Low ROI                                                    │    │
│    │     ├── One-time tests                                         │    │
│    │     ├── Rarely run scenarios                                   │    │
│    │     └── Exploratory testing                                    │    │
│    │                                                                │    │
│    │  ❌ Unstable UI                                                │    │
│    │     ├── Rapidly changing UI                                    │    │
│    │     ├── Third-party components                                 │    │
│    │     └── Dynamic content (ads, banners)                         │    │
│    │                                                                │    │
│    │  ❌ Complex Setup                                               │    │
│    │     ├── Heavy external dependencies                            │    │
│    │     ├── Manual data preparation                                │    │
│    │     └── Hardware/sensor interactions                           │    │
│    │                                                                │    │
│    └────────────────────────────────────────────────────────────────┘    │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 4.2 ROI Calculation

```markdown
## Automation ROI Analysis

### ROI Formula

```
ROI = (Manual Testing Cost - Automation Testing Cost) / Automation Testing Cost × 100%

Where:
├── Manual Testing Cost = (Hours × Rate × Runs)
└── Automation Testing Cost = (Development Hours × Rate) + (Maintenance Hours × Rate)
```

### Example: Login Module ROI

| Factor | Manual | Automated |
|--------|--------|-----------|
| Development Effort | 0 | 40 hours |
| Test Cases | 15 | 15 |
| Execution Time per Run | 30 min | 5 min |
| Runs per Sprint | 10 | 10 |
| Hourly Rate | $30 | $30 |
| **Cost per Sprint** | **$2,250** | **$40 + $50 = $90** |

**ROI = ($2,250 - $90) / $90 × 100% = 2,400%**

### Break-Even Point

```
Break-Even Run = Development Effort / (Manual Time - Automation Time)

Break-Even Run = 40 hours / (30 min - 5 min) = 40 hours / 0.42 hours
Break-Even Run = 96 runs

After 96 runs, automation becomes cost-effective.
For a project running 10 times per sprint:
Break-Even = 9.6 sprints (approximately 4.8 months)
```

```

### 4.3 Suitability Assessment Matrix

```markdown
## Suitability Assessment Matrix

| Module | Total TCs | Automatable | Manual Only | Suitability Score | Decision |
|--------|-----------|------------|------------|------------------|----------|
| Login | 15 | 13 | 2 | 87% (High) | Full Automation |
| Employee | 45 | 38 | 7 | 84% (High) | Full Automation |
| Leave | 35 | 30 | 5 | 86% (High) | Full Automation |
| Payroll | 20 | 12 | 8 | 60% (Medium) | Selective Automation |
| Reports | 25 | 20 | 5 | 80% (High) | Full Automation |
| Settings | 10 | 5 | 5 | 50% (Medium) | Selective Automation |
| **Total** | **150** | **118** | **32** | **79%** | **Strong Candidate** |

Suitability Score = (Automatable TCs / Total TCs) × 100%

Decision Thresholds:
├── ≥ 80%: Strong Candidate → Full automation
├── 60-79%: Good Candidate → Selective automation
├── 40-59%: Marginal → Automate critical paths only
└── < 40%: Poor Candidate → Manual testing
```

---

## 5. Step 3: Determine Technical Approach

### 5.1 Framework Selection

```
TECHNICAL APPROACH DECISION MATRIX
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│ FRAMEWORK SELECTION                                                         │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  CURRENT PROJECT STACK (Based on Framework Rules):                          │
│  ┌─────────────────────────────────────────────────────────────────────┐   │
│  │  Language      │ Java 21                                           │   │
│  │  Build Tool    │ Maven                                             │   │
│  │  Test Framework│ TestNG 7.4.0                                      │   │
│  │  Web Automation│ Selenium WebDriver 4.35.0                          │   │
│  │  API Testing   │ Rest-Assured 5.3.2                                 │   │
│  │  Reporting     │ Extent Reports 5.1.1, Allure TestNG 2.30.0         │   │
│  │  Logging       │ Log4j2 2.24.3                                      │   │
│  │  Data Handling │ Apache POI 5.2.5, DataFaker 2.5.3                  │   │
│  └─────────────────────────────────────────────────────────────────────┘   │
│                                                                             │
│  RATIONALE:                                                                 │
│  ├── Java 21: Strong typing, good for maintainability                     │
│  ├── TestNG: Rich annotation model, data providers, parallel execution     │
│  ├── Selenium 4.35: Waits, relative locators, CDP support                 │
│  ├── Rest-Assured: Fluent API, BDD syntax, easy assertions                │
│  └── Extent/Allure: Comprehensive reporting, trend analysis                │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 5.2 Architecture Decisions

```markdown
## Architecture Decisions

### Decision 1: Page Object Model Structure

**Decision:** Use 3-Layer POM Architecture

| Layer | Components | Responsibility |
|-------|------------|----------------|
| **Page Elements** | LoginPageElements | Locator definitions only |
| **Page Actions** | LoginPageActions | Business-level operations |
| **Page Object** | LoginPage | Orchestration, delegation |

**Rationale:**
- Clear separation of concerns
- Easy to maintain when locators change
- Reusable action methods across tests
- Follows Framework Rules

---

### Decision 2: Test Data Management

**Decision:** Hybrid approach - Excel + DataFaker + Properties

| Data Type | Storage | Access Method |
|-----------|---------|---------------|
| Static test data | Properties files | PropertiesHelper |
| Test case data | Excel files | ExcelHelper |
| Dynamic test data | DataFaker | Inline generation |
| API test data | JSON files | Jackson/Gson |

**Rationale:**
- Excel: Easy for non-technical team members to maintain
- Properties: Configuration and credentials
- DataFaker: Unique data for each run
- JSON: API request/response validation

---

### Decision 3: Wait Strategy

**Decision:** Explicit waits with custom helpers

```java
// Preferred wait strategy
waitHelper.waitForElementVisible(locator, 30);
waitHelper.waitForElementClickable(locator, 20);
waitHelper.waitForUrlContains("expected", 30);

// Avoid implicit waits where possible
// Avoid Thread.sleep() - only for debugging
```

**Rationale:**
- Explicit waits are more reliable than implicit
- Custom helpers provide retry logic
- Better control over wait times
- Easier to debug failures

---

### Decision 4: Locator Strategy (Priority Order)

| Priority | Type | Example | When to Use |
|----------|------|---------|-------------|
| 1 | data-testid | By.dataTestid("login-btn") | All new elements |
| 2 | data-cy | By.dataCy("username-input") | Cypress compatibility |
| 3 | id | By.id("username") | Form elements |
| 4 | name | By.name("email") | Form inputs |
| 5 | css | By.cssSelector(".class") | Performance critical |
| 6 | xpath | By.xpath("//button") | Last resort |

```

### 5.3 Tool & Environment Decisions

```markdown
## Tool & Environment Decisions

### Browser Support Matrix

| Browser | Version | Priority | Headless Support |
|---------|---------|----------|------------------|
| Chrome | Latest | Primary | ✅ Yes |
| Firefox | Latest | Secondary | ✅ Yes |
| Edge | Latest | Tertiary | ✅ Yes |

### Environment Matrix

| Environment | Purpose | URL | Data Reset |
|-------------|---------|-----|------------|
| Local | Development | localhost:8080 | Manual |
| QA | Test Execution | qa.hrm.example.com | Daily |
| Staging | Pre-production | staging.hrm.example.com | Weekly |

### CI/CD Integration

| CI Tool | GitHub Actions | Status | Notes |
|---------|---------------|--------|-------|
| Build | ✅ Configured | Maven build | Every commit |
| Unit Tests | ✅ Configured | Maven test | Every commit |
| API Tests | ✅ Configured | Maven test | PR only |
| UI Smoke | ✅ Configured | Maven test | PR + Daily |
| UI Full | ⏳ Pending | Maven test | Daily at night |

```

---

## 6. Step 4: Create Module Breakdown & Effort Estimate

### 6.1 Module Breakdown Structure

```markdown
## Module Breakdown & Effort Estimate

### Module: Authentication (Login)

┌────────────────────────────────────────────────────────────────────────────┐
│ STATISTICS                                                                 │
├────────────────────────────────────────────────────────────────────────────┤
│ Total Test Cases: 15        Automatable: 13        Manual Only: 2        │
│ Estimated Hours: 45          Actual Hours: -        Variance: -          │
└────────────────────────────────────────────────────────────────────────────┘

### Test Case Breakdown

| Category | Count | Avg Hours/TC | Total Hours |
|----------|-------|---------------|-------------|
| Positive Cases | 5 | 1.5 | 7.5 |
| Negative Cases | 4 | 2.0 | 8.0 |
| Boundary Cases | 2 | 2.5 | 5.0 |
| Security Cases | 2 | 3.0 | 6.0 |
| **Subtotal** | **13** | - | **26.5** |

### Component Breakdown

| Component | Type | Hours | Notes |
|-----------|------|-------|-------|
| LoginPage.java | Page Object | 8 | Elements + Actions + Validations |
| LoginTests.java | Test Class | 6 | All test cases |
| LoginData.xlsx | Test Data | 2 | All test data |
| API Login Tests | API Testing | 8 | If separate API module |
| Documentation | Docs | 2 | Code comments, README |
| Review & Fix | QA | 4 | Peer review, bug fixes |
| **Module Total** | - | **30** | - |

### Risk Buffer (20%)

| Risk | Likelihood | Impact | Buffer |
|------|-------------|--------|--------|
| Locator changes | High | Medium | +4 hours |
| Environment issues | Medium | Low | +2 hours |
| Complex auth flow | Low | High | +3 hours |
| **Total Buffer** | - | - | **+9 hours** |

**Total Estimate: 39 hours**

---

### Module: Employee Management

┌────────────────────────────────────────────────────────────────────────────┐
│ STATISTICS                                                                 │
├────────────────────────────────────────────────────────────────────────────┤
│ Total Test Cases: 45        Automatable: 38        Manual Only: 7         │
│ Estimated Hours: 156        Actual Hours: -        Variance: -           │
└────────────────────────────────────────────────────────────────────────────┘

### Component Breakdown

| Component | Type | Hours | Notes |
|-----------|------|-------|-------|
| EmployeePage.java | Page Object | 24 | CRUD operations |
| EmployeeActions.java | Actions | 16 | Business flows |
| EmployeeTests.java | Test Class | 20 | All test cases |
| EmployeeData.xlsx | Test Data | 6 | CRUD + validation data |
| API Employee Tests | API Testing | 20 | CRUD API validation |
| Integration Tests | Integration | 12 | Cross-module flows |
| Documentation | Docs | 4 | Code comments, README |
| Review & Fix | QA | 12 | Peer review, bug fixes |
| **Subtotal** | - | **114** | - |

### Risk Buffer (20%)

| Risk | Likelihood | Impact | Buffer |
|------|-------------|--------|--------|
| Complex form validation | High | High | +8 hours |
| Table pagination | Medium | Medium | +6 hours |
| File upload handling | Medium | Low | +4 hours |
| **Total Buffer** | - | - | **+18 hours** |

**Total Estimate: 132 hours**

```

### 6.2 Summary Effort Estimate

```markdown
## Master Effort Estimate

┌────────────────────────────────────────────────────────────────────────────┐
│ EFFORT SUMMARY BY MODULE                                                    │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  Module              │ TCs   │ Automatable │ Est Hours │ Priority │ Sprint│
│  ────────────────────┼───────┼─────────────┼──────────┼─────────┼───────│
│  Authentication     │   15  │     13      │    39    │   P0    │   1   │
│  Employee Mgmt      │   45  │     38      │   132    │   P0    │  1-2  │
│  Leave Management   │   35  │     30      │   105    │   P1    │  2-3  │
│  Attendance         │   25  │     20      │    70    │   P1    │   3   │
│  Payroll            │   20  │     12      │    56    │   P1    │   4   │
│  Reports            │   25  │     20      │    65    │   P2    │   4   │
│  Settings           │   10  │      5      │    20    │   P3    │   5   │
│  Integration        │   15  │     12      │    42    │   P1    │   5   │
│  ────────────────────┼───────┼─────────────┼──────────┼─────────┼───────│
│  **TOTAL**          │ **190**│   **150**   │  **529** │    -    │  1-5  │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

### Effort by Category

| Category | Hours | Percentage |
|----------|-------|------------|
| Page Objects | 120 | 23% |
| Test Classes | 100 | 19% |
| API Tests | 80 | 15% |
| Test Data | 40 | 8% |
| Integration Tests | 50 | 9% |
| Documentation | 30 | 6% |
| Review & Fix | 70 | 13% |
| Risk Buffer | 39 | 7% |
| **TOTAL** | **529** | **100%** |

### Team Capacity Planning

| Resource | Availability | Allocation | Available Hours |
|----------|--------------|------------|-----------------|
| Senior QA (1) | 40 hrs/week | 80% | 32 hrs/week |
| QA Engineer (2) | 40 hrs/week each | 70% | 56 hrs/week |
| **Total Weekly** | - | - | **88 hrs/week** |

### Timeline Calculation

```
Weeks Required = Total Hours / Weekly Capacity
Weeks Required = 529 / 88 = 6.01 weeks

With 20% contingency: 529 × 1.2 / 88 = 7.2 weeks

Recommended Timeline: 8 weeks (2 sprints of 4 weeks each)
```

```

---

## 7. Step 5: Plan Sprint/Iteration Schedule

### 7.1 Sprint Planning

```markdown
## Sprint Plan

### Sprint 1: Foundation + Authentication (2 weeks)

**Objective:** Setup framework infrastructure and complete Authentication module

┌────────────────────────────────────────────────────────────────────────────┐
│ SPRINT 1 BACKLOG                                                           │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  FOUNDATION (Week 1)                                                       │
│  ├── [ ] Setup project structure (pom.xml, folders)                        │
│  ├── [ ] Configure Maven dependencies                                      │
│  ├── [ ] Setup Extent Reports configuration                                │
│  ├── [ ] Setup Log4j2 logging                                             │
│  ├── [ ] Create BaseUI and BasePage classes                               │
│  ├── [ ] Implement DriverManager                                           │
│  ├── [ ] Create WaitHelper, ActionHelper, ElementHelper                   │
│  └── [ ] Setup CI/CD pipeline (GitHub Actions)                             │
│                                                                             │
│  AUTHENTICATION (Week 2)                                                   │
│  ├── [ ] Create LoginPage, LoginPageElements, LoginActions                 │
│  ├── [ ] Create LoginTests with data provider                              │
│  ├── [ ] Create LoginTestData.xlsx                                         │
│  ├── [ ] Implement API login tests (if applicable)                        │
│  ├── [ ] Peer code review                                                 │
│  └── [ ] Execute and validate all login tests                              │
│                                                                             │
│  DELIVERABLES:                                                            │
│  ├── ✅ Framework infrastructure ready                                      │
│  ├── ✅ All 13 login test cases automated                                 │
│  └── ✅ Login test execution: 100% pass rate                               │
│                                                                             │
│  METRICS:                                                                  │
│  ├── Estimated: 80 hours                                                   │
│  ├── Actual: ___ hours                                                     │
│  └── Velocity: ___ points                                                  │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

---

### Sprint 2: Employee Management (2 weeks)

**Objective:** Complete Employee CRUD and core functionality

┌────────────────────────────────────────────────────────────────────────────┐
│ SPRINT 2 BACKLOG                                                           │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  EMPLOYEE CREATE (Week 3)                                                 │
│  ├── [ ] Create EmployeePage, EmployeePageElements, EmployeeActions         │
│  ├── [ ] Implement Employee list page objects                              │
│  ├── [ ] Create EmployeeTests - Create                                     │
│  ├── [ ] Create EmployeeTestData.xlsx (Create sheet)                       │
│  ├── [ ] Implement validation tests                                        │
│  └── [ ] API tests for employee creation                                   │
│                                                                             │
│  EMPLOYEE CRUD + SEARCH (Week 4)                                           │
│  ├── [ ] Create EmployeeTests - Edit, Delete                              │
│  ├── [ ] Implement search and filter tests                                │
│  ├── [ ] Create pagination tests                                          │
│  ├── [ ] Integration tests with Employee module                            │
│  ├── [ ] Peer code review                                                 │
│  └── [ ] Execute and validate all employee tests                           │
│                                                                             │
│  DELIVERABLES:                                                            │
│  ├── ✅ All 38 employee test cases automated                               │
│  └── ✅ Employee test execution: 95% pass rate                             │
│                                                                             │
│  METRICS:                                                                  │
│  ├── Estimated: 132 hours                                                  │
│  ├── Actual: ___ hours                                                     │
│  └── Velocity: ___ points                                                  │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

---

### Sprint 3: Leave Management (2 weeks)

**Objective:** Complete Leave module with approval workflow

┌────────────────────────────────────────────────────────────────────────────┐
│ SPRINT 3 BACKLOG                                                           │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  LEAVE REQUEST (Week 5)                                                   │
│  ├── [ ] Create LeavePage, LeavePageElements, LeaveActions                 │
│  ├── [ ] Implement leave request tests                                    │
│  ├── [ ] Create LeaveTestData.xlsx                                        │
│  ├── [ ] Implement leave balance validation                               │
│  └── [ ] API tests for leave requests                                     │
│                                                                             │
│  LEAVE APPROVAL (Week 6)                                                  │
│  ├── [ ] Create manager approval flow tests                               │
│  ├── [ ] Implement rejection workflow tests                                │
│  ├── [ ] Create cancellation tests                                         │
│  ├── [ ] Integration with Employee module                                 │
│  ├── [ ] Peer code review                                                 │
│  └── [ ] Execute and validate all leave tests                             │
│                                                                             │
│  DELIVERABLES:                                                            │
│  ├── ✅ All 30 leave test cases automated                                 │
│  └── ✅ Leave test execution: 95% pass rate                               │
│                                                                             │
│  METRICS:                                                                  │
│  ├── Estimated: 105 hours                                                  │
│  ├── Actual: ___ hours                                                     │
│  └── Velocity: ___ points                                                  │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

---

### Sprint 4: Attendance + Payroll (2 weeks)

**Objective:** Complete Attendance and Payroll modules

┌────────────────────────────────────────────────────────────────────────────┐
│ SPRINT 4 BACKLOG                                                           │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  ATTENDANCE (Week 7)                                                       │
│  ├── [ ] Create AttendancePage, AttendanceActions                          │
│  ├── [ ] Implement clock in/out tests                                      │
│  ├── [ ] Create AttendanceTestData.xlsx                                    │
│  ├── [ ] Implement overtime calculation tests                              │
│  └── [ ] API tests for attendance                                          │
│                                                                             │
│  PAYROLL (Week 8)                                                          │
│  ├── [ ] Create PayrollPage, PayrollActions                                │
│  ├── [ ] Implement salary calculation tests                                │
│  ├── [ ] Create PayrollTestData.xlsx                                       │
│  ├── [ ] Integration tests with Attendance                                 │
│  ├── [ ] Peer code review                                                 │
│  └── [ ] Execute and validate all payroll tests                            │
│                                                                             │
│  DELIVERABLES:                                                            │
│  ├── ✅ All 32 attendance + payroll test cases automated                  │
│  └── ✅ Combined test execution: 95% pass rate                             │
│                                                                             │
│  METRICS:                                                                  │
│  ├── Estimated: 126 hours                                                  │
│  ├── Actual: ___ hours                                                     │
│  └── Velocity: ___ points                                                  │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

---

### Sprint 5: Integration + Polish (2 weeks)

**Objective:** Complete remaining modules and integration, polish framework

┌────────────────────────────────────────────────────────────────────────────┐
│ SPRINT 5 BACKLOG                                                           │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  REMAINING MODULES (Week 9)                                                │
│  ├── [ ] Complete Reports module (20 tests)                                │
│  ├── [ ] Complete Settings module (5 tests)                                │
│  └── [ ] Integration test suite (12 tests)                                │
│                                                                             │
│  POLISH & OPTIMIZE (Week 10)                                               │
│  ├── [ ] Performance optimization (parallel execution)                     │
│  ├── [ ] Enhance reporting (Allure integration)                            │
│  ├── [ ] Create test execution dashboard                                   │
│  ├── [ ] Final code review and refactoring                                 │
│  ├── [ ] Update documentation                                             │
│  └── [ ] Final regression run                                              │
│                                                                             │
│  DELIVERABLES:                                                            │
│  ├── ✅ All 150 test cases automated                                      │
│  ├── ✅ Complete integration test suite                                     │
│  ├── ✅ Production-ready framework                                          │
│  └── ✅ 95% overall pass rate                                              │
│                                                                             │
│  METRICS:                                                                  │
│  ├── Estimated: 86 hours                                                   │
│  ├── Actual: ___ hours                                                     │
│  └── Velocity: ___ points                                                  │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 7.2 Milestone Timeline

```
MILESTONE TIMELINE
═══════════════════════════════════════════════════════════════════════════════

Week:     1   2   3   4   5   6   7   8   9   10
──────────────────────────────────────────────────────────────────────────
Sprint 1  ████████
Sprint 2          ████████████████
Sprint 3                      ████████████████
Sprint 4                                  ████████████████
Sprint 5                                              ████████████████

Milestones:
    M1 (Week 2):   Framework Ready + Login Complete
    M2 (Week 4):   Employee Module Complete
    M3 (Week 6):   Leave Module Complete
    M4 (Week 8):   Attendance + Payroll Complete
    M5 (Week 10):  All Modules + Integration Complete

Legend: ████ Active Sprint

```

---

## 8. Step 6: Define Infrastructure & Environment

### 8.1 Environment Architecture

```markdown
## Environment Architecture

### Test Environments

┌────────────────────────────────────────────────────────────────────────────┐
│ ENVIRONMENT MATRIX                                                          │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  LOCAL DEVELOPMENT                                                         │
│  ├── URL: http://localhost:8080                                            │
│  ├── Purpose: Individual development & debugging                           │
│  ├── Data: Synthetic, manually reset                                       │
│  ├── Browser: Chrome/Firefox (headed mode)                                │
│  └── Access: Individual developer                                          │
│                                                                             │
│  QA ENVIRONMENT                                                            │
│  ├── URL: https://qa.hrm.example.com                                      │
│  ├── Purpose: Test execution, CI/CD                                       │
│  ├── Data: Synthetic, auto-reset daily                                      │
│  ├── Browser: Chrome/Firefox/Edge (headless for CI)                       │
│  ├── Access: QA Team                                                       │
│  └── Maintenance: DevOps                                                    │
│                                                                             │
│  STAGING ENVIRONMENT                                                       │
│  ├── URL: https://staging.hrm.example.com                                 │
│  ├── Purpose: Pre-production validation                                    │
│  ├── Data: Production-like, reset weekly                                   │
│  ├── Browser: Chrome/Firefox/Edge                                          │
│  ├── Access: QA + Product Team                                            │
│  └── Maintenance: DevOps                                                    │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 8.2 Infrastructure Requirements

```markdown
## Infrastructure Requirements

### Local Development Machine

| Component | Minimum | Recommended |
|-----------|---------|-------------|
| CPU | 4 cores | 8 cores |
| RAM | 8 GB | 16 GB |
| Storage | 50 GB | 100 GB SSD |
| OS | Windows 10 / macOS 12 | Windows 11 / macOS 14 |

### CI/CD Build Agent

| Component | Specification |
|-----------|---------------|
| Machine | Ubuntu 22.04 LTS |
| CPU | 4 cores |
| RAM | 8 GB |
| Storage | 100 GB SSD |
| Browser | Chrome/Firefox headless |

### Docker Setup (Optional)

```yaml
# docker-compose.yml for test environment
version: '3.8'
services:
  selenium-hub:
    image: selenium/hub:4.18
    ports:
      - "4444:4444"
  
  chrome:
    image: selenium/node-chrome:4.18
    depends_on:
      - selenium-hub
    shm_size: '2gb'
  
  firefox:
    image: selenium/node-firefox:4.18
    depends_on:
      - selenium-hub
  
  hrm-app:
    image: hrm-app:qa
    ports:
      - "8080:8080"
```

```

### 8.3 Test Data Management

```
TEST DATA MANAGEMENT STRATEGY
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│ DATA LIFECYCLE                                                             │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  1. DATA CREATION                                                          │
│     ├── Static data: Created once, maintained in version control            │
│     ├── Dynamic data: Generated at runtime using DataFaker                  │
│     └── Test-specific: Created in @BeforeMethod, deleted in @AfterMethod   │
│                                                                             │
│  2. DATA SETUP STRATEGIES                                                  │
│     ├── Pre-condition: Create necessary data before test                   │
│     ├── Inline: Data embedded in test method                               │
│     └── Fixture: Shared test data loaded via @BeforeClass                   │
│                                                                             │
│  3. DATA CLEANUP                                                           │
│     ├── After each test: Clean test-specific data                         │
│     ├── After test class: Clean fixture data                               │
│     └── End of suite: Full environment reset                               │
│                                                                             │
│  4. DATA RESET STRATEGIES                                                  │
│     ├── Database reset: Truncate tables, reload fixtures                   │
│     ├── API reset: DELETE created records                                 │
│     └── Application reset: Logout, clear session                          │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

---

## 9. Step 7: Document Automation Plan

### 9.1 Master Automation Plan Template

```markdown
# Automation Plan: HRM Application

## Document Metadata

| Field | Value |
|-------|-------|
| Plan ID | AP-HRM-2026-001 |
| Version | 1.0 |
| Author | Senior QA Engineer |
| Date | 2026-05-10 |
| Status | Draft |
| Approved By | |

## Executive Summary

[Brief overview of automation scope, approach, and timeline]

## 1. Scope & Objectives

### 1.1 Automation Scope

| Include | Exclude |
|---------|---------|
| Login/Authentication | CAPTCHA handling |
| Employee Management | Third-party SSO |
| Leave Management | Third-party integrations |
| Attendance | Manual biometric devices |
| Payroll | Legacy modules |
| Reports | Print functionality |
| Settings | Admin configuration |

### 1.2 Objectives

| # | Objective | Target | Timeline |
|---|-----------|--------|----------|
| 1 | Automation Coverage | 70% test cases | 10 weeks |
| 2 | Test Execution Time | Reduce 50% | 8 weeks |
| 3 | CI/CD Integration | 100% in pipeline | 6 weeks |
| 4 | Flaky Test Rate | < 5% | 10 weeks |

## 2. Technical Approach

### 2.1 Technology Stack

[Per Section 5.1]

### 2.2 Architecture Decisions

[Per Section 5.2]

### 2.3 Framework Components

| Component | Description | Status |
|-----------|-------------|--------|
| BaseUI | Test base class | Ready |
| BasePage | Page base class | Ready |
| DriverManager | Browser management | Ready |
| WaitHelper | Explicit wait utilities | Ready |
| ActionHelper | User action utilities | Ready |
| ElementHelper | Element query utilities | Ready |
| ExtentReportManager | HTML reporting | Ready |
| ExcelHelper | Excel data reading | Ready |
| PropertiesHelper | Config management | Ready |

## 3. Module Breakdown & Estimates

### 3.1 Effort Summary

[Per Section 6.2]

### 3.2 Sprint Plan

[Per Section 7.1]

## 4. Resource Plan

### 4.1 Team Allocation

| Resource | Role | Sprint 1 | Sprint 2 | Sprint 3 | Sprint 4 | Sprint 5 |
|----------|------|----------|----------|----------|----------|----------|
| QA Lead | Review/Approve | 25% | 25% | 25% | 25% | 25% |
| Senior QA | Lead/Develop | 100% | 100% | 100% | 100% | 100% |
| QA Engineer 1 | Develop | 80% | 80% | 80% | 80% | 80% |
| QA Engineer 2 | Develop | 80% | 80% | 80% | 80% | 80% |

### 4.2 Training Plan

| Topic | Duration | Target Audience | Timeline |
|-------|----------|----------------|----------|
| Framework Architecture | 2 hours | All QA | Week 1 |
| POM Best Practices | 4 hours | All QA | Week 1 |
| TestNG Advanced | 4 hours | Senior QA | Week 2 |
| CI/CD Pipeline | 2 hours | All QA | Week 3 |

## 5. Risk Management

### 5.1 Risk Register

| Risk | Probability | Impact | Score | Mitigation |
|------|-------------|--------|-------|------------|
| Locator changes | High | Medium | 6 | Request data-testid, fallback locators |
| Environment instability | Medium | High | 6 | Docker containers, monitoring |
| Skill gaps | Medium | Medium | 4 | Training, pair programming |
| Scope creep | Medium | Medium | 4 | Clear scope, change process |

### 5.2 Contingency Plans

| Scenario | Trigger | Response |
|----------|---------|----------|
| >10% schedule overrun | Weekly review | Add resources, reduce scope |
| >5% flaky tests | Daily check | Pause, investigate, fix |
| Environment down >2 days | Alert | Escalate, use alternative |
| Key resource unavailable | Notice | Redistribute work, document |

## 6. Quality Metrics

### 6.1 Quality Gates

| Gate | Criteria | Checkpoint |
|------|----------|------------|
| Code Coverage | >80% | Each commit |
| Code Review | 100% | Each PR |
| Build Success | 100% | Each commit |
| Test Pass Rate | >95% | Each sprint |
| Flaky Rate | <5% | Weekly review |

### 6.2 Success Metrics

| Metric | Baseline | Target | Current |
|--------|----------|--------|---------|
| Automation Coverage | 0% | 70% | TBD |
| Execution Time | 8 hours | 4 hours | TBD |
| Defect Detection | 60% | 80% | TBD |
| CI/CD Pass Rate | N/A | >95% | TBD |

## 7. Deliverables

| # | Deliverable | Format | Location | Due |
|---|-------------|--------|----------|-----|
| 1 | Automation Plan | Markdown | outputs/automation/ | Week 1 |
| 2 | Framework Code | Java | src/test/java/ | Ongoing |
| 3 | Test Data | Excel | src/test/resources/ | Ongoing |
| 4 | Execution Reports | HTML | extentReports/ | Per run |
| 5 | Sprint Reports | Markdown | outputs/reports/ | Weekly |

## 8. Approval

| Role | Name | Signature | Date |
|------|------|-----------|------|
| QA Lead | | | |
| QA Manager | | | |
| Project Manager | | | |

```

---

## 10. Deliverables Summary

### 10.1 Primary Deliverables

| # | Deliverable | Format | Description |
|---|-------------|--------|-------------|
| 1 | Master Automation Plan | Markdown | Complete automation strategy |
| 2 | Module Breakdown | Excel | Detailed effort per module |
| 3 | Sprint Backlog | Markdown | Sprint-wise task breakdown |
| 4 | Effort Estimate | Markdown | Hours breakdown by category |
| 5 | Technical Decisions | Markdown | Architecture and approach |

### 10.2 File Naming Convention

```
outputs/automation/
├── plan/
│   ├── automation-plan-v1.0.md
│   ├── module-breakdown.xlsx
│   └── sprint-backlog.md
├── estimates/
│   ├── effort-estimate-v1.0.md
│   └── roi-analysis.md
└── decisions/
    └── technical-decisions.md
```

---

## 11. Roles & Responsibilities

### 11.1 Role Matrix

| Role | Activities | Deliverables | Authority |
|------|------------|--------------|-----------|
| **QA Lead** | Review plan, approve approach, manage risks | Approved plan | Final approval |
| **Senior QA** | Lead planning, create estimates, coordinate | All planning docs | Technical decisions |
| **QA Engineer** | Contribute to estimates, identify risks | Risk inputs | Recommend changes |
| **DevOps** | Infrastructure setup, CI/CD | CI/CD pipeline | Technical setup |

### 11.2 RACI Matrix

| Activity | QA Lead | Senior QA | QA Eng | DevOps |
|----------|---------|-----------|--------|--------|
| Analyze requirements | A | R | C | I |
| Assess suitability | A | R | C | - |
| Technical decisions | C | R | I | C |
| Create estimates | A | R | C | - |
| Sprint planning | C | R | C | - |
| Infrastructure setup | I | C | I | R |

Legend: R=Responsible, A=Accountable, C=Consulted, I=Informed

---

## 12. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer (ISTQB) | Initial automation plan |
