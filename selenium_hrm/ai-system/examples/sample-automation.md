# Sample: Automation Plan - Leave Management Module

## Document Metadata

| Field | Value |
|-------|-------|
| Plan ID | AP-HRM-LEAVE-20260511-001 |
| Version | 1.0 |
| Author | Nguyen Van A - Senior QA |
| Date | 2026-05-11 |
| Status | Approved |
| Module | Leave Management |
| Related Test Suite | TC-LEAVE-20260511-001 |

---

## Executive Summary

This automation plan outlines the strategy, approach, and implementation details for automating the Leave Management module test suite. The plan covers 38 automatable test cases out of 45 total, targeting 84% automation coverage.

**Automation Summary:**
- Total Test Cases: 45
- Automatable: 38 (84%)
- Manual Only: 7 (16%)
- Estimated Automation Effort: 105 hours
- Target Completion: 3 sprints

---

## 1. Automation Scope

### 1.1 In Scope

| # | Feature | Test Cases | Automatable | Priority |
|---|---------|------------|-------------|----------|
| 1 | Leave Request Submission | 12 | 10 | P0 |
| 2 | Leave Approval Workflow | 10 | 9 | P0 |
| 3 | Leave Balance Tracking | 6 | 5 | P1 |
| 4 | Leave Cancellation | 7 | 6 | P1 |
| 5 | Leave Encashment | 5 | 4 | P2 |
| 6 | Leave Reports | 5 | 4 | P2 |

### 1.2 Out of Scope

| # | Feature | Reason |
|---|---------|--------|
| 1 | Approval timeout escalation | Time-dependent, requires wait simulation |
| 2 | Year-end carry-over | End-of-year scenario, manual trigger |
| 3 | Email notification content | Requires email server verification |

### 1.3 Module Dependencies

| Dependency | Impact | Mitigation |
|------------|--------|------------|
| Employee Management | Need existing employees | Pre-create test employees |
| Payroll Module | Balance affects salary | Mock payroll integration |
| Notification Service | External dependency | Mock notification responses |

---

## 2. Automation Suitability Assessment

### 2.1 Suitability Matrix

| Feature | Suitability | Reason |
|---------|-------------|--------|
| Leave Request Submission | HIGH | Stable UI, clear steps |
| Leave Approval Workflow | HIGH | Standard workflow |
| Leave Balance Tracking | MEDIUM | Database verification needed |
| Leave Cancellation | HIGH | UI-based process |
| Leave Encashment | MEDIUM | Complex calculation |
| Leave Reports | MEDIUM | Report generation time |

### 2.2 ROI Analysis

| Factor | Manual | Automated | Notes |
|--------|--------|-----------|-------|
| Execution Time (per run) | 120 min | 25 min | 80% reduction |
| Effort per Sprint | 24 hours | 8 hours | 66% reduction |
| Cost per Sprint | $720 | $240 | 66% reduction |
| **ROI (5 sprints)** | - | **400%** | Break-even at sprint 2 |

---

## 3. Technical Approach

### 3.1 Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| Language | Java | 21 |
| Build Tool | Maven | 3.9+ |
| Test Framework | TestNG | 7.4.0 |
| Web Automation | Selenium WebDriver | 4.35.0 |
| API Testing | Rest-Assured | 5.3.2 |
| Reporting | Extent Reports | 5.1.1 |
| Logging | Log4j2 | 2.24.3 |
| Data Handling | Apache POI | 5.2.5 |
| Data Generation | DataFaker | 2.5.3 |

