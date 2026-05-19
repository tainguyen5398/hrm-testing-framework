# Template: Test Data Design

## Document Metadata

| Field | Value |
|-------|-------|
| Document ID | TD-[Module]-[YYYYMMDD] |
| Version | 1.0 |
| Author | QA Engineer |
| Date | YYYY-MM-DD |
| Status | Draft/Approved |
| Module | [Module Name] |
| Related Test Suite | TC-[Module]-[Date] |

---

## 1. Overview

### 1.1 Purpose

This document defines the test data requirements, design, and management strategy for the [Module] test suite. It provides a comprehensive reference for all test data needed to execute test cases effectively.

### 1.2 Test Data Strategy

| Strategy | Description | Usage |
|----------|-------------|-------|
| Static Data | Pre-defined values | Valid credentials, expected messages |
| Dynamic Data | Generated at runtime | Unique emails, random values |
| Synthetic Data | Manually created | Complex scenarios, edge cases |
| Production-like | Realistic data | UAT, E2E testing |

---

## 2. Test Data Requirements

### 2.1 Data Categories

#### 2.1.1 Master Data

| Data Type | Description | Volume | Refresh Rate |
|-----------|-------------|--------|--------------|
| User Accounts | Test user credentials | 20 | Weekly |
| Departments | Organization units | 10 | Monthly |
| Employee Records | Test employee data | 100 | Daily |
| Leave Types | Leave categories | 5 | Static |

#### 2.1.2 Transaction Data

| Data Type | Description | Volume | Refresh Rate |
|-----------|-------------|--------|--------------|
| Leave Requests | Leave submissions | 50 | Daily |
| Attendance Records | Clock in/out logs | 200 | Daily |
| Payroll Records | Salary calculations | 30 | Monthly |

---

## 3. Data Dictionary

### 3.1 User Accounts

| User Type | Username | Password | Role | Permissions |
|-----------|----------|----------|------|--------------|
| Admin | admin | Admin@123 | Administrator | Full access |
| Manager | manager | Manager@123 | Manager | Team management |
| Employee | employee | Employee@123 | Employee | Self-service |
| HR | hr | HR@123 | HR | Employee mgmt |
| Viewer | viewer | Viewer@123 | Viewer | Read-only |

### 3.2 Employee Data Fields

| Field | Type | Length | Validation | Example |
|-------|------|--------|------------|---------|
| Employee ID | String | 10 | Alphanumeric | EMP001 |
| Full Name | String | 100 | Required, 2-100 chars | John Doe |
| Email | String | 255 | Valid email format | john.doe@test.com |
| Phone | String | 20 | Valid phone format | +84-123-456-789 |
| Department | String | 50 | From list | Engineering |
| Position | String | 100 | Required | Software Engineer |
| Join Date | Date | - | Not future | 2024-01-15 |
| Status | Enum | - | ACTIVE/INACTIVE | ACTIVE |

### 3.3 Leave Request Fields

| Field | Type | Validation | Example |
|-------|------|------------|---------|
| Leave Type | Enum | Required | ANNUAL |
| Start Date | Date | Not past | 2026-06-01 |
| End Date | Date | >= Start Date | 2026-06-05 |
| Days | Number | 1-14 | 5 |
| Reason | String | 0-500 chars | Family vacation |
| Status | Enum | PENDING/APPROVED/REJECTED | PENDING |

---

## 4. Test Data Matrix

### 4.1 Valid Test Data

| Test Case ID | Data Category | Field | Value | Expected Result |
|--------------|---------------|-------|-------|-----------------|
| TC-001 | Create | Name | John Doe | Success |
| TC-002 | Create | Email | john.doe@test.com | Success |
| TC-003 | Create | Phone | +84-123-456-789 | Success |
| TC-004 | Create | Department | Engineering | Success |

### 4.2 Invalid Test Data

| Test Case ID | Data Category | Field | Value | Expected Error |
|--------------|---------------|-------|-------|----------------|
| TC-010 | Create | Name | (empty) | Name is required |
| TC-011 | Create | Email | invalid-email | Invalid email format |
| TC-012 | Create | Phone | 123 | Phone too short |
| TC-013 | Create | Department | InvalidDept | Department not found |

