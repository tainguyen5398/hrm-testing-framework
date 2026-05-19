# Template: Code Review Report

## Document Metadata

| Field | Value |
|-------|-------|
| Document ID | REVIEW-[Module]-[YYYYMMDD] |
| Version | 1.0 |
| Author | Reviewer |
| Date | YYYY-MM-DD |
| Status | Draft/In Review/Completed |
| Module | [Module Name] |
| Review Type | [Code/Design/Document] |

---

## Executive Summary

[Brief overview of review results. Include scope, findings summary, and overall assessment.]

**Review Summary:**
- Total Items Reviewed: [N]
- Passed: [N]
- Issues Found: [N]
- Critical Issues: [N]
- Major Issues: [N]
- Minor Issues: [N]
- Suggestions: [N]

---

## 1. Review Details

### 1.1 Review Information

| Field | Value |
|-------|-------|
| **Review ID** | REVIEW-[ID] |
| **Reviewer** | [Name] |
| **Author** | [Author Name] |
| **Review Date** | YYYY-MM-DD |
| **Review Type** | Code/Design/Document |
| **Scope** | [Files/Sections Reviewed] |

### 1.2 Files Reviewed

| # | File Path | File Type | Lines | Status |
|---|-----------|-----------|-------|--------|
| 1 | [Path] | Java | [N] | Reviewed |
| 2 | [Path] | Java | [N] | Reviewed |

---

## 2. Review Criteria

### 2.1 Code Quality Criteria

| Criteria | Weight | Score | Comments |
|----------|--------|-------|----------|
| Code Structure | 20% | [X]/10 | |
| Naming Conventions | 15% | [X]/10 | |
| Error Handling | 15% | [X]/10 | |
| Documentation | 15% | [X]/10 | |
| Testability | 15% | [X]/10 | |
| Performance | 10% | [X]/10 | |
| Security | 10% | [X]/10 | |

### 2.2 Overall Quality Score

| Metric | Score |
|--------|-------|
| Weighted Score | [X]/10 |
| Grade | [A/B/C/D/F] |
| Status | [Approved/Conditionally Approved/Rejected] |

---

## 3. Findings Summary

### 3.1 Issues by Severity

| Severity | Count | Open | Resolved |
|----------|-------|------|----------|
| Critical | [N] | [N] | [N] |
| Major | [N] | [N] | [N] |
| Minor | [N] | [N] | [N] |
| Suggestion | [N] | [N] | [N] |

### 3.2 Issues by Category

| Category | Count |
|----------|-------|
| Code Structure | [N] |
| Naming Conventions | [N] |
| Error Handling | [N] |
| Documentation | [N] |
| Testability | [N] |
| Performance | [N] |
| Security | [N] |
| Other | [N] |

---

## 4. Detailed Findings

### 4.1 Critical Issues

Critical issues must be resolved before approval.

#### ISSUE-001: [Issue Title]

| Field | Value |
|-------|-------|
| **Issue ID** | ISSUE-001 |
| **Severity** | Critical |
| **Category** | [Category] |
| **File** | [File Path] |
| **Line** | [Line Number] |
| **Status** | Open/Resolved |
| **Assignee** | [Author] |

**Description:**
[Detailed description of the issue]

**Current Code:**
```java
// [Code snippet]
```

**Recommended Fix:**
```java
// [Fixed code snippet]
```

**Impact:**
[What happens if not fixed]

**Resolution:**
[How it was resolved / to be resolved]

---

### 4.2 Major Issues

Major issues should be addressed but may not block approval.

#### ISSUE-002: [Issue Title]

| Field | Value |
|-------|-------|
| **Issue ID** | ISSUE-002 |
| **Severity** | Major |
| **Category** | [Category] |
| **File** | [File Path] |
| **Line** | [Line Number] |
| **Status** | Open/Resolved |
| **Assignee** | [Author] |

**Description:**
[Description of the issue]

**Current Code:**
```java
// [Code snippet]
```

**Recommended Fix:**
```java
// [Fixed code snippet]
```

**Impact:**
[Impact if not addressed]

**Resolution:**
[Resolution]

---

### 4.3 Minor Issues

Minor issues are recommendations for improvement.

#### ISSUE-003: [Issue Title]

| Field | Value |
|-------|-------|
| **Issue ID** | ISSUE-003 |
| **Severity** | Minor |
| **Category** | [Category] |
| **File** | [File Path] |
| **Line** | [Line Number] |
| **Status** | Open/Resolved |