### 3.2 Framework Architecture

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                    3-LAYER PAGE OBJECT MODEL                                │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  LAYER 1: PAGE ELEMENTS (Locators Only)                                     │
│  ├── LeavePageElements.java                                                │
│  │   ├── By TXT_START_DATE = By.id("startDate")                            │
│  │   ├── By BTN_SUBMIT = By.id("submitBtn")                               │
│  │   └── By LBL_SUCCESS = By.cssSelector(".alert-success")                │
│  └── Purpose: Centralized locators, easy maintenance                         │
│                                                                             │
│  LAYER 2: PAGE ACTIONS (Business Operations)                               │
│  ├── LeaveActions.java                                                     │
│  │   ├── submitLeaveRequest(type, startDate, endDate, reason)              │
│  │   ├── approveLeaveRequest(requestId)                                    │
│  │   └── cancelLeaveRequest(requestId)                                     │
│  └── Purpose: Reusable business methods                                      │
│                                                                             │
│  LAYER 3: PAGE OBJECT (Facade)                                              │
│  ├── LeavePage.java                                                        │
│  │   ├── navigate()                                                        │
│  │   ├── createLeaveRequest(data)                                          │
│  │   └── verifySuccessMessage()                                            │
│  └── Purpose: Orchestration, high-level interface                          │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 3.3 Locator Strategy

| Priority | Type | Example | Usage |
|----------|------|---------|-------|
| 1 | data-testid | `[data-testid='leave-submit-btn']` | All new elements |
| 2 | id | `#startDate` | Form fields |
| 3 | name | `[name='leaveType']` | Form inputs |
| 4 | css | `.leave-request-form` | Complex elements |
| 5 | xpath | `//button[contains(text(),'Submit')]` | Dynamic elements |

### 3.4 Wait Strategy

| Wait Type | Usage | Duration |
|-----------|-------|----------|
| Explicit Wait | Element visible | 30 seconds |
| Explicit Wait | Element clickable | 20 seconds |
| Explicit Wait | URL contains | 30 seconds |
| Explicit Wait | AJAX complete | 30 seconds |
| JavaScript Wait | Page load | 10 seconds |

---

## 4. Module Breakdown & Effort Estimate

### 4.1 Leave Request Submission

| Component | Type | Hours | Notes |
|-----------|------|-------|-------|
| LeavePageElements | Locators | 4 | All form elements |
| LeaveActions | Actions | 8 | Submit, validation |
| LeavePage | Page Object | 6 | Facade methods |
| LeaveRequestTests | Test Class | 12 | 10 test cases |
| LeaveRequestTestData | Excel | 3 | Test data |
| Documentation | Comments | 2 | Code documentation |
| Review & Fix | QA | 5 | Peer review |
| **Subtotal** | | **40** | |

### 4.2 Leave Approval Workflow

| Component | Type | Hours | Notes |
|-----------|------|-------|-------|
| LeaveApprovalActions | Actions | 6 | Approve, reject |
| LeaveApprovalPage | Page Object | 5 | Approval interface |
| LeaveApprovalTests | Test Class | 10 | 9 test cases |
| LeaveApprovalTestData | Excel | 2 | Approval data |
| **Subtotal** | | **23** | |

### 4.3 Leave Balance Tracking

| Component | Type | Hours | Notes |
|-----------|------|-------|-------|
| BalanceVerification | Database | 4 | DB validation |
| BalanceTests | Test Class | 6 | 5 test cases |
| **Subtotal** | | **10** | |

### 4.4 Leave Cancellation

| Component | Type | Hours | Notes |
|-----------|------|-------|-------|
| CancellationActions | Actions | 4 | Cancel workflow |
| CancellationTests | Test Class | 8 | 6 test cases |
| **Subtotal** | | **12** | |

### 4.5 Leave Encashment

| Component | Type | Hours | Notes |
|-----------|------|-------|-------|
| EncashmentTests | Test Class | 8 | 4 test cases |
| EncashmentTestData | Excel | 2 | Calculation data |
| **Subtotal** | | **10** | |

### 4.6 Leave Reports

| Component | Type | Hours | Notes |
|-----------|------|-------|-------|
| ReportActions | Actions | 3 | Generate, export |
| ReportTests | Test Class | 6 | 4 test cases |
| **Subtotal** | | **9** | |

### 4.7 Summary Estimate

