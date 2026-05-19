# Workflow: Code Review

## 1. Overview

### 1.1 Purpose
Workflow review code (Code Review) là bước thứ năm trong QA process, đảm bảo tất cả code được generate đạt chất lượng cao nhất trước khi đưa vào production. Workflow này hướng dẫn QA Engineer thực hiện code review có hệ thống để đảm bảo:

- **Code Quality**: Tất cả code đạt quality standards
- **Best Practices**: Tuân thủ coding standards và framework rules
- **Bug Prevention**: Phát hiện và fix bugs sớm
- **Knowledge Sharing**: Chia sẻ kiến thức trong team
- **Continuous Improvement**: Cải thiện process liên tục

### 1.2 Objectives

| # | Objective | Success Criteria |
|---|-----------|-----------------|
| 1 | Quality Gate | 100% code reviewed before merge |
| 2 | Standards Compliance | 0 critical violations |
| 3 | Bug Detection | At least 80% bugs found in review |
| 4 | Constructive Feedback | All comments actionable |
| 5 | Timely Review | Review completed within 24 hours |

### 1.3 Workflow Position

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                     AI AUTOMATION PIPELINE - WORKFLOW 5                      │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   INPUT:                                                                    │
│   ├── Generated Source Code (từ Workflow 4: Code Generation)                │
│   ├── coding-standards.md (từ AI Context)                                  │
│   ├── framework-rules.md (từ AI Context)                                   │
│   └── review-checklist.md (this workflow)                                  │
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │                      CODE REVIEW                                 │     │
│   │  ┌─────────────────────────────────────────────────────────┐   │     │
│   │  │ 1. Prepare for Review                                 │   │     │
│   │  │ 2. Conduct Code Review                                │   │     │
│   │  │ 3. Document Findings                                 │   │     │
│   │  │ 4. Track & Resolve Issues                           │   │     │
│   │  │ 5. Approve & Sign-off                              │   │     │
│   │  └─────────────────────────────────────────────────────────┘   │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│                                                                             │
│   OUTPUT:                                                                   │
│   ├── review-report.md (Code Review Report)                                 │
│   ├── review-comments.md (Detailed comments)                                │
│   └── approved-code.md (Sign-off confirmation)                              │
│                                                                             │
│   ┌─────────────────────────────────────────────────────────────────┐     │
│   │              ▶ NEXT WORKFLOW: Reporting                           │     │
│   └─────────────────────────────────────────────────────────────────┘     │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 2. Inputs & Prerequisites

### 2.1 Required Inputs

| # | Input | Format | Source | Priority |
|---|-------|--------|--------|----------|
| 1 | Generated Source Code | Java files | Workflow 4: Code Generation | Required |
| 2 | Coding Standards | Document | AI Context | Required |
| 3 | Framework Rules | Document | AI Context | Required |
| 4 | Naming Convention | Document | AI Context | Required |
| 5 | Locator Strategy | Document | AI Context | Required |
| 6 | API Rules | Document | AI Context | Required |
| 7 | Previous Reviews | Reports | Historical | Recommended |

### 2.2 Prerequisites Checklist

```
PRE-WORKFLOW CHECKLIST
═══════════════════════════════════════════════════════════════════════════════

□ Code is ready for review (compiles successfully)
□ All test cases executed at least once
□ No blocking issues in execution
□ Review environment accessible
□ Reviewers assigned and available
□ Review tools configured (GitHub PR, GitLab MR, or IDE)
□ Communication channel established with author

```

---

## 3. Review Principles

### 3.1 Core Principles

