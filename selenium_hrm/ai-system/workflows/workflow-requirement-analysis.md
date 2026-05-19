# Workflow: Requirement Analysis

## 1. Overview

### 1.1 Purpose
Workflow phân tích yêu cầu (Requirement Analysis) là bước đầu tiên và quan trọng nhất trong QA process. Workflow này hướng dẫn QA Engineer phân tích, đánh giá, và tài liệu hóa requirements để đảm bảo:
- Hiểu đầy đủ scope và mục tiêu của dự án/feature
- Xác định testable requirements
- Đánh giá rủi ro và phụ thuộc
- Tạo nền tảng vững chắc cho test design và automation

### 1.2 Objectives
| # | Objective | Success Criteria |
|---|-----------|-----------------|
| 1 | Complete understanding | 100% requirements được review và clarified |
| 2 | Traceability | Mỗi requirement có unique ID và mapped criteria |
| 3 | Risk awareness | Tất cả risks được identify và document |
| 4 | Testability | Không có ambiguous requirements |
| 5 | Communication | Clear alignment với stakeholders |

### 1.3 Workflow Position

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                     AI AUTOMATION PIPELINE - WORKFLOW 1                     │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   ┌─────────────┐                                                         │
│   │  INPUT:     │                                                         │
│   │  User Story │                                                         │
│   │  / Features │                                                         │
│   │  / Specs   │                                                         │
│   └──────┬──────┘                                                         │
│          │                                                                │
│          ▼                                                                │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │                    REQUIREMENT ANALYSIS                          │     │
│   │  ┌─────────────────────────────────────────────────────────┐   │     │
│   │  │ 1. Gather & Review Requirements                          │   │     │
│   │  │ 2. Clarify Ambiguities                                  │   │     │
│   │  │ 3. Identify Testable Requirements                       │   │     │
│   │  │ 4. Analyze Business Rules                               │   │     │
│   │  │ 5. Create Traceability Matrix                           │   │     │
│   │  │ 6. Perform Risk Assessment                              │   │     │
│   │  │ 7. Document Analysis Results                            │   │     │
│   │  └─────────────────────────────────────────────────────────┘   │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│          │                                                                │
│          ▼                                                                │
│   ┌─────────────┐                                                         │
│   │  OUTPUT:    │                                                         │
│   │ analysis-   │                                                         │
│   │ result.md   │                                                         │
│   └─────────────┘                                                         │
│          │                                                                │
│          ▼                                                                │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │              ▶ NEXT WORKFLOW: Test Case Design                   │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 2. Inputs & Prerequisites

### 2.1 Required Inputs

| # | Input | Format | Source | Priority |
|---|-------|--------|--------|----------|
| 1 | User Stories / Requirements | JIRA tickets, Confluence, Word | Product Owner / BA | Required |
| 2 | Functional Specifications | Document, Mockups, Figma | Product Team | Required |
| 3 | Technical Specifications | API docs, Database schema | Development Team | Optional |
| 4 | Business Rules | Process flows, Decision trees | Business Analyst | Required |
| 5 | Acceptance Criteria | Definition of Done | Product Owner | Required |
| 6 | Previous Similar Requirements | Historical data | QA Team | Recommended |
| 7 | Non-Functional Requirements | Performance/Security specs | Tech Lead | Recommended |

### 2.2 Prerequisites Checklist

```
PRE-WORKFLOW CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

□ Requirements document received from Product Owner
□ Meeting scheduled with BA/PM for clarification
□ Access granted to test environment (if available)
□ Access granted to JIRA/Confluence/Design tools
□ Historical test artifacts reviewed (if applicable)
□ Team capacity confirmed for analysis phase

```

### 2.3 Stakeholder Identification

| Stakeholder | Role | Responsibility | Communication Frequency |
|-------------|------|----------------|------------------------|
| Product Owner | Requirement Owner | Final decision on scope | As needed |
| Business Analyst | Requirements Clarifier | Detailed explanation | Daily (during analysis) |
| Tech Lead | Technical Input | Feasibility assessment | Weekly sync |
| QA Lead | Quality Assurance | Test strategy alignment | Weekly sync |
| Development Team | Implementation | Technical constraints | As needed |

---

## 3. Process Steps

### 3.1 Step 1: Gather & Review Requirements

**Objective:** Collect và initial review tất cả available requirements

**Activities:**

