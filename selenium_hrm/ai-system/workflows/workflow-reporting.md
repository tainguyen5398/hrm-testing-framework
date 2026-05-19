# Workflow: Test Reporting

## 1. Overview

### 1.1 Purpose
Workflow báo cáo test (Test Reporting) là bước cuối cùng trong QA process, tổng hợp và báo cáo kết quả test execution. Workflow này hướng dẫn QA Engineer tạo các báo cáo chuyên nghiệp để đảm bảo:

- **Visibility**: Stakeholders có cái nhìn rõ ràng về quality status
- **Traceability**: Từ requirements đến execution results
- **Decision Support**: Data-driven insights cho decisions
- **Communication**: Clear communication với team và management
- **Continuous Improvement**: Insights cho process improvement

### 1.2 Objectives

| # | Objective | Success Criteria |
|---|-----------|-----------------|
| 1 | Comprehensive Reporting | 100% test execution có report |
| 2 | Actionable Insights | Reports có clear recommendations |
| 3 | Traceability | Requirements → Tests → Results mapped |
| 4 | Timely Communication | Reports delivered within SLA |
| 5 | Continuous Tracking | Historical data tracked trends |

### 1.3 Workflow Position

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                     AI AUTOMATION PIPELINE - WORKFLOW 6                      │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   INPUT:                                                                    │
│   ├── Test Execution Results (từ Test Run)                                │
│   ├── Test Case Suite (từ Workflow 2: Test Design)                        │
│   ├── Traceability Matrix (từ Workflow 2)                                 │
│   └── Defect Reports (từ Execution)                                        │
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │                    TEST REPORTING                                  │     │
│   │  ┌─────────────────────────────────────────────────────────┐   │     │
│   │  │ 1. Gather Execution Data                               │   │     │
│   │  │ 2. Generate Test Reports                              │   │     │
│   │  │ 3. Create Defect Reports                             │   │     │
│   │  │ 4. Build Dashboard & Summary                        │   │     │
│   │  │ 5. Distribute & Communicate                         │   │     │
│   │  └─────────────────────────────────────────────────────────┘   │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│                                                                             │
│   OUTPUT:                                                                   │
│   ├── execution-report.md (Test Execution Report)                            │
│   ├── defect-report.md (Defect Summary Report)                               │
│   ├── trend-report.md (Historical Trend Report)                              │
│   └── HTML/Allure Reports (Visual Reports)                                  │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 2. Inputs & Prerequisites

### 2.1 Required Inputs

| # | Input | Format | Source | Priority |
|---|-------|--------|--------|----------|
| 1 | Test Execution Results | XML (TestNG), HTML | Test Run | Required |
| 2 | Test Case Suite | Markdown/Excel | Workflow 2: Test Design | Required |
| 3 | Traceability Matrix | Markdown/Excel | Workflow 2: Test Design | Required |
| 4 | Defect List | JIRA, Excel | During Execution | Required |
| 5 | Screenshots/Videos | PNG, MP4 | During Execution | Required |
| 6 | Logs | Log files | Test Execution | Required |

### 2.2 Prerequisites Checklist

```
PRE-WORKFLOW CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

□ Test execution completed
□ Results collected from all environments
□ TestNG/Extent Reports generated
□ Defects logged and categorized
□ Screenshots captured for failures
□ Logs available for analysis
□ Requirements coverage verified

```

---

## 3. Report Types

### 3.1 Report Hierarchy

