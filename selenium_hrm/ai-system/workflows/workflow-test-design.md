# Workflow: Test Case Design

## 1. Overview

### 1.1 Purpose
Workflow thiết kế test case (Test Case Design) là bước thứ hai trong QA process,承接 từ Requirement Analysis và chuẩn bị đầu vào cho Automation. Workflow này hướng dẫn QA Engineer thiết kế, tài liệu hóa, và review test cases để đảm bảo:

- **Complete Coverage**: Tất cả requirements đều có test cases
- **Optimal Test Count**: Số lượng test cases tối ưu, tránh overlap
- **Testability**: Test cases có thể execute được
- **Traceability**: Mỗi test case trace được về requirement
- **Maintainability**: Test cases dễ maintain khi requirements thay đổi

### 1.2 Objectives

| # | Objective | Success Criteria |
|---|-----------|-----------------|
| 1 | Complete Coverage | 100% requirements có test cases |
| 2 | Optimal Efficiency | Test cases tối thiểu, coverage tối đa |
| 3 | Clear Documentation | Step-by-step rõ ràng, unambiguous |
| 4 | Executable | Có thể execute manual hoặc automate |
| 5 | Review Approved | Đã qua peer review và approved |

### 1.3 Workflow Position

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                     AI AUTOMATION PIPELINE - WORKFLOW 2                      │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   INPUT:                                                                    │
│   ├── analysis-result.md (từ Workflow 1)                                    │
│   ├── Requirements Traceability Matrix                                      │
│   ├── Business Rules Register                                               │
│   └── Risk Register                                                         │
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │                   TEST CASE DESIGN                                │     │
│   │  ┌─────────────────────────────────────────────────────────┐   │     │
│   │  │ 1. Design Test Strategy                                 │   │     │
│   │  │ 2. Identify Test Conditions                             │   │     │
│   │  │ 3. Apply Test Design Techniques                         │   │     │
│   │  │ 4. Write Test Cases                                     │   │     │
│   │  │ 5. Prepare Test Data                                    │   │     │
│   │  │ 6. Review & Refine                                      │   │     │
│   │  │ 7. Finalize & Approve                                  │   │     │
│   │  └─────────────────────────────────────────────────────────┘   │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│                                                                             │
│   OUTPUT:                                                                   │
│   ├── testcases.md (Test Case Suite)                                       │
│   ├── Test Data Files (Excel/JSON)                                         │
│   └── test-coverage-report.md                                              │
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │              ▶ NEXT WORKFLOW: Automation Planning                  │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 2. Inputs & Prerequisites

### 2.1 Required Inputs

| # | Input | Format | Source | Priority |
|---|-------|--------|--------|----------|
| 1 | Analysis Result Document | Markdown | Workflow 1: Requirement Analysis | Required |
| 2 | Requirements Traceability Matrix | Markdown/Excel | Workflow 1: Requirement Analysis | Required |
| 3 | Business Rules Register | Markdown | Workflow 1: Requirement Analysis | Required |
| 4 | Risk Register | Markdown | Workflow 1: Requirement Analysis | Required |
| 5 | Clarification Log | Markdown | Workflow 1: Requirement Analysis | Required |
| 6 | UI Mockups/Prototypes | Figma/PDF | Product Team | Recommended |
| 7 | API Specifications | Swagger/OpenAPI | Development Team | Recommended |

### 2.2 Prerequisites Checklist

```
PRE-WORKFLOW CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

□ Analysis Result Document approved by QA Lead
□ All clarification questions resolved
□ Business Rules Register complete
□ Risk Register reviewed and approved
□ Access to design tools (Figma/Mockups) if needed
□ Access to test environment (for verification)
□ Test data sources identified
□ Reviewers assigned

```

---

## 3. Test Design Strategy

### 3.1 Test Strategy Framework

```
TEST DESIGN STRATEGY OVERVIEW
═══════════════════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────────────────────┐
│                        TEST STRATEGY COMPONENTS                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   1. TEST TYPE SELECTION                                                    │
│   ├── Functional Testing                                                    │
│   ├── Integration Testing                                                  │
│   ├── E2E Testing                                                          │
│   └── Non-Functional (Performance, Security, Usability)                     │
│                                                                             │
│   2. TEST LEVEL DETERMINATION                                              │
│   ├── Unit Testing (Developer)                                             │
│   ├── Integration Testing (QA)                                             │
│   ├── System Testing (QA)                                                  │
│   └── Acceptance Testing (UAT)                                             │
│                                                                             │
│   3. TEST TECHNIQUE APPLICATION                                             │
│   ├── Black-box: Equivalence Partitioning, Boundary Value, Decision Table    │
│   ├── White-box: Statement, Branch, Path Coverage                          │
│   └── Experience-based: Error Guessing, Exploratory                        │
│                                                                             │
│   4. TEST PRIORITIZATION                                                   │
│   ├── P0: Critical paths, core functionality                                │
│   ├── P1: High business impact                                             │
│   ├── P2: Standard features                                                │
│   └── P3: Edge cases, nice-to-have                                         │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 3.2 Test Approach Selection Matrix

| Scenario | Recommended Approach | Test Count | Coverage Focus |
|----------|---------------------|------------|----------------|
| **Login/Auth** | All combinations + security | High | Security & boundary |
| **Form Validation** | Equivalence + Boundary | Medium | All validation rules |
| **Calculation/Formula** | Orthogonal Arrays + Boundary | High | All formula paths |
| **Workflow/Approval** | State Transition + Path | Medium | All transitions |
| **Data Search/Filter** | Combinations + Null | Medium | All filter combos |
| **Report Generation** | Data combinations | High | All sections |

### 3.3 Coverage Model

```
COVERAGE PYRAMID FOR TEST DESIGN
═══════════════════════════════════════════════════════════════════════════════

                              ┌───────────────┐
                              │   E2E Tests  │  ← 10% - Critical user journeys
                              │   (P0 only)  │
                             ┌┴───────────────┴┐
                             │  Integration    │  ← 30% - API + Module integration
                             │    Tests        │
                            ┌┴─────────────────┴┐
                            │    System Tests   │  ← 50% - Feature-level coverage
                            │                   │
                           ┌┴───────────────────┴┐
                           │     Unit Tests      │  ← Developer responsibility
                           │    (Not in scope)   │
                           └─────────────────────┘

