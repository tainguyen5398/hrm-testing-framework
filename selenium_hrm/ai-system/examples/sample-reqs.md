# Sample: Requirements Document - Leave Management Module

## Document Metadata

| Field | Value |
|-------|-------|
| Document ID | REQ-HRM-LEAVE-20260511-001 |
| Version | 1.0 |
| Author | Product Owner - Le Van C |
| Date | 2026-05-11 |
| Status | Approved |
| Project | HRM System |
| Module | Leave Management |

---

## Executive Summary

This document defines the functional requirements for the **Leave Management Module** of the HRM System. The module enables employees to submit leave requests, managers to approve/reject requests, and the system to track leave balances.

**Requirements Summary:**
- Total Requirements: 15
- Functional Requirements: 12
- Non-Functional Requirements: 3
- P0 (Critical): 5
- P1 (High): 6
- P2 (Medium): 4

---

## 1. Module Overview

### 1.1 Purpose

The Leave Management Module allows employees to request time off, managers to approve or reject requests, and the system to maintain accurate leave balances. This module is essential for HR operations and affects all employees in the organization.

### 1.2 System Context

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                        LEAVE MANAGEMENT SYSTEM                               │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│    ┌─────────────┐                                                         │
│    │  EMPLOYEE   │                                                         │
│    │  (Submitter)│                                                         │
│    └──────┬──────┘                                                         │
│           │                                                                 │
│           ▼                                                                 │
│    ┌─────────────────────────────────────────────┐                         │
│    │           LEAVE REQUEST                     │                         │
│    │  • Select leave type                        │                         │
│    │  • Choose dates                              │                         │
│    │  • Provide reason                           │                         │
│    │  • Submit for approval                      │                         │
│    └──────┬──────────────────────────────────────┘                         │
│           │                                                                 │
│           ▼                                                                 │
│    ┌─────────────────────────────────────────────┐                         │
│    │         BALANCE VALIDATION                   │                         │
│    │  • Check available balance                   │                         │
│    │  • Validate date range                       │                         │
│    │  • Check for conflicts                       │                         │
│    └──────┬──────────────────────────────────────┘                         │
│           │                                                                 │
│           ▼                                                                 │
│    ┌─────────────────────────────────────────────┐                         │
│    │         APPROVAL WORKFLOW                     │                         │
│    │  • Manager approval (≤5 days)                │                         │
│    │  • Manager + HR approval (>5 days)           │                         │
│    │  • Rejection handling                        │                         │
│    │  • Notification system                       │                         │
│    └──────┬──────────────────────────────────────┘                         │
│           │                                                                 │
│           ▼                                                                 │
│    ┌─────────────────────────────────────────────┐                         │
│    │         BALANCE UPDATE                        │                         │
│    │  • Deduct on approval                        │                         │
│    │  • Restore on cancellation                   │                         │
│    │  • Year-end carry-over                       │                         │
│    └─────────────────────────────────────────────┘                         │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 1.3 User Roles

| Role | Permissions |
|------|-------------|
| Employee | Submit leave, view own balance, cancel own requests |
| Manager | Approve/reject team requests, view team calendar |
| HR | Approve long leave (>5 days), view all leave reports |
| Admin | Configure leave types, manage leave policies |

---

## 2. Functional Requirements

### 2.1 Leave Request Submission

#### REQ-LEAVE-001: Submit Leave Request

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-001 |
| **Title** | Submit Leave Request |
| **Type** | Functional |
| **Priority** | P0 |
| **Status** | Approved |

**Description:**
Authenticated employee can submit a leave request specifying: leave type, start date, end date, and optional reason. System validates all inputs before submission.

**Detailed Requirements:**
1. Employee must select from available leave types: Annual, Sick, Personal, Unpaid
2. Start date must be today or future date
3. End date must be greater than or equal to start date
4. Requested days must not exceed available balance (except Unpaid leave)
5. Request must not overlap with existing approved leave
6. Optional reason field accepts up to 500 characters

**Validation Rules:**
- Start Date: >= TODAY
- End Date: >= START_DATE
- Days: >= 1 AND <= 14
- Balance: >= REQUESTED_DAYS (except Unpaid)
- No overlap with approved leaves