```
TEST REPORT TYPES
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│                           REPORT PYRAMID                                    │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  EXECUTIVE SUMMARY (Top - for Management)                                  │
│  ├── High-level metrics                                                    │
│  ├── Risk assessment                                                       │
│  ├── Go/No-Go decision                                                   │
│  └── Recommendations                                                       │
│                                                                             │
│  ┌──────────────────────────────────────────────────────────────────────┐ │
│  │ DETAILED SUMMARY (Middle - for QA Lead/PM)                           │ │
│  ├── Module-wise breakdown                                               │ │
│  ├── Defect analysis                                                     │ │
│  ├── Coverage metrics                                                    │ │
│  │ └── Requirements traceability                                         │ │
│  └──────────────────────────────────────────────────────────────────────┘ │
│                                                                             │
│  ┌──────────────────────────────────────────────────────────────────────┐ │
│  │ TECHNICAL REPORT (Bottom - for QA Team/Developers)                    │ │
│  │   ├── Detailed test results                                          │ │
│  │   ├── Failure analysis                                               │ │
│  │   ├── Logs and screenshots                                          │ │
│  │   └── Technical recommendations                                      │ │
│  └──────────────────────────────────────────────────────────────────────┘ │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 3.2 Report Comparison

| Report Type | Audience | Frequency | Format | Detail Level |
|-------------|----------|-----------|--------|--------------|
| **Executive Summary** | Management, PM | Per release | Email, PDF | High-level |
| **Test Execution Report** | QA Lead, PM | Per sprint/run | Markdown, HTML | Medium |
| **Defect Report** | QA Lead, Dev | Per test run | Excel, Markdown | High |
| **Trend Report** | QA Team, Management | Weekly/Monthly | Dashboard, PDF | Variable |
| **Technical Report** | QA Team, Dev | Per test run | HTML, Log files | Very High |

---

## 4. Report Templates

### 4.1 Executive Summary Template

```markdown
# Executive Summary - Test Execution Report

## Document Information

| Field | Value |
|-------|-------|
| Report ID | TES-EXEC-2026-001 |
| Date | 2026-05-11 |
| Sprint | Sprint 3 |
| Environment | QA |
| Prepared By | Senior QA Engineer |
| Review Status | Draft/Final |

---

## 1. Overview

### 1.1 Test Execution Summary

| Metric | Value | Status |
|--------|-------|--------|
| Total Test Cases | 150 | |
| Executed | 145 | |
| Passed | 138 | ✅ |
| Failed | 5 | 🔴 |
| Blocked | 2 | ⚠️ |
| Pass Rate | **95.2%** | ✅ |

### 1.2 Quality Status

```
QUALITY STATUS INDICATOR
═══════════════════════════════════════════════════════════════════════════════

Pass Rate:  95.2%  ████████████████████████████░░░  [✅ TARGET MET]

Legend: ██ Passed  ░░ Failed/Blocked
Target: > 90%

```

---

## 2. Risk Assessment

### 2.1 Current Risks

| Risk | Impact | Likelihood | Status | Mitigation |
|------|--------|------------|--------|------------|
| Login module has 2 recurring failures | High | Medium | 🟡 In Progress | Investigating with Dev |
| Performance degradation in payroll | Medium | Low | ✅ Monitored | Performance tests scheduled |

### 2.2 Release Readiness

| Criteria | Status | Notes |
|----------|--------|-------|
| Critical test cases pass | ✅ Pass | All P0 cases passed |
| High-priority defects resolved | ⚠️ In Progress | 1 P1 defect pending |
| Performance acceptable | ✅ Pass | Response time < 3s |
| Security scan clean | ✅ Pass | No vulnerabilities |

**Overall Release Readiness: ✅ READY TO PROCEED**

---

## 3. Key Highlights

### 3.1 Successes
- ✅ Authentication module: 100% pass rate (15/15 tests)
- ✅ Employee creation: Improved from 85% to 98% pass rate
- ✅ Automation coverage increased to 75%

### 3.2 Areas of Concern
- 🔴 Leave calculation: 3 edge cases failed - requires investigation
- ⚠️ Performance: Payroll report generation time increased 20%

---

## 4. Recommendations

1. **Proceed with release** - All critical paths pass
2. **Schedule bug triage** - Resolve 5 failures before production
3. **Continue monitoring** - Track payroll performance

---