**Description:**
[Description]

**Current Code:**
```java
// [Code snippet]
```

**Recommended Improvement:**
```java
// [Improved code snippet]
```

**Resolution:**
[Resolution]

---

### 4.4 Suggestions

Suggestions for future improvements.

#### SUGGEST-001: [Suggestion]

| Field | Value |
|-------|-------|
| **Suggestion ID** | SUGGEST-001 |
| **Category** | [Category] |
| **File** | [File Path] |

**Description:**
[Brief description of the suggestion]

**Recommendation:**
[What could be improved]

---

## 5. Positive Findings

### 5.1 Strengths

| # | Strength | Location |
|---|----------|----------|
| 1 | [Strength description] | [File/Location] |
| 2 | [Strength description] | [File/Location] |

### 5.2 Best Practices Observed

- [ ] [Best practice 1]
- [ ] [Best practice 2]
- [ ] [Best practice 3]

---

## 6. Compliance Checklist

### 6.1 Coding Standards Compliance

| Standard | Status | Notes |
|----------|--------|-------|
| Naming Conventions | ✅/❌ | [Notes] |
| Code Formatting | ✅/❌ | [Notes] |
| Comment Standards | ✅/❌ | [Notes] |
| Error Handling | ✅/❌ | [Notes] |
| Logging Standards | ✅/❌ | [Notes] |

### 6.2 Framework Compliance

| Requirement | Status | Notes |
|-------------|--------|-------|
| POM Structure | ✅/❌ | [Notes] |
| Base Class Usage | ✅/❌ | [Notes] |
| Helper Usage | ✅/❌ | [Notes] |
| Wait Strategies | ✅/❌ | [Notes] |
| Locator Strategy | ✅/❌ | [Notes] |

### 6.3 Documentation Compliance

| Requirement | Status | Notes |
|-------------|--------|-------|
| Javadoc Comments | ✅/❌ | [Notes] |
| README Updated | ✅/❌ | [Notes] |
| Change Log | ✅/❌ | [Notes] |

---

## 7. Review Statistics

### 7.1 Time Spent

| Activity | Time |
|----------|------|
| Initial Review | [X] hours |
| Discussion | [X] hours |
| Follow-up Review | [X] hours |
| **Total** | [X] hours |

### 7.2 Review Coverage

| Metric | Value |
|--------|-------|
| Files Reviewed | [N] |
| Lines of Code | [N] |
| Test Cases Covered | [N] |
| Code Coverage | [X]% |

---

## 8. Recommendations

### 8.1 Must Fix (Blocking)

These items must be resolved before approval:

1. [Issue-001 description]
2. [Issue-002 description]

### 8.2 Should Fix (Non-Blocking)

These items should be addressed but do not block approval:

1. [Issue description]
2. [Issue description]

### 8.3 Nice to Have

Optional improvements for future consideration:

1. [Suggestion]
2. [Suggestion]

---

## 9. Approval

### 9.1 Review Decision

| Decision | Status |
|----------|--------|
| **Overall Status** | [Approved / Conditionally Approved / Rejected] |

### 9.2 Conditions (if applicable)

[If conditionally approved, list conditions]

### 9.3 Sign-off

| Role | Name | Date | Signature | Decision |
|------|------|------|-----------|----------|
| Author | | | | Acknowledged |
| Reviewer | | | | Approved |
| QA Lead | | | | Approved |

---

## 10. Follow-up Actions

### 10.1 Action Items

| # | Action | Owner | Due Date | Status |
|---|--------|-------|----------|--------|
| 1 | [Action description] | [Name] | YYYY-MM-DD | Open/Completed |

### 10.2 Re-review Schedule

| Review | Scheduled Date | Status |
|--------|----------------|--------|
| Re-review | [Date] | Scheduled |

---

## 11. Appendices

### Appendix A: File Checklist

| File | Reviewed | Issues | Status |
|------|----------|--------|--------|
| [File 1] | ✅ | [N] | [OK/Issues] |
| [File 2] | ✅ | [N] | [OK/Issues] |

### Appendix B: Glossary

| Term | Definition |
|------|------------|
| Critical | Must fix - blocks approval |
| Major | Should fix - recommended |
| Minor | Nice to fix - optional |
| Suggestion | Future improvement |

---

## 12. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | YYYY-MM-DD | [Author] | Initial report |
