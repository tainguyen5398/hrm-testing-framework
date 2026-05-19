# Sample: Requirement Analysis - Leave Management Module

## Document Information

| Field | Value |
|-------|-------|
| Document ID | AR-LEAVE-20260511-001 |
| Version | 1.0 |
| Author | Nguyen Van A - Senior QA |
| Date | 2026-05-11 |
| Status | Reviewed |
| Project | HRM System |

---

## Executive Summary

Analysis document for **Leave Management Module** covering employee leave request, approval workflow, and balance tracking. This module is critical for HR operations and affects all employees in the organization.

**Analysis Scope:**
- Modules Covered: Leave Request, Leave Approval, Leave Balance, Leave Reports
- Requirements Count: 15
- Business Rules: 8
- Identified Risks: 5

**Key Findings:**
1. Leave balance calculation has complex rules for carry-over and encashment
2. Approval workflow varies by leave type and duration
3. Integration with Payroll module requires careful handling of deductions
4. Public holiday handling needs clarification from business

---

## 1. Scope Definition

### 1.1 In Scope

| # | Item | Priority | Owner |
|---|------|----------|-------|
| 1 | Leave request submission | P0 | Leave Team |
| 2 | Leave approval workflow | P0 | Leave Team |
| 3 | Leave balance tracking | P0 | Leave Team |
| 4 | Leave cancellation | P1 | Leave Team |
| 5 | Leave encashment | P2 | Finance Team |
| 6 | Leave reports | P1 | Reporting Team |

### 1.2 Out of Scope

| # | Item | Reason |
|---|------|--------|
| 1 | Leave quota configuration | Admin module |
| 2 | Public holiday management | Separate HR process |
| 3 | Leave policy setup | HR Admin module |
| 4 | Integration with external calendar | Future enhancement |
| 5 | Mobile app leave features | Separate project |

### 1.3 Dependencies

| Dependency | Impact | Owner | Status |
|------------|--------|-------|--------|
| Employee Management | Need employee data for leave | HR Dev Team | ✅ Confirmed |
| Payroll Module | Balance deduction | Payroll Dev | ✅ Confirmed |
| Notification Service | Email/SMS alerts | Infra Team | ✅ Confirmed |
| Approval Workflow Engine | Multi-level approval | Workflow Team | ⚠️ In Progress |

---

## 2. Requirements Analysis

### 2.1 Requirements Summary

| Req ID | Requirement | Type | Priority | Testable | Status |
|--------|-------------|------|----------|----------|--------|
| REQ-LEAVE-001 | User can submit leave request | Functional | P0 | Yes | Ready |
| REQ-LEAVE-002 | System validates leave balance | Functional | P0 | Yes | Ready |
| REQ-LEAVE-003 | Manager receives approval notification | Functional | P0 | Yes | Ready |
| REQ-LEAVE-004 | Manager can approve/reject leave | Functional | P0 | Yes | Ready |
| REQ-LEAVE-005 | System updates balance on approval | Functional | P0 | Yes | Ready |
| REQ-LEAVE-006 | Leave auto-cancels on conflict | Functional | P1 | Yes | Ready |
| REQ-LEAVE-007 | Employee views leave history | Functional | P1 | Yes | Ready |
| REQ-LEAVE-008 | Manager views team calendar | Functional | P1 | Yes | Pending Clarification |
| REQ-LEAVE-009 | Leave encashment process | Functional | P2 | Yes | Ready |
| REQ-LEAVE-010 | Leave balance carries over | Functional | P0 | Yes | Ready |

### 2.2 Requirements Quality Assessment

| Req ID | Quality | Issues | Action Required |
|--------|---------|--------|----------------|
| REQ-LEAVE-001 | Good | None | Proceed |
| REQ-LEAVE-002 | Good | None | Proceed |
| REQ-LEAVE-003 | Fair | Notification timing unclear | Clarify: immediate or batch? |
| REQ-LEAVE-008 | Poor | "Team calendar" vague | Rewrite required |

### 2.3 Detailed Requirement: REQ-LEAVE-001

**Requirement:** User can submit leave request

**Current Text:**
> "Employee should be able to submit a leave request through the system"

**Issues:**
- No mention of leave types
- No validation criteria specified
- No user roles mentioned

**Proposed Revision:**
> "Authenticated employee can submit a leave request specifying: leave type (Annual/Sick/Personal), start date, end date, and optional reason. System validates:
> 1. Dates are not in the past
> 2. Start date <= End date
> 3. Sufficient balance for leave type
> 4. No overlapping approved leaves"