## 5. Sign-off

| Role | Name | Date | Decision |
|------|------|------|----------|
| QA Lead | | | |
| Project Manager | | | |
| Release Manager | | | |

```

### 4.2 Test Execution Report Template

```markdown
# Test Execution Report

## Document Information

| Field | Value |
|-------|-------|
| Report ID | TER-2026-001 |
| Date | 2026-05-11 |
| Sprint | Sprint 3 |
| Build | v2.3.1 |
| Environment | QA Environment |
| Duration | 2 hours 45 minutes |

---

## 1. Executive Summary

### 1.1 Summary Metrics

```
┌────────────────────────────────────────────────────────────────────────────┐
│                         TEST EXECUTION SUMMARY                               │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  Total Tests:     150  │  Passed:  138  │  Failed:    5  │  Blocked:  2  │
│  Pass Rate:       95.2%                                           │
│  Duration:        2h 45m                                            │
│  Browser:         Chrome 120                                         │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘
```

### 1.2 Module-wise Results

| Module | Total | Passed | Failed | Blocked | Pass Rate |
|--------|-------|--------|--------|---------|-----------|
| Authentication | 15 | 15 | 0 | 0 | **100%** ✅ |
| Employee Management | 45 | 43 | 2 | 0 | **95.6%** ✅ |
| Leave Management | 35 | 32 | 2 | 1 | **91.4%** ⚠️ |
| Attendance | 25 | 23 | 1 | 1 | **92.0%** ⚠️ |
| Payroll | 20 | 18 | 0 | 2 | **90.0%** ⚠️ |
| Reports | 10 | 7 | 0 | 0 | **100%** ✅ |
| **TOTAL** | **150** | **138** | **5** | **2** | **95.2%** |

---

## 2. Detailed Test Results

### 2.1 Passed Tests Summary

| Module | Test Count | Key Tests Passed |
|--------|------------|------------------|
| Authentication | 15 | Login, Logout, Password Reset, SSO |
| Employee | 43 | Create, Edit, Delete, Search, Filter |
| Leave | 32 | Request, Approve, Reject, Cancel |
| Attendance | 23 | Clock In, Clock Out, Overtime |
| Payroll | 18 | Salary Calc, Payslip, Deductions |
| Reports | 7 | Generate, Export, Schedule |

### 2.2 Failed Tests Details

| Test ID | Module | Test Case | Failure Reason | Severity | Status |
|---------|--------|-----------|---------------|----------|--------|
| TC-LEAVE-045 | Leave | Leave balance calculation - boundary | Incorrect calculation for 18 days | High | Open |
| TC-LEAVE-046 | Leave | Leave balance - leap year | Date calculation error | Medium | Open |
| TC-EMP-078 | Employee | Edit with special characters | XSS vulnerability | Critical | Open |
| TC-EMP-079 | Employee | Search - SQL injection | Input not sanitized | Critical | Open |
| TC-ATT-023 | Attendance | Overtime calculation | Formula incorrect | Medium | Open |

### 2.3 Blocked Tests

| Test ID | Module | Reason | Blocker Issue | ETA |
|---------|--------|--------|---------------|-----|
| TC-LEAVE-050 | Leave | API timeout | External service down | 2026-05-12 |
| TC-PAY-015 | Payroll | Test data missing | Data not provisioned | 2026-05-11 |

---

## 3. Defect Analysis

### 3.1 Defect Summary

| Category | Count | Percentage |
|----------|-------|------------|
| UI Defects | 2 | 40% |
| Logic Errors | 2 | 40% |
| Security Issues | 1 | 20% |
| **Total** | **5** | **100%** |

### 3.2 Defect Severity Breakdown

```
DEFECT SEVERITY DISTRIBUTION
═══════════════════════════════════════════════════════════════════════════════

Critical ████ 1 (20%)
High     ████ 2 (40%)
Medium   ██   1 (20%)
Low      ██   1 (20%)

```