**Acceptance Criteria:**
- [ ] User can select leave type from dropdown
- [ ] User can select start and end date
- [ ] System calculates days automatically
- [ ] System validates against available balance
- [ ] System prevents overlapping requests
- [ ] Success message shown on submission
- [ ] Manager receives notification

**Test Traceability:**
- TC-LEAVE-001: Valid leave request
- TC-LEAVE-003: Past date rejection
- TC-LEAVE-004: End date before start date
- TC-LEAVE-005: Overlapping leave rejection

---

#### REQ-LEAVE-002: Validate Leave Balance

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-002 |
| **Title** | Validate Leave Balance |
| **Type** | Functional |
| **Priority** | P0 |
| **Status** | Approved |

**Description:**
System must validate that the employee has sufficient leave balance before accepting the request. Validation occurs both at form submission and in real-time.

**Detailed Requirements:**
1. Real-time balance check as user enters dates
2. Display available balance prominently on form
3. Show projected balance after request approval
4. Allow Unpaid leave even when balance is zero
5. Clear error message when balance is insufficient

**Business Rules:**
- Annual Leave: Standard balance 18 days/year
- Sick Leave: Standard balance 12 days/year
- Personal Leave: Standard balance 3 days/year
- Unpaid Leave: No limit

**Acceptance Criteria:**
- [ ] Balance displayed before submission
- [ ] Error shown if insufficient balance
- [ ] Unpaid leave available when balance is zero
- [ ] Projected balance shown

**Test Traceability:**
- TC-LEAVE-002: Insufficient balance rejection

---

### 2.2 Leave Approval Workflow

#### REQ-LEAVE-003: Manager Notification

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-003 |
| **Title** | Manager Notification |
| **Type** | Functional |
| **Priority** | P0 |
| **Status** | Approved |

**Description:**
Manager receives immediate notification when an employee submits a leave request. Notification includes request details and action links.

**Detailed Requirements:**
1. Email notification sent within 1 minute of submission
2. Dashboard badge updates in real-time
3. Notification includes: employee name, dates, leave type, days
4. Quick action links: Approve, Reject, View Details
5. Notification logged for audit trail

**Notification Content:**
```
Subject: Leave Request - {Employee Name}

{Employee Name} has submitted a leave request:
- Type: {Leave Type}
- Dates: {Start} to {End}
- Days: {Number of Days}
- Reason: {Reason if provided}

[View Request] [Approve] [Reject]
```

**Acceptance Criteria:**
- [ ] Email sent within 1 minute
- [ ] Dashboard badge updates immediately
- [ ] All required information included
- [ ] Action links functional

**Test Traceability:**
- TC-LEAVE-001: Notification trigger
- TC-LEAVE-043: E2E workflow

---

#### REQ-LEAVE-004: Approve/Reject Leave

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-004 |
| **Title** | Approve or Reject Leave Request |
| **Type** | Functional |
| **Priority** | P0 |
| **Status** | Approved |

**Description:**
Manager can approve or reject leave requests from their team. Approval workflow varies based on leave duration.

**Detailed Requirements:**
1. Leave ≤ 5 days: Single-level approval (Manager only)
2. Leave > 5 days: Two-level approval (Manager + HR)
3. Manager must provide reason for rejection
4. Approval creates audit log entry
5. Employee receives notification of decision
6. Backup approver can act if primary is unavailable

**Approval Flow:**
```
Leave <= 5 days:
Employee → Manager → Approved/Rejected

Leave > 5 days:
Employee → Manager → HR → Approved/Rejected
```

**Business Rules:**
- BR-LEAVE-004: Leave ≤ 5 days: Manager approval only
- BR-LEAVE-005: Leave > 5 days: Manager + HR approval

**Acceptance Criteria:**
- [ ] Manager can approve pending requests
- [ ] Manager can reject with reason
- [ ] HR approval required for >5 days
- [ ] Employee notified of decision
- [ ] Audit log created

**Test Traceability:**
- TC-LEAVE-013: Manager approval
- TC-LEAVE-014: Manager rejection
- TC-LEAVE-015: Multi-level approval

---

### 2.3 Leave Balance Management

#### REQ-LEAVE-005: Update Balance on Approval

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-005 |
| **Title** | Update Leave Balance |
| **Type** | Functional |
| **Priority** | P0 |
| **Status** | Approved |