FOR THIS WORKFLOW (QA System Tests):
├── Primary Coverage: 50% Feature-level (P0 + P1 + P2)
├── Secondary Coverage: 30% Integration
└── Validation: 10% E2E (critical paths only)

```

---

## 4. Test Design Techniques

### 4.1 ISTQB Standard Techniques

#### 4.1.1 Equivalence Partitioning (EP)

```
EQUIVALENCE PARTITIONING - EXAMPLE: Password Field
═══════════════════════════════════════════════════════════════════════════════

Requirement: Password must be 8-20 characters

┌─────────────────────────────────────────────────────────────────────────────┐
│                                                                             │
│   VALID PARTITIONS                    INVALID PARTITIONS                     │
│   ┌─────────────────────┐            ┌─────────────────────┐               │
│   │ 8-20 characters     │            │ < 8 characters      │               │
│   │ (valid password)    │            │ (too short)        │               │
│   │                     │            │                     │               │
│   │ "Password123!"      │            │ "Abc123!"          │               │
│   │ "SecurePass@2026"   │            │ "Admin"            │               │
│   └─────────────────────┘            └─────────────────────┘               │
│                                         │                                   │
│                                         │                                   │
│                                         ▼                                   │
│                                  ┌─────────────────────┐                   │
│                                  │ > 20 characters     │                   │
│                                  │ (too long)         │                   │
│                                  │                     │                   │
│                                  │ "VeryLongPassword   │                   │
│                                  │  ThatExceeds20     │                   │
│                                  └─────────────────────┘                   │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘

Test Cases from EP:
├── TC-001: Password with exactly 8 characters → Pass
├── TC-002: Password with exactly 20 characters → Pass
├── TC-003: Password with 10 characters (mid-range) → Pass
├── TC-004: Password with 7 characters → Fail (too short)
└── TC-005: Password with 21 characters → Fail (too long)

```

#### 4.1.2 Boundary Value Analysis (BVA)

```
BOUNDARY VALUE ANALYSIS - EXAMPLE: Leave Days Request
═══════════════════════════════════════════════════════════════════════════════

Requirement: Leave request allows 1-14 consecutive days

BOUNDARY VALUES:
├── Min boundary: 1 day
├── Max boundary: 14 days

TEST VALUES AT BOUNDARIES:
                                  
    Invalid      Valid         Valid      Invalid
    ┌─────┐    ┌─────┐      ┌─────┐    ┌─────┐
    │     │    │     │      │     │    │     │
    ▼     ▼    ▼     ▼      ▼     ▼    ▼     ▼
    0     1    2     13     14     15    30
    ▲     ▲                ▲     ▲
    │     │                │     │
    └─────┴────────────────┴─────┘
    Min-1  Min               Max   Max+1

Test Cases from BVA:
├── TC-001: Request 0 days → Fail (validation error)
├── TC-002: Request 1 day (min valid) → Pass
├── TC-003: Request 2 days → Pass
├── TC-004: Request 13 days → Pass
├── TC-005: Request 14 days (max valid) → Pass
├── TC-006: Request 15 days → Fail (exceeds max)
└── TC-007: Request 30 days → Fail (far exceeds max)

```

#### 4.1.3 Decision Table Testing

```
DECISION TABLE - EXAMPLE: Leave Approval Logic
═══════════════════════════════════════════════════════════════════════════════

RULES:
├── Rule 1: Days ≤ 5 AND Manager = Approver → Auto-Approve
├── Rule 2: Days ≤ 5 AND Manager ≠ Approver → Manager Approval
├── Rule 3: Days > 5 AND VP = Approver → VP Approval
├── Rule 4: Days > 5 AND VP ≠ Approver → Reject (escalation needed)

DECISION TABLE:

┌────────────────────────────────────────────────────────────────────────┐
│ Conditions                        │         Rules                         │
│──────────────────────────────────┼───────────────────────────────────────│
│ Days ≤ 5                         │   T    │   T   │   F   │    F       │
│ Manager = Direct Approver         │   T    │   F   │   T   │    F       │
│──────────────────────────────────┼─────────┼───────┼───────┼────────────│
│ Actions:                         │         │       │       │            │
│ Auto-Approve                     │   X    │       │       │            │
│ Route to Manager                 │        │   X   │       │            │
│ Route to VP                      │        │       │   X   │            │
│ Reject                           │        │       │       │    X       │
└────────────────────────────────────────────────────────────────────────┘

Test Cases from Decision Table:
├── TC-001: 3 days, direct manager → Auto-Approved
├── TC-002: 3 days, different manager → Sent to Manager
├── TC-003: 10 days, VP is approver → Sent to VP
└── TC-004: 10 days, VP not approver → Rejected