### 3.3 Defect Status

| Status | Count |
|--------|-------|
| New | 5 |
| Assigned | 3 |
| In Progress | 1 |
| Resolved | 0 |
| Closed | 0 |

---

## 4. Test Coverage

### 4.1 Requirements Coverage

| Requirement ID | Requirement | Test Cases | Passed | Coverage |
|----------------|-------------|------------|--------|----------|
| REQ-AUTH-001 | User authentication | 5 | 5 | 100% |
| REQ-EMP-001 | Employee CRUD | 12 | 10 | 83% |
| REQ-LEAVE-001 | Leave request | 8 | 6 | 75% |
| REQ-PAY-001 | Payroll processing | 6 | 5 | 83% |

**Overall Requirements Coverage: 85%**

### 4.2 Test Type Coverage

| Test Type | Executed | Passed | Pass Rate |
|-----------|----------|--------|-----------|
| Smoke | 25 | 25 | 100% |
| Sanity | 35 | 33 | 94% |
| Regression | 90 | 80 | 89% |
| E2E | 10 | 10 | 100% |

---

## 5. Environment & Infrastructure

### 5.1 Test Environment

| Component | Version | Status |
|-----------|---------|--------|
| Application | v2.3.1 | ✅ Running |
| Database | PostgreSQL 14 | ✅ Connected |
| API | v2.3.1 | ✅ Available |
| Browser | Chrome 120 | ✅ Ready |

### 5.2 Execution Environment

| Metric | Value |
|--------|-------|
| Machine | Build Server |
| OS | Ubuntu 22.04 |
| RAM | 16 GB |
| Execution Mode | Sequential |

---

## 6. Attachments

| Document | Location |
|----------|----------|
| TestNG Results | `test-output/testng-results.xml` |
| Extent Report | `extentReports/TestExecution.html` |
| Screenshots | `screenshots/` |
| Logs | `logs/` |

---

## 7. Conclusion

### 7.1 Overall Assessment

**Status: ⚠️ CONDITIONALLY READY**

The test execution shows a pass rate of 95.2%, meeting the target of >90%. However, 2 critical security defects (XSS and SQL injection) must be resolved before production release.

### 7.2 Next Steps

1. **Immediate**: Assign security defects to development
2. **Within 24h**: Resolve critical defects
3. **Within 48h**: Re-run failed tests
4. **After Resolution**: Final release sign-off

---

## 8. Approval

| Role | Name | Date | Signature |
|------|------|------|-----------|
| QA Engineer | | | |
| QA Lead | | | |
| Project Manager | | | |

```

### 4.3 Defect Report Template

```markdown
# Defect Report

## Summary

| Metric | Value |
|--------|-------|
| Total Defects | 5 |
| Critical | 1 |
| High | 2 |
| Medium | 1 |
| Low | 1 |
| New | 5 |
| Resolved | 0 |
| Closed | 0 |

---

## Defect Details

### DEF-001: XSS Vulnerability in Employee Edit

| Field | Value |
|-------|-------|
| **Defect ID** | DEF-001 |
| **Test Case** | TC-EMP-078 |
| **Severity** | 🔴 Critical |
| **Priority** | P1 |
| **Module** | Employee Management |
| **Environment** | QA |
| **Found Date** | 2026-05-11 |
| **Found By** | QA Engineer |

**Description:**
Employee edit form allows XSS injection via name field. When editing an employee with name `<script>alert('XSS')</script>`, the script executes when viewing the employee list.

**Steps to Reproduce:**
1. Navigate to Employee Management
2. Create new employee with any name
3. Edit the employee
4. Change name to: `<script>alert('XSS')</script>`
5. Save
6. View employee list
7. Alert popup appears

**Expected Result:**
Special characters should be escaped or sanitized before display.

**Actual Result:**
Raw script tags execute in browser.

**Evidence:**
[Screenshot attached: screenshots/DEF-001.png]