```
STEP 1: GATHER & REVIEW REQUIREMENTS
═══════════════════════════════════════════════════════════════════════════════

1.1 COLLECT DOCUMENTS
    ├── Read all related user stories in JIRA
    ├── Review functional specs in Confluence
    ├── Check Figma/Mockups for UI requirements
    ├── Read API documentation if applicable
    └── Review any existing test artifacts

1.2 INITIAL SCAN
    ├── List all requirements/features
    ├── Note all referenced documents
    ├── Identify missing information
    └── Flag urgent/high-priority items

1.3 STRUCTURAL ANALYSIS
    ├── Break down epics into stories
    ├── Identify feature dependencies
    ├── Map user journeys/workflows
    └── Identify integration points

1.4 DOCUMENT REVIEW NOTES
    ├── Quality of requirements (clear/vague)
    ├── Completeness (any gaps?)
    ├── Consistency (conflicts?)
    └── Testability (can we test it?)
```

**Deliverables:**
- List of collected documents with links
- Initial assessment summary
- Gap analysis report

**Template - Document Collection Log:**

```markdown
## Document Collection Log

| # | Document Name | Type | Source | Link | Status | Notes |
|---|---------------|------|--------|------|--------|-------|
| 1 | US-123: Login Feature | User Story | JIRA | [Link] | ✅ Collected | |
| 2 | Login Spec v2.0 | Functional Spec | Confluence | [Link] | ✅ Collected | |
| 3 | Auth API Docs | API Spec | Swagger | [Link] | ✅ Collected | |
| 4 | Figma: Login Screen | Mockup | Figma | [Link] | ⏳ Pending | |

Status Legend: ✅ Complete | ⏳ Pending | ❌ Not Available
```

---

### 3.2 Step 2: Clarify Ambiguities

**Objective:** Resolve unclear requirements trước khi proceed

**Question Categories:**

```
QUESTION TYPES BY SEVERITY
═══════════════════════════════════════════════════════════════════════════════

🔴 CRITICAL (Blocker) - Must resolve before proceeding
   ├── Missing required field definition
   ├── Undefined user role/permission
   ├── Contradictory requirements
   └── Missing acceptance criteria

🟡 HIGH (Major) - Should resolve before design
   ├── Ambiguous business rules
   ├── Unclear error handling
   ├── Missing edge case definition
   └── Incomplete workflow steps

🟢 MEDIUM (Minor) - Can resolve during design
   ├── Vague UI descriptions
   ├── Optional field clarification
   ├── Performance threshold not specified
   └── Non-critical edge cases

```

**Question Template:**

```markdown
## Requirement Clarification Log

### CRITICAL Questions (Must Answer)

---

**Question ID:** CLAR-001
**Related Requirement:** REQ-LOGIN-004
**Current Text:** "System shall validate user credentials"
**Ambiguity:** What constitutes "valid"? Are there character limits?
**Question:** What are the exact validation rules for username/password?
**Asked By:** QA Engineer
**Asked Date:** 2026-05-11
**Answered By:** [Name]
**Answer Date:** [Date]
**Resolved:** ✅ Yes / ❌ No

---

**Question ID:** CLAR-002
**Related Requirement:** REQ-LOGIN-007
**Current Text:** "System shall display error message for invalid login"
**Ambiguity:** Does "invalid login" include both wrong password AND wrong username?
**Question:** Should we show different messages for wrong username vs wrong password?
**Asked By:** QA Engineer
**Asked Date:** 2026-05-11
**Answered By:** [Name]
**Answer Date:** [Date]
**Resolved:** ✅ Yes / ❌ No

```

**Clarification Meeting Agenda:**

```
MEETING AGENDA: Requirements Clarification
═══════════════════════════════════════════════════════════════════════════════

Date: [Date]
Time: [Time]
Attendees: [Names]
Duration: [30-60 minutes]

1. Opening (5 min)
   └── Review meeting objectives

2. Requirement Walkthrough (30 min)
   └── Go through each requirement systematically

3. Open Questions Discussion (15 min)
   └── Address all clar-xxx questions

4. Action Items (5 min)
   └── Assign owners and deadlines

5. Next Steps (5 min)
   └── Confirm timeline for answers

PRE-MEETING HOMEWORK
├── Review all requirements documents
├── List all questions before meeting
└── Prepare clarification matrix

```

---

### 3.3 Step 3: Identify Testable Requirements