| Category | Hours | Percentage |
|----------|-------|------------|
| Page Objects | 30 | 29% |
| Test Classes | 50 | 48% |
| Test Data | 7 | 7% |
| Documentation | 2 | 2% |
| Review & Fix | 16 | 15% |
| **Total** | **105** | **100%** |

---

## 5. Sprint Plan

### 5.1 Sprint 1: Leave Request (2 weeks)

```
WEEK 1-2: LEAVE REQUEST AUTOMATION
═══════════════════════════════════════════════════════════════════════════════

Activities:
├── Day 1-2: Create LeavePageElements.java
├── Day 3-4: Create LeaveActions.java
├── Day 5: Create LeavePage.java
├── Day 6-8: Implement LeaveRequestTests.java (positive cases)
├── Day 9-10: Implement negative and boundary cases
└── Day 11-12: Peer review and fix

Deliverables:
├── LeavePageElements.java ✅
├── LeaveActions.java ✅
├── LeavePage.java ✅
├── LeaveRequestTests.java ✅ (10 test cases)
└── LeaveRequestTestData.xlsx ✅

Metrics:
├── Estimated: 40 hours
├── Actual: ___ hours
└── Velocity: ___ points
```

### 5.2 Sprint 2: Leave Approval & Balance (2 weeks)

```
WEEK 3-4: APPROVAL & BALANCE AUTOMATION
═══════════════════════════════════════════════════════════════════════════════

Activities:
├── Day 1-2: Create LeaveApprovalActions.java
├── Day 3-4: Create LeaveApprovalTests.java
├── Day 5-6: Implement balance verification tests
├── Day 7-8: Create cancellation tests
└── Day 9-10: Integration tests
└── Day 11-12: Review and fix

Deliverables:
├── LeaveApprovalTests.java ✅ (9 test cases)
├── LeaveBalanceTests.java ✅ (5 test cases)
├── LeaveCancellationTests.java ✅ (6 test cases)
└── All approvals and balance covered

Metrics:
├── Estimated: 45 hours
├── Actual: ___ hours
└── Velocity: ___ points
```

### 5.3 Sprint 3: Reports & Polish (2 weeks)

```
WEEK 5-6: REPORTS & POLISH
═══════════════════════════════════════════════════════════════════════════════

Activities:
├── Day 1-2: Create ReportTests.java
├── Day 3-4: Implement encashment tests
├── Day 5-6: Performance optimization
├── Day 7-8: Report enhancement
├── Day 9-10: Full regression run
└── Day 11-12: Documentation and handoff

Deliverables:
├── LeaveReportTests.java ✅ (4 test cases)
├── LeaveEncashmentTests.java ✅ (4 test cases)
├── Complete Leave module automation ✅
└── Production-ready code ✅

Metrics:
├── Estimated: 20 hours
├── Actual: ___ hours
└── Velocity: ___ points
```

---

## 6. Implementation Details

### 6.1 Page Objects Structure

```
src/test/java/com/selenium_hrm/ui/pages/leave/
├── LeavePageElements.java
├── LeavePageActions.java
├── LeaveRequestPage.java
├── LeaveApprovalPage.java
├── LeaveBalancePage.java
├── LeaveCancellationPage.java
└── LeaveReportPage.java
```

### 6.2 Test Classes Structure

```
src/test/java/com/selenium_hrm/ui/tests/leave/
├── LeaveRequestTests.java
├── LeaveApprovalTests.java
├── LeaveBalanceTests.java
├── LeaveCancellationTests.java
├── LeaveEncashmentTests.java
└── LeaveReportTests.java
```

### 6.3 Test Data Structure

```
src/test/resources/testdata/leave/
├── LeaveRequestTestData.xlsx
├── LeaveApprovalTestData.xlsx
├── LeaveBalanceTestData.xlsx
├── LeaveCancellationTestData.xlsx
└── LeaveEncashmentTestData.xlsx
```

---

## 7. Test Data Strategy

### 7.1 Test Data Setup