**Assigned To:** Development Team
**Status:** New

---

### DEF-002: SQL Injection in Employee Search

| Field | Value |
|-------|-------|
| **Defect ID** | DEF-002 |
| **Test Case** | TC-EMP-079 |
| **Severity** | 🔴 Critical |
| **Priority** | P1 |
| **Module** | Employee Management |
| **Environment** | QA |

**Description:**
Search input not sanitized, allowing SQL injection.

**Steps to Reproduce:**
1. Navigate to Employee Search
2. Enter: `' OR '1'='1`
3. Click Search
4. All employees returned regardless of filter

**Expected Result:**
Input should be sanitized, error or no results returned.

**Actual Result:**
SQL query vulnerable, returns all records.

**Evidence:**
[Logs attached: logs/DEF-002.log]

**Assigned To:** Development Team
**Status:** New

---

## Defect Summary Table

| ID | Title | Severity | Priority | Module | Status | Assigned To |
|----|-------|----------|----------|--------|--------|-------------|
| DEF-001 | XSS in Employee Edit | Critical | P1 | Employee | New | Dev Team |
| DEF-002 | SQL Injection in Search | Critical | P1 | Employee | New | Dev Team |
| DEF-003 | Leave Balance Calc Error | High | P1 | Leave | New | Dev Team |
| DEF-004 | Leave Date Calculation | High | P2 | Leave | New | Dev Team |
| DEF-005 | Overtime Formula | Medium | P2 | Attendance | New | Dev Team |

---

## Defect Distribution

### By Module

| Module | Critical | High | Medium | Low | Total |
|--------|----------|------|--------|-----|-------|
| Employee | 2 | 0 | 0 | 0 | 2 |
| Leave | 0 | 1 | 1 | 0 | 2 |
| Attendance | 0 | 1 | 0 | 0 | 1 |
| **Total** | **2** | **2** | **1** | **0** | **5** |

### By Root Cause

| Root Cause | Count | Percentage |
|------------|-------|------------|
| Input Validation | 3 | 60% |
| Calculation Logic | 2 | 40% |

```

### 4.4 Trend Report Template

```markdown
# Test Execution Trend Report

## Period: May 2026 (Week 1-4)

---

## 1. Executive Summary

### 1.1 Overall Trend

```
TEST EXECUTION TREND (4 WEEKS)
═══════════════════════════════════════════════════════════════════════════════

Week 1:  ████████████████████████░░░░░░░  78%
Week 2:  ████████████████████████████████░  85%
Week 3:  ████████████████████████████████░  90%
Week 4:  ████████████████████████████████░  95%

Target:  >90%

```

### 1.2 Key Insights

- ✅ Pass rate improved from 78% to 95% (+17%)
- ✅ Critical defects reduced by 60%
- ✅ Test execution time reduced by 25%
- ⚠️ Automation coverage needs improvement

---

## 2. Pass Rate Trend

| Week | Pass Rate | Passed | Failed | Blocked | Delta |
|------|-----------|--------|--------|---------|-------|
| Week 1 | 78% | 117 | 28 | 5 | - |
| Week 2 | 85% | 128 | 19 | 3 | +7% |
| Week 3 | 90% | 135 | 12 | 3 | +5% |
| Week 4 | 95% | 143 | 5 | 2 | +5% |

---

## 3. Defect Trend

### 3.1 Defects by Week

| Week | New | Resolved | Open | Critical Open |
|------|-----|----------|------|---------------|
| Week 1 | 15 | 5 | 10 | 3 |
| Week 2 | 12 | 15 | 7 | 2 |
| Week 3 | 8 | 10 | 5 | 1 |
| Week 4 | 5 | 7 | 3 | 0 |

### 3.2 Defect Resolution Time