```

#### 4.1.4 State Transition Testing

```
STATE TRANSITION - EXAMPLE: Employee Status
═══════════════════════════════════════════════════════════════════════════════

STATES:
├── NEW → PROBATION → ACTIVE → ON_LEAVE → ACTIVE
├── ACTIVE → INACTIVE
├── ACTIVE → TERMINATED

STATE DIAGRAM:

        ┌─────────┐
        │   NEW   │
        └────┬────┘
             │ Start Date Reached
             ▼
    ┌────────────────┐
───▶│   PROBATION   │
    └───────┬────────┘
            │ 6 months completed
            ▼
    ┌────────────────┐                      ┌────────────┐
    │    ACTIVE      │◀─────────────────────│  ON_LEAVE  │
    └───────┬────────┘                      └────────────┘
            │                                     ▲
    ┌───────┴───────┐                           │
    │               │                           │ Leave Approved
    ▼               ▼                           │
┌─────────┐   ┌─────────────┐           ┌──────────────┐
│INACTIVE │   │ TERMINATED  │           │  LEAVE       │
└─────────┘   └─────────────┘           │  REQUEST     │
                                        └──────┬───────┘
                                               │ Leave Period
                                               ▼

TRANSITION TEST CASES:
├── TC-001: NEW → PROBATION (valid transition)
├── TC-002: PROBATION → ACTIVE (6 months completed)
├── TC-003: ACTIVE → ON_LEAVE (leave approved)
├── TC-004: ON_LEAVE → ACTIVE (return from leave)
├── TC-005: ACTIVE → INACTIVE (manual deactivation)
├── TC-006: ACTIVE → TERMINATED (termination action)
└── TC-007: NEW → ACTIVE (invalid - must go through PROBATION)

INVALID TRANSITIONS (to verify system rejects):
├── TC-008: ON_LEAVE → TERMINATED (invalid)
├── TC-009: INACTIVE → ACTIVE (must go through HR approval)
└── TC-010: TERMINATED → any state (closed)

```

#### 4.1.5 Pairwise Testing

```
PAIRWISE TESTING - EXAMPLE: Employee Search Filters
═══════════════════════════════════════════════════════════════════════════════

PARAMETERS:
├── Department: [Engineering, Sales, HR, Finance] = 4 values
├── Status: [Active, Inactive, Probation] = 3 values
├── Role: [Admin, Manager, Employee] = 3 values
├── Location: [HCM, HN, DN] = 3 values

TOTAL COMBINATIONS: 4 × 3 × 3 × 3 = 108 combinations

WITH PAIRWISE TESTING: ~12 test cases (covers all pairs)

GENERATED TEST CASES:

┌────────────────────────────────────────────────────────────────────────┐
│ Test Case │ Department  │ Status    │ Role    │ Location │ Reason      │
├───────────┼─────────────┼───────────┼─────────┼─────────┼─────────────│
│ TC-001    │ Engineering │ Active    │ Admin   │ HCM     │ Core combo  │
│ TC-002    │ Sales       │ Inactive  │ Manager │ HN      │ Mixed       │
│ TC-003    │ HR          │ Probation │ Employee│ DN      │ All diff    │
│ TC-004    │ Finance     │ Active    │ Manager │ HCM     │ Mixed       │
│ TC-005    │ Engineering │ Inactive  │ Employee│ HN      │ Mixed       │
│ TC-006    │ Sales       │ Probation │ Admin   │ DN      │ Mixed       │
│ TC-007    │ HR          │ Active    │ Employee│ HCM     │ Mixed       │
│ TC-008    │ Finance     │ Inactive  │ Admin   │ HN      │ Mixed       │
│ TC-009    │ Engineering │ Probation │ Manager │ DN      │ Mixed       │
│ TC-010    │ Sales       │ Active    │ Employee│ HCM     │ Mixed       │
│ TC-011    │ HR          │ Inactive  │ Manager │ HN      │ Mixed       │
│ TC-012    │ Finance     │ Probation │ Employee│ DN      │ Final combo │
└───────────┴─────────────┴───────────┴─────────┴─────────┴─────────────│
```

### 4.2 Technique Selection Guide

| Test Scenario | Primary Technique | Supporting Techniques |
|---------------|-------------------|---------------------|
| **Input Fields** | EP + BVA | Error Guessing |
| **Business Rules** | Decision Table | State Transition |
| **Workflows** | State Transition | Path Coverage |
| **Data Combinations** | Pairwise | Orthogonal Arrays |
| **UI Interactions** | Use Case | Error Guessing |
| **Calculation Logic** | BVA | Error Guessing |
| **Validation Rules** | EP | BVA |
| **Permission/Roles** | Decision Table | Pairwise |

---

## 5. Test Case Structure

### 5.1 Standard Test Case Template (ISTQB Compliant)

```markdown
## Test Case Template

