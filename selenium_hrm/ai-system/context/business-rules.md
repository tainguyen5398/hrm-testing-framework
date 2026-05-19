# Business Rules - HRM Application

## 1. Introduction

### 1.1 Purpose
Business rules định nghĩa các quy tắc nghiệp vụ của HRM (Human Resource Management) system. Tài liệu này là nguồn tham chiếu cho QA team để viết test cases và verify behavior của application.

### 1.2 Scope
| Module | Coverage |
|--------|----------|
| Authentication | Login, logout, password policies |
| Employee Management | CRUD, profiles, status |
| Leave Management | Request, approval, balance |
| Attendance | Clock in/out, overtime |
| Payroll | Salary, deductions, processing |
| Organization | Departments, roles, reporting |

---

## 2. Authentication Module

### 2.1 Login Rules

| Rule ID | Description | Validation |
|---------|-------------|------------|
| AUTH-001 | Username required | Username field cannot be empty |
| AUTH-002 | Password required | Password field cannot be empty |
| AUTH-003 | Valid credentials | Username + password must match database |
| AUTH-004 | Case sensitivity | Credentials are case-insensitive for username, case-sensitive for password |
| AUTH-005 | Locked account | Account locked after 5 failed attempts |
| AUTH-006 | Session timeout | Session expires after 30 minutes of inactivity |
| AUTH-007 | Remember me | Session persists for 7 days when checked |
| AUTH-008 | Password minimum length | Password must be at least 8 characters |

### 2.2 Password Rules

| Rule ID | Description | Validation |
|---------|-------------|------------|
| AUTH-010 | Minimum length | Password must be at least 8 characters |
| AUTH-011 | Maximum length | Password must not exceed 20 characters |
| AUTH-012 | Password complexity | Must contain uppercase, lowercase, number, special char |
| AUTH-013 | No reuse | Cannot reuse last 5 passwords |
| AUTH-014 | Password change interval | Must change password every 90 days |
| AUTH-015 | Forgot password flow | Email verification required |

### 2.3 User Roles

| Role | Permissions |
|------|-------------|
| **ADMIN** | Full system access, manage all users, system settings |
| **HR_MANAGER** | Manage employees, leave approvals, reports |
| **MANAGER** | View team, approve team leave, team reports |
| **EMPLOYEE** | View own profile, request leave, view own reports |
| **VIEWER** | Read-only access to assigned modules |

---

## 3. Employee Management Module

### 3.1 Employee Creation Rules

| Rule ID | Description | Validation |
|---------|-------------|------------|
| EMP-001 | Required fields | firstName, lastName, email, department, position are required |
| EMP-002 | Email format | Must be valid email format (user@domain.com) |
| EMP-003 | Email uniqueness | Email must be unique across all employees |
| EMP-004 | Phone format | Must be valid phone number (10-15 digits) |
| EMP-005 | Employee ID | Auto-generated, format: EMP-XXXXX |
| EMP-006 | Start date | Cannot be in the past for new employees |
| EMP-007 | Department exists | Must reference valid department |
| EMP-008 | Manager assignment | Manager must be existing active employee |
| EMP-009 | Duplicate check | Cannot create employee with duplicate email |

### 3.2 Employee Fields & Validation

```java
// Field Validation Rules
Employee {
    // Required Fields
    firstName: String, min 2 chars, max 50 chars, letters only
    lastName: String, min 2 chars, max 50 chars, letters only
    email: String, valid email format, unique
    phone: String, valid phone format (10-15 digits)
    
    // Required References
    department: Department (required)
    position: String, required
    employmentType: FULL_TIME | PART_TIME | CONTRACT | INTERN
    
    // Optional Fields
    middleName: String, optional
    dateOfBirth: Date, must be 18+ years old
    gender: MALE | FEMALE | OTHER
    address: String, max 200 chars
    emergencyContact: String
    emergencyPhone: String
    reportingManager: Employee (optional)
    
    // Auto-generated
    employeeId: String, format EMP-XXXXX
    createdAt: DateTime
    createdBy: User
    
    // System Fields
    status: ACTIVE | INACTIVE | PROBATION | TERMINATED
    isActive: Boolean
}
```

### 3.3 Employee Status Transitions

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         EMPLOYEE STATUS FLOW                                  │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   NEW ──────▶ PROBATION ──────▶ ACTIVE                                     │
│                    │                   │                                    │
│                    │                   │                                    │
│                    │                   ▼                                    │
│                    │               ON_LEAVE                                 │
│                    │                   │                                    │
│                    │                   ▼                                    │
│                    └──────────▶ INACTIVE ◀──── TERMINATED                   │
│                                   │                                        │
│                                   │                                        │
│                                   ▼                                        │
│                                 ACTIVE                                     │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

