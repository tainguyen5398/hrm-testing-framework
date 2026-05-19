# HRM Selenium Automation

## Overview

HRM Selenium Automation là framework automation test cho hệ thống HRM (Human Resource Management) sử dụng Selenium WebDriver với Page Object Model pattern.

### Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| Test Framework | TestNG | 7.4.0 |
| Web Automation | Selenium WebDriver | 4.35.0 |
| Build Tool | Maven | - |
| Language | Java | 21 |
| HTML Reports | Extent Reports | 5.1.1 |
| Test Reports | Allure TestNG | 2.30.0 |
| Logging | Log4j2 | 2.24.3 |
| Excel Handling | Apache POI | 5.2.5 |
| JSON Processing | Gson | 2.13.1 |
| API Testing | Rest-Assured | 5.3.2 |

### Supported Browsers
- Chrome (default)
- Firefox
- Edge

### Supported Environments
- `local` - Development
- `qa` - QA Testing
- `staging` - Pre-production

---

## Sources

### Project Structure

```
selenium_hrm/
├── src/
│   ├── main/java/com/selenium_hrm/
│   │   ├── config/          # Configuration management
│   │   ├── factory/         # DriverManager (ThreadLocal WebDriver)
│   │   ├── listeners/       # TestNG listeners
│   │   └── utils/           # Helper utilities
│   │       ├── ActionHelper.java
│   │       ├── ElementHelper.java
│   │       ├── WaitHelper.java
│   │       ├── VerificationHelper.java
│   │       ├── CaptureHelper.java
│   │       ├── JsonHelper.java
│   │       └── ExcelHelper.java
│   │
│   └── test/
│       ├── java/com/selenium_hrm/ui/
│       │   ├── base/        # BaseUI, BasePage
│       │   ├── pages/       # Page Objects
│       │   └── tests/        # Test classes
│       └── resources/
│           └── config/       # Environment properties
│
├── pom.xml                  # Maven configuration
├── extentReports/          # HTML reports output
└── logs/                   # Log files
```

### Key Files

| File | Purpose |
|------|---------|
| `pom.xml` | Maven dependencies & build config |
| `BaseUI.java` | Test base class - driver initialization |
| `BasePage.java` | Page base class - helper delegation |
| `DriverManager.java` | ThreadLocal WebDriver factory |
| `ConfigHelper.java` | Environment configuration loader |
| `TestListener.java` | TestNG listener for reporting |

### Page Object Pattern

```
Locator (By locators)
    ↓
Page (Element interactions)
    ↓
Action (Business flows)
    ↓
Test (Test methods)
```

---

## Quick Start

### Run Tests

```bash
# Default (local environment)
mvn test

# Specific environment
mvn test -Denv=qa
mvn test -Denv=staging

# Specific browser
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

### View Reports

- Extent Reports: `extentReports/ExtentReport.html`
- Allure Reports: `mvn allure:serve`