**Description:**
System updates employee's leave balance upon leave approval. Balance is deducted when leave is approved and restored when leave is cancelled.

**Detailed Requirements:**
1. Balance deducted immediately upon approval
2. Balance restored upon cancellation (if within policy)
3. Deduction/restoration logged in balance history
4. Balance updates reflected in real-time
5. Carry-over calculated at year-end

**Balance Formula:**
```
Available Balance = Allotted Balance - Days Taken + Carry Over
```

**Acceptance Criteria:**
- [ ] Balance deducted on approval
- [ ] Balance restored on cancellation
- [ ] History log maintained
- [ ] Real-time balance display

**Test Traceability:**
- TC-LEAVE-023: Balance display
- TC-LEAVE-024: Balance update after approval

---

#### REQ-LEAVE-010: Leave Carry-Over

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-010 |
| **Title** | Leave Balance Carry-Over |
| **Type** | Functional |
| **Priority** | P0 |
| **Status** | Approved |

**Description:**
Unused annual leave days can be carried over to the next year, subject to maximum carry-over limits.

**Detailed Requirements:**
1. Maximum carry-over: 5 days
2. Carry-over days expire if not used by March 31
3. Excess days (>5) eligible for encashment
4. Carry-over processed automatically on January 1
5. Employee notified of carry-over details

**Business Rule:**
- BR-LEAVE-003: Carry-over max 5 days to next year

**Formula:**
```
Carry Over = MIN(Unused Days, 5)
New Year Balance = 18 + Carry Over
```

**Acceptance Criteria:**
- [ ] Max 5 days carry over
- [ ] Excess days encashed
- [ ] Carried-over days expire March 31
- [ ] Automatic processing on Jan 1

**Test Traceability:**
- TC-LEAVE-025: Year-end carry-over

---

### 2.4 Leave Cancellation

#### REQ-LEAVE-006: Cancel Leave Request

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-006 |
| **Title** | Cancel Leave Request |
| **Type** | Functional |
| **Priority** | P1 |
| **Status** | Approved |

**Description:**
Employee can cancel their own leave request. Cancellation rules vary based on leave status and timing.

**Detailed Requirements:**
1. Pending requests: Can cancel anytime, no penalty
2. Approved requests > 24 hours before start: Cancel with balance restore
3. Approved requests ≤ 24 hours before start: Cancel with penalty (1 day deducted)
4. Cancellation must include reason
5. Manager notified of cancellation

**Business Rule:**
- BR-LEAVE-006: Cancel request ≤ 24h before start: Penalty

**Acceptance Criteria:**
- [ ] Pending leave can be cancelled
- [ ] Approved leave can be cancelled
- [ ] Late cancellation penalty applied
- [ ] Balance restored appropriately
- [ ] Manager notified

**Test Traceability:**
- TC-LEAVE-029: Cancel pending request
- TC-LEAVE-030: Late cancellation penalty

---

### 2.5 Leave Encashment

#### REQ-LEAVE-009: Leave Encashment

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-009 |
| **Title** | Leave Encashment |
| **Type** | Functional |
| **Priority** | P2 |
| **Status** | Approved |

**Description:**
Employees can encash unused annual leave days that exceed the carry-over limit, subject to policy approval.

**Detailed Requirements:**
1. Encashment only for unused annual leave
2. Minimum encashment: 3 days
3. Encashment rate: Daily salary rate
4. Annual encashment limit: 10 days
5. Encashment processed at year-end or on termination
6. Tax deductions apply per local regulations

**Encashment Formula:**
```
Encashment Amount = Days × Daily Salary × (1 - Tax Rate)
```

**Acceptance Criteria:**
- [ ] Encashment option available
- [ ] Minimum 3 days for encashment
- [ ] Calculation correct
- [ ] Tax applied
- [ ] Payment processed

**Test Traceability:**
- TC-LEAVE-036 to TC-LEAVE-038: Encashment scenarios

---

### 2.6 Leave Reports

#### REQ-LEAVE-007: View Leave History

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-007 |
| **Title** | View Leave History |
| **Type** | Functional |
| **Priority** | P1 |
| **Status** | Approved |

**Description:**
Employee can view their complete leave history including all requests, approvals, and balance transactions.