```
CODE REVIEW CORE PRINCIPLES
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│                           7 PRINCIPLES OF CODE REVIEW                       │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  1. RESPECT                                                              │
│     └── Review the code, not the author                                   │
│     └── Be constructive, not destructive                                   │
│                                                                             │
│  2. CLARITY                                                               │
│     └── Comments should be specific and actionable                         │
│     └── Explain WHY, not just WHAT                                        │
│                                                                             │
│  3. CONSISTENCY                                                           │
│     └── Apply same standards to all code                                   │
│     └── Reference coding standards consistently                             │
│                                                                             │
│  4. BALANCE                                                               │
│     └── Focus on critical issues, not nitpicks                             │
│     └── Prioritize: Security > Correctness > Performance > Style           │
│                                                                             │
│  5. TIMELINESS                                                            │
│     └── Review promptly (within 24 hours)                                 │
│     └── Don't block on minor issues                                       │
│                                                                             │
│  6. COMPLETENESS                                                           │
│     └── Review all aspects: logic, design, testing                        │
│     └── Don't skip any layer or component                                 │
│                                                                             │
│  7. LEARNING                                                              │
│     └── Review is a learning opportunity for both parties                  │
│     └── Share knowledge and best practices                                 │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 3.2 Review Focus Areas

```
REVIEW FOCUS AREAS
═══════════════════════════════════════════════════════════════════════════════

┌────────────────────────────────────────────────────────────────────────────┐
│ REVIEW PRIORITY MATRIX                                                     │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  🔴 CRITICAL (Must Fix)                                                   │
│  ├── Security vulnerabilities                                             │
│  ├── Data integrity issues                                                │
│  ├── Critical bugs that cause failures                                    │
│  └── Violations of mandatory framework rules                              │
│                                                                             │
│  🟠 HIGH (Should Fix)                                                     │
│  ├── Logic errors                                                         │
│  ├── Missing error handling                                               │
│  ├── Inefficient algorithms                                              │
│  └── Missing test coverage                                               │
│                                                                             │
│  🟡 MEDIUM (Consider Fixing)                                              │
│  ├── Code duplication                                                    │
│  ├── Unclear naming                                                      │
│  ├── Missing comments                                                    │
│  └── Suboptimal patterns                                                 │
│                                                                             │
│  🟢 LOW (Nice to Have)                                                   │
│  ├── Style preferences                                                   │
│  ├── Minor optimizations                                                │
│  └── Documentation improvements                                          │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

---

## 4. Review Categories

### 4.1 Code Structure Review

```markdown
## Code Structure Review Checklist

### 4.1.1 Package & File Organization

| Check | Criteria | Status |
|-------|----------|--------|
| □ | Package structure follows convention | |
| □ | Files organized by module | |
| □ | No circular dependencies | |
| □ | Proper package-private vs public | |

### 4.1.2 Class Structure

| Check | Criteria | Status |
|-------|----------|--------|
| □ | Class has single responsibility | |
| □ | No god classes (>500 lines) | |
| □ | Proper inheritance hierarchy | |
| □ | Interfaces used appropriately | |

### 4.1.3 Method Structure

| Check | Criteria | Status |
|-------|----------|--------|
| □ | Methods are small (<50 lines) | |
| □ | Single level of abstraction | |
| □ | No deeply nested code (>3 levels) | |
| □ | Parameters reasonable (<4) | |

```

### 4.2 Coding Standards Review

```markdown
## Coding Standards Review Checklist

### 4.2.1 Naming Conventions

| Element | Convention | Example | Compliant? |
|---------|------------|---------|------------|
| Class | PascalCase | LoginPage | |
| Method | camelCase | clickLoginButton | |
| Variable | camelCase | validUsername | |
| Constant | UPPER_SNAKE | MAX_RETRY | |
| Locator | PREFIX_ELEMENT | TXT_USERNAME | |

### 4.2.2 Formatting

| Check | Criteria | Status |
|-------|----------|--------|
| □ | 4 spaces indentation (no tabs) | |
| □ | Max 120 characters per line | |
| □ | K&R brace style | |
| □ | Blank lines between logical groups | |
| □ | Imports organized alphabetically | |

### 4.2.3 Documentation

| Check | Criteria | Status |
|-------|----------|--------|
| □ | Javadoc for public methods | |
| □ | No TODO/FIXME comments | |
| □ | Complex logic has comments | |
| □ | README updated if needed | |

```

### 4.3 Framework Compliance Review