**Objective:** Convert requirements thành testable statements

**Requirement Quality Assessment Matrix:**

| Rating | Criteria | Action |
|--------|----------|--------|
| **Testable (A)** | Clear inputs, actions, expected outputs | Proceed to test case design |
| **Conditionally Testable (B)** | Has conditions, need edge case analysis | Create test conditions |
| **Non-Testable (C)** | Vague, no measurable criteria | Request clarification |
| **Out of Scope (D)** | Cannot test within this sprint | Document and exclude |

**Transformation Examples:**

```markdown
## Requirement Transformation

### Example 1: Before → After

**BEFORE (Non-Testable):**
"User should be able to login quickly"

**AFTER (Testable):**
"User with valid credentials shall be logged in within 3 seconds"

---

### Example 2: Before → After

**BEFORE (Ambiguous):**
"System should handle multiple users"

**AFTER (Testable):**
"System shall support minimum 100 concurrent user sessions without degradation"

---

### Example 3: Before → After

**BEFORE (Incomplete):**
"Admin can manage employees"

**AFTER (Testable):**
"Admin user shall be able to: create, view, edit, delete employee records"
"Non-admin users shall receive 403 Forbidden when attempting delete"

```

**Testability Checklist:**

```
TESTABILITY CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

For each requirement, verify:

□ Inputs defined?
  └── What data/actions trigger this?

□ Expected outputs defined?
  └── What should happen?

□ Conditions specified?
  └── Under what circumstances?

□ Constraints stated?
  └── Any limits, boundaries?

□ Success criteria clear?
  └── How do we know it works?

□ Failure modes defined?
  └── What could go wrong?

□ Measurable?
  └── Can we quantify pass/fail?

□ Independent?
  └── Can it be tested in isolation?

```

---

### 3.4 Step 4: Analyze Business Rules

**Objective:** Identify và document business logic, validations, và constraints

**Business Rules Analysis Framework:**

```
BUSINESS RULES CATEGORIZATION
═══════════════════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────────────────────┐
│                         BUSINESS RULES TYPES                                │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  1. VALIDATION RULES                                                        │
│     ├── Data format validation (email, phone, date)                         │
│     ├── Required field rules                                                │
│     ├── Length/range validation                                             │
│     └── Cross-field validation                                              │
│                                                                             │
│  2. CALCULATION RULES                                                       │
│     ├── Formula definitions                                                  │
│     ├── Rounding/precision rules                                            │
│     └── Currency/percentage calculations                                    │
│                                                                             │
│  3. WORKFLOW RULES                                                          │
│     ├── Approval chains                                                     │
│     ├── Status transitions                                                 │
│     ├── Notification triggers                                               │
│     └── Escalation paths                                                   │
│                                                                             │
│  4. AUTHORIZATION RULES                                                     │
│     ├── Role-based permissions                                              │
│     ├── Resource ownership                                                  │
│     └── Access control matrices                                             │
│                                                                             │
│  5. DATA RELATIONSHIP RULES                                                 │
│     ├── Parent-child relationships                                          │
│     ├── Cascading effects                                                   │
│     └── Data integrity constraints                                          │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

**Business Rules Documentation Template:**

```markdown
## Business Rules Register

### BR-001: Employee Email Uniqueness

| Field | Value |
|-------|-------|
| **Rule ID** | BR-001 |
| **Category** | Validation |
| **Module** | Employee Management |
| **Description** | Email address must be unique across all employees |
| **Condition** | On create/update employee |
| **Action** | System validates uniqueness against database |
| **Error** | "Email already exists in the system" |
| **Testable** | ✅ Yes |

**Test Scenarios:**
- TC-BR001-001: Create employee with unique email → Success
- TC-BR001-002: Create employee with duplicate email → Error message

---

### BR-002: Leave Balance Validation

| Field | Value |
|-------|-------|
| **Rule ID** | BR-002 |
| **Category** | Validation |
| **Module** | Leave Management |
| **Description** | Leave request days cannot exceed available balance |
| **Condition** | On submit leave request |
| **Formula** | requestedDays <= availableBalance |
| **Error** | "Insufficient leave balance" |
| **Testable** | ✅ Yes |

**Test Scenarios:**
- TC-BR002-001: Request 3 days with 5 days balance → Success
- TC-BR002-002: Request 10 days with 5 days balance → Error

---

