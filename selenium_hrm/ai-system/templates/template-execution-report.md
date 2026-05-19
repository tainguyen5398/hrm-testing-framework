# Template: Test Execution Report

## Document Metadata

| Field | Value |
|-------|-------|
| Document ID | EXEC-[Module]-[YYYYMMDD]-[Run#] |
| Version | 1.0 |
| Author | QA Engineer |
| Date | YYYY-MM-DD |
| Status | Draft/Completed |
| Module | [Module Name] |
| Test Suite | [Suite Name] |
| Environment | [Environment] |

---

## Executive Summary

[Brief overview of test execution results. Include overall status, key metrics, and notable findings.]

**Execution Summary:**
- Total Test Cases: [N]
- Executed: [N]
- Passed: [N]
- Failed: [N]
- Blocked: [N]
- Pass Rate: [X]%
- Execution Date: [Date]
- Duration: [Duration]

---

## 1. Execution Overview

### 1.1 Test Execution Details

| Field | Value |
|-------|-------|
| **Execution ID** | EXEC-[ID] |
| **Start Date/Time** | [DateTime] |
| **End Date/Time** | [DateTime] |
| **Duration** | [Duration] |
| **Environment** | [QA/Staging] |
| **Build Version** | [Version] |
| **Browser** | [Chrome/Firefox/Edge] |
| **Tester** | [Name] |

### 1.2 Overall Results

| Metric | Count | Percentage |
|--------|-------|------------|
| Total Test Cases | [N] | 100% |
| Executed | [N] | [X]% |
| Passed | [N] | [X]% |
| Failed | [N] | [X]% |
| Blocked | [N] | [X]% |
| Skipped | [N] | [X]% |
| Pass Rate | - | [X]% |

### 1.3 Result Summary by Priority

| Priority | Total | Passed | Failed | Blocked | Pass Rate |
|----------|-------|--------|--------|---------|-----------|
| P0 (Critical) | [N] | [N] | [N] | [N] | [X]% |
| P1 (High) | [N] | [N] | [N] | [N] | [X]% |
| P2 (Medium) | [N] | [N] | [N] | [N] | [X]% |
| P3 (Low) | [N] | [N] | [N] | [N] | [X]% |

---

## 2. Detailed Test Results

### 2.1 Test Results by Category

#### 2.1.1 Passed Test Cases

| Test Case ID | Test Name | Priority | Duration | Executed By | Date |
|--------------|-----------|----------|----------|-------------|------|
| TC-XXX-001 | [Test name] | P0 | 30s | [Name] | YYYY-MM-DD |
| TC-XXX-002 | [Test name] | P0 | 45s | [Name] | YYYY-MM-DD |

#### 2.1.2 Failed Test Cases

| Test Case ID | Test Name | Priority | Failure Reason | Severity | Defect ID |
|--------------|-----------|----------|----------------|----------|-----------|
| TC-XXX-010 | [Test name] | P0 | [Reason] | Critical | DEF-001 |
| TC-XXX-011 | [Test name] | P1 | [Reason] | Major | DEF-002 |

#### 2.1.3 Blocked Test Cases

| Test Case ID | Test Name | Priority | Block Reason | Blocker ID |
|--------------|-----------|----------|--------------|------------|
| TC-XXX-020 | [Test name] | P2 | [Reason] | [ID] |

---

## 3. Defect Summary

### 3.1 Defect Overview

| Status | Count |
|--------|-------|
| New Defects | [N] |
| Open Defects | [N] |
| Closed Defects | [N] |
| Deferred Defects | [N] |

### 3.2 Defect Details

#### 3.2.1 New Defects

| Defect ID | Test Case | Severity | Priority | Title | Status | Assignee |
|-----------|-----------|----------|----------|-------|--------|----------|
| DEF-001 | TC-XXX-010 | Critical | P0 | [Title] | Open | [Name] |
| DEF-002 | TC-XXX-011 | Major | P1 | [Title] | Open | [Name] |

#### 3.2.2 Defect Details Template

**DEF-001: [Defect Title]**

| Field | Value |
|-------|-------|
| **Defect ID** | DEF-001 |
| **Test Case** | TC-XXX-010 |
| **Severity** | Critical/Major/Minor |
| **Priority** | P0/P1/P2/P3 |
| **Status** | Open |
| **Assignee** | [Developer] |
| **Reported Date** | YYYY-MM-DD |
| **Environment** | [Environment] |
| **Browser** | [Browser] |

**Description:**
[Detailed description of the defect]

**Steps to Reproduce:**
1. [Step 1]
2. [Step 2]
3. [Step 3]

**Expected Result:**
[What should happen]

**Actual Result:**
[What actually happened]

**Screenshots:**
[Attach screenshots]

---

## 4. Test Execution Metrics

### 4.1 Execution Time Analysis

| Metric | Value |
|--------|-------|
| Total Execution Time | [Duration] |
| Average Time per Test | [Time] |
| Longest Test | [Test Name] - [Time] |
| Shortest Test | [Test Name] - [Time] |

### 4.2 Test Execution by Module

| Module | Total | Passed | Failed | Pass Rate |
|--------|-------|--------|--------|-----------|
| [Module 1] | [N] | [N] | [N] | [X]% |
| [Module 2] | [N] | [N] | [N] | [X]% |

### 4.3 Flaky Test Analysis

| Test Case | Execution Count | Pass Count | Fail Count | Flakiness Rate |
|-----------|----------------|------------|------------|----------------|
| TC-XXX-015 | [N] | [N] | [N] | [X]% |

---

## 5. Test Coverage

### 5.1 Requirements Coverage

| Requirement ID | Requirement | Test Cases | Coverage | Status |
|----------------|-------------|------------|----------|--------|
| REQ-XXX-001 | [Description] | TC-XXX-001, TC-XXX-002 | 100% | Covered |
| REQ-XXX-002 | [Description] | TC-XXX-003 | 50% | Partial |

### 5.2 Coverage Summary

| Category | Coverage |
|----------|----------|
| Requirements Coverage | [X]% |
| Business Rules Coverage | [X]% |
| Edge Cases Coverage | [X]% |

---

## 6. Environment & Configuration

### 6.1 Environment Details

| Component | Version | Configuration |
|-----------|---------|---------------|
| Application | [Version] | [Config] |
| Browser | [Browser] | [Version] |
| Database | [DB] | [Version] |
| API | [API] | [Version] |

### 6.2 Test Data

| Data Set | Records | Source | Last Updated |
|----------|---------|--------|--------------|
| Test Users | [N] | Synthetic | YYYY-MM-DD |
| Employee Records | [N] | Synthetic | YYYY-MM-DD |

---

## 7. Risks & Issues

### 7.1 Identified Risks

| Risk ID | Risk Description | Impact | Mitigation |
|---------|-----------------|--------|------------|
| RISK-001 | [Description] | [Impact] | [Mitigation] |

### 7.2 Blockers

| Blocker | Impact | Resolution |
|---------|--------|------------|
| [Blocker] | [Impact] | [Resolution] |

---

## 8. Recommendations

### 8.1 Immediate Actions

- [ ] Fix P0 defects before release
- [ ] Re-run failed tests after fix
- [ ] Review flaky tests for stability

### 8.2 Long-term Improvements

- [ ] Add more boundary test cases
- [ ] Improve test data management
- [ ] Enhance test reporting

---

## 9. Conclusion

[Summary of test execution results and overall quality assessment]

**Overall Status:** [Green/Yellow/Red]

[Explanation of status]

---

## 10. Approval

| Role | Name | Date | Signature | Decision |
|------|------|------|-----------|----------|
| QA Engineer | | | | Submitted |
| QA Lead | | | | Approved |
| Project Manager | | | | Approved |

---

## 11. Appendices

### 11.1 Execution Logs

[Attach or link to execution logs]

### 11.2 Screenshots

[Attach or link to screenshots of failures]

### 11.3 Report Generation Info

| Tool | Version |
|------|---------|
| Extent Reports | 5.1.1 |
| TestNG | 7.4.0 |

---

## 12. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | YYYY-MM-DD | [Author] | Initial report |