---

## 3. Clarification Log

### 3.1 Critical Questions

| Q-ID | Requirement | Question | Asked | Answered | Status |
|------|-------------|----------|-------|----------|--------|
| CLAR-001 | REQ-LEAVE-003 | Should notification be immediate or batched (hourly)? | 2026-05-08 | 2026-05-09 | ✅ Resolved |
| CLAR-002 | REQ-LEAVE-004 | How many approval levels needed? | 2026-05-08 | 2026-05-10 | ✅ Resolved |
| CLAR-003 | REQ-LEAVE-008 | What exactly is "team calendar"? | 2026-05-09 | - | ⏳ Pending |
| CLAR-004 | REQ-LEAVE-010 | Carry-over calculation date? | 2026-05-09 | 2026-05-10 | ✅ Resolved |
| CLAR-005 | REQ-LEAVE-006 | Conflict definition for auto-cancel? | 2026-05-10 | - | ⏳ Pending |

### 3.2 Clarification Details

**CLAR-001: Notification Timing**

| Field | Value |
|-------|-------|
| Related Requirement | REQ-LEAVE-003 |
| Current Text | "Manager receives approval notification" |
| Ambiguity | Unclear if notification is immediate or scheduled |
| Question | Should managers receive immediate notification when employee submits leave, or batch notifications every hour? |
| Answer | **Immediate notification** via email. Additionally, dashboard badge updates in real-time. |
| Updated Requirement | "Manager receives immediate email notification and dashboard update when employee submits leave request" |
| Resolved By | Product Owner - Mr. Tran |
| Resolution Date | 2026-05-09 |

**CLAR-002: Approval Levels**

| Field | Value |
|-------|-------|
| Related Requirement | REQ-LEAVE-004 |
| Current Text | "Manager can approve/reject leave" |
| Ambiguity | Single or multiple approvers? |
| Question | Does all leave require single manager approval, or are there multi-level approvals? |
| Answer | **Single-level approval** for all leave <= 5 days. Leave > 5 days requires Manager + HR approval. |
| Resolved By | Product Owner - Mrs. Linh |
| Resolution Date | 2026-05-10 |

**CLAR-003: Team Calendar (PENDING)**

| Field | Value |
|-------|-------|
| Related Requirement | REQ-LEAVE-008 |
| Current Text | "Manager views team calendar" |
| Ambiguity | What data? What format? |
| Question | What exactly should the team calendar display? Monthly view? Daily? Which team members? |
| Status | ⏳ Awaiting response from Product Owner |
| Due Date | 2026-05-12 |

---

## 4. Business Rules Analysis

### 4.1 Business Rules Register

| Rule ID | Rule | Module | Category | Testable | Status |
|---------|------|--------|----------|----------|--------|
| BR-LEAVE-001 | Annual leave balance = 18 days/year | Balance | Calculation | Yes | Ready |
| BR-LEAVE-002 | Sick leave balance = 12 days/year | Balance | Calculation | Yes | Ready |
| BR-LEAVE-003 | Carry-over max 5 days to next year | Balance | Calculation | Yes | Ready |
| BR-LEAVE-004 | Leave <= 5 days: Manager approval | Approval | Workflow | Yes | Ready |
| BR-LEAVE-005 | Leave > 5 days: Manager + HR approval | Approval | Workflow | Yes | Ready |
| BR-LEAVE-006 | Cancel request <= 24h before start: Penalty | Cancellation | Validation | Yes | Ready |
| BR-LEAVE-007 | Partial day leave not allowed | Request | Validation | Yes | Ready |
| BR-LEAVE-008 | Weekend/holiday excluded from calculation | Calculation | Validation | Yes | Ready |

### 4.2 Business Rule Details

**BR-LEAVE-001: Annual Leave Balance**

| Field | Value |
|-------|-------|
| **Category** | Balance / Calculation |
| **Module** | Leave Balance |
| **Description** | Full-time employees receive 18 days of annual leave per calendar year |
| **Condition** | Applied to employees with status = ACTIVE and join_date <= Jan 1 of current year |
| **Action** | Credit 18 days to annual_leave_balance on Jan 1 |
| **Prorated Rule** | Employees joining mid-year: (18 / 12) × remaining months, minimum 1 day |
| **Formula** | `Annual Leave = 18 - (Days Taken) + (Carry Over, max 5)` |
| **Testable** | Yes |