### BR-003: Manager Approval Threshold

| Field | Value |
|-------|-------|
| **Rule ID** | BR-003 |
| **Category** | Workflow |
| **Module** | Leave Management |
| **Description** | Leave > 5 days requires VP approval |
| **Condition** | Leave request days > 5 |
| **Action** | Route to VP for approval |
| **Testable** | ✅ Yes |

**Workflow Diagram:**
```
Leave Request (3 days) → Manager Approval → Approved
Leave Request (10 days) → Manager Approval → VP Approval → Approved
```

```

---

### 3.5 Step 5: Create Traceability Matrix

**Objective:** Map requirements → test conditions để đảm bảo coverage

**Traceability Matrix Template:**

```markdown
## Requirements Traceability Matrix (RTM)

### Legend
- ✅ Covered by Test Case
- ⏳ Partial Coverage
- ❌ Not Covered
- N/A Not Applicable

### Login Module

| Req ID | Requirement Description | Priority | TC Count | Coverage | Test Case IDs | Notes |
|--------|------------------------|----------|----------|----------|---------------|-------|
| REQ-LOGIN-001 | System shall authenticate valid users | High | 5 | ✅ | TC-LOGIN-001 to TC-LOGIN-005 | |
| REQ-LOGIN-002 | System shall reject invalid credentials | High | 4 | ✅ | TC-LOGIN-006 to TC-LOGIN-009 | |
| REQ-LOGIN-003 | System shall lock account after 5 failed attempts | High | 3 | ✅ | TC-LOGIN-010 to TC-LOGIN-012 | |
| REQ-LOGIN-004 | System shall support password reset | Medium | 3 | ✅ | TC-LOGIN-013 to TC-LOGIN-015 | |
| REQ-LOGIN-005 | System shall remember user session | Low | 2 | ⏳ | TC-LOGIN-016, TC-LOGIN-017 | Edge cases pending |
| REQ-LOGIN-006 | System shall support SSO integration | Medium | 0 | ❌ | - | Third-party, out of scope |

---

### Summary Statistics

| Category | Count |
|----------|-------|
| Total Requirements | 6 |
| Requirements Fully Covered | 4 |
| Requirements Partially Covered | 1 |
| Requirements Not Covered | 1 |
| **Coverage Percentage** | **83.3%** |

```

**Backward Traceability Check:**

```
TRACEABILITY VERIFICATION
═══════════════════════════════════════════════════════════════════════════════

Forward Traceability:
└── Requirements → Test Cases → Test Execution
    ✅ Each requirement has at least one test case
    ✅ Each test case traces to a requirement

Backward Traceability:
└── Test Cases → Requirements
    ✅ Each test case validates at least one requirement
    ✅ No orphan test cases (unmapped to requirements)

Coverage Calculation:
├── Requirements Coverage: [Covered] / [Total] = XX%
├── Test Case Efficiency: [Executable] / [Total] = XX%
└── Risk Coverage: [High Priority Covered] / [High Priority Total] = XX%

