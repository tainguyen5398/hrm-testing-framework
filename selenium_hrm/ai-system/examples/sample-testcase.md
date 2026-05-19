# Sample: Test Case Suite - Leave Management Module

## Document Metadata

| Field | Value |
|-------|-------|
| Document ID | TC-LEAVE-20260511-001 |
| Version | 1.0 |
| Author | Nguyen Van A - Senior QA |
| Date | 2026-05-11 |
| Status | Approved |
| Module | Leave Management |
| Related Analysis | AR-LEAVE-20260511-001 |

---

## Executive Summary

This test case suite covers the **Leave Management Module** of the HRM System. It includes comprehensive test cases for leave request submission, approval workflow, balance tracking, and cancellation.

**Test Suite Summary:**
- Total Test Cases: 45
- P0 (Critical): 12
- P1 (High): 18
- P2 (Medium): 10
- P3 (Low): 5
- Automatable: 38 (84%)
- Manual Only: 7 (16%)

---

## 1. Test Suite Overview

### 1.1 Scope

#### 1.1.1 In Scope

| # | Feature | Test Case IDs | Priority |
|---|---------|---------------|----------|
| 1 | Leave Request Submission | TC-LEAVE-001 to TC-LEAVE-012 | P0 |
| 2 | Leave Approval Workflow | TC-LEAVE-013 to TC-LEAVE-022 | P0 |
| 3 | Leave Balance Tracking | TC-LEAVE-023 to TC-LEAVE-028 | P1 |
| 4 | Leave Cancellation | TC-LEAVE-029 to TC-LEAVE-035 | P1 |
| 5 | Leave Encashment | TC-LEAVE-036 to TC-LEAVE-040 | P2 |
| 6 | Leave Reports | TC-LEAVE-041 to TC-LEAVE-045 | P2 |

#### 1.1.2 Out of Scope

| # | Feature | Reason |
|---|---------|--------|
| 1 | Leave quota configuration | Admin module - separate project |
| 2 | Public holiday management | Separate HR process |
| 3 | Mobile app leave features | Separate project |

### 1.2 Module Dependencies

| Dependency | Impact | Test Considerations |
|------------|--------|-------------------|
| Employee Management | Need employee data | Employee must exist for leave |
| Payroll Module | Balance deduction | Verify salary calculation on approval |
| Notification Service | Email alerts | Test notification delivery |
| Approval Workflow Engine | Multi-level approval | Complex routing scenarios |

---

## 2. Test Cases by Category

### 2.1 Test Cases by Category

| Category | Count | Percentage |
|----------|-------|------------|
| Positive Cases | 20 | 44% |
| Negative Cases | 12 | 27% |
| Boundary Cases | 8 | 18% |
| Security Cases | 3 | 7% |
| Performance Cases | 2 | 4% |

### 2.2 Test Cases by Type

| Type | Count | Percentage |
|------|-------|------------|
| Functional | 35 | 78% |
| Integration | 6 | 13% |
| E2E | 3 | 7% |
| Non-Functional | 1 | 2% |

### 2.3 Test Cases by Priority

| Priority | Count | Automated | Manual Only |
|----------|-------|-----------|-------------|
| P0 (Critical) | 12 | 12 | 0 |
| P1 (High) | 18 | 16 | 2 |
| P2 (Medium) | 10 | 7 | 3 |
| P3 (Low) | 5 | 3 | 2 |

---

## 3. Test Cases

### 3.1 Leave Request Submission

#### TC-LEAVE-001: Submit Leave Request with Valid Data

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-001 |
| **Test Suite** | Leave Request |
| **Module** | Leave Management |
| **Feature** | Leave Request Submission |
| **Title** | Submit leave request with valid data |
| **Objective** | Verify employee can submit a valid leave request |
| **Priority** | P0 |
| **Severity** | Critical |
| **Type** | Positive |
| **Pre-conditions** | 1. User is logged in as Employee<br>2. Employee has sufficient leave balance<br>3. Leave page is accessible |
| **Test Steps** | |
| 1 | Navigate to Leave > Request Leave |
| 2 | Select leave type: "Annual Leave" |
| 3 | Select start date: Tomorrow's date |
| 4 | Select end date: 3 days from start |
| 5 | Enter reason: "Family vacation" |
| 6 | Click Submit button |
| **Test Data** | |
| Leave Type | Annual Leave |
| Start Date | Tomorrow (2026-05-12) |
| End Date | 2026-05-14 |
| Days | 3 |
| Reason | Family vacation |
| **Expected Result** | |
| 1 | Success message: "Leave request submitted successfully" |
| 2 | Request status: "Pending Approval" |
| 3 | Leave balance updated (preview) |
| 4 | Manager notification sent |
| 5 | Redirected to leave request list |
| **Actual Result** | [To be filled during execution] |
| **Status** | [Pass/Fail/Blocked] |
| **Automated** | Yes |
| **Automation Effort** | Low |
| **Traceability** | REQ-LEAVE-001 |
| **Created By** | Nguyen Van A |
| **Created Date** | 2026-05-11 |