```markdown
## Framework Compliance Review Checklist

### 4.3.1 Page Object Model (POM)

| Check | Criteria | Status |
|-------|----------|--------|
| □ | 3-layer structure: Elements, Actions, Page | |
| □ | No business logic in Elements | |
| □ | Actions use helpers appropriately | |
| □ | Page class is facade only | |

### 4.3.2 Base Classes

| Check | Criteria | Status |
|-------|----------|--------|
| □ | BasePage used for all pages | |
| □ | BaseUI used for all tests | |
| □ | No direct WebDriver calls in pages | |
| □ | Delegation to helpers used | |

### 4.3.3 Helper Utilities

| Check | Criteria | Status |
|-------|----------|--------|
| □ | ActionHelper for user actions | |
| □ | WaitHelper for waits | |
| □ | ElementHelper for queries | |
| □ | VerificationHelper for assertions | |
| □ | No duplicate helper logic | |

```

### 4.4 Test Quality Review

```markdown
## Test Quality Review Checklist

### 4.4.1 Test Structure

| Check | Criteria | Status |
|-------|----------|--------|
| □ | AAA pattern used | |
| □ | Test independence | |
| □ | Proper setup/teardown | |
| □ | Descriptive test names | |

### 4.4.2 Test Coverage

| Check | Criteria | Status |
|-------|----------|--------|
| □ | All test cases from design automated | |
| □ | Positive cases covered | |
| □ | Negative cases covered | |
| □ | Boundary cases covered | |
| □ | Error scenarios covered | |

### 4.4.3 Test Data

| Check | Criteria | Status |
|-------|----------|--------|
| □ | Data externalized (not hardcoded) | |
| □ | Test data files properly structured | |
| □ | Unique data for each run | |
| □ | Cleanup handled properly | |

```

### 4.5 Security Review

```markdown
## Security Review Checklist

### 4.5.1 Credentials & Secrets

| Check | Criteria | Status |
|-------|----------|--------|
| □ | No hardcoded passwords | |
| □ | Credentials in config files only | |
| □ | Config files not committed to repo | |
| □ | Sensitive data logged | |

### 4.5.2 Input Validation

| Check | Criteria | Status |
|-------|----------|--------|
| □ | User inputs validated | |
| □ | SQL injection prevention | |
| □ | XSS prevention | |
| □ | File path validation | |

### 4.5.3 API Security

| Check | Criteria | Status |
|-------|----------|--------|
| □ | Authentication tokens secured | |
| □ | HTTPS used for API calls | |
| □ | No sensitive data in URLs | |
| □ | Proper error messages (no stack traces) | |

```

### 4.6 Performance Review

```markdown
## Performance Review Checklist

### 4.6.1 Wait Strategies

| Check | Criteria | Status |
|-------|----------|--------|
| □ | Explicit waits used (not implicit) | |
| □ | No Thread.sleep() | |
| □ | Waits have timeouts | |
| □ | Waits are not too long | |

### 4.6.2 Resource Management

| Check | Criteria | Status |
|-------|----------|--------|
| □ | Browser closed after tests | |
| □ | No memory leaks | |
| □ | Connections properly closed | |
| □ | Large objects dereferenced | |

### 4.6.3 Test Execution

| Check | Criteria | Status |
|-------|----------|--------|
| □ | Tests run in reasonable time | |
| □ | Parallel execution supported | |
| □ | No unnecessary navigation | |
| □ | Efficient locators used | |

```

---

## 5. Review Process Steps

### 5.1 Step 1: Prepare for Review

```
STEP 1: PREPARE FOR REVIEW
═══════════════════════════════════════════════════════════════════════════════

ACTIVITIES:

1.1 CODE AUTHOR SUBMITS
├── Ensure code compiles
├── Run all tests locally
├── Update any related documentation
├── Create pull request/merge request
└── Fill out PR description template

1.2 REVIEWER PREPARATION
├── Read PR description
├── Understand the scope
├── List files to review
├── Note any areas of concern
└── Schedule review time

1.3 PR TEMPLATE

```markdown
## Pull Request Description