| Transition | From | To | Conditions |
|------------|------|----|------------|
| Hire | NEW | PROBATION | Start date reached |
| Confirm | PROBATION | ACTIVE | Probation period completed, approved by HR |
| Terminate | ACTIVE/PROBATION | TERMINATED | HR/admin action, requires reason |
| Deactivate | ACTIVE | INACTIVE | Manual or automated (no activity) |
| Reactivate | INACTIVE | ACTIVE | HR action |
| Leave | ACTIVE | ON_LEAVE | Leave request approved |
| Return | ON_LEAVE | ACTIVE | Leave period ended |

### 3.4 Employee Search & Filter Rules

| Rule ID | Description | Validation |
|---------|-------------|------------|
| EMP-020 | Search by name | Partial match supported (case-insensitive) |
| EMP-021 | Search by email | Exact or partial match |
| EMP-022 | Search by employee ID | Exact match |
| EMP-023 | Filter by department | Must select from existing departments |
| EMP-024 | Filter by status | ACTIVE, INACTIVE, PROBATION, TERMINATED |
| EMP-025 | Filter by position | Must select from existing positions |
| EMP-026 | Pagination | Default 20 items per page, max 100 |
| EMP-027 | Sort options | name, email, department, startDate, status |

---

## 4. Leave Management Module

### 4.1 Leave Types

| Leave Type | Code | Paid | Annual Entitlement | Carry Forward |
|------------|------|------|-------------------|---------------|
| Annual Leave | ANNUAL | Yes | 18 days | Max 5 days |
| Sick Leave | SICK | Yes | 12 days | No |
| Casual Leave | CASUAL | Yes | 6 days | No |
| Maternity Leave | MATERNITY | Yes | 90 days | N/A |
| Paternity Leave | PATERNITY | Yes | 10 days | N/A |
| Unpaid Leave | UNPAID | No | Unlimited | N/A |
| Bereavement | BEREAVEMENT | Yes | 5 days | No |

### 4.2 Leave Request Rules

| Rule ID | Description | Validation |
|---------|-------------|------------|
| LEAVE-001 | Start date required | Must be selected |
| LEAVE-002 | End date required | Must be selected |
| LEAVE-003 | Date range valid | End date >= Start date |
| LEAVE-004 | Advance notice | Request must be submitted 2 days in advance (except sick) |
| LEAVE-005 | Sufficient balance | Available balance >= requested days |
| LEAVE-006 | No overlap | Cannot overlap with existing approved leave |
| LEAVE-007 | Past dates | Cannot request leave for past dates |
| LEAVE-008 | Future limit | Cannot request leave more than 6 months in advance |
| LEAVE-009 | Half day option | Only for SICK and CASUAL leave |
| LEAVE-010 | Maximum continuous | Max 14 consecutive days without special approval |

### 4.3 Leave Approval Workflow

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         LEAVE APPROVAL WORKFLOW                              │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   EMPLOYEE                                                                   │
│       │                                                                     │
│       ▼ Submit Request                                                       │
│   ┌─────────────┐                                                            │
│   │  PENDING    │ ── Auto-cancel if insufficient balance                   │
│   └──────┬──────┘                                                            │
│          │                                                                   │
│          ▼                                                                   │
│   ┌─────────────┐                                                            │
│   │ SUPERVISOR │ ── Approve/Reject (within 2 working days)                │
│   │   REVIEW    │                                                            │
│   └──────┬──────┘                                                            │
│          │                                                                   │
│     ┌────┴────┐                                                              │
│     │         │                                                              │
│     ▼         ▼                                                              │
│ ┌───────┐ ┌────────┐                                                         │
│ │APPROVE│ │ REJECT │                                                         │
│ └───┬───┘ └───┬────┘                                                         │
│     │         │                                                               │
│     ▼         ▼ (Rejected)                                                   │
│ ┌───────────────┐                                                             │
│ │ AUTO-DEDUCT   │ ── Deduct from leave balance                              │
│ │   BALANCE     │                                                             │
│ └───────┬───────┘                                                             │
│         │                                                                     │
│         ▼                                                                     │
│   ┌───────────┐                                                              │
│   │ APPROVED  │                                                              │
│   └───────────┘                                                              │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 4.4 Leave Balance Calculation