---

#### TC-LEAVE-002: Submit Leave Request - Insufficient Balance

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-002 |
| **Title** | Submit leave request with insufficient balance |
| **Objective** | Verify system rejects leave request when balance is insufficient |
| **Priority** | P0 |
| **Severity** | Critical |
| **Type** | Negative |
| **Pre-conditions** | 1. User is logged in as Employee<br>2. Employee has 2 days leave balance |
| **Test Steps** | |
| 1 | Navigate to Leave > Request Leave |
| 2 | Select leave type: "Annual Leave" |
| 3 | Select start date: Tomorrow |
| 4 | Select end date: 5 days from start |
| 5 | Click Submit button |
| **Test Data** | |
| Leave Type | Annual Leave |
| Requested Days | 5 |
| Available Balance | 2 |
| **Expected Result** | |
| 1 | Error message: "Insufficient leave balance. Available: 2 days, Requested: 5 days" |
| 2 | Form remains open for correction |
| 3 | No leave request created |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-002 |

---

#### TC-LEAVE-003: Submit Leave Request - Past Date

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-003 |
| **Title** | Submit leave request for past date |
| **Objective** | Verify system rejects leave request with past start date |
| **Priority** | P0 |
| **Severity** | Major |
| **Type** | Negative |
| **Pre-conditions** | User is logged in as Employee |
| **Test Steps** | |
| 1 | Navigate to Leave > Request Leave |
| 2 | Select leave type: "Annual Leave" |
| 3 | Select start date: Yesterday |
| 4 | Click Submit button |
| **Test Data** | |
| Start Date | Yesterday (2026-05-10) |
| **Expected Result** | |
| 1 | Validation error: "Start date cannot be in the past" |
| 2 | Submit button remains disabled |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-001 |

---

#### TC-LEAVE-004: Submit Leave Request - End Date Before Start Date

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-004 |
| **Title** | Submit leave request with end date before start date |
| **Objective** | Verify system validates date range correctly |
| **Priority** | P0 |
| **Severity** | Major |
| **Type** | Negative |
| **Pre-conditions** | User is logged in as Employee |
| **Test Steps** | |
| 1 | Navigate to Leave > Request Leave |
| 2 | Select leave type: "Annual Leave" |
| 3 | Select start date: June 10, 2026 |
| 4 | Select end date: June 5, 2026 |
| 5 | Observe validation |
| **Test Data** | |
| Start Date | 2026-06-10 |
| End Date | 2026-06-05 |
| **Expected Result** | |
| 1 | Error message: "End date must be after or equal to start date" |
| 2 | Days count shows 0 or error |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-001 |

---

#### TC-LEAVE-005: Submit Leave Request - Overlapping Approved Leave

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-005 |
| **Title** | Submit leave request overlapping existing approved leave |
| **Objective** | Verify system prevents duplicate leave requests |
| **Priority** | P1 |
| **Severity** | Major |
| **Type** | Negative |
| **Pre-conditions** | 1. User has an approved leave from June 1-5, 2026<br>2. User is logged in as Employee |
| **Test Steps** | |
| 1 | Navigate to Leave > Request Leave |
| 2 | Select leave type: "Annual Leave" |
| 3 | Select start date: June 3, 2026 (within existing range) |
| 4 | Select end date: June 7, 2026 |
| 5 | Click Submit button |
| **Test Data** | |
| Existing Leave | June 1-5, 2026 (Approved) |
| New Request | June 3-7, 2026 |
| Overlap Days | June 3-5 |
| **Expected Result** | |
| 1 | Error message: "Leave request overlaps with approved leave from June 1 to June 5" |
| 2 | No duplicate request created |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-006 |