| Field | Description | Example |
|-------|-------------|---------|
| **Test Case ID** | Unique identifier | TC-LOGIN-001 |
| **Test Suite** | Grouping | Login |
| **Module** | System module | Authentication |
| **Feature** | Feature being tested | User Login |
| **Title** | Short description | Login with valid credentials |
| **Objective** | What this test validates | Verify user can login with valid credentials |
| **Priority** | P0/P1/P2/P3 | P0 |
| **Severity** | Critical/Major/Minor | Critical |
| **Type** | Positive/Negative/Boundary | Positive |
| **Pre-conditions** | Setup required | User account exists in system |
| **Test Steps** | Numbered actions | 1. Navigate to login page... |
| **Test Data** | Input data | Username: admin, Password: Admin@123 |
| **Expected Result** | Expected outcome | User redirected to dashboard |
| **Actual Result** | (Fill during execution) | |
| **Status** | Pass/Fail/Blocked | |
| **Automated** | Yes/No | Yes |
| **Automation Effort** | Low/Medium/High | Low |
| **Notes** | Additional info | |
| **Traceability** | Related requirements | REQ-LOGIN-001 |
```

### 5.2 Detailed Test Case Example

```markdown
## TC-LOGIN-001: Login with Valid Credentials

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LOGIN-001 |
| **Test Suite** | Authentication |
| **Module** | Login |
| **Feature** | User Authentication |
| **Title** | Login with valid username and password |
| **Objective** | Verify user successfully logs in with valid credentials |
| **Priority** | P0 |
| **Severity** | Critical |
| **Type** | Positive |
| **Pre-conditions** | 1. User account exists in system<br>2. Account is active<br>3. Login page is accessible |
| **Test Steps** | |
| 1 | Navigate to application URL |
| 2 | Enter valid username in username field |
| 3 | Enter valid password in password field |
| 4 | Click Login button |
| **Test Data** | |
| Username | admin |
| Password | Admin@123 |
| **Expected Result** | |
| 1 | Login page disappears |
| 2 | Dashboard page loads |
| 3 | User greeting "Welcome, [Username]" appears in header |
| 4 | Session cookie is set |
| 5 | URL changes to /dashboard |
| **Actual Result** | [To be filled during execution] |
| **Status** | [To be filled] |
| **Automated** | Yes |
| **Automation Effort** | Low |
| **Notes** | Critical path test - must pass for any further testing |
| **Traceability** | REQ-AUTH-001, REQ-AUTH-003 |
| **Created By** | QA Engineer |
| **Created Date** | 2026-05-11 |
| **Reviewed By** | |
| **Reviewed Date** | |

---

## TC-LOGIN-002: Login with Invalid Password

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LOGIN-002 |
| **Test Suite** | Authentication |
| **Module** | Login |
| **Feature** | User Authentication |
| **Title** | Login with valid username but invalid password |
| **Objective** | Verify system rejects login with incorrect password |
| **Priority** | P0 |
| **Severity** | Critical |
| **Type** | Negative |
| **Pre-conditions** | User account exists with valid credentials |
| **Test Steps** | |
| 1 | Navigate to login page |
| 2 | Enter valid username |
| 3 | Enter incorrect password |
| 4 | Click Login button |
| **Test Data** | |
| Username | admin |
| Password | WrongPassword123! |
| **Expected Result** | |
| 1 | Error message displayed: "Invalid username or password" |
| 2 | User remains on login page |
| 3 | URL remains /login |
| 4 | No session cookie created |
| 5 | Attempt counter incremented |
| **Actual Result** | [To be filled] |
| **Status** | [To be filled] |
| **Automated** | Yes |
| **Automation Effort** | Low |

---

## TC-LOGIN-003: Login - Account Locked After 5 Failed Attempts

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-LOGIN-003 |
| **Test Suite** | Authentication |
| **Module** | Login |
| **Feature** | Account Security |
| **Title** | Account locked after 5 consecutive failed login attempts |
| **Objective** | Verify account is locked after maximum failed attempts |
| **Priority** | P1 |
| **Severity** | Major |
| **Type** | Negative |
| **Pre-conditions** | User account exists |
| **Test Steps** | |
| 1 | Attempt login with wrong password |
| 2 | Repeat step 1 four more times (total 5 failures) |
| **Test Data** | |
| Username | testuser |
| Password | WrongPassword (for all attempts) |
| **Expected Result** | |
| 1 | After 5th failed attempt: Error message "Account locked. Please try again in 30 minutes." |
| 2 | 6th attempt rejected immediately without checking credentials |
| 3 | Account status changed to LOCKED in database |
| 4 | Admin notification sent (if configured) |
| **Expected DB State** | failed_attempts = 5, account_status = 'LOCKED', lockout_until = current_time + 30min |
| **Actual Result** | [To be filled] |
| **Status** | [To be filled] |
| **Automated** | Yes |
| **Automation Effort** | Medium |
| **Notes** | Requires test data reset between runs |
```

### 5.3 Test Case ID Naming Convention

```
TEST CASE ID FORMAT
═══════════════════════════════════════════════════════════════════════════════

Format: TC-{MODULE}-{SEQUENCE}

Examples:
├── TC-LOGIN-001, TC-LOGIN-002, ...
├── TC-EMP-001, TC-EMP-002, ...
├── TC-LEAVE-001, TC-LEAVE-002, ...
├── TC-PAYROLL-001, TC-PAYROLL-002, ...

For Sub-modules:
Format: TC-{MODULE}-{SUBMODULE}-{SEQUENCE}

Examples:
├── TC-EMP-CREATE-001
├── TC-EMP-EDIT-001
├── TC-EMP-DELETE-001
├── TC-EMP-SEARCH-001
├── TC-LEAVE-REQUEST-001
├── TC-LEAVE-APPROVE-001

```

---

## 6. Test Design Process

### 6.1 Step 1: Design Test Strategy

```
STEP 1: DESIGN TEST STRATEGY
═══════════════════════════════════════════════════════════════════════════════

Activities:
├── 1.1 Review analysis outputs
│   ├── Read Analysis Result Document
│   ├── Review Risk Register
│   └── Understand Business Rules
│
├── 1.2 Determine test approach
│   ├── Select test types
│   ├── Determine test levels
│   └── Identify test techniques
│
├── 1.3 Create test strategy document
│   ├── Define coverage goals
│   ├── Set priorities
│   └── Assign resources
│
└── 1.4 Get approval
    └── Review with QA Lead