### 4.3 Boundary Test Data

| Test Case ID | Field | Value | Boundary Type | Expected Result |
|--------------|-------|-------|---------------|-----------------|
| TC-020 | Name | A | Min - 1 | Error: too short |
| TC-021 | Name | AB | Min | Success |
| TC-022 | Name | Test User | Typical | Success |
| TC-023 | Name | 100 chars | Max | Success |
| TC-024 | Name | 101 chars | Max + 1 | Error: too long |

---

## 5. Data Files

### 5.1 File Structure

```
src/test/resources/testdata/
├── {module}/
│   ├── {Module}TestData.xlsx
│   ├── {Module}ValidationData.xlsx
│   └── {Module}BoundaryData.xlsx
├── login/
│   └── LoginCredentials.xlsx
└── common/
    ├── Departments.xlsx
    └── LeaveTypes.xlsx
```

### 5.2 Excel File Template

#### Sheet: Create_Valid

| Test_ID | Name | Email | Phone | Department | Position | Expected_Result | Expected_Message |
|---------|------|-------|-------|------------|----------|-----------------|-----------------|
| TC001 | John Doe | john.doe@test.com | +84-123-456-789 | Engineering | SE | PASS | Record created |
| TC002 | Jane Smith | jane.smith@test.com | +84-234-567-890 | Sales | Manager | PASS | Record created |

#### Sheet: Create_Invalid

| Test_ID | Name | Email | Phone | Expected_Result | Expected_Error |
|---------|------|-------|-------|-----------------|----------------|
| TC101 | (empty) | test@test.com | +84-123-456-789 | FAIL | Name is required |
| TC102 | Test | invalid-email | +84-123-456-789 | FAIL | Invalid email |
| TC103 | Test | test@test.com | 123 | FAIL | Invalid phone |

#### Sheet: Edit_Valid

| Test_ID | Current_Name | New_Name | New_Email | Expected_Result |
|---------|--------------|----------|-----------|-----------------|
| TC201 | John Doe | John Updated | john.new@test.com | PASS |
| TC202 | Jane Smith | Jane Updated | jane.new@test.com | PASS |

#### Sheet: Search

| Test_ID | Search_Term | Search_Field | Expected_Match | Expected_Count |
|---------|-------------|--------------|----------------|----------------|
| TC301 | John | Name | true | >= 1 |
| TC302 | Engineering | Department | true | >= 1 |
| TC303 | Nonexistent | Name | false | 0 |

---

## 6. Data Generation

### 6.1 DataFaker Configuration

```java
// Faker Configuration
private static final Faker faker = new Faker(new Locale("en", "US"));

// Name Generation
faker.name().fullName();           // "John Doe"
faker.name().firstName();          // "John"
faker.name().lastName();          // "Doe"

// Email Generation
faker.internet().emailAddress();   // "john.doe@email.com"
faker.internet().safeEmailAddress(); // "john.doe@test.com"

// Phone Generation
faker.phoneNumber().phoneNumber(); // "+84-123-456-789"
faker.phoneNumber().cellPhone();   // "+84-123-456-789"

// Address Generation
faker.address().fullAddress();    // Full address
faker.address().city();           // City name
faker.address().department();     // Department

// Date Generation
faker.date().birthday();          // Random birthday
faker.date().future(30, TimeUnit.DAYS); // Future date
```

### 6.2 Unique Data Generation

```java
// Generate unique email with timestamp
public static String generateUniqueEmail(String prefix) {
    return prefix + "." + System.currentTimeMillis() + "@test.com";
}

// Generate unique employee ID
public static String generateEmployeeId() {
    return "EMP" + String.format("%05d", new Random().nextInt(99999));
}

// Generate unique phone number
public static String generatePhoneNumber() {
    return "+84-" + faker.number().randomNumber(9, true);
}
```

---

## 7. Data Setup & Teardown

### 7.1 Test Data Lifecycle