**Test Scenarios:**
- TC-BR001-001: Full year employee → 18 days credited → Pass
- TC-BR001-002: Employee joined March → ~15 days (prorated) → Pass
- TC-BR001-003: Employee joined December → 1 day (minimum) → Pass
- TC-BR001-004: Part-time employee → 0 days (not eligible) → Pass

**BR-LEAVE-003: Carry-Over Rules**

| Field | Value |
|-------|-------|
| **Category** | Balance / Calculation |
| **Module** | Leave Balance |
| **Description** | Unused annual leave days can carry over to next year, maximum 5 days |
| **Condition** | Applied when calendar year ends and employee has unused annual leave |
| **Action** | Carry over MIN(unused_days, 5) to new year balance |
| **Encashment Option** | Remaining days (>5) can be encashed at year-end |
| **Expiry** | Carried-over days expire if not used by March 31 of new year |
| **Formula** | `Carry Over = MIN(Remaining Balance, 5)` |

**Test Scenarios:**
- TC-BR003-001: 3 unused days → Carry 3 days → Pass
- TC-BR003-002: 7 unused days → Carry 5 days → Pass
- TC-BR003-003: 0 unused days → Carry 0 days → Pass
- TC-BR003-004: 5 unused days → Carry 5 days → Pass
- TC-BR003-005: 10 unused days → 5 carry + 5 encashment → Pass

**BR-LEAVE-005: Multi-Level Approval**

| Field | Value |
|-------|-------|
| **Category** | Workflow / Approval |
| **Module** | Leave Approval |
| **Description** | Leave requests longer than 5 days require two-level approval |
| **Condition** | When leave_days > 5 AND leave_type = ANNUAL |
| **Action** | Route to Manager first, then to HR after Manager approval |
| **Rejection** | Either Manager or HR can reject; rejection is final |
| **Timeout** | If not approved within 3 business days, auto-escalate to backup approver |

**Approval Flow:**
```
Employee submits leave (7 days)
    ↓
Manager receives notification
    ↓
Manager approves OR rejects
    ↓
If Manager approves:
    → HR receives notification
    → HR approves OR rejects
    → If HR approves: Leave confirmed
    → If HR rejects: Leave cancelled, employee notified
If Manager rejects:
    → Leave cancelled, employee notified
```

**Test Scenarios:**
- TC-BR005-001: 3-day leave → Manager only → Pass
- TC-BR005-002: 6-day leave → Manager + HR → Pass
- TC-BR005-003: Manager rejects → No HR needed → Pass
- TC-BR005-004: HR rejects after Manager approves → Leave cancelled → Pass
- TC-BR005-005: 5-day leave (boundary) → Manager only → Pass

**BR-LEAVE-006: Late Cancellation Penalty**

| Field | Value |
|-------|-------|
| **Category** | Cancellation / Validation |
| **Module** | Leave Cancellation |
| **Description** | Cancelling leave within 24 hours of start date incurs penalty |
| **Condition** | When cancel_request_time < (leave_start_date - 24 hours) |
| **Penalty** | Deduct 1 day from annual leave balance |
| **Exception** | Medical emergency with documentation: No penalty |
| **Notification** | Employee informed of penalty at cancellation time |

**Test Scenarios:**
- TC-BR006-001: Cancel 48h before → No penalty → Pass
- TC-BR006-002: Cancel 24h before → Penalty applied → Pass
- TC-BR006-003: Cancel 12h before → Penalty applied → Pass
- TC-BR006-004: Cancel 48h before with medical cert → No penalty → Pass

---

## 5. Test Approach

### 5.1 Testing Strategy

| Aspect | Approach | Justification |
|--------|----------|----------------|
| Test Type | Functional + Integration | Leave has complex workflow + external integrations |
| Test Level | System + Acceptance | Cover both unit integration and business flow |
| Technique | EP + BVA + Decision Table | Input validation, calculations, workflow rules |
| Environment | QA (primary), Staging (UAT) | QA for execution, Staging for final validation |
| Data | Synthetic + Production-like | Realistic scenarios with controlled data |

### 5.2 Test Design Techniques

**Equivalence Partitioning (for leave days):**
```
Valid Partitions:
├── 1-5 days: Single approval
├── 6-14 days: Multi-level approval
└── > 14 days: Special review required

Invalid Partitions:
├── 0 days: Reject (no leave)
├── Negative days: Reject (invalid)
├── > 30 days: Reject (exceeds policy)
└── Non-numeric: Reject (validation error)
```