Deliverable: Test Strategy Document
Time Estimate: 2-4 hours

```

### 6.2 Step 2: Identify Test Conditions

```
STEP 2: IDENTIFY TEST CONDITIONS
═══════════════════════════════════════════════════════════════════════════════

Test Condition = A specific aspect of behavior to be tested

IDENTIFICATION TECHNIQUES:
├── 1. From Requirements
│   └── Each requirement → at least one test condition
│
├── 2. From Business Rules
│   └── Each rule → test condition for pass and fail
│
├── 3. From User Flows
│   └── Each path through the system → test condition
│
├── 4. From Risk Assessment
│   └── Each risk → test condition to verify mitigation
│
└── 5. From Edge Cases
    └── Boundary conditions → test conditions

TEST CONDITION REGISTER TEMPLATE:

┌────────────────────────────────────────────────────────────────────────────┐
│ Test Condition Register                                                     │
├────────────────────────────────────────────────────────────────────────────┤
│ Cond ID │ Condition Description │ Related Req │ Priority │ Technique     │
├─────────┼───────────────────────┼─────────────┼──────────┼───────────────│
│ TC-001  │ Valid login           │ REQ-001     │ P0       │ N/A (basic)   │
│ TC-002  │ Invalid password     │ REQ-001     │ P0       │ N/A (basic)   │
│ TC-003  │ Boundary: 8 char pass │ REQ-002     │ P1       │ BVA           │
│ TC-004  │ Boundary: 20 char pass│ REQ-002     │ P1       │ BVA           │
│ TC-005  │ 5 failed attempts    │ REQ-003     │ P1       │ N/A (critical)│
└─────────┴───────────────────────┴─────────────┴──────────┴───────────────┘

```

### 6.3 Step 3: Apply Test Design Techniques

```
STEP 3: APPLY TEST DESIGN TECHNIQUES
═══════════════════════════════════════════════════════════════════════════════

For Each Test Condition:

1. IDENTIFY INPUTS
   ├── Valid inputs
   ├── Invalid inputs
   ├── Boundary values
   └── Missing inputs

2. SELECT TECHNIQUE
   ├── Equivalence Partitioning (EP)
   ├── Boundary Value Analysis (BVA)
   ├── Decision Table
   ├── State Transition
   ├── Pairwise Testing
   └── Error Guessing

3. DERIVE TEST VALUES
   ├── Select representatives from partitions
   ├── Select boundary values
   ├── Generate combinations
   └── Document decisions

4. DOCUMENT IN TEST CASE

EXAMPLE: Leave Balance Check

Analysis:
├── Valid partitions: 0 < balance ≤ entitled, balance > 0
├── Invalid partitions: balance ≤ 0
└── Boundary: exactly 0, exactly balance

Techniques Applied:
├── EP: Entitled (1-18), Zero (0), Negative (-1)
└── BVA: 0, 1, 5, 18, 19

Test Cases Generated:
├── TC-BAL-001: Request 5 days with 18 balance → Success
├── TC-BAL-002: Request 18 days with 18 balance → Success
├── TC-BAL-003: Request 19 days with 18 balance → Fail
├── TC-BAL-004: Request 0 days → Fail
└── TC-BAL-005: Request -5 days → Fail (security check)

```

### 6.4 Step 4: Write Test Cases

```
STEP 4: WRITE TEST CASES
═══════════════════════════════════════════════════════════════════════════════

WRITING GUIDELINES:

✅ DO:
├── Use clear, simple language
├── One action per step
├── Include expected results for each step
├── State specific data values
├── Include precondition setup
├── Reference requirement IDs
└── Keep test cases atomic (independent)

❌ DON'T:
├── Use vague descriptions
├── Combine multiple actions
├── Skip expected results
├── Use placeholder data (e.g., "valid user")
├── Assume implicit actions
├── Create dependent test cases
└── Skip boundary conditions

TEST CASE TEMPLATE SELECTION:

┌────────────────────────────────────────────────────────────────────────────┐
│ Template Selection Guide                                                   │
├────────────────────────────────────────────────────────────────────────────┤
│ Simple UI/Form          │ Use Standard Template (Section 5.2)              │
│ Complex Workflow        │ Use Detailed Template with sub-steps            │
│ Calculation Logic      │ Include formula and expected results             │
│ API Testing             │ Include request/response examples               │
│ Integration             │ Include external system states                  │
│ Performance             │ Include metrics and thresholds                   │
└────────────────────────────────────────────────────────────────────────────┘

```

### 6.5 Step 5: Prepare Test Data

```
STEP 5: PREPARE TEST DATA
═══════════════════════════════════════════════════════════════════════════════

TEST DATA CATEGORIES:

┌────────────────────────────────────────────────────────────────────────────┐
│ Static Test Data (Constants)                                              │
├────────────────────────────────────────────────────────────────────────────┤
│ • Valid usernames/passwords                                               │
│ • Standard date formats                                                   │
│ • Expected field lengths                                                 │
│ • Valid file formats                                                      │
└────────────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────────────────────────────────────────┐
│ Dynamic Test Data (Generated)                                             │
├────────────────────────────────────────────────────────────────────────────┤
│ • Unique email addresses (timestamp-based)                                │
│ • Random phone numbers                                                    │
│ • Test employee IDs                                                       │
│ • Session tokens                                                          │
└────────────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────────────────────────────────────────┐
│ Boundary Test Data                                                        │
├────────────────────────────────────────────────────────────────────────────┤
│ • Minimum length passwords (8 chars)                                        │
│ • Maximum length passwords (20 chars)                                      │
│ • Empty strings                                                           │
│ • Special characters                                                       │
│ • SQL injection patterns                                                   │
│ • XSS patterns                                                            │
└────────────────────────────────────────────────────────────────────────────┘