```

---

### 3.6 Step 6: Perform Risk Assessment

**Objective:** Identify, assess, và document testing risks

**Risk Assessment Matrix:**

```
RISK ASSESSMENT FRAMEWORK
═══════════════════════════════════════════════════════════════════════════════

                      IMPACT
              Low         Medium        High
         ┌─────────────┬─────────────┬─────────────┐
    Low  │    GREEN    │   YELLOW    │   ORANGE    │
         │  (Low Risk) │ (Medium Risk)│ (High Risk) │
    P ───┼─────────────┼─────────────┼─────────────┤
    r    │   YELLOW    │   ORANGE    │    RED      │
         │ (Medium Risk│  (High Risk) │ (Critical)  │
    o ───┼─────────────┼─────────────┼─────────────┤
    b    │   ORANGE    │    RED      │    RED      │
         │  (High Risk)│  (Critical) │ (Critical)  │
         └─────────────┴─────────────┴─────────────┘

Risk Score = Probability × Impact

```

**Risk Categories:**

| Category | Description | Examples |
|----------|-------------|----------|
| **Technical** | Technology-related risks | Browser compatibility, API changes, Database migration |
| **Functional** | Business logic risks | Complex calculations, Integration failures |
| **Environmental** | Test environment risks | Unstable environment, Data availability |
| **Resource** | Team/resource risks | Skill gaps, Capacity issues |
| **Schedule** | Timeline risks | Tight deadlines, Dependency delays |
| **Data** | Test data risks | Data availability, Data quality |

**Risk Register Template:**

```markdown
## Risk Register

### Risk Matrix Summary

| Risk ID | Risk Description | Category | Probability | Impact | Score | Level | Mitigation |
|---------|-----------------|----------|-------------|--------|-------|-------|------------|
| RISK-001 | UI elements frequently change | Technical | High | Medium | 6 | 🟠 High | Use data-testid, flexible locators |
| RISK-002 | API endpoints not documented | Technical | Medium | High | 6 | 🟠 High | Request API specs early |
| RISK-003 | Test data not available | Data | Medium | High | 6 | 🟠 High | Create synthetic test data |
| RISK-004 | Complex leave calculation logic | Functional | Medium | Medium | 4 | 🟡 Medium | Design boundary test cases |
| RISK-005 | Third-party SSO dependency | Technical | Low | High | 3 | 🟡 Medium | Mock external service |

---

### Detailed Risk Assessment

---

**RISK-001: UI Elements Change Frequently**

| Field | Value |
|-------|-------|
| **Risk ID** | RISK-001 |
| **Category** | Technical |
| **Probability** | High (4/5) |
| **Impact** | Medium (3/5) |
| **Risk Score** | 12 (High) |
| **Description** | Development team frequently updates UI, causing locator breakage |
| **Impact Details** | Test maintenance effort increases, flaky tests |
| **Mitigation Strategy** | Request data-testid attributes from developers; Use stable IDs first |
| **Contingency Plan** | Schedule weekly locator review; Automate locator health check |
| **Owner** | QA Team Lead |
| **Status** | Active |
| **Last Updated** | 2026-05-11 |

**Mitigation Implementation:**
- [ ] Request data-testid in all new UI elements
- [ ] Update existing locators to use stable attributes
- [ ] Create locator fallback mechanism
- [ ] Add locator health monitoring to CI pipeline

---

**RISK-002: Complex Leave Balance Calculation**

| Field | Value |
|-------|-------|
| **Risk ID** | RISK-002 |
| **Category** | Functional |
| **Probability** | Medium (3/5) |
| **Impact** | High (4/5) |
| **Risk Score** | 12 (High) |
| **Description** | Leave balance calculation involves multiple factors (carry forward, proration, holidays) |
| **Impact Details** | Incorrect calculation could lead to over/under payment |
| **Mitigation Strategy** | Create comprehensive calculation test matrix covering all scenarios |
| **Contingency Plan** | Involve Finance team in verification |
| **Owner** | Senior QA Engineer |
| **Status** | Active |
| **Last Updated** | 2026-05-11 |

```

---

### 3.7 Step 7: Document Analysis Results

**Objective:** Compile tất cả analysis artifacts vào output document

**Analysis Result Document Structure:**

```markdown
# Analysis Result: [Feature/Module Name]

## Document Metadata
| Field | Value |
|-------|-------|
| Document ID | AR-[Module]-[Date] |
| Version | 1.0 |
| Author | [Name] |
| Date | [Date] |
| Status | Draft/Reviewed/Approved |
| Related JIRA | [Link] |

## Executive Summary
[Brief overview of the feature and analysis findings]

## 1. Scope Definition
### 1.1 In Scope
### 1.2 Out of Scope
### 1.3 Dependencies

## 2. Requirements Analysis
### 2.1 Requirements Summary Table
### 2.2 Requirements Quality Assessment
### 2.3 Clarification Log

## 3. Business Rules Analysis
### 3.1 Business Rules Register
### 3.2 Workflows & State Machines
### 3.3 Data Validation Rules

## 4. Test Approach
### 4.1 Testing Strategy
### 4.2 Test Types Coverage
### 4.3 Test Environment Requirements

## 5. Traceability Matrix
[RTM table]

## 6. Risk Assessment
### 6.1 Risk Summary
### 6.2 Detailed Risk Register
### 6.3 Mitigation Plans

## 7. Resource Estimation
### 7.1 Effort Estimate
### 7.2 Timeline
### 7.3 Skills Required

## 8. Open Issues & Assumptions
### 8.1 Open Questions
### 8.2 Assumptions

## 9. Approval & Sign-off
| Role | Name | Signature | Date |
|------|------|-----------|------|
| QA Lead | | | |
| Product Owner | | | |
| Tech Lead | | | |

## 10. Appendices
### 10.1 Reference Documents
### 10.2 Glossary
### 10.3 Change History

```

---

## 4. Detailed Activity Breakdown

### 4.1 Activity: Requirements Elicitation

```markdown
## Activity: Requirements Elicitation

### Definition
Process of gathering requirements from stakeholders through various techniques.

### Techniques
| Technique | When to Use | Effort | Output Quality |
|-----------|-------------|--------|----------------|
| **Document Review** | When specs exist | Low | Medium |
| **Interview** | When expert knowledge needed | Medium | High |
| **Workshop** | When multiple perspectives | High | High |
| **Prototype Review** | When UI requirements unclear | Medium | Medium |
| **Observation** | When current process exists | High | High |
| **Survey/Questionnaire** | When large stakeholder group | Medium | Variable |

### Interview Guide Template

```
INTERVIEW PREPARATION CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

PRE-INTERVIEW:
□ Research background information
□ Prepare interview questions
□ Schedule appropriate time (30-60 min)
□ Send agenda in advance

INTERVIEW QUESTIONS BY CATEGORY:

1. GENERAL
   └── What is the main purpose of this feature?
   └── Who are the primary users?
   └── What problem does this solve?

2. FUNCTIONAL REQUIREMENTS
   └── What should the system do?
   └── What are the key user interactions?
   └── What data is involved?

3. BUSINESS RULES
   └── Are there any validation rules?
   └── What are the workflow steps?
   └── Are there approval/authorization requirements?

4. NON-FUNCTIONAL REQUIREMENTS
   └── Are there performance expectations?
   └── What about security requirements?
   └── Any compliance needs?

5. EDGE CASES
   └── What could go wrong?
   └── Are there error scenarios?
   └── How should edge cases be handled?

6. INTEGRATION
   └── Does this integrate with other systems?
   └── What are the data exchange formats?

POST-INTERVIEW:
□ Send thank you note
□ Document key findings
□ Follow up on open questions
```

```

### 4.2 Activity: Requirements Classification

```markdown
## Activity: Requirements Classification

### Classification Criteria

#### By Priority

| Priority | Definition | Criteria | Test Timing |
|----------|------------|----------|-------------|
| **P0 - Critical** | Core functionality | Revenue-impacting, Legal compliance | First |
| **P1 - High** | Important features | Major user workflows | Early |
| **P2 - Medium** | Standard features | Normal functionality | Normal |
| **P3 - Low** | Nice-to-have | Enhancements | Last |

#### By Type

| Type | Description | Testing Approach |
|------|-------------|-----------------|
| **Functional** | What system does | Black-box testing |
| **Non-Functional** | How system performs | Performance, Security, Usability |
| **Regulatory** | Compliance requirements | Audit trails, Reports |
| **Technical** | Implementation details | API contracts, Data formats |

#### By Testability

| Category | Definition | Action |
|----------|------------|--------|
| **Directly Testable** | Can test immediately | Design test cases |
| **Indirectly Testable** | Need special conditions | Plan setup |
| **Not Testable** | Cannot verify automatically | Manual verification or exclude |

```

### 4.3 Activity: Requirements Prioritization

```markdown
## Activity: Requirements Prioritization

### Prioritization Framework: MoSCoW

| Category | Description | Typical % | Test Focus |
|----------|-------------|-----------|------------|
| **Must Have** | Core functionality, no workaround | 60% | P0 tests first |
| **Should Have** | Important but not critical | 20% | P1 tests early |
| **Could Have** | Desirable but optional | 15% | P2 tests when time allows |
| **Won't Have** | Not in current scope | 5% | Document and defer |

### Prioritization Workshop Agenda

```
PRIORITIZATION WORKSHOP
═══════════════════════════════════════════════════════════════════════════════

Duration: 1-2 hours
Participants: Product Owner, BA, QA Lead, Tech Lead

AGENDA:
1. Introduction (5 min)
   └── Review objectives and scope

2. Feature Walkthrough (20 min)
   └── Brief overview of each requirement

3. MoSCoW Sorting (40 min)
   └── Sort each item into categories
   └── Use voting for tiebreakers

4. Discussion & Adjustment (20 min)
   └── Address concerns
   └── Finalize categorization

5. Sign-off (15 min)
   └── Get agreement from all stakeholders

OUTPUTS:
├── Prioritized requirements list
├── Agreed scope definition
└── Timeline implications

```

---

## 5. Quality Gates

### 5.1 Analysis Quality Checklist

```
REQUIREMENT ANALYSIS QUALITY GATES
═══════════════════════════════════════════════════════════════════════════════

GATE 1: Completeness Check
═══════════════════════════════════════════════════════════════════════════════
□ All user stories have been reviewed
□ All acceptance criteria are documented
□ All business rules are identified
□ All integrations are mapped
□ All edge cases are considered
□ All non-functional requirements are listed

GATE 2: Clarity Check
═══════════════════════════════════════════════════════════════════════════════
□ No ambiguous requirements (verified by 2nd reviewer)
□ No contradictory requirements
□ All abbreviations are defined
□ All technical terms are explained
□ All acronyms are expanded on first use

GATE 3: Testability Check
═══════════════════════════════════════════════════════════════════════════════
□ Every requirement has measurable acceptance criteria
□ Every rule has clear pass/fail conditions
□ Every workflow has defined entry/exit criteria
□ Every error scenario has expected behavior

GATE 4: Traceability Check
═══════════════════════════════════════════════════════════════════════════════
□ Requirements mapped to test conditions
□ Test conditions linked to test cases
□ No orphaned requirements (without tests)
□ No orphaned tests (without requirements)

GATE 5: Risk Coverage Check
═══════════════════════════════════════════════════════════════════════════════
□ High-risk areas have test coverage
□ Critical paths are identified
□ Risk mitigation strategies defined

GATE 6: Approval Check
═══════════════════════════════════════════════════════════════════════════════
□ Analysis reviewed by QA Lead
□ Requirements clarified with PO/BA
□ Risks acknowledged by stakeholders
□ Scope agreed and signed off

```

### 5.2 Sign-off Criteria

| Role | Sign-off Required | Criteria |
|------|-------------------|----------|
| QA Engineer | Yes | All gates passed |
| QA Lead | Yes | Quality verified |
| Product Owner | Yes | Scope agreed |
| Tech Lead | Recommended | Technical feasibility |

---

## 6. Deliverables

### 6.1 Primary Deliverables

| # | Deliverable | Format | Location | Due |
|---|-------------|--------|----------|-----|
| 1 | Analysis Result Document | Markdown | `outputs/analysis/` | End of workflow |
| 2 | Requirements Traceability Matrix | Markdown/Excel | `outputs/analysis/` | End of workflow |
| 3 | Clarification Log | Markdown | `outputs/analysis/` | As needed |
| 4 | Risk Register | Markdown | `outputs/analysis/` | End of workflow |
| 5 | Business Rules Register | Markdown | `outputs/analysis/` | End of workflow |

### 6.2 Analysis Result Document Template

Xem Section 3.7 cho full template.

### 6.3 Output File Naming Convention

```
Format: {Type}-{Module}-{Date}.{Extension}

Examples:
├── analysis-result-login-20260511.md
├── rtm-employee-module-20260511.md
├── clarifications-leave-20260511.md
├── risk-register-payroll-20260511.md
├── business-rules-20260511.md
```

---

## 7. Roles & Responsibilities

### 7.1 Role Matrix

| Role | Activities | Deliverables | Authority |
|------|------------|--------------|-----------|
| **QA Engineer** | Gather, analyze, document requirements | Analysis document, RTM | Execute analysis |
| **QA Lead** | Review, approve, escalate | Approved analysis | Approve analysis |
| **Product Owner** | Clarify scope, validate priorities | Requirements clarification | Final scope decision |
| **Business Analyst** | Explain business rules, validate | Business rules clarification | Domain expertise |
| **Tech Lead** | Assess technical feasibility | Technical feasibility input | Technical guidance |

### 7.2 RACI Matrix

| Activity | QA Engineer | QA Lead | PO | BA | Tech Lead |
|----------|-------------|---------|----|----|-----------|
| Gather requirements | R | I | C | A | I |
| Review & analyze | R | C | I | C | I |
| Clarify ambiguities | R | C | A | C | C |
| Identify business rules | R | I | C | A | I |
| Create RTM | R | I | - | - | - |
| Risk assessment | R | A | I | I | C |
| Document analysis | R | C | I | I | I |
| Approve analysis | I | R | A | C | C |

Legend: R=Responsible, A=Accountable, C=Consulted, I=Informed

---

## 8. Effort Estimation

### 8.1 Time Estimation Guidelines

| Input Size | Analysis Effort | Deliverable |
|------------|-----------------|-------------|
| 1-5 User Stories | 2-4 hours | Quick analysis |
| 6-15 User Stories | 1-2 days | Standard analysis |
| 16-30 User Stories | 3-5 days | Detailed analysis |
| Epic/Full Module | 1-2 weeks | Comprehensive analysis |

### 8.2 Effort Breakdown

| Activity | % of Total | Notes |
|----------|------------|-------|
| Gather & Review | 20% | Initial collection |
| Clarification | 25% | Meetings, Q&A |
| Analysis & Documentation | 35% | Main work |
| Review & Revision | 15% | QA/PO review |
| Sign-off | 5% | Final approval |

---

## 9. Common Pitfalls & Mitigations

### 9.1 Pitfall Prevention Guide

| Pitfall | Symptoms | Prevention | Mitigation |
|---------|----------|------------|------------|
| **Scope Creep** | Adding requirements mid-analysis | Clear scope document | Reference scope agreement |
| **Analysis Paralysis** | Over-analyzing simple items | Timeboxing | Use standard templates |
| **Missing Stakeholders** | Key requirements missed | Stakeholder checklist | Conduct stakeholder analysis |
| **Vague Requirements** | Non-testable specs | Review against checklist | Request clarification |
| **Incomplete Risk Assessment** | Surprised by issues | Structured risk workshop | Re-assess at each phase |
| **Late Dependency Discovery** | Blocked testing | Integration mapping | Early tech coordination |

### 9.2 Anti-Patterns to Avoid

```
❌ ANALYSIS ANTI-PATTERNS
═══════════════════════════════════════════════════════════════════════════════

1. "I'll figure it out later"
   └── Always clarify upfront
   └── Document all assumptions

2. "The spec says it, so it's done"
   └── Challenge ambiguous specs
   └── Verify with stakeholders

3. "Testing can't start until perfect"
   └── Start with known requirements
   └── Progressive elaboration

4. "One meeting solves everything"
   └── Continuous communication
   └── Regular check-ins

5. "Requirements don't change"
   └── Embrace change management
   └── Document all changes

```

---

## 10. Templates Repository

### 10.1 Quick Reference Templates

#### Template A: Requirements Summary Table

```markdown
## Requirements Summary

| Req ID | Requirement | Type | Priority | Status | Testable |
|--------|-------------|------|----------|--------|----------|
| REQ-001 | [Description] | Functional | P0 | Ready | Yes |
```

#### Template B: Clarification Question

```markdown
**Q-[NUMBER]:** [Question]
**Related Req:** [ID]
**Asked By:** [Name]
**Date:** [Date]
**Answer:** [Response]
**Status:** Open/Closed
```

#### Template C: Risk Entry

```markdown
**Risk [ID]:** [Description]
**Score:** [P × I = Score]
**Mitigation:** [Strategy]
**Owner:** [Name]
**Status:** [Active/Mitigated]
```

### 10.2 Export Formats

All templates available in:
- Markdown format (for documentation)
- Excel format (for sharing with stakeholders)
- Confluence format (for team wiki)

---

## 11. Integration with Other Workflows

### 11.1 Handoff to Test Case Design

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                        WORKFLOW HANDOFF                                       │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  FROM: Requirement Analysis                                                 │
│  TO: Test Case Design                                                       │
│                                                                             │
│  INPUTS REQUIRED:                                                           │
│  ├── ✅ Analysis Result Document                                            │
│  ├── ✅ Requirements Traceability Matrix                                    │
│  ├── ✅ Business Rules Register                                             │
│  ├── ✅ Risk Register                                                       │
│  └── ✅ Clarification Log                                                   │
│                                                                             │
│  ACCEPTANCE CRITERIA:                                                       │
│  ├── All requirements clarified                                             │
│  ├── RTM has 100% coverage                                                  │
│  ├── Risks identified and mitigation planned                                │
│  └── Analysis approved by QA Lead                                           │
│                                                                             │
│  COMMON HANDOFF ISSUES:                                                     │
│  ├── Missing clarification on edge cases                                    │
│  ├── Business rules not detailed enough                                     │
│  └── Risk assessment too superficial                                        │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 12. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA/BA Engineer | Initial workflow document |