### Summary
[Brief description of changes]

### Scope
- Files changed: [list]
- Lines changed: [count]
- Tests added: [count]

### Type of Change
- [ ] New feature
- [ ] Bug fix
- [ ] Refactoring
- [ ] Documentation

### Testing Done
- [ ] Unit tests pass
- [ ] Integration tests pass
- [ ] Manual testing done
- [ ] Performance tested

### Related Issues
- Related to: [JIRA/Ticket]

### Checklist
- [ ] Code follows coding standards
- [ ] Tests added/updated
- [ ] Documentation updated
- [ ] No new warnings
```

```

### 5.2 Step 2: Conduct Code Review

```
STEP 2: CONDUCT CODE REVIEW
═══════════════════════════════════════════════════════════════════════════════

REVIEW TECHNIQUE:

2.1 FIRST PASS - Quick Scan (5-10 min)
├── Check overall structure
├── Verify file organization
├── Look for obvious issues
└── Note any concerns for deep dive

2.2 SECOND PASS - Detailed Review (20-30 min)
├── Review each file systematically
├── Check against all review categories
├── Test edge cases mentally
├── Verify test coverage
└── Check for security issues

2.3 THIRD PASS - Focus Areas (10-15 min)
├── Complex logic
├── Areas with high change frequency
├── New dependencies
├── Potential bugs
└── Performance concerns

REVIEW TOOLS:

┌────────────────────────────────────────────────────────────────────────────┐
│ TOOL USAGE FOR CODE REVIEW                                                  │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  GitHub PR Review                                                         │
│  ├── Line comments for specific issues                                     │
│  ├── Suggestion mode for proposed changes                                 │
│  └── Approve/Request Changes status                                       │
│                                                                             │
│  IDE-based Review                                                         │
│  ├── IntelliJ/Eclipse Code Inspection                                     │
│  ├── SonarLint integration                                               │
│  └── Compare with baseline                                                │
│                                                                             │
│  Static Analysis Tools                                                    │
│  ├── Checkstyle for formatting                                           │
│  ├── SpotBugs for issues                                                │
│  ├── PMD for code quality                                               │
│  └── SonarQube for comprehensive analysis                                │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

```

### 5.3 Step 3: Document Findings

```
STEP 3: DOCUMENT FINDINGS
═══════════════════════════════════════════════════════════════════════════════

COMMENT TEMPLATES:

🟢 Nitpick/Question (Non-blocking)
```
[Nitpick] Optional: Consider renaming `variableName` to `moreDescriptiveName`
for better readability.
```

🟡 Suggestion (Consider addressing)
```
[Suggestion] This could be simplified using Java Streams:
```java
// Current:
for (String item : list) {
    if (item.startsWith("A")) {
        result.add(item);
    }
}

// Suggested:
List<String> result = list.stream()
    .filter(s -> s.startsWith("A"))
    .collect(Collectors.toList());
```
```

🔴 Issue (Should fix before merge)
```
[Issue] Missing null check before using `element`
Location: EmployeePage.java:45

The code assumes `element` is never null, but it could be null if the
locator doesn't match any element. This could cause NullPointerException.

Suggestion: Add null check:
```java
if (element != null) {
    // existing logic
} else {
    Log.warn("Element not found for locator: " + locator);
}
```
```

🔴 Critical (Must fix before merge)
```
[CRITICAL] Security Issue - Hardcoded credentials
Location: LoginTests.java:78

Credentials are hardcoded in test file. This is a security risk and
violates framework rules.

Required: Move to config file and reference via ConfigHelper:
```java
private static final String USERNAME = ConfigHelper.getUsername();
private static final String PASSWORD = ConfigHelper.getPassword();
```
```

```

### 5.4 Step 4: Track & Resolve Issues