| Week | Avg Resolution Time | Target | Status |
|------|-------------------|--------|--------|
| Week 1 | 72 hours | < 48h | ❌ |
| Week 2 | 56 hours | < 48h | ❌ |
| Week 3 | 45 hours | < 48h | ✅ |
| Week 4 | 36 hours | < 48h | ✅ |

---

## 4. Module-wise Trends

| Module | Week 1 | Week 2 | Week 3 | Week 4 | Trend |
|--------|--------|--------|--------|--------|-------|
| Authentication | 100% | 100% | 100% | 100% | ➡️ Stable |
| Employee | 75% | 85% | 92% | 96% | 📈 Improving |
| Leave | 70% | 80% | 88% | 91% | 📈 Improving |
| Attendance | 80% | 88% | 90% | 92% | 📈 Improving |
| Payroll | 65% | 75% | 85% | 90% | 📈 Improving |
| Reports | 90% | 95% | 95% | 100% | 📈 Improving |

---

## 5. Test Execution Time Trend

| Week | Total Time | Delta | Avg per Test |
|------|-----------|-------|-------------|
| Week 1 | 4h 30m | - | 1.8 min |
| Week 2 | 3h 45m | -17% | 1.5 min |
| Week 3 | 3h 15m | -13% | 1.3 min |
| Week 4 | 2h 45m | -15% | 1.1 min |

**Total Improvement: 40% faster execution**

---

## 6. Recommendations

### 6.1 Successes to Maintain
- Authentication module stability
- Improved defect resolution time

### 6.2 Areas for Improvement
1. Increase automation coverage (currently 75%)
2. Focus on Payroll module stability
3. Continue reducing execution time

### 6.3 Next Month Goals
- Achieve 98% pass rate
- Increase automation to 85%
- Reduce execution time by additional 15%

---

## 7. Appendix

### 7.1 Data Sources
- TestNG execution results
- JIRA defect tracking
- CI/CD pipeline metrics
- Allure reports

### 7.2 Definitions
- **Pass Rate**: (Passed / Executed) × 100%
- **Execution Time**: Time from first test to last test completion
- **Defect Resolution**: Time from defect creation to resolved status

```

---

## 5. Report Generation Tools

### 5.1 Report Generation Pipeline

```
REPORT GENERATION PIPELINE
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│                         REPORT GENERATION FLOW                               │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  1. TEST EXECUTION                                                        │
│     └── Maven/TestNG runs tests                                           │
│     └── Results in test-output/                                           │
│                                                                             │
│  2. COLLECTION                                                            │
│     └── Gather TestNG XML results                                         │
│     └── Collect Extent Report                                             │
│     └── Fetch Allure results                                              │
│                                                                             │
│  3. AGGREGATION                                                           │
│     └── Parse test results                                                │
│     └── Calculate metrics                                                 │
│     └── Correlate with requirements                                      │
│                                                                             │
│  4. GENERATION                                                            │
│     └── Generate Extent HTML Report                                       │
│     └── Generate Allure Report                                            │
│     └── Generate Markdown reports                                         │
│                                                                             │
│  5. DISTRIBUTION                                                          │
│     └── Publish to CI/CD dashboard                                        │
│     └── Email stakeholders                                                │
│     └── Archive to wiki/docs                                            │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 5.2 Maven Commands for Reports

```bash
# GENERATE REPORTS

# 1. Run tests and generate reports
mvn clean test

# 2. Generate Extent Report
mvn test -DextentReport=true
# Output: extentReports/TestExecution.html

# 3. Generate Allure Report
mvn allure:report
# Output: target/site/allure-maven-plugin/

# 4. View Allure locally
mvn allure:serve

# 5. Generate combined report
mvn test allure:report
# Then combine with custom markdown generator

# 6. Run specific suite
mvn test -DsuiteXmlFile=src/test/resources/suites/SuiteRegression.xml

# 7. Parallel execution with reports
mvn test -Dparallel=methods -DthreadCount=4
```

### 5.3 Report Configuration