```java
// Leave Balance Rules

// Annual Entitlement
entitledAnnual = 18 days
entitledSick = 12 days
entitledCasual = 6 days

// Carry Forward Rules
carryForwardMax = 5 days
carryForwardExpiry = March 31 of new year

// Calculation Formula
currentBalance = 
    previousBalance 
    + entitledLeave 
    + carryForward 
    - approvedLeave 
    - rejectedLeave (if re-credited)

// Leave Credit Date
// Credit happens on: January 1 of each year
// Or: Employee's anniversary date

// Prorata Calculation (mid-year hire)
prorataDays = (entitledDays / 12) * remainingMonths
remainingMonths = 12 - (monthOfJoin - 1)
```

### 4.5 Leave Status

| Status | Description | Can Employee Cancel? |
|--------|-------------|---------------------|
| **PENDING** | Awaiting approval | Yes |
| **APPROVED** | Approved by supervisor | No (must submit cancel request) |
| **REJECTED** | Rejected by supervisor | No |
| **CANCELLED** | Cancelled by employee | N/A |
| **COMPLETED** | Leave period ended | N/A |

---

## 5. Attendance Module

### 5.1 Clock In/Out Rules

| Rule ID | Description | Validation |
|---------|-------------|------------|
| ATT-001 | Clock in required | Must clock in before work starts |
| ATT-002 | Clock out required | Must clock out after work ends |
| ATT-003 | Minimum work hours | Minimum 4 hours per day to count |
| ATT-004 | Overtime threshold | Hours > 8 per day = overtime |
| ATT-005 | Late grace period | 5 minutes grace period for late |
| ATT-006 | Early leave deduction | Leave before 4 hours = half day |
| ATT-007 | Weekend rules | No clock-in required on weekends |
| ATT-008 | Holiday handling | Clock-in not required on holidays |

### 5.2 Attendance Status

| Status | Code | Description |
|--------|------|-------------|
| Present | P | Worked full day |
| Half Day | HD | Worked half day |
| Absent | A | Did not work |
| Late | L | Arrived after grace period |
| On Leave | LVE | Approved leave |
| Holiday | H | Company holiday |
| Weekend | W | Saturday/Sunday |

### 5.3 Overtime Rules

| Rule ID | Description | Validation |
|---------|-------------|------------|
| OVT-001 | Weekday overtime | Hours > 8 per day |
| OVT-002 | Weekend overtime | Any hours on weekend |
| OVT-003 | Holiday overtime | Any hours on holiday |
| OVT-004 | Overtime cap | Max 12 hours per week |
| OVT-005 | OT rate weekday | 1.5x regular rate |
| OVT-006 | OT rate weekend | 2.0x regular rate |
| OVT-007 | OT rate holiday | 2.5x regular rate |

---

## 6. Payroll Module

### 6.1 Salary Components

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         PAYROLL STRUCTURE                                    │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   EARNINGS                                                                  │
│   ├── Basic Salary                                                          │
│   ├── House Rent Allowance (HRA)          = 40% of Basic                     │
│   ├── Transport Allowance (TA)            = 10% of Basic                    │
│   ├── Medical Allowance                   = Fixed amount                    │
│   ├── Special Allowance                  = Variable                         │
│   ├── Overtime Pay                       = Hourly rate × OT hours          │
│   └── Performance Bonus                   = Based on rating                 │
│                                                                             │
│   DEDUCTIONS                                                                │
│   ├── Provident Fund (PF)                = 12% of Basic (employee)          │
│   ├── Employee State Insurance (ESI)      = 0.75% of gross (if < 21K)       │
│   ├── Tax Deducted at Source (TDS)       = Based on tax slab               │
│   ├── Professional Tax                   = Fixed (state-specific)           │
│   └── Other Deductions                   = Loan EMI, fines, etc.             │
│                                                                             │
│   NET PAY = GROSS EARNINGS - TOTAL DEDUCTIONS                              │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 6.2 Payroll Processing Rules

| Rule ID | Description | Validation |
|---------|-------------|------------|
| PAY-001 | Pay period | Monthly (1st to last day of month) |
| PAY-002 | Processing date | 25th of each month |
| PAY-003 | Payment date | Last working day of month |
| PAY-004 | Calculation basis | Based on attendance records |
| PAY-005 | Prorata salary | For mid-month joining/resignation |
| PAY-006 | LOP calculation | Loss of Pay = Daily rate × absent days |
| PAY-007 | PF eligibility | Employee with salary <= 15,000/month |
| PAY-008 | ESI eligibility | Employee with salary <= 21,000/month |

### 6.3 Salary Calculation Formulas

