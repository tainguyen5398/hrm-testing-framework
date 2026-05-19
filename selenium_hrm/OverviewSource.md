# HRM Selenium Automation Framework

> Enterprise-grade UI automation testing framework for Human Resource Management systems

[![Test Status](https://github.com/tainguyen5398/hrm-testing-framework/actions/workflows/selenium-test.yml/badge.svg)](https://github.com/tainguyen5398/hrm-testing-framework/actions)
[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](https://adoptium.net/)
[![TestNG](https://img.shields.io/badge/TestNG-7.4.0-green.svg)](https://testng.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.35.0-blueviolet.svg)](https://www.selenium.dev/)

---

## Overview

A robust, scalable, and maintainable Selenium automation framework designed for testing HRM (Human Resource Management) web applications. Built with industry best practices including Page Object Model (POM) pattern, data-driven testing, and comprehensive reporting capabilities.

### Key Features

- **Page Object Model (POM)** - Separation of page elements and test logic
- **Multi-environment Support** - Seamless switching between local, QA, and staging environments
- **Parallel Execution Ready** - Thread-safe driver management via ThreadLocal
- **Data-Driven Testing** - Support for JSON and Excel test data
- **Comprehensive Reporting** - Extent Reports and Allure integration
- **CI/CD Ready** - GitHub Actions integration with artifact upload

---

## Technology Stack

### Core Technologies

| Component | Technology | Version | Purpose |
|-----------|------------|--------|---------|
| Test Framework | TestNG | 7.4.0 | Test execution & configuration |
| Web Automation | Selenium WebDriver | 4.35.0 | Browser automation |
| Build Tool | Maven | 3.9+ | Dependency management |
| Language | Java | 21 | Runtime |
| Reporting | Extent Reports | 5.1.1 | HTML test reports |
| Reporting | Allure TestNG | 2.30.0 | Advanced test analytics |
| Logging | Log4j2 | 2.24.3 | Application logging |

### Supporting Libraries

| Library | Version | Purpose |
|---------|---------|---------|
| Apache POI | 5.2.5 | Excel file handling |
| Gson | 2.13.1 | JSON parsing |
| Rest-Assured | 5.3.2 | API testing capabilities |

### Supported Browsers

| Browser | Status | Headless Mode |
|---------|--------|--------------|
| Chrome | ✅ Default | ✅ CI Support |
| Firefox | ✅ Supported | ✅ CI Support |
| Edge | ✅ Supported | ✅ CI Support |

### Supported Environments

| Environment | Purpose | Configuration |
|-------------|---------|---------------|
| `local` | Local development | Default |
| `qa` | QA testing | `-Denv=qa` |
| `staging` | Pre-production | `-Denv=staging` |

---

## Project Architecture

### Directory Structure

```
selenium_hrm/
├── src/
│   ├── main/java/com/selenium_hrm/
│   │   ├── config/
│   │   │   ├── ConfigHelper.java          # Environment configuration loader
│   │   │   └── testdata/
│   │   │       ├── PropertiesHelper.java   # Properties file handler
│   │   │       ├── JsonHelper.java        # JSON data reader
│   │   │       └── ExcelHelper.java       # Excel data reader
│   │   │
│   │   ├── factory/
│   │   │   └── DriverManager.java         # ThreadLocal WebDriver factory
│   │   │
│   │   ├── listeners/
│   │   │   └── TestListener.java          # TestNG event listeners
│   │   │
│   │   └── utils/
│   │       ├── ActionHelper.java         # User action utilities
│   │       ├── ElementHelper.java         # Element interaction helpers
│   │       ├── WaitHelper.java            # Explicit wait utilities
│   │       ├── VerificationHelper.java    # Assertion utilities
│   │       └── CaptureHelper.java         # Screenshot utilities
│   │
│   └── test/
│       ├── java/com/selenium_hrm/ui/
│       │   ├── base/
│       │   │   ├── BaseUI.java            # Test base class
│       │   │   └── BasePage.java          # Page object base class
│       │   │
│       │   ├── pages/
│       │   │   ├── login/
│       │   │   │   ├── LoginPage.java     # Login page elements
│       │   │   │   └── LoginAction.java   # Login business flows
│       │   │   └── ...
│       │   │
│       │   └── tests/
│       │       ├── login/
│       │       │   ├── LoginTest.java     # Login test cases
│       │       │   └── LoginTestData.java # Login test data
│       │       └── ...
│       │
│       └── resources/
│           ├── config/
│           │   ├── config.properties       # Environment config
│           │   ├── qa.properties
│           │   └── staging.properties
│           └── testdata/                  # Test data files
│
├── pom.xml                                 # Maven configuration
├── extentReports/                          # HTML report output
└── logs/                                   # Log file output
```

### Design Patterns

#### Page Object Model (POM)

```
┌─────────────────────────────────────────────────────────┐
│                     Test Layer                          │
│  LoginTest.java - Test cases & assertions              │
└─────────────────────┬───────────────────────────────────┘
                      │ calls
                      ▼
┌─────────────────────────────────────────────────────────┐
│                   Action Layer                          │
│  LoginAction.java - Business logic & flows              │
└─────────────────────┬───────────────────────────────────┘
                      │ interacts
                      ▼
┌─────────────────────────────────────────────────────────┐
│                    Page Layer                           │
│  LoginPage.java - Web elements & interactions           │
└─────────────────────┬───────────────────────────────────┘
                      │ locates
                      ▼
┌─────────────────────────────────────────────────────────┐
│                   Base Layer                            │
│  BasePage.java - Common helpers (wait, click, sendKeys)  │
└─────────────────────────────────────────────────────────┘
```

#### Driver Management (ThreadLocal)

```
Thread 1 ──► DriverManager ──► ChromeDriver #1
Thread 2 ──► DriverManager ──► ChromeDriver #2
Thread N ──► DriverManager ──► ChromeDriver #N

• Each thread gets its own isolated WebDriver instance
• Enables parallel test execution without conflicts
```

---

## Quick Start

### Prerequisites

- Java 21 or higher
- Maven 3.9+
- Chrome/Firefox/Edge browser installed

### Installation

```bash
# Clone repository
git clone https://github.com/tainguyen5398/hrm-testing-framework.git

# Navigate to project
cd hrm-testing-framework/selenium_hrm

# Download dependencies
mvn clean install
```

### Running Tests

#### Basic Commands

```bash
# Run all tests (default: local, chrome)
mvn test

# Run specific test class
mvn test -Dtest=LoginTest

# Run with specific environment
mvn test -Denv=qa
mvn test -Denv=staging

# Run with specific browser
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge

# Combine options
mvn test -Denv=qa -Dtest=LoginTest -Dbrowser=chrome
```

#### Headless Mode

```bash
# Enable headless for local testing
mvn test -Dheadless=true
```

### Viewing Reports

#### Extent Reports

```bash
# Report location after test run
open extentReports/ExtentReport.html
```

#### Allure Reports

```bash
# Generate and serve Allure report
mvn allure:serve

# Generate report without serving
mvn allure:generate

# Open existing report
mvn allure:open -Dallure.report.directory=target/allure-results
```

---

## CI/CD Integration

### GitHub Actions

The framework includes automated CI/CD pipeline at `.github/workflows/selenium-test.yml`.

#### Workflow Features

- Automatic test execution on push/PR
- Headless Chrome execution in CI environment
- Artifact upload for test results
- Parallel execution support

#### Accessing CI Reports

1. Navigate to [Actions](https://github.com/tainguyen5398/hrm-testing-framework/actions)
2. Select the workflow run
3. Download artifacts from the job summary

---

## Configuration

### Environment Configuration

Edit `src/test/resources/config/config.properties`:

```properties
# Environment selection
environment=local

# Base URL
URL=https://hrm.anhtester.com/

# Timeouts (seconds)
DEFAULT_TIMEOUT=10
PAGE_LOAD_TIMEOUT=20
IMPLICIT_WAIT=0
EXPLICIT_WAIT=10

# Browser settings
BROWSER=chrome
HEADLESS=false
MAXIMIZE_WINDOW=true

# Reporting
SCREENSHOT_PATH=target/screenshots
TAKE_SCREENSHOT_ON_FAILURE=true
EXTENT_REPORT_PATH=target/extent-reports
ALLURE_RESULTS_PATH=target/allure-results
```

### Test Data

Test data can be provided via:

- **JSON**: Define in test data classes or JSON files
- **Excel**: Place `.xlsx` files in `src/test/resources/testdata/`
- **Properties**: Use `.properties` files for key-value data

---

## Contributing

1. Create a feature branch: `git checkout -b feature/your-feature`
2. Commit changes: `git commit -m 'Add new test case'`
3. Push to branch: `git push origin feature/your-feature`
4. Submit a Pull Request

---

## License

This project is for educational and testing purposes.

---

## Support

- **Documentation**: This file and inline code comments
- **Issues**: GitHub Issues page
- **CI/CD**: GitHub Actions tab for build status