```xml
<!-- pom.xml report configuration -->

<properties>
    <!-- Extent Report -->
    <extent.reports.dir>extentReports</extent.reports.dir>
    <extent.reporter>html,spark</extent.reporter>
    <extent.config.theme>standard</extent.config.theme>
    <extent.config.documentTitle>HRM Test Report</extent.config.documentTitle>
    
    <!-- Allure -->
    <allure.results.directory>allure-results</allure.results.directory>
    <allure.link.issue.pattern>https://jira.example.com/browse/{}</allure.link.issue.pattern>
</properties>

<build>
    <plugins>
        <plugin>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports-maven-plugin</artifactId>
            <configuration>
                <reportName>HRM Automation Test Report</reportName>
            </configuration>
        </plugin>
        
        <plugin>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

---

## 6. Dashboard & Visualization

### 6.1 Dashboard Components

```
TEST EXECUTION DASHBOARD
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│                    TEST EXECUTION DASHBOARD                                  │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  QUICK STATS                              TREND CHART                       │
│  ┌─────────┬─────────┬─────────┐        ┌──────────────────────────────┐  │
│  │  150   │  95.2%  │  2h45m  │        │  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░  │  │
│  │  Total │ Pass %  │ Duration │        │  Pass Rate Trend             │  │
│  └─────────┴─────────┴─────────┘        └──────────────────────────────┘  │
│                                                                             │
│  MODULE BREAKDOWN                      DEFECTS BY SEVERITY                  │
│  ┌───────────────────────────┐        ┌──────────────────────────────┐  │
│  │ Auth ████████████████ 100%│        │  Critical ██ 1              │  │
│  │ Emp  ███████████████ 96% │        │  High     ████ 2              │  │
│  │ Leave███████████████ 91% │        │  Medium   ██ 1                │  │
│  │ Att  ███████████████ 92% │        │  Low      ██ 1                │  │
│  │ Pay  ██████████████  90% │        └──────────────────────────────┘  │
│  │ Rpt  ████████████████100%│                                       │
│  └───────────────────────────┘        RECENT EXECUTIONS                  │
│                                        ┌──────────────────────────────┐  │
│  RECENT FAILURES                      │ Run #45  ▓▓▓▓▓ PASS  95.2% │  │
│  ┌───────────────────────────┐        │ Run #44  ▓▓▓▓░ FAIL  88.5% │  │
│  │ TC-EMP-078  XSS Vuln  🔴  │        │ Run #43  ▓▓▓░░ FAIL  85.0% │  │
│  │ TC-EMP-079  SQL Inj   🔴  │        │ Run #42  ▓▓▓▓░ FAIL  90.0% │  │
│  │ TC-LEAVE-045 Calc Err 🟠  │        └──────────────────────────────┘  │
│  └───────────────────────────┘                                          │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 6.2 Dashboard Tools

| Tool | Purpose | Integration |
|------|---------|-------------|
| **Allure** | Test reporting | CI/CD, Jenkins |
| **Extent Reports** | HTML reporting | Maven, TestNG |
| **Grafana** | Metrics dashboard | Prometheus, InfluxDB |
| **Kibana** | Log analysis | Elasticsearch |
| **JUnit XML** | Standard format | Any CI/CD |

---

## 7. Communication & Distribution

### 7.1 Report Distribution Matrix