---

#### TC-LEAVE-006: Submit Leave Request - Single Day Leave

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-006 |
| **Title** | Submit single day leave request |
| **Objective** | Verify single day leave can be submitted |
| **Priority** | P1 |
| **Severity** | Minor |
| **Type** | Positive |
| **Pre-conditions** | User is logged in as Employee with sufficient balance |
| **Test Steps** | |
| 1 | Navigate to Leave > Request Leave |
| 2 | Select leave type: "Annual Leave" |
| 3 | Select start date and end date: Same day (June 20, 2026) |
| 4 | Enter reason: "Doctor appointment" |
| 5 | Click Submit button |
| **Test Data** | |
| Leave Type | Annual Leave |
| Start Date | 2026-06-20 |
| End Date | 2026-06-20 |
| Days | 1 |
| **Expected Result** | |
| 1 | Success message displayed |
| 2 | Days count = 1 |
| 3 | Leave request created with 1 day |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-001 |

---

### 3.2 Leave Approval Workflow

#### TC-LEAVE-013: Manager Approves Leave Request

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-013 |
| **Test Suite** | Leave Approval |
| **Module** | Leave Management |
| **Feature** | Leave Approval Workflow |
| **Title** | Manager approves leave request |
| **Objective** | Verify manager can approve pending leave request |
| **Priority** | P0 |
| **Severity** | Critical |
| **Type** | Positive |
| **Pre-conditions** | 1. Employee submitted a leave request (June 15-17, 2026)<br>2. User is logged in as Manager |
| **Test Steps** | |
| 1 | Navigate to Leave > Approval |
| 2 | View pending leave requests |
| 3 | Select the pending request from employee |
| 4 | Click Approve button |
| 5 | Add optional comment: "Approved. Enjoy!" |
| 6 | Confirm approval |
| **Test Data** | |
| Leave Type | Annual Leave |
| Requested Days | 3 |
| Employee | Test Employee |
| **Expected Result** | |
| 1 | Status changes to "Approved" |
| 2 | Success message: "Leave request approved" |
| 3 | Employee notified via email |
| 4 | Leave balance deducted |
| 5 | Request appears in "Approved" tab |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-004 |

---

#### TC-LEAVE-014: Manager Rejects Leave Request

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-014 |
| **Title** | Manager rejects leave request with reason |
| **Objective** | Verify manager can reject leave request with reason |
| **Priority** | P0 |
| **Severity** | Major |
| **Type** | Positive |
| **Pre-conditions** | 1. Employee submitted a leave request<br>2. User is logged in as Manager |
| **Test Steps** | |
| 1 | Navigate to Leave > Approval |
| 2 | Select pending leave request |
| 3 | Click Reject button |
| 4 | Enter rejection reason: "Critical project deadline" |
| 5 | Confirm rejection |
| **Test Data** | |
| Rejection Reason | Critical project deadline |
| **Expected Result** | |
| 1 | Status changes to "Rejected" |
| 2 | Rejection reason displayed |
| 3 | Employee notified via email |
| 4 | Leave balance NOT deducted |
| 5 | Request appears in "Rejected" tab |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-004 |

---

#### TC-LEAVE-015: Leave Request Exceeds 5 Days - Requires HR Approval

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-015 |
| **Title** | Leave request > 5 days requires HR approval |
| **Objective** | Verify multi-level approval for long leave requests |
| **Priority** | P0 |
| **Severity** | Critical |
| **Type** | Positive |
| **Pre-conditions** | 1. User is logged in as Employee<br>2. Employee has sufficient balance |
| **Test Steps** | |
| 1 | Navigate to Leave > Request Leave |
| 2 | Submit leave request for 7 days (June 1-7) |
| 3 | Verify request is submitted |
| 4 | Login as Manager |
| 5 | Approve the request |
| 6 | Login as HR |
| 7 | Verify HR receives pending approval |
| 8 | HR approves the request |
| **Test Data** | |
| Leave Days | 7 |
| Manager Approval | Required |
| HR Approval | Required |
| **Expected Result** | |
| 1 | After manager approval: Status = "Pending HR Approval" |
| 2 | HR receives notification |
| 3 | After HR approval: Status = "Approved" |
| 4 | Both approvals logged in history |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-004, BR-LEAVE-005 |