```
┌─────────────────────────────────────────────────────────────────────────────┐
│ TEST DATA LIFECYCLE                                                         │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  1. BEFORE SUITE                                                           │
│     ├── Create master test data (user accounts, departments)                │
│     └── Load static configuration                                           │
│                                                                             │
│  2. BEFORE CLASS                                                           │
│     ├── Setup class-level fixtures                                          │
│     └── Create shared test data                                             │
│                                                                             │
│  3. BEFORE METHOD                                                          │
│     ├── Generate unique test data                                           │
│     └── Setup test-specific preconditions                                   │
│                                                                             │
│  4. TEST EXECUTION                                                         │
│     └── Use test data for test execution                                    │
│                                                                             │
│  5. AFTER METHOD                                                           │
│     ├── Cleanup test-specific data                                          │
│     └── Reset any modified state                                            │
│                                                                             │
│  6. AFTER CLASS                                                            │
│     ├── Cleanup class fixtures                                              │
│     └── Archive test data                                                   │
│                                                                             │
│  7. AFTER SUITE                                                            │
│     ├── Full environment reset                                              │
│     └── Generate data summary report                                        │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 7.2 Data Cleanup Strategy

| Data Type | Cleanup Method | Timing | Priority |
|-----------|---------------|--------|----------|
| Created Records | API DELETE / UI Delete | After test | Critical |
| Modified Records | Restore original | After test | High |
| Session Data | Clear cookies | After test | Medium |
| Temporary Files | Delete files | After test | Low |

---

## 8. Test Data Examples

### 8.1 Employee Test Data Examples

```excel
/* EmployeeCreateData.xlsx */
| Employee_ID | Full_Name | Email | Phone | Department | Position | Status |
|-------------|-----------|-------|-------|------------|----------|--------|
| EMP001 | John Doe | john.doe@test.com | +84-123-456-789 | Engineering | SE | ACTIVE |
| EMP002 | Jane Smith | jane.smith@test.com | +84-234-567-890 | Sales | Manager | ACTIVE |
| EMP003 | Bob Wilson | bob.wilson@test.com | +84-345-678-901 | HR | HR Staff | ACTIVE |
```

### 8.2 Leave Test Data Examples

```excel
/* LeaveRequestData.xlsx */
| Request_ID | Leave_Type | Start_Date | End_Date | Days | Reason | Status |
|------------|------------|------------|----------|------|--------|--------|
| LR001 | ANNUAL | 2026-06-01 | 2026-06-05 | 5 | Vacation | PENDING |
| LR002 | SICK | 2026-06-10 | 2026-06-11 | 2 | Medical | PENDING |
| LR003 | PERSONAL | 2026-06-15 | 2026-06-15 | 1 | Personal | PENDING |
```

### 8.3 Login Test Data Examples

```excel
/* LoginCredentials.xlsx */
| Test_ID | Username | Password | Expected_Result | Expected_Message |
|---------|----------|----------|-----------------|------------------|
| TC001 | admin | Admin@123 | PASS | Dashboard displayed |
| TC002 | admin | WrongPass | FAIL | Invalid credentials |
| TC003 | invalid | Admin@123 | FAIL | User not found |
```

---

## 9. Data Quality Standards

### 9.1 Quality Checklist

- [ ] All required fields have data
- [ ] Data formats are correct
- [ ] Data is unique where required
- [ ] Boundary values are included
- [ ] Invalid data covers all error scenarios
- [ ] Data is realistic and production-like
- [ ] Sensitive data is masked or anonymized
- [ ] Data can be easily maintained

### 9.2 Data Validation Rules

| Rule | Validation | Action on Failure |
|------|------------|-------------------|
| Required Field | Not empty | Reject test data |
| Email Format | RFC 5322 compliant | Reject test data |
| Phone Format | Valid pattern | Reject test data |
| Date Range | Within bounds | Reject test data |
| Unique Constraint | No duplicates | Reject test data |

---

## 10. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 0.1 | YYYY-MM-DD | [Author] | Initial draft |
| 1.0 | YYYY-MM-DD | [Author] | Final version |