```
REPORT DISTRIBUTION
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│                          DISTRIBUTION LIST                                  │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  EXECUTIVE SUMMARY                                                         │
│  ├── Who: VP Engineering, Product Director, Release Manager               │
│  ├── When: After each major release/sprint                                 │
│  ├── Format: PDF, Email                                                   │
│  └── Frequency: Weekly, End of Sprint                                      │
│                                                                             │
│  TEST EXECUTION REPORT                                                     │
│  ├── Who: QA Lead, Project Manager, Development Lead                      │
│  ├── When: After each test execution                                       │
│  ├── Format: HTML, Markdown                                               │
│  └── Frequency: Daily during testing                                       │
│                                                                             │
│  DEFECT REPORT                                                             │
│  ├── Who: QA Team, Developers, QA Lead                                    │
│  ├── When: With each execution report                                     │
│  ├── Format: Excel, Markdown                                              │
│  └── Frequency: Per execution cycle                                         │
│                                                                             │
│  TECHNICAL REPORT                                                          │
│  ├── Who: QA Team, Developers                                             │
│  ├── When: As needed                                                       │
│  ├── Format: HTML (Allure/Extent)                                         │
│  └── Frequency: Per test run                                              │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 7.2 Communication Templates

```markdown
## Email Template: Test Execution Complete

Subject: [TEST REPORT] Sprint 3 Execution Complete - Pass Rate: 95.2%

Hi Team,

Test execution for Sprint 3 has been completed.

📊 SUMMARY
├── Total Tests: 150
├── Passed: 138 (95.2%)
├── Failed: 5
└── Blocked: 2

🔴 CRITICAL ISSUES (Blocking Release)
1. DEF-001: XSS Vulnerability in Employee Edit
2. DEF-002: SQL Injection in Employee Search

✅ NEXT STEPS
1. Development to fix critical defects by EOD tomorrow
2. Re-run failed tests after fixes
3. Final sign-off by QA Lead

📎 ATTACHMENTS
- Full Test Report: [link]
- Extent Report: [link]
- Allure Dashboard: [link]

Please review and provide updates.

Best regards,
QA Team
```

---

## 8. Best Practices

### 8.1 Report Quality Checklist

```
REPORT QUALITY CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

□ ACCURACY
├── All data verified against source
├── Calculations correct
├── No typos or errors
└── Facts are objective

□ COMPLETENESS
├── Executive summary included
├── Detailed results available
├── Defects documented
├── Recommendations provided
└── All metrics populated

□ CLARITY
├── Clear structure
├── Visual aids (charts, tables)
├── Jargon-free for executive reports
├── Technical details in appendices
└── Consistent formatting

□ TIMELINESS
├── Delivered within SLA
├── Updated promptly after changes
├── Fresh data
└── Historical comparison available

□ ACTIONABILITY
├── Clear findings
├── Prioritized issues
├── Specific recommendations
├── Owner assigned
└── Next steps defined

```

### 8.2 Common Pitfalls

| Pitfall | Impact | Prevention |
|---------|--------|------------|
| Delayed reporting | Wrong decisions | Automate generation |
| Incomplete data | Missed issues | Automated collection |
| Unclear findings | No action taken | Follow template |
| Too much detail | Information overload | Use executive summary |
| No recommendations | Issues unaddressed | Always include actions |

---

## 9. Deliverables

### 9.1 Report Inventory

| Report | Format | Location | Frequency |
|--------|--------|----------|-----------|
| Executive Summary | PDF, Email | Email, Wiki | Weekly |
| Test Execution Report | HTML, Markdown | CI/CD, Wiki | Per run |
| Defect Report | Excel, Markdown | JIRA, Wiki | Per run |
| Trend Report | Dashboard, PDF | Dashboard, Wiki | Monthly |
| Technical Report | HTML | Allure, Extent | Per run |

### 9.2 Report Storage Structure

```
outputs/reports/
├── 2026/
│   ├── May/
│   │   ├── Week1/
│   │   │   ├── executive-summary.pdf
│   │   │   ├── test-execution-report.md
│   │   │   ├── defect-report.xlsx
│   │   │   └── extent-report.html
│   │   ├── Week2/
│   │   └── Week3/
│   └── June/
├── archive/
│   ├── 2025/
│   └── 2024/
└── templates/
    ├── executive-summary-template.md
    ├── test-execution-report-template.md
    └── defect-report-template.xlsx
```

---

## 10. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer | Initial workflow document |