**Boundary Value Analysis:**
```
Leave Days Boundaries:
├── Min: 1 day (valid)
├── Min - 1: 0 days (invalid)
├── Max valid: 14 days (standard)
├── Max + 1: 15 days (special review)
└── Absolute max: 30 days (rejected)
```

**Decision Table (Leave Approval):**
```
| Leave Days | Leave Type   | Approvers       | Expected Result      |
|------------|--------------|-----------------|----------------------|
| <= 5       | Annual       | Manager         | Single approval      |
| <= 5       | Sick         | Manager         | Single approval      |
| > 5        | Annual       | Manager + HR    | Double approval      |
| > 5        | Sick         | Manager + HR    | Double approval      |
| Any        | Emergency    | Manager (auto)  | Auto-approve        |
```

### 5.3 Test Environment Requirements

| Requirement | Specification | Priority | Notes |
|-------------|---------------|----------|-------|
| Browser | Chrome 120, Firefox 120, Edge 120 | Required | Cross-browser |
| Environment | QA (https://qa-hrm.example.com) | Required | |
| Test Users | 10 employees, 3 managers, 2 HR | Required | Various statuses |
| Test Data | 1 year leave history | Required | For carry-over tests |
| API Tools | Postman collection ready | Recommended | For integration tests |

---

## 6. Traceability Matrix

### 6.1 Requirements to Test Conditions

| Req ID | Requirement | Test Conditions | Coverage |
|--------|-------------|-----------------|----------|
| REQ-LEAVE-001 | Submit leave request | TC-001 to TC-015 | 100% |
| REQ-LEAVE-002 | Validate balance | TC-016 to TC-025 | 100% |
| REQ-LEAVE-003 | Notification | TC-026 to TC-030 | 100% |
| REQ-LEAVE-004 | Approve/Reject | TC-031 to TC-045 | 100% |
| REQ-LEAVE-005 | Update balance | TC-046 to TC-050 | 100% |
| REQ-LEAVE-006 | Auto-cancel conflict | TC-051 to TC-055 | 100% |
| REQ-LEAVE-007 | View history | TC-056 to TC-060 | 100% |
| REQ-LEAVE-008 | Team calendar | TC-061 to TC-065 | 0% (Pending Clarification) |
| REQ-LEAVE-009 | Encashment | TC-066 to TC-070 | 100% |
| REQ-LEAVE-010 | Carry-over | TC-071 to TC-080 | 100% |

### 6.2 Coverage Summary

| Category | Count | Coverage |
|----------|-------|----------|
| Total Requirements | 10 | 100% |
| Fully Covered | 9 | 90% |
| Partially Covered | 0 | 0% |
| Not Covered | 1 | 10% (Pending Clarification) |

---

## 7. Risk Assessment

### 7.1 Risk Summary

| Risk ID | Risk Description | Category | Probability | Impact | Score | Level |
|---------|-----------------|----------|-------------|--------|-------|-------|
| RISK-001 | Complex calculation causing balance errors | Technical | High | High | 12 | 🔴 Critical |
| RISK-002 | Approval workflow integration delays | Technical | Medium | Medium | 6 | 🟠 High |
| RISK-003 | Test data setup complexity for carry-over | Process | Medium | Low | 3 | 🟢 Low |
| RISK-004 | Calendar date handling with timezone | Technical | Medium | Medium | 6 | 🟠 High |
| RISK-005 | Notification service reliability | Infrastructure | Low | Medium | 4 | 🟡 Medium |

### 7.2 Risk Details

**RISK-001: Complex Balance Calculation**

| Field | Value |
|-------|-------|
| **Description** | Leave balance calculation involves multiple factors (prorated days, carry-over, encashment, deductions) which increases risk of calculation errors |
| **Category** | Technical |
| **Probability** | High (4/5) - Many edge cases |
| **Impact** | High (4/5) - Direct financial impact |
| **Risk Score** | 12 - 🔴 CRITICAL |
| **Mitigation Strategy** | 1. Create comprehensive decision table<br>2. Implement boundary tests for all scenarios<br>3. Use test data with known expected values<br>4. Add database verification in tests |
| **Contingency** | If critical bugs found: delay release by 2 sprints to fix |
| **Owner** | QA Lead - Nguyen Van A |
| **Status** | Active - Mitigation in progress |

**Mitigation Test Cases:**
- TC-BR001-001 to TC-BR003-005 (all balance scenarios)
- Additional boundary tests for leap years, month boundaries
- Database state verification after each operation

**RISK-004: Calendar Date Handling**

| Field | Value |
|-------|-------|
| **Description** | Leave calculation must handle weekends, holidays, timezone differences which can cause off-by-one errors |
| **Category** | Technical |
| **Probability** | Medium (3/5) |
| **Impact** | Medium (3/5) - Incorrect leave days |
| **Risk Score** | 9 - 🟠 HIGH |
| **Mitigation Strategy** | 1. Test with various date ranges (including holidays)<br>2. Verify against known calendar<br>3. Test timezone edge cases |
| **Contingency** | Add manual verification step for calendar calculations |
| **Owner** | QA Lead - Nguyen Van A |
| **Status** | Active |

---

## 8. Resource Estimation

### 8.1 Effort Estimate

| Activity | Estimate (Hours) | Confidence | Notes |
|----------|-----------------|------------|-------|
| Test Case Design | 24 | High | Includes EP, BVA, Decision Table |
| Test Data Preparation | 16 | Medium | Complex data requirements |
| Test Script Development | 40 | High | Automation scripts |
| Test Execution | 32 | High | Manual + automated |
| Defect Reporting & Tracking | 8 | High | |
| Review & Sign-off | 8 | High | |
| **Total** | **128** | | |

### 8.2 Timeline

| Phase | Start | End | Duration | Milestone |
|-------|-------|-----|---------|-----------|
| Analysis | 2026-05-11 | 2026-05-15 | 5 days | ✅ Analysis Complete |
| Design | 2026-05-16 | 2026-05-23 | 7 days | Design Complete |
| Data Prep | 2026-05-20 | 2026-05-27 | 7 days | Data Ready |
| Automation | 2026-05-23 | 2026-06-06 | 12 days | Scripts Ready |
| Execution | 2026-06-07 | 2026-06-18 | 10 days | Execution Complete |
| UAT Support | 2026-06-19 | 2026-06-25 | 5 days | UAT Complete |

### 8.3 Resource Requirements

| Resource | Role | Allocation |
|----------|------|------------|
| QA Lead | Test Lead | 40% |
| QA Engineer 1 | Automation | 100% |
| QA Engineer 2 | Manual Testing | 60% |
| Developer | Test Support | 20% |

---

## 9. Open Issues & Assumptions

### 9.1 Open Issues

| Issue | Priority | Owner | ETA | Blocking |
|-------|----------|-------|-----|----------|
| CLAR-003: Team calendar definition | High | Product Owner | 2026-05-12 | Yes - TC-061 to TC-065 |
| CLAR-005: Conflict definition | Medium | Product Owner | 2026-05-13 | No - TC-051 to TC-055 |

### 9.2 Assumptions

| Assumption | Impact if Wrong | Owner |
|-----------|-----------------|-------|
| Notification service will be available | Tests may show false failures | DevOps |
| Public holiday list is available in system | Balance calculation tests may fail | HR Admin |
| Test environment mirrors production data structure | Tests may not reflect reality | Dev Team |
| Users have English language preference | UI text may differ | UX Team |

---

## 10. Approval & Sign-off

| Role | Name | Signature | Date | Decision |
|------|------|-----------|------|----------|
| QA Engineer | Nguyen Van A | ✅ | 2026-05-11 | Submitted |
| QA Lead | Tran Thi B | ✅ | 2026-05-11 | Approved |
| Product Owner | Le Van C | ✅ | 2026-05-12 | Approved |
| Tech Lead | Pham Van D | ⏳ | - | Pending |

---

## 11. Appendices

### 11.1 Reference Documents

| Document | Link | Version |
|----------|------|---------|
| Leave Management Spec | [SharePoint Link] | v2.3 |
| Approval Workflow Design | [Confluence Link] | v1.2 |
| API Documentation | [Swagger Link] | Latest |
| HRM Database Schema | [Confluence Link] | v1.5 |

### 11.2 Glossary

| Term | Definition |
|------|------------|
| Carry-over | Unused leave days transferred to next year |
| Encashment | Converting unused leave to cash payment |
| Prorated | Calculated proportionally based on time period |
| EL | Employee Level (approval hierarchy) |
| Auto-cancel | System-initiated cancellation due to conflicts |

---

## Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 0.1 | 2026-05-08 | Nguyen Van A | Initial draft |
| 0.2 | 2026-05-09 | Nguyen Van A | Added clarifications from PO |
| 0.3 | 2026-05-10 | Nguyen Van A | Added risk analysis |
| 1.0 | 2026-05-11 | Nguyen Van A | Final version for approval |