---

#### TC-LEAVE-016: Approval Timeout - Auto Escalation

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-016 |
| **Title** | Leave request auto-escalates after timeout |
| **Objective** | Verify requests escalate to backup approver after timeout |
| **Priority** | P1 |
| **Severity** | Major |
| **Type** | Negative |
| **Pre-conditions** | 1. Leave request submitted<br>2. Manager has 3-day approval window |
| **Test Steps** | |
| 1 | Submit leave request |
| 2 | Wait 3 business days without manager action |
| 3 | Observe auto-escalation |
| **Test Data** | |
| Timeout Period | 3 business days |
| Escalation Target | Backup Approver |
| **Expected Result** | |
| 1 | Request escalated to backup approver |
| 2 | Manager notified of escalation |
| 3 | Backup approver receives notification |
| 4 | Escalation logged in history |
| **Automated** | No |
| **Traceability** | BR-LEAVE-005 |
| **Notes** | Manual test due to time dependency |

---

### 3.3 Leave Balance Tracking

#### TC-LEAVE-023: Verify Leave Balance Display

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-023 |
| **Test Suite** | Leave Balance |
| **Module** | Leave Management |
| **Feature** | Leave Balance Tracking |
| **Title** | Verify leave balance is displayed correctly |
| **Objective** | Verify employee can view their current leave balance |
| **Priority** | P1 |
| **Severity** | Major |
| **Type** | Positive |
| **Pre-conditions** | 1. User is logged in as Employee<br>2. Employee has 15 Annual, 10 Sick, 3 Personal days |
| **Test Steps** | |
| 1 | Navigate to Leave > My Leave |
| 2 | View leave balance section |
| 3 | Verify displayed balances |
| **Test Data** | |
| Annual Leave | 15 days |
| Sick Leave | 10 days |
| Personal Leave | 3 days |
| **Expected Result** | |
| 1 | Annual Leave: 15 days displayed |
| 2 | Sick Leave: 10 days displayed |
| 3 | Personal Leave: 3 days displayed |
| 4 | Last updated date shown |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-005 |

---

#### TC-LEAVE-024: Leave Balance Updates After Approval

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-024 |
| **Title** | Leave balance updates after approval |
| **Objective** | Verify balance is deducted after leave approval |
| **Priority** | P0 |
| **Severity** | Critical |
| **Type** | Integration |
| **Pre-conditions** | 1. Employee has 15 annual leave days<br>2. Employee submits 3-day leave request |
| **Test Steps** | |
| 1 | Employee: Submit 3-day leave request |
| 2 | Manager: Approve the request |
| 3 | Employee: Check leave balance |
| **Test Data** | |
| Initial Balance | 15 days |
| Requested Days | 3 days |
| Remaining Balance | 12 days |
| **Expected Result** | |
| 1 | Balance reduced from 15 to 12 days |
| 2 | Deduction logged in leave history |
| 3 | Deduction reflects approved request |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-005 |

---

#### TC-LEAVE-025: Leave Balance Carry-Over Year End

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-025 |
| **Title** | Leave balance carries over at year end |
| **Objective** | Verify unused leave days carry over (max 5 days) |
| **Priority** | P0 |
| **Severity** | Critical |
| **Type** | Integration |
| **Pre-conditions** | Year-end (December 31, 2025) with unused leave |
| **Test Steps** | |
| 1 | Verify employee has 7 unused annual leave days |
| 2 | Execute year-end carry-over process |
| 3 | Check new year balance |
| **Test Data** | |
| Unused Days 2025 | 7 days |
| Carry-Over Limit | 5 days |
| Encashment Days | 2 days |
| **Expected Result** | |
| 1 | 5 days carried to new year |
| 2 | 2 days encashed (if applicable) |
| 3 | New year balance = 18 (allotted) + 5 (carry-over) = 23 days |
| 4 | Carry-over logged |
| **Automated** | No |
| **Traceability** | REQ-LEAVE-010, BR-LEAVE-003 |
| **Notes** | End-to-end test at year-end |

---

### 3.4 Leave Cancellation