```markdown
## Issue Tracking Template

### Review Issue Register

| Issue ID | Type | Severity | File | Line | Status | Resolved By | Date |
|----------|------|----------|------|------|--------|-------------|------|
| REV-001 | Security | Critical | LoginTests.java | 78 | Open | | |
| REV-002 | Bug | High | EmployeePage.java | 145 | Open | | |
| REV-003 | Style | Low | LoginPage.java | 56 | Open | | |
| REV-004 | Suggestion | Medium | EmployeeActions.java | 203 | Resolved | John Doe | 2026-05-11 |

### Issue Details

**REV-001: Hardcoded Credentials**
| Field | Value |
|-------|-------|
| Severity | 🔴 Critical |
| Category | Security |
| File | LoginTests.java |
| Line | 78 |
| Description | Username and password hardcoded in test method |
| Impact | Security risk, violates framework rules |
| Required Action | Move to config file |
| Author Response | Will fix |
| Status | Open |

**REV-002: Missing Null Check**
| Field | Value |
|-------|-------|
| Severity | 🟠 High |
| Category | Bug |
| File | EmployeePage.java |
| Line | 145 |
| Description | No null check before accessing element |
| Impact | Potential NullPointerException |
| Required Action | Add null check |
| Author Response | Will fix |
| Status | Open |

```

### 5.5 Step 5: Approve & Sign-off

```
STEP 5: APPROVE & SIGN-OFF
═══════════════════════════════════════════════════════════════════════════════

APPROVAL CRITERIA:

┌────────────────────────────────────────────────────────────────────────────┐
│ APPROVAL DECISION MATRIX                                                   │
├────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  ✅ APPROVED (All conditions met)                                          │
│  ├── No critical issues                                                   │
│  ├── No high-priority issues unresolved                                  │
│  ├── All required changes addressed                                       │
│  ├── Tests pass consistently                                              │
│  └── Code follows standards                                               │
│                                                                             │
│  ⚠️ REQUEST CHANGES (Issues to address)                                   │
│  ├── Critical issues present                                             │
│  ├── High-priority issues unresolved                                     │
│  └── Author needs to make revisions                                      │
│                                                                             │
│  🔄 APPROVE WITH COMMENTS (Non-blocking feedback)                         │
│  ├── Minor issues present (nitpicks)                                     │
│  ├── Suggestions for improvement                                         │
│  └── Author can merge, but encouraged to address comments                 │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘

SIGN-OFF TEMPLATE:

```markdown
## Code Review Sign-off

### Review Information
| Field | Value |
|-------|-------|
| PR Number | #123 |
| Author | John Doe |
| Reviewer | Jane Smith |
| Review Date | 2026-05-11 |
| Files Reviewed | 5 |
| Lines Changed | 234 |

### Issue Summary
| Severity | Found | Resolved | Remaining |
|----------|-------|----------|-----------|
| Critical | 1 | 1 | 0 |
| High | 2 | 2 | 0 |
| Medium | 3 | 2 | 1 |
| Low | 5 | 4 | 1 |

### Decision
**STATUS: ✅ APPROVED**

### Comments for Author
- Great job on the refactoring!
- Please address the remaining nitpicks when you have time
- Consider adding more unit tests for EmployeeActions class

### Reviewer Sign-off
| Role | Name | Signature | Date |
|------|------|-----------|------|
| Reviewer | Jane Smith | [Signed] | 2026-05-11 |

```

---

## 6. Review Metrics

### 6.1 Review Metrics Template

```markdown
## Review Metrics

### Process Metrics

| Metric | Target | Actual | Status |
|--------|--------|--------|--------|
| Review Turnaround Time | < 24 hours | [value] | |
| Comments per Review | 10-30 | [value] | |
| Issues Found | Varies | [value] | |
| Issues Resolved | > 90% | [value] | |
| Critical Issues | 0 | [value] | |

### Quality Metrics

| Metric | Target | Actual | Status |
|--------|--------|--------|--------|
| Code Coverage | > 80% | [value] | |
| Standards Compliance | 100% | [value] | |
| Test Pass Rate | > 95% | [value] | |
| Bug Escape Rate | < 10% | [value] | |