| Data Type | Setup Method | Cleanup | Priority |
|-----------|-------------|---------|----------|
| Test Employees | @BeforeClass | @AfterClass | Critical |
| Leave Balance | @BeforeMethod | @AfterMethod | High |
| Leave Requests | @BeforeMethod | @AfterMethod | High |
| Manager Assignment | @BeforeClass | - | Medium |

### 7.2 Data Generation

```java
// Unique leave request ID
public static String generateLeaveRequestId() {
    return "LR" + String.format("%06d", new Random().nextInt(999999));
}

// Unique email for test employee
public static String generateTestEmail() {
    return "leave.test." + System.currentTimeMillis() + "@test.com";
}
```

---

## 8. Environment Configuration

### 8.1 Environment Matrix

| Environment | URL | Purpose | Data Reset |
|-------------|-----|---------|------------|
| Local | localhost:8080 | Development | Manual |
| QA | qa-hrm.example.com | Test Execution | Daily |
| Staging | staging-hrm.example.com | UAT | Weekly |

### 8.2 Browser Support

| Browser | Version | Priority | Headless |
|---------|---------|----------|----------|
| Chrome | Latest | Primary | Yes |
| Firefox | Latest | Secondary | Yes |
| Edge | Latest | Tertiary | Yes |

---

## 9. Quality Gates

### 9.1 Code Quality Checklist

- [ ] All code compiles without errors
- [ ] 100% naming convention compliance
- [ ] No hardcoded values (use config)
- [ ] Explicit waits used (no Thread.sleep)
- [ ] Proper exception handling
- [ ] Javadoc for public methods
- [ ] Zero TODO/FIXME comments

### 9.2 Test Quality Checklist

- [ ] All test cases executable
- [ ] Tests are independent
- [ ] Data cleanup implemented
- [ ] Proper assertions
- [ ] Meaningful test names
- [ ] Clear pre-conditions
- [ ] Specific expected results

---

## 10. Risk Management

### 10.1 Risk Register

| Risk | Probability | Impact | Score | Mitigation |
|------|-------------|--------|-------|------------|
| Locator changes | High | Medium | 6 | Request data-testid, fallback locators |
| Environment issues | Medium | High | 6 | Docker containers, monitoring |
| Dynamic elements | Medium | Medium | 4 | Explicit waits, retry logic |
| Date handling | Medium | Medium | 4 | Centralized date utilities |

### 10.2 Contingency Plans

| Scenario | Trigger | Response |
|----------|---------|----------|
| Locator broken | Test fails | Add fallback locator, notify dev |
| Environment down | Test fails | Escalate to DevOps, use alternative |
| Schedule overrun | >10% late | Reduce scope, extend timeline |

---

## 11. Deliverables

### 11.1 Deliverables List

| # | Deliverable | Format | Location | Status |
|---|-------------|--------|----------|--------|
| 1 | Page Objects | Java | src/test/java/ | Pending |
| 2 | Test Classes | Java | src/test/java/ | Pending |
| 3 | Test Data | Excel | src/test/resources/ | Pending |
| 4 | Execution Reports | HTML | target/reports/ | After execution |
| 5 | Automation Summary | Markdown | outputs/ | After completion |

### 11.2 Success Criteria

| Metric | Target | Measurement |
|--------|--------|-------------|
| Automation Coverage | 84% | (38/45 test cases) |
| Test Pass Rate | >95% | Per sprint |
| Code Quality | 100% | Linting pass |
| Schedule | On time | Within 6 weeks |

---

## 12. Approval

| Role | Name | Date | Status |
|------|------|------|--------|
| QA Engineer | Nguyen Van A | 2026-05-11 | Submitted |
| QA Lead | Tran Thi B | 2026-05-11 | Approved |
| Tech Lead | Pham Van D | 2026-05-12 | Approved |

---

## 13. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 0.1 | 2026-05-08 | Nguyen Van A | Initial draft |
| 0.2 | 2026-05-10 | Nguyen Van A | Added estimates |
| 1.0 | 2026-05-11 | Nguyen Van A | Final version |