#### TC-LEAVE-029: Cancel Leave Request - Pending Status

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-029 |
| **Test Suite** | Leave Cancellation |
| **Module** | Leave Management |
| **Feature** | Leave Cancellation |
| **Title** | Cancel pending leave request |
| **Objective** | Verify employee can cancel their pending leave request |
| **Priority** | P1 |
| **Severity** | Major |
| **Type** | Positive |
| **Pre-conditions** | Employee has a pending leave request |
| **Test Steps** | |
| 1 | Navigate to Leave > My Leave |
| 2 | Select pending leave request |
| 3 | Click Cancel button |
| 4 | Confirm cancellation |
| **Expected Result** | |
| 1 | Status changes to "Cancelled" |
| 2 | Success message displayed |
| 3 | Leave balance not affected |
| 4 | Manager notified of cancellation |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-006 |

---

#### TC-LEAVE-030: Cancel Approved Leave - Late Cancellation Penalty

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-030 |
| **Title** | Cancel approved leave within 24 hours - penalty applied |
| **Objective** | Verify penalty is applied for late cancellation |
| **Priority** | P1 |
| **Severity** | Major |
| **Type** | Negative |
| **Pre-conditions** | Employee has approved leave starting in 12 hours |
| **Test Steps** | |
| 1 | Navigate to Leave > My Leave |
| 2 | Select approved leave request |
| 3 | Click Cancel button |
| 4 | Observe penalty warning |
| 5 | Confirm cancellation |
| **Test Data** | |
| Hours Until Start | 12 hours |
| Penalty | 1 day deducted |
| **Expected Result** | |
| 1 | Warning message: "Late cancellation - 1 day penalty will be applied" |
| 2 | Employee confirms or cancels action |
| 3 | If confirmed: Status = "Cancelled" |
| 4 | 1 day penalty deducted from balance |
| 5 | Balance log shows penalty |
| **Automated** | Yes |
| **Traceability** | BR-LEAVE-006 |

---

### 3.5 Boundary Test Cases

#### TC-LEAVE-040: Leave Request - Maximum Days (14 Days)

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-040 |
| **Test Suite** | Leave Request |
| **Module** | Leave Management |
| **Feature** | Boundary Testing |
| **Title** | Submit maximum allowed leave request (14 days) |
| **Objective** | Verify system accepts maximum leave days |
| **Priority** | P2 |
| **Severity** | Minor |
| **Type** | Boundary |
| **Pre-conditions** | Employee has 18+ leave balance |
| **Test Steps** | |
| 1 | Submit leave request for exactly 14 days |
| 2 | Verify acceptance |
| **Test Data** | |
| Requested Days | 14 (maximum allowed) |
| **Expected Result** | |
| 1 | Request accepted |
| 2 | Days count = 14 |
| 3 | No validation errors |
| **Automated** | Yes |
| **Traceability** | BR-LEAVE-001 |

---

#### TC-LEAVE-041: Leave Request - Exceeds Maximum (15 Days)

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-041 |
| **Title** | Submit leave request exceeding maximum (15 days) |
| **Objective** | Verify system rejects leave exceeding maximum |
| **Priority** | P2 |
| **Severity** | Minor |
| **Type** | Boundary |
| **Pre-conditions** | Employee has sufficient balance |
| **Test Steps** | |
| 1 | Attempt to submit 15-day leave request |
| 2 | Observe rejection |
| **Test Data** | |
| Requested Days | 15 (exceeds max) |
| Maximum Allowed | 14 days |
| **Expected Result** | |
| 1 | Validation error: "Leave request cannot exceed 14 days" |
| 2 | Request rejected |
| **Automated** | Yes |
| **Traceability** | BR-LEAVE-001 |

---

### 3.6 E2E Test Cases