### Team Metrics

| Metric | Target | Actual | Status |
|--------|--------|--------|--------|
| Reviews Completed | All PRs | [value] | |
| Average Review Time | < 1 hour | [value] | |
| Knowledge Sharing | Documented | [value] | |

```

---

## 7. Best Practices

### 7.1 For Reviewers

```
BEST PRACTICES FOR REVIEWERS
═══════════════════════════════════════════════════════════════════════════════

✅ DO:
├── Review promptly - don't let PRs sit
├── Be specific and constructive
├── Explain the "why" behind comments
├── Acknowledge good work
├── Focus on critical issues first
├── Use code suggestions when possible
├── Ask questions if unclear
└── Consider the author's perspective

❌ DON'T:
├── Block on minor style issues
├── Be overly critical or personal
├── Suggest major rewrites without justification
├── Leave vague comments
├── Rush through reviews
├── Skip review areas
└── Approve code you don't understand

```

### 7.2 For Authors

```
BEST PRACTICES FOR AUTHORS
═══════════════════════════════════════════════════════════════════════════════

✅ DO:
├── Keep PRs small and focused
├── Write clear PR descriptions
├── Self-review before requesting
├── Respond to comments promptly
├── Ask for clarification if needed
├── Accept feedback gracefully
├── Thank reviewers for their time
└── Document decisions in code

❌ DON'T:
├── Submit large, unfocused PRs
├── Take comments personally
├── Argue without justification
├── Dismiss feedback without consideration
├── Ignore review comments
├── Submit untested code
└── Merge without approval

```

---

## 8. Review Report Template

```markdown
# Code Review Report

## Document Metadata

| Field | Value |
|-------|-------|
| Report ID | CRR-2026-001 |
| Date | 2026-05-11 |
| Reviewer | [Name] |
| Author | [Name] |
| PR Number | #123 |
| Status | Approved/Conditional/Rejected |

## Executive Summary

[Brief overview of review scope and outcome]

## Review Scope

### Files Reviewed

| File | Lines Changed | Complexity | Issues Found |
|------|---------------|------------|-------------|
| LoginPage.java | 150 | Medium | 2 |
| LoginTests.java | 200 | High | 3 |
| EmployeePage.java | 180 | Medium | 1 |
| Total | 530 | - | 6 |

### Review Coverage

| Area | Coverage | Findings |
|------|----------|----------|
| Code Structure | 100% | 1 issue |
| Coding Standards | 100% | 2 issues |
| Framework Compliance | 100% | 0 issues |
| Test Quality | 100% | 2 issues |
| Security | 100% | 1 critical |
| Performance | 100% | 0 issues |

## Findings Summary

### By Severity

| Severity | Count | Resolved | Open |
|----------|-------|----------|------|
| Critical | 1 | 1 | 0 |
| High | 2 | 2 | 0 |
| Medium | 2 | 1 | 1 |
| Low | 1 | 0 | 1 |
| **Total** | **6** | **4** | **2** |

### By Category

| Category | Count | Resolved | Open |
|----------|-------|----------|------|
| Security | 1 | 1 | 0 |
| Logic | 2 | 2 | 0 |
| Style | 1 | 0 | 1 |
| Test Coverage | 1 | 1 | 0 |
| Documentation | 1 | 0 | 1 |

## Detailed Findings

### Critical Issues

[Details of any critical issues and resolution]

### Remaining Issues

| Issue | Category | Severity | Action Required |
|-------|----------|----------|----------------|
| Variable naming | Style | Low | Consider renaming |
| Comment formatting | Documentation | Low | Update format |

## Recommendations

1. [Recommendation 1]
2. [Recommendation 2]
3. [Recommendation 3]

## Conclusion

**Overall Assessment:** [Assessment]

**Recommendation:** [Approved/Conditional/Rejected]

## Sign-off

| Role | Name | Date | Signature |
|------|------|------|-----------|
| Reviewer | | | |
| Author | | | |

```

---

## 9. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer | Initial workflow document |