TEST DATA MATRIX TEMPLATE:

┌────────────────────────────────────────────────────────────────────────────┐
│ Test Data Matrix                                                           │
├──────────────┬──────────────────┬────────────────┬─────────────────────────│
│ Test Case ID │ Data Type        │ Test Data      │ Expected Result          │
├──────────────┼──────────────────┼────────────────┼─────────────────────────│
│ TC-001       │ Valid login      │ admin/Admin@123│ Dashboard load           │
│ TC-002       │ Invalid password│ admin/WrongPass│ Error message            │
│ TC-003       │ Min boundary    │ Pass/Admin123! │ Pass validation          │
│ TC-004       │ Max boundary    │ Pass/1234567890│ Pass validation          │
│ TC-005       │ Min-1 boundary  │ Pass/Admin123  │ Fail: too short          │
│ TC-006       │ Max+1 boundary  │ Pass/1234567890│ Fail: too long           │
│ TC-007       │ SQL injection    │ Pass/ ' OR 1=1 │ Fail: security block     │
└──────────────┴──────────────────┴────────────────┴─────────────────────────┘

```

### 6.6 Step 6: Review & Refine

```
STEP 6: REVIEW & REFINE
═══════════════════════════════════════════════════════════════════════════════

REVIEW CHECKLIST:

□ Completeness Check
  ├── All requirements have test cases?
  ├── All business rules covered?
  ├── All edge cases identified?
  └── All risks mitigated?

□ Correctness Check
  ├── Steps are accurate?
  ├── Expected results are correct?
  ├── Test data is valid?
  └── Traceability is complete?

□ Clarity Check
  ├── Steps are unambiguous?
  ├── Language is clear?
  ├── No jargon without explanation?
  └── Can someone else execute?

□ Consistency Check
  ├── Naming conventions followed?
  ├── Same format throughout?
  ├── Similar cases handled same way?
  └── No conflicting test cases?

□ Maintainability Check
  ├── Test cases are independent?
  ├── Easy to update for changes?
  ├── Data is externalized?
  └── No hardcoded values?

REVIEW MEETING:
├── Duration: 1-2 hours
├── Participants: QA Lead, Peer QA, BA (if needed)
├── Format: Walkthrough session
└── Output: Review comments and sign-off

```

### 6.7 Step 7: Finalize & Approve

```
STEP 7: FINALIZE & APPROVE
═══════════════════════════════════════════════════════════════════════════════

FINALIZATION ACTIVITIES:

1. Address all review comments
2. Update test case IDs if needed
3. Finalize test data files
4. Complete traceability matrix
5. Generate test coverage report
6. Prepare for handoff

APPROVAL CHECKLIST:

□ All review comments addressed
□ Test cases peer-reviewed
□ QA Lead approved
□ Traceability matrix complete
□ Test coverage meets target (>80%)
□ Test data files validated
□ Handoff package ready

```

---

## 7. Test Case Categories

### 7.1 By Testing Type

```
TEST CASE CATEGORIES BY TYPE
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│ FUNCTIONAL TEST CASES                                                       │
├────────────────────────────────────────────────────────────────────────────┤
│ Description: Verify system functions according to specifications           │
│ Count Target: 60-70% of total test cases                                   │
│ Examples:                                                                  │
│ ├── TC-001: Login with valid credentials                                   │
│ ├── TC-002: Create new employee                                            │
│ ├── TC-003: Submit leave request                                           │
│ └── TC-004: Generate payroll report                                        │
└────────────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────────────────────────────────────────┐
│ INTEGRATION TEST CASES                                                      │
├────────────────────────────────────────────────────────────────────────────┤
│ Description: Verify components work together                               │
│ Count Target: 15-20% of total test cases                                   │
│ Examples:                                                                  │
│ ├── TC-INT-001: Employee created → reflected in reports                   │
│ ├── TC-INT-002: Leave approved → balance updated                           │
│ └── TC-INT-003: Payroll processed → salary credited                        │
└────────────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────────────────────────────────────────┐
│ E2E TEST CASES                                                             │
├────────────────────────────────────────────────────────────────────────────┤
│ Description: Verify complete user journeys                                  │
│ Count Target: 10-15% of total test cases                                   │
│ Examples:                                                                  │
│ ├── TC-E2E-001: New employee onboarding flow                               │
│ ├── TC-E2E-002: Leave request to approval flow                             │
│ └── TC-E2E-003: Monthly payroll processing flow                             │
└────────────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────────────────────────────────────────┐
│ NON-FUNCTIONAL TEST CASES                                                  │
├────────────────────────────────────────────────────────────────────────────┤
│ Description: Verify performance, security, usability                       │
│ Count Target: 5-10% of total test cases                                     │
│ Examples:                                                                  │
│ ├── TC-PERF-001: Login response time < 3 seconds                           │
│ ├── TC-SEC-001: SQL injection prevention                                   │
│ └── TC-USAB-001: UI accessible via keyboard navigation                     │
└────────────────────────────────────────────────────────────────────────────┘