#### TC-LEAVE-043: Complete Leave Request to Approval Flow

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LEAVE-043 |
| **Test Suite** | E2E |
| **Module** | Leave Management |
| **Feature** | End-to-End Testing |
| **Title** | Complete leave request to approval workflow |
| **Objective** | Verify complete leave workflow from request to completion |
| **Priority** | P0 |
| **Severity** | Critical |
| **Type** | E2E |
| **Pre-conditions** | 1. Test employee account exists<br>2. Test manager account exists<br>3. Employee has sufficient balance |
| **Test Steps** | |
| 1 | Login as Employee |
| 2 | Submit 3-day leave request |
| 3 | Verify request status: Pending |
| 4 | Logout and login as Manager |
| 5 | View pending requests |
| 6 | Approve the request |
| 7 | Login as Employee |
| 8 | Verify leave is approved |
| 9 | Verify balance deducted |
| 10 | Verify leave appears in calendar |
| **Test Data** | |
| Leave Type | Annual Leave |
| Days | 3 |
| Employee | test.employee |
| Manager | test.manager |
| **Expected Result** | |
| 1 | Complete workflow successful |
| 2 | All status transitions correct |
| 3 | Balance correctly updated |
| 4 | Notification delivered |
| 5 | Calendar updated |
| **Automated** | Yes |
| **Traceability** | REQ-LEAVE-001 to REQ-LEAVE-005 |
| **Estimated Duration** | 5 minutes |

---

## 4. Traceability Matrix

### 4.1 Requirements to Test Cases

| Req ID | Requirement | Test Case IDs | Priority | Coverage |
|--------|-------------|--------------|----------|----------|
| REQ-LEAVE-001 | Submit leave request | TC-LEAVE-001, TC-LEAVE-003, TC-LEAVE-004, TC-LEAVE-006 | P0 | 100% |
| REQ-LEAVE-002 | Validate leave balance | TC-LEAVE-002 | P0 | 100% |
| REQ-LEAVE-003 | Notification to manager | TC-LEAVE-001, TC-LEAVE-043 | P0 | 100% |
| REQ-LEAVE-004 | Approve/Reject leave | TC-LEAVE-013, TC-LEAVE-014, TC-LEAVE-015 | P0 | 100% |
| REQ-LEAVE-005 | Update balance on approval | TC-LEAVE-023, TC-LEAVE-024 | P0 | 100% |
| REQ-LEAVE-006 | Auto-cancel on conflict | TC-LEAVE-005, TC-LEAVE-029 | P1 | 100% |
| REQ-LEAVE-007 | View leave history | TC-LEAVE-023 | P1 | 100% |
| REQ-LEAVE-009 | Encashment process | TC-LEAVE-025, TC-LEAVE-036-038 | P2 | 100% |
| REQ-LEAVE-010 | Carry-over | TC-LEAVE-025 | P0 | 100% |

### 4.2 Coverage Summary

| Category | Count | Coverage |
|----------|-------|----------|
| Total Requirements | 9 | 100% |
| Fully Covered | 9 | 100% |
| Partially Covered | 0 | 0% |
| Not Covered | 0 | 0% |
| Total Test Cases | 45 | - |

---

## 5. Test Data Requirements

### 5.1 Required Test Accounts

| Account Type | Username | Password | Role | Leave Balance |
|--------------|----------|----------|------|---------------|
| Test Employee | employee.test | Test@123 | Employee | 15 Annual, 10 Sick |
| Test Manager | manager.test | Test@123 | Manager | N/A |
| Test HR | hr.test | Test@123 | HR | N/A |
| Backup Manager | manager.backup | Test@123 | Manager | N/A |

### 5.2 Test Data Files

| File | Location | Sheets |
|------|----------|--------|
| LeaveTestData.xlsx | src/test/resources/testdata/leave/ | Create_Valid, Create_Invalid, Approval, Balance |

---

## 6. Test Environment Requirements

| Requirement | Specification |
|-------------|---------------|
| Browser | Chrome 120, Firefox 120 |
| Environment | QA (https://qa-hrm.example.com) |
| Database | QA Database with synthetic data |
| API | Mock notification service |
| Users | Test accounts as specified |

---

## 7. Review & Sign-off

### 7.1 Review Checklist

- [x] All requirements covered
- [x] Test cases are executable
- [x] Test data identified
- [x] Traceability complete
- [x] Boundary conditions covered
- [x] E2E scenarios included
- [x] Peer review completed

### 7.2 Approval

| Role | Name | Date | Status |
|------|------|------|--------|
| QA Engineer | Nguyen Van A | 2026-05-11 | Submitted |
| QA Lead | Tran Thi B | 2026-05-11 | Approved |
| Product Owner | Le Van C | 2026-05-12 | Approved |

---

## 8. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 0.1 | 2026-05-08 | Nguyen Van A | Initial draft |
| 0.2 | 2026-05-10 | Nguyen Van A | Added boundary cases |
| 1.0 | 2026-05-11 | Nguyen Van A | Final version after review |