**Detailed Requirements:**
1. Display all leave requests (all statuses)
2. Show request date, type, dates, status
3. Include approval/rejection comments
4. Show balance transactions
5. Filter by date range, status, type
6. Export to PDF/Excel

**Acceptance Criteria:**
- [ ] All requests displayed
- [ ] Status filters work
- [ ] Date filters work
- [ ] Export function works

**Test Traceability:**
- TC-LEAVE-041 to TC-LEAVE-042: Leave history scenarios

---

## 3. Non-Functional Requirements

### 3.1 Performance

#### REQ-LEAVE-NF-001: Response Time

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-NF-001 |
| **Title** | Leave Module Response Time |
| **Type** | Non-Functional |
| **Priority** | P1 |
| **Status** | Approved |

**Requirements:**
1. Page load time: ≤ 3 seconds
2. Form submission: ≤ 2 seconds
3. Balance calculation: ≤ 1 second
4. Search/filter: ≤ 2 seconds

**Acceptance Criteria:**
- [ ] Page load under 3 seconds
- [ ] Submission response under 2 seconds
- [ ] Balance calculation instant

---

### 3.2 Security

#### REQ-LEAVE-NF-002: Data Security

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-NF-002 |
| **Title** | Leave Data Security |
| **Type** | Non-Functional |
| **Priority** | P1 |
| **Status** | Approved |

**Requirements:**
1. Users can only view their own leave data
2. Managers can only view team members' data
3. HR can view all leave data
4. All leave data encrypted at rest
5. Audit log for all leave actions
6. Session timeout after 30 minutes inactivity

**Acceptance Criteria:**
- [ ] Access control enforced
- [ ] Data encryption confirmed
- [ ] Audit log complete
- [ ] Session timeout works

---

### 3.3 Usability

#### REQ-LEAVE-NF-003: User Experience

| Field | Value |
|-------|-------|
| **Requirement ID** | REQ-LEAVE-NF-003 |
| **Title** | Leave Module Usability |
| **Type** | Non-Functional |
| **Priority** | P2 |
| **Status** | Approved |

**Requirements:**
1. Intuitive navigation
2. Clear form labels and validation messages
3. Mobile-responsive design
4. Keyboard navigation support
5. Loading indicators for async operations

**Acceptance Criteria:**
- [ ] Usability test passed
- [ ] Mobile responsive
- [ ] Accessibility compliant

---

## 4. Business Rules

### 4.1 Leave Balance Rules

| Rule ID | Rule | Description |
|---------|------|-------------|
| BR-LEAVE-001 | Annual Leave Balance | Full-time employees receive 18 days/year |
| BR-LEAVE-002 | Sick Leave Balance | Full-time employees receive 12 days/year |
| BR-LEAVE-003 | Carry-Over Limit | Max 5 days carry over to next year |
| BR-LEAVE-007 | Partial Day Leave | Partial day leave not allowed |

### 4.2 Approval Rules

| Rule ID | Rule | Description |
|---------|------|-------------|
| BR-LEAVE-004 | Single Approval | Leave ≤ 5 days: Manager approval only |
| BR-LEAVE-005 | Multi-Level Approval | Leave > 5 days: Manager + HR approval |

### 4.3 Cancellation Rules

| Rule ID | Rule | Description |
|---------|------|-------------|
| BR-LEAVE-006 | Late Cancellation | Cancel ≤ 24h before: 1 day penalty |
| BR-LEAVE-008 | Weekend Exclusion | Weekends/holidays excluded from calculation |

---

## 5. User Stories

### 5.1 Employee Stories

| Story ID | Story | Priority |
|----------|-------|----------|
| US-EMP-001 | As an employee, I want to submit a leave request so that I can take time off | P0 |
| US-EMP-002 | As an employee, I want to view my leave balance so that I can plan my time off | P0 |
| US-EMP-003 | As an employee, I want to cancel my leave request so that I can change my plans | P1 |
| US-EMP-004 | As an employee, I want to view my leave history so that I can track my requests | P1 |

### 5.2 Manager Stories

| Story ID | Story | Priority |
|----------|-------|----------|
| US-MGR-001 | As a manager, I want to approve/reject leave requests so that I can manage my team's schedule | P0 |
| US-MGR-002 | As a manager, I want to view team calendar so that I can plan coverage | P1 |
| US-MGR-003 | As a manager, I want to receive notifications so that I don't miss requests | P0 |