```

### 7.2 By Test Approach

```
BY TEST APPROACH
═══════════════════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────┬─────────────────────────────────┐
│ POSITIVE TEST CASES                     │ NEGATIVE TEST CASES              │
├─────────────────────────────────────────┼─────────────────────────────────┤
│ Purpose: Verify expected behavior       │ Purpose: Verify error handling   │
│ Count: 40-50%                           │ Count: 30-40%                    │
│ Example: Valid login succeeds           │ Example: Invalid login fails     │
└─────────────────────────────────────────┴─────────────────────────────────┘

┌─────────────────────────────────────────┬─────────────────────────────────┐
│ BOUNDARY TEST CASES                     │ ERROR GUESSING TEST CASES        │
├─────────────────────────────────────────┼─────────────────────────────────┤
│ Purpose: Test edge conditions           │ Purpose: Test common errors     │
│ Count: 10-20%                           │ Count: 5-10%                    │
│ Example: Password exactly 8 chars        │ Example: Double-click submit     │
└─────────────────────────────────────────┴─────────────────────────────────┘

┌─────────────────────────────────────────┬─────────────────────────────────┐
│ SECURITY TEST CASES                     │ PERFORMANCE TEST CASES           │
├─────────────────────────────────────────┼─────────────────────────────────┤
│ Purpose: Verify security controls        │ Purpose: Verify response times  │
│ Count: 5-10%                            │ Count: 5-10%                    │
│ Example: XSS prevention                  │ Example: 100 concurrent users    │
└─────────────────────────────────────────┴─────────────────────────────────┘

```

---

## 8. Traceability Matrix

### 8.1 Requirements Traceability

```markdown
## Requirements Traceability Matrix (RTM)

| TC ID | Requirement | Test Case Title | Priority | Status | Automated |
|-------|-------------|-----------------|----------|--------|-----------|
| REQ-AUTH-001 | System shall authenticate valid users | TC-LOGIN-001 | P0 | Ready | Yes |
| REQ-AUTH-001 | System shall authenticate valid users | TC-LOGIN-002 | P0 | Ready | Yes |
| REQ-AUTH-002 | System shall reject invalid credentials | TC-LOGIN-003 | P0 | Ready | Yes |
| REQ-AUTH-003 | System shall lock after 5 failed attempts | TC-LOGIN-004 | P1 | Ready | Yes |
| REQ-AUTH-004 | System shall support password reset | TC-LOGIN-005 | P2 | Ready | No |
| REQ-AUTH-005 | System shall support remember me | TC-LOGIN-006 | P3 | Ready | No |
| REQ-EMP-001 | Admin can create employee | TC-EMP-001 | P0 | Ready | Yes |
| REQ-EMP-002 | Email must be unique | TC-EMP-002 | P0 | Ready | Yes |
| REQ-EMP-003 | Required fields must be validated | TC-EMP-003 | P0 | Ready | Yes |

Coverage Summary:
├── Total Requirements: 9
├── Requirements Fully Covered: 9 (100%)
├── Total Test Cases: 18
├── Test Cases Ready: 18 (100%)
├── Test Cases Automated: 12 (67%)
└── Test Cases Manual Only: 6 (33%)

```

### 8.2 Coverage Analysis

```markdown
## Test Coverage Analysis

### By Module

| Module | Requirements | Test Cases | Coverage % | P0 Count |
|--------|--------------|------------|------------|----------|
| Authentication | 5 | 12 | 100% | 4 |
| Employee | 8 | 24 | 100% | 6 |
| Leave | 10 | 35 | 100% | 8 |
| Attendance | 6 | 18 | 100% | 4 |
| Payroll | 7 | 21 | 100% | 5 |
| **Total** | **36** | **110** | **100%** | **27** |

### By Priority

| Priority | Count | Percentage | Covered by Automation |
|----------|-------|------------|---------------------|
| P0 (Critical) | 27 | 25% | 25 (93%) |
| P1 (High) | 45 | 41% | 35 (78%) |
| P2 (Medium) | 28 | 25% | 15 (54%) |
| P3 (Low) | 10 | 9% | 2 (20%) |
| **Total** | **110** | **100%** | **77 (70%)** |

### By Test Type

| Type | Count | Percentage |
|------|-------|------------|
| Positive | 55 | 50% |
| Negative | 33 | 30% |
| Boundary | 15 | 14% |
| Security | 5 | 5% |
| Performance | 2 | 2% |
| **Total** | **110** | **100%** |