```java
// Monthly Gross
grossSalary = basicSalary + hra + ta + medical + specialAllowance

// PF Contribution (Employee)
employeePF = Math.min(basicSalary * 0.12, 1800) // Max 1800

// PF Contribution (Employer)
employerPF = Math.min(basicSalary * 0.12, 1800) // Max 1800

// Professional Tax (example for Karnataka)
professionalTax = 200 // if monthly gross > 15000
professionalTax = 0 // if monthly gross <= 15000

// TDS Calculation (simplified)
tds = calculateTaxSlab(annualIncome - exemptions - deductions) / 12

// Net Salary
netSalary = grossSalary - pfEmployee - esi - tds - professionalTax - otherDeductions
```

---

## 7. Organization Module

### 7.1 Department Rules

| Rule ID | Description | Validation |
|---------|-------------|------------|
| ORG-001 | Department name required | Cannot be empty |
| ORG-002 | Department code unique | Each department has unique code |
| ORG-003 | Parent department | Can have parent (for nested structure) |
| ORG-004 | Max depth | Department hierarchy max 4 levels |
| ORG-005 | Cannot delete | Cannot delete department with employees |

### 7.2 Organization Hierarchy

```
CEO
├── Engineering
│   ├── Frontend
│   ├── Backend
│   └── DevOps
├── Human Resources
│   ├── Recruitment
│   └── Training
├── Sales
│   ├── Enterprise
│   └── SMB
├── Marketing
│   ├── Digital
│   └── Brand
└── Finance
    ├── Accounting
    └── Compliance
```

### 7.3 Role & Permissions

| Role | Can Manage Employees | Can Approve Leave | Can View Reports | Can Process Payroll |
|------|---------------------|-------------------|------------------|---------------------|
| ADMIN | Yes (all) | Yes | Yes (all) | Yes |
| HR_MANAGER | Yes (department) | Yes | Yes (department) | No |
| MANAGER | No | Yes (team) | Yes (team) | No |
| EMPLOYEE | No | Own only | Own only | No |
| VIEWER | No | No | Assigned | No |

---

## 8. Common Validation Rules

### 8.1 Date Validation

| Rule | Validation |
|------|------------|
| Date format | yyyy-MM-dd |
| Past dates | Allowed for search, not for creation (with exceptions) |
| Future dates | Max 1 year in advance |
| Date range | End date >= Start date |
| Working days | Excludes weekends and holidays |

### 8.2 String Validation

| Field Type | Min Length | Max Length | Special Rules |
|------------|------------|------------|---------------|
| Name | 2 chars | 50 chars | Letters, spaces, hyphens, apostrophes |
| Email | 5 chars | 100 chars | Valid email format |
| Phone | 10 digits | 15 digits | Numbers, +, -, spaces |
| Address | 5 chars | 200 chars | Alphanumeric, spaces, special chars |
| Password | 8 chars | 20 chars | See password rules |
| Remarks | 0 chars | 500 chars | Optional |

### 8.3 Numeric Validation

| Field | Min | Max | Decimal Places |
|-------|-----|-----|----------------|
| Salary | 0 | 999,999,999 | 2 |
| Age | 18 | 100 | 0 |
| Percentage | 0 | 100 | 2 |
| Days | 0 | 365 | 0 |
| Hours | 0 | 24 | 2 |

---

## 9. Error Messages

### 9.1 Authentication Errors

| Error Code | Message | Display Condition |
|------------|---------|-------------------|
| AUTH_E001 | Username is required | Empty username submitted |
| AUTH_E002 | Password is required | Empty password submitted |
| AUTH_E003 | Invalid credentials | Username/password mismatch |
| AUTH_E004 | Account locked | After 5 failed attempts |
| AUTH_E005 | Session expired | Session timeout |

### 9.2 Employee Errors

| Error Code | Message |
|------------|--------|
| EMP_E001 | First name is required |
| EMP_E002 | Last name is required |
| EMP_E003 | Email is required |
| EMP_E004 | Invalid email format |
| EMP_E005 | Email already exists |
| EMP_E006 | Department is required |
| EMP_E007 | Position is required |
| EMP_E008 | Invalid phone number |
| EMP_E009 | Employee not found |

### 9.3 Leave Errors

| Error Code | Message |
|------------|--------|
| LEAVE_E001 | Start date is required |
| LEAVE_E002 | End date is required |
| LEAVE_E003 | Invalid date range |
| LEAVE_E004 | Insufficient leave balance |
| LEAVE_E005 | Leave request overlaps with existing |
| LEAVE_E006 | Cannot request for past dates |
| LEAVE_E007 | Notice period not met |
| LEAVE_E008 | Maximum consecutive days exceeded |

---

## 10. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Business Analyst | Initial business rules |