### 5.3 HR Stories

| Story ID | Story | Priority |
|----------|-------|----------|
| US-HR-001 | As HR, I want to approve long leave so that I can ensure policy compliance | P0 |
| US-HR-002 | As HR, I want to view leave reports so that I can analyze trends | P1 |
| US-HR-003 | As HR, I want to process encashment so that employees can convert unused days | P2 |

---

## 6. Data Model

### 6.1 Leave Request Entity

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| request_id | UUID | Yes | Unique identifier |
| employee_id | String | Yes | Reference to employee |
| leave_type | Enum | Yes | ANNUAL, SICK, PERSONAL, UNPAID |
| start_date | Date | Yes | Leave start date |
| end_date | Date | Yes | Leave end date |
| days | Number | Yes | Total days requested |
| reason | String | No | Request reason |
| status | Enum | Yes | PENDING, APPROVED, REJECTED, CANCELLED |
| manager_id | String | Yes | Approving manager |
| hr_approval | Boolean | No | HR approval flag |
| created_at | DateTime | Yes | Request timestamp |
| updated_at | DateTime | Yes | Last update timestamp |

### 6.2 Leave Balance Entity

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| balance_id | UUID | Yes | Unique identifier |
| employee_id | String | Yes | Reference to employee |
| leave_type | Enum | Yes | Leave type |
| allotted | Number | Yes | Total allotted days |
| used | Number | Yes | Days used |
| carry_over | Number | No | Carried over days |
| available | Number | Yes | Computed available |

---

## 7. Acceptance Criteria Summary

### 7.1 Critical (P0)

| Requirement | Acceptance Criteria | Test Count |
|-------------|-------------------|------------|
| REQ-LEAVE-001 | Submit leave request | 4 tests |
| REQ-LEAVE-002 | Validate leave balance | 1 test |
| REQ-LEAVE-003 | Manager notification | 2 tests |
| REQ-LEAVE-004 | Approve/Reject leave | 3 tests |
| REQ-LEAVE-005 | Update balance | 2 tests |
| REQ-LEAVE-010 | Carry-over | 1 test |

### 7.2 High (P1)

| Requirement | Acceptance Criteria | Test Count |
|-------------|-------------------|------------|
| REQ-LEAVE-006 | Cancel leave | 2 tests |
| REQ-LEAVE-007 | View history | 2 tests |

### 7.3 Medium (P2)

| Requirement | Acceptance Criteria | Test Count |
|-------------|-------------------|------------|
| REQ-LEAVE-009 | Encashment | 3 tests |

---

## 8. Dependencies

### 8.1 System Dependencies

| Dependency | Description | Status |
|------------|-------------|--------|
| Employee Management | Employee data | ✅ Available |
| Payroll Module | Salary data for encashment | ✅ Available |
| Notification Service | Email/SMS notifications | ✅ Available |
| Calendar Service | Holiday data | ⚠️ In Progress |

### 8.2 External Dependencies

| Dependency | Description | Status |
|------------|-------------|--------|
| SMTP Server | Email delivery | ✅ Configured |
| SMS Gateway | SMS notifications | ✅ Configured |
| Payroll System | Salary calculations | ⚠️ Integration pending |

---

## 9. Open Issues

| Issue | Priority | Owner | ETA | Blocking |
|-------|----------|-------|-----|----------|
| Calendar service integration | High | Dev Team | 2026-05-20 | No |
| Holiday data validation | Medium | HR Admin | 2026-05-15 | No |

---

## 10. Approval

| Role | Name | Date | Status |
|------|------|------|--------|
| Product Owner | Le Van C | 2026-05-11 | Approved |
| QA Lead | Tran Thi B | 2026-05-11 | Approved |
| Tech Lead | Pham Van D | 2026-05-12 | Approved |

---

## 11. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 0.1 | 2026-05-08 | Le Van C | Initial draft |
| 0.2 | 2026-05-09 | Le Van C | Added business rules |
| 0.3 | 2026-05-10 | Le Van C | Added acceptance criteria |
| 1.0 | 2026-05-11 | Le Van C | Final version |