```

---

## 9. Test Data Management

### 9.1 Test Data Strategy

```
TEST DATA MANAGEMENT
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│ TEST DATA REQUIREMENTS BY TEST TYPE                                         │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  SMOKE TEST DATA                                                           │
│  ├── Minimal set of valid data                                              │
│  ├── Quick to set up                                                       │
│  └── Reusable                                                              │
│                                                                             │
│  REGRESSION TEST DATA                                                      │
│  ├── Comprehensive coverage                                                 │
│  ├── Stable data (doesn't change between runs)                             │
│  └── Backup/restore capability                                              │
│                                                                             │
│  BOUNDARY TEST DATA                                                        │
│  ├── Precise values at boundaries                                           │
│  ├── Unique identifiers                                                    │
│  └── Clear expected results                                                │
│                                                                             │
│  E2E TEST DATA                                                             │
│  ├── Complete workflow data                                                │
│  ├── Related records across modules                                         │
│  └── End-state cleanup                                                     │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 9.2 Test Data File Structure

```
TEST DATA FILE STRUCTURE
═══════════════════════════════════════════════════════════════════════════════

src/test/resources/
├── testdata/
│   ├── login/
│   │   ├── valid-credentials.xlsx
│   │   ├── invalid-credentials.xlsx
│   │   └── account-locked.xlsx
│   ├── employee/
│   │   ├── create-employee.xlsx
│   │   ├── edit-employee.xlsx
│   │   └── employee-search.xlsx
│   ├── leave/
│   │   ├── leave-request.xlsx
│   │   ├── leave-balance.xlsx
│   │   └── leave-approval.xlsx
│   └── payroll/
│       ├── salary-calculation.xlsx
│       └── payroll-processing.xlsx
├── config/
│   ├── qa-config.properties
│   ├── staging-config.properties
│   └── test-users.properties
└── schemas/
    ├── employee-schema.json
    └── leave-schema.json

```

---

## 10. Quality Gates

### 10.1 Design Quality Checklist

```
TEST DESIGN QUALITY GATES
═══════════════════════════════════════════════════════════════════════════════

GATE 1: Coverage Verification
═══════════════════════════════════════════════════════════════════════════════
□ 100% requirements have at least one test case
□ 100% business rules have pass and fail test cases
□ All high-risk items have test coverage
□ Critical paths are covered by P0 test cases

GATE 2: Test Case Quality
═══════════════════════════════════════════════════════════════════════════════
□ All test cases are clear and unambiguous
□ All test cases are traceable to requirements
□ All test cases have unique IDs
□ Test data is identified for each test case
□ Expected results are specific and measurable

GATE 3: Technique Application
═══════════════════════════════════════════════════════════════════════════════
□ EP applied to all input fields
□ BVA applied to all boundary conditions
□ Decision tables created for complex logic
□ State transitions covered for workflows

GATE 4: Review Completion
═══════════════════════════════════════════════════════════════════════════════
□ Peer review completed
□ QA Lead review completed
□ All review comments addressed
□ No open blocking issues

GATE 5: Automation Readiness
═══════════════════════════════════════════════════════════════════════════════
□ Test cases are executable
□ Test data is available
□ Environment is accessible
□ Automation feasibility assessed

```

### 10.2 Sign-off Criteria

| Role | Required | Sign-off Criteria |
|------|----------|-------------------|
| QA Engineer | Yes | All test cases written per template |
| QA Peer | Yes | Peer review completed |
| QA Lead | Yes | Quality gates passed |
| Product Owner | Recommended | Scope alignment confirmed |

---

## 11. Deliverables

### 11.1 Primary Deliverables

| # | Deliverable | Format | Location | Due |
|---|-------------|--------|----------|-----|
| 1 | Test Case Suite | Markdown/Excel | `outputs/testcase/` | End of workflow |
| 2 | Test Coverage Report | Markdown | `outputs/testcase/` | End of workflow |
| 3 | Traceability Matrix | Markdown/Excel | `outputs/testcase/` | End of workflow |
| 4 | Test Data Files | Excel/JSON | `src/test/resources/` | End of workflow |
| 5 | Test Design Summary | Markdown | `outputs/testcase/` | End of workflow |

### 11.2 Test Case Suite Template

```markdown
# Test Case Suite: [Module Name]

## Metadata
| Field | Value |
|-------|-------|
| Module | [Module Name] |
| Version | 1.0 |
| Created By | [Name] |
| Created Date | [Date] |
| Reviewed By | [Name] |
| Reviewed Date | [Date] |
| Status | Approved |

## Summary
| Metric | Count |
|--------|-------|
| Total Test Cases | XX |
| P0 (Critical) | XX |
| P1 (High) | XX |
| P2 (Medium) | XX |
| P3 (Low) | XX |
| Automated | XX |
| Manual Only | XX |

## Test Cases

[Detailed test cases per Section 5.2 template]

## Traceability Matrix

[Per Section 8.1 template]

## Test Data Requirements

[Per Section 9.2 template]

```

---

## 12. Roles & Responsibilities

### 12.1 Role Matrix

| Role | Activities | Deliverables | Authority |
|------|------------|--------------|-----------|
| **QA Engineer** | Write test cases, prepare test data | Test suite, test data | Execute design |
| **QA Peer** | Review test cases | Review comments | Recommend changes |
| **QA Lead** | Approve design, assign reviewers | Approved suite | Final approval |
| **Product Owner** | Validate coverage | Coverage confirmation | Scope alignment |

### 12.2 Effort Estimation

| Activity | Time Estimate |
|----------|----------------|
| Test Strategy Design | 2-4 hours |
| Identify Test Conditions | 4-8 hours |
| Apply Techniques | 8-16 hours |
| Write Test Cases | 16-32 hours |
| Prepare Test Data | 8-16 hours |
| Review & Refine | 8-16 hours |
| **Total per Module** | **46-92 hours** |

---

## 13. Integration with Other Workflows

### 13.1 Handoff to Automation Planning

```
HANDOFF FROM: Test Case Design
TO: Automation Planning

INPUTS REQUIRED:
├── ✅ Test Case Suite (complete)
├── ✅ Traceability Matrix (updated)
├── ✅ Test Coverage Report
├── ✅ Test Data Files (ready)
└── ✅ Test Design Summary

ACCEPTANCE CRITERIA:
├── 100% requirements covered
├── All P0/P1 test cases documented
├── Test data files validated
├── Automation feasibility assessed for each test case
└── QA Lead approved test suite

AUTOMATION SUITABILITY ASSESSMENT:
├── High Suitability: Stable UI, repeatable, P0/P1
├── Medium Suitability: Moderate change frequency
└── Low Suitability: Complex setup, exploratory only

```

---

## 14. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer (ISTQB) | Initial workflow document |
