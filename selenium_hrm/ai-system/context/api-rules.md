# API Rules - HRM Automation

## 1. Introduction

### 1.1 Purpose
API rules định nghĩa các quy tắc và hướng dẫn cho việc testing REST APIs trong HRM application. Tài liệu này cover API testing strategy, conventions, và best practices.

### 1.2 Scope
| Area | Coverage |
|------|----------|
| **API Fundamentals** | REST conventions, HTTP methods |
| **Request/Response** | Structure, headers, status codes |
| **Authentication** | Auth methods, token handling |
| **Test Automation** | Rest-Assured usage, data management |
| **Error Handling** | Error responses, validation |

### 1.3 Technology Stack
- **API Client**: Rest-Assured 5.3+
- **Assertions**: Rest-Assured + TestNG Assert
- **Serialization**: Jackson/Gson
- **Authentication**: Bearer Token / OAuth2

---

## 2. API Fundamentals

### 2.1 REST Conventions

```java
// ✅ ĐÚNG: RESTful API conventions

// Resource naming - nouns, lowercase, plural
GET    /api/v1/employees           // Get all employees
GET    /api/v1/employees/{id}      // Get employee by ID
POST   /api/v1/employees           // Create new employee
PUT    /api/v1/employees/{id}      // Update employee
DELETE /api/v1/employees/{id}      // Delete employee

GET    /api/v1/leave/requests     // Get all leave requests
GET    /api/v1/leave/requests/{id} // Get leave request by ID
POST   /api/v1/leave/requests     // Create leave request
PUT    /api/v1/leave/requests/{id} // Update leave request
DELETE /api/v1/leave/requests/{id} // Delete leave request

// ❌ SAI: Actions as endpoints
POST   /api/v1/createEmployee
POST   /api/v1/deleteEmployee
POST   /api/v1/getEmployee
```

### 2.2 HTTP Methods

| Method | Usage | Idempotent | Safe |
|--------|-------|------------|------|
| **GET** | Retrieve resources | Yes | Yes |
| **POST** | Create new resources | No | No |
| **PUT** | Full update (replace) | Yes | No |
| **PATCH** | Partial update | No | No |
| **DELETE** | Remove resource | Yes | No |

### 2.3 HTTP Status Codes

```java
// ============ SUCCESS (2xx) ============
200 OK                    // Successful GET, PUT, PATCH, DELETE
201 Created              // Successful POST (new resource created)
202 Accepted             // Async request accepted
204 No Content          // Successful DELETE (no body)

// ============ CLIENT ERRORS (4xx) ============
400 Bad Request         // Invalid request format, validation error
401 Unauthorized        // Missing or invalid authentication
403 Forbidden           // Authenticated but no permission
404 Not Found           // Resource doesn't exist
405 Method Not Allowed  // HTTP method not supported
409 Conflict            // Conflict with existing data (duplicate)
422 Unprocessable       // Validation errors in request body
429 Too Many Requests   // Rate limit exceeded

// ============ SERVER ERRORS (5xx) ============
500 Internal Server Error // Unexpected server error
502 Bad Gateway          // Upstream server error
503 Service Unavailable  // Server temporarily down
504 Gateway Timeout      // Upstream timeout
```

---

## 3. API Endpoint Reference

### 3.1 Authentication API

```java
public class AuthEndpoints {
    
    // Base URL
    private static final String BASE_URL = "https://api.hrm.example.com";
    private static final String API_VERSION = "/api/v1";
    
    // Auth endpoints
    public static final String LOGIN = API_VERSION + "/auth/login";
    public static final String LOGOUT = API_VERSION + "/auth/logout";
    public static final String REFRESH_TOKEN = API_VERSION + "/auth/refresh";
    public static final String FORGOT_PASSWORD = API_VERSION + "/auth/forgot-password";
    public static final String RESET_PASSWORD = API_VERSION + "/auth/reset-password";
    public static final String CHANGE_PASSWORD = API_VERSION + "/auth/change-password";
    public static final String VERIFY_TOKEN = API_VERSION + "/auth/verify";
    
    // User endpoints
    public static final String CURRENT_USER = API_VERSION + "/auth/me";
    public static final String USER_PROFILE = API_VERSION + "/users/profile";
    public static final String USER_SETTINGS = API_VERSION + "/users/settings";
}
```

### 3.2 Employee API

```java
public class EmployeeEndpoints {
    
    private static final String API_VERSION = "/api/v1";
    
    // Employee endpoints
    public static final String EMPLOYEES = API_VERSION + "/employees";
    
    public static String employeeById(String id) {
        return API_VERSION + "/employees/" + id;
    }
    
    public static final String EMPLOYEE_SEARCH = API_VERSION + "/employees/search";
    public static final String EMPLOYEE_EXPORT = API_VERSION + "/employees/export";
    public static final String EMPLOYEE_IMPORT = API_VERSION + "/employees/import";
    
    public static String employeePhoto(String id) {
        return API_VERSION + "/employees/" + id + "/photo";
    }
    
    public static String employeeDocuments(String id) {
        return API_VERSION + "/employees/" + id + "/documents";
    }
    
    public static String employeeLeaveBalance(String id) {
        return API_VERSION + "/employees/" + id + "/leave-balance";
    }
}
```

### 3.3 Leave API

```java
public class LeaveEndpoints {
    
    private static final String API_VERSION = "/api/v1";
    
    // Leave request endpoints
    public static final String LEAVE_REQUESTS = API_VERSION + "/leave/requests";
    
    public static String leaveRequestById(String id) {
        return API_VERSION + "/leave/requests/" + id;
    }
    
    public static String approveLeaveRequest(String id) {
        return API_VERSION + "/leave/requests/" + id + "/approve";
    }
    
    public static String rejectLeaveRequest(String id) {
        return API_VERSION + "/leave/requests/" + id + "/reject";
    }
    
    public static String cancelLeaveRequest(String id) {
        return API_VERSION + "/leave/requests/" + id + "/cancel";
    }
    
    // Leave balance endpoints
    public static final String LEAVE_BALANCES = API_VERSION + "/leave/balances";
    
    public static String leaveBalanceByEmployee(String employeeId) {
        return API_VERSION + "/leave/balances/" + employeeId;
    }
    
    // Leave types
    public static final String LEAVE_TYPES = API_VERSION + "/leave/types";
}
```

### 3.4 Department API

```java
public class DepartmentEndpoints {
    
    private static final String API_VERSION = "/api/v1";
    
    public static final String DEPARTMENTS = API_VERSION + "/departments";
    
    public static String departmentById(String id) {
        return API_VERSION + "/departments/" + id;
    }
    
    public static String departmentEmployees(String id) {
        return API_VERSION + "/departments/" + id + "/employees";
    }
}
```

---

## 4. Request Structure

### 4.1 Request Headers

```java
// ✅ STANDARD HEADERS FOR ALL REQUESTS
public class RequestHeaders {
    
    // Content types
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_MULTIPART = "multipart/form-data";
    
    // Standard headers
    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    
    // Custom headers
    public static final String X_REQUEST_ID = "X-Request-ID";
    public static final String X_CORRELATION_ID = "X-Correlation-ID";
    public static final String X_CLIENT_VERSION = "X-Client-Version";
    public static final String X_API_KEY = "X-API-Key";
}
```

### 4.2 Request Body Structure

```java
// ============ LOGIN REQUEST ============
{
    "username": "admin",
    "password": "admin123",
    "rememberMe": true
}

// ============ CREATE EMPLOYEE REQUEST ============
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@company.com",
    "phone": "+1234567890",
    "dateOfBirth": "1990-05-15",
    "gender": "MALE",
    "departmentId": "DEPT-001",
    "position": "Software Engineer",
    "employmentType": "FULL_TIME",
    "startDate": "2026-06-01",
    "managerId": "EMP-00001",
    "address": {
        "street": "123 Main Street",
        "city": "New York",
        "state": "NY",
        "zipCode": "10001",
        "country": "USA"
    }
}

// ============ LEAVE REQUEST ============
{
    "employeeId": "EMP-00002",
    "leaveType": "ANNUAL",
    "startDate": "2026-07-01",
    "endDate": "2026-07-05",
    "reason": "Family vacation",
    "halfDay": false,
    "contactNumber": "+1234567890"
}
```

### 4.3 Query Parameters

```java
// ============ GET EMPLOYEES WITH FILTERS ============
// GET /api/v1/employees?page=0&size=20&sort=name,asc&department=Engineering&status=ACTIVE

// Pagination
page = 0           // Page number (0-indexed)
size = 20          // Items per page
total = true       // Include total count

// Sorting
sort = name,asc    // Sort by name ascending
sort = createdAt,desc  // Sort by createdAt descending

// Filtering
department = Engineering
status = ACTIVE
search = john

// Date filtering
startDate = 2026-01-01
endDate = 2026-12-31
```

---

## 5. Response Structure

### 5.1 Standard Success Response

```java
// ============ SINGLE RESOURCE ============
{
    "success": true,
    "data": {
        "id": "EMP-00001",
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@company.com",
        "department": {
            "id": "DEPT-001",
            "name": "Engineering"
        },
        "status": "ACTIVE",
        "createdAt": "2026-01-15T10:30:00Z",
        "updatedAt": "2026-05-10T14:20:00Z"
    },
    "message": null,
    "timestamp": "2026-05-11T08:45:00Z"
}

// ============ PAGINATED RESPONSE ============
{
    "success": true,
    "data": {
        "content": [
            { "id": "EMP-00001", "firstName": "John", ... },
            { "id": "EMP-00002", "firstName": "Jane", ... }
        ],
        "page": 0,
        "size": 20,
        "totalElements": 150,
        "totalPages": 8,
        "first": true,
        "last": false
    },
    "message": null,
    "timestamp": "2026-05-11T08:45:00Z"
}

// ============ LIST RESPONSE ============
{
    "success": true,
    "data": [
        { "id": "1", "name": "Annual Leave" },
        { "id": "2", "name": "Sick Leave" }
    ],
    "message": null,
    "timestamp": "2026-05-11T08:45:00Z"
}
```

### 5.2 Standard Error Response

```java
// ============ VALIDATION ERROR ============
{
    "success": false,
    "data": null,
    "errors": [
        {
            "field": "email",
            "message": "Invalid email format",
            "code": "INVALID_EMAIL"
        },
        {
            "field": "firstName",
            "message": "First name is required",
            "code": "REQUIRED_FIELD"
        }
    ],
    "message": "Validation failed",
    "timestamp": "2026-05-11T08:45:00Z"
}

// ============ AUTHENTICATION ERROR ============
{
    "success": false,
    "data": null,
    "errors": null,
    "message": "Invalid credentials",
    "errorCode": "AUTH_001",
    "timestamp": "2026-05-11T08:45:00Z"
}

// ============ NOT FOUND ERROR ============
{
    "success": false,
    "data": null,
    "errors": null,
    "message": "Employee not found",
    "errorCode": "EMP_404",
    "timestamp": "2026-05-11T08:45:00Z"
}
```

### 5.3 Response Headers

```java
// Common response headers to check
Content-Type: application/json
Content-Length: 1234
X-Request-ID: abc-123-def
X-Rate-Limit-Remaining: 99
X-Rate-Limit-Reset: 1620712800
Cache-Control: no-cache, no-store, max-age=0
```

---

## 6. Authentication & Authorization

### 6.1 Bearer Token Authentication

```java
public class AuthHelper {
    
    private static String accessToken;
    private static String refreshToken;
    private static final String BASE_URL = ConfigHelper.getApiBaseUrl();
    
    // Login and store tokens
    public static void login(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);
        
        Response response = given()
            .baseUri(BASE_URL)
            .contentType(ContentType.JSON)
            .body(request)
        .when()
            .post(AuthEndpoints.LOGIN);
        
        response.then().statusCode(200);
        
        accessToken = response.jsonPath().getString("data.accessToken");
        refreshToken = response.jsonPath().getString("data.refreshToken");
    }
    
    // Get auth headers
    public static Map<String, String> getAuthHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return headers;
    }
    
    // Refresh token
    public static void refreshAccessToken() {
        RefreshTokenRequest request = new RefreshTokenRequest(refreshToken);
        
        Response response = given()
            .baseUri(BASE_URL)
            .contentType(ContentType.JSON)
            .body(request)
        .when()
            .post(AuthEndpoints.REFRESH_TOKEN);
        
        response.then().statusCode(200);
        
        accessToken = response.jsonPath().getString("data.accessToken");
    }
    
    // Logout
    public static void logout() {
        given()
            .baseUri(BASE_URL)
            .headers(getAuthHeaders())
        .when()
            .post(AuthEndpoints.LOGOUT)
        .then()
            .statusCode(200);
        
        accessToken = null;
        refreshToken = null;
    }
}
```

### 6.2 Role-Based Access

```java
// ============ ROLE PERMISSIONS ============
public class RolePermissions {
    
    // ADMIN - Full access
    // POST /api/v1/employees
    // PUT /api/v1/employees/{id}
    // DELETE /api/v1/employees/{id}
    // POST /api/v1/leave/requests/{id}/approve
    
    // HR_MANAGER - Department management
    // GET /api/v1/employees
    // GET /api/v1/employees/{id}
    // POST /api/v1/employees
    // PUT /api/v1/employees/{id} (department only)
    
    // MANAGER - Team management
    // GET /api/v1/employees (team only)
    // POST /api/v1/leave/requests/{id}/approve (team only)
    
    // EMPLOYEE - Self only
    // GET /api/v1/employees/{self}
    // PUT /api/v1/employees/{self}
    // POST /api/v1/leave/requests (self only)
}

// ============ TESTING PERMISSIONS ============
@Test
public void testAdmin_CanCreateEmployee() {
    AuthHelper.login("admin", "admin123");
    
    EmployeeRequest request = createEmployeeRequest();
    
    given()
        .headers(AuthHelper.getAuthHeaders())
        .body(request)
    .when()
        .post(EmployeeEndpoints.EMPLOYEES)
    .then()
        .statusCode(201);
}

@Test
public void testEmployee_CannotCreateEmployee() {
    AuthHelper.login("employee", "password123");
    
    EmployeeRequest request = createEmployeeRequest();
    
    given()
        .headers(AuthHelper.getAuthHeaders())
        .body(request)
    .when()
        .post(EmployeeEndpoints.EMPLOYEES)
    .then()
        .statusCode(403); // Forbidden
}
```

---

## 7. API Test Patterns

### 7.1 Base Test Configuration

```java
public class BaseApiTest {
    
    protected static String baseUrl;
    protected static String apiVersion;
    
    @BeforeClass
    public static void setUpClass() {
        baseUrl = ConfigHelper.getApiBaseUrl();
        apiVersion = "/api/v1";
        
        // Setup test data
        setupTestData();
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Cleanup test data
        cleanupTestData();
    }
    
    @BeforeMethod
    public void setUp() {
        // Login before each test
        AuthHelper.login(ConfigHelper.getTestUsername(), 
                        ConfigHelper.getTestPassword());
    }
    
    @AfterMethod
    public void tearDown() {
        AuthHelper.logout();
    }
}
```

### 7.2 GET Test Patterns

```java
// ============ GET ALL RESOURCES ============
@Test
public void testGetAllEmployees_ReturnsSuccess() {
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
        .queryParam("page", 0)
        .queryParam("size", 20)
    .when()
        .get(apiVersion + "/employees");
    
    response.then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("success", equalTo(true))
        .body("data.content", notNullValue())
        .body("data.totalElements", greaterThan(0));
}

// ============ GET BY ID ============
@Test
public void testGetEmployeeById_ReturnsEmployee() {
    String employeeId = "EMP-00001";
    
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
    .when()
        .get(apiVersion + "/employees/" + employeeId);
    
    response.then()
        .statusCode(200)
        .body("success", equalTo(true))
        .body("data.id", equalTo(employeeId))
        .body("data.firstName", notNullValue())
        .body("data.lastName", notNullValue())
        .body("data.email", notNullValue());
}

// ============ GET NOT FOUND ============
@Test
public void testGetEmployeeByInvalidId_Returns404() {
    String invalidId = "EMP-99999";
    
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
    .when()
        .get(apiVersion + "/employees/" + invalidId);
    
    response.then()
        .statusCode(404)
        .body("success", equalTo(false))
        .body("errorCode", equalTo("EMP_404"));
}

// ============ GET WITH FILTERS ============
@Test
public void testGetEmployees_WithDepartmentFilter() {
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
        .queryParam("department", "Engineering")
        .queryParam("status", "ACTIVE")
    .when()
        .get(apiVersion + "/employees");
    
    response.then()
        .statusCode(200)
        .body("success", equalTo(true))
        .body("data.content.size()", greaterThan(0));
}
```

### 7.3 POST Test Patterns

```java
// ============ CREATE RESOURCE ============
@Test
public void testCreateEmployee_ReturnsCreatedEmployee() {
    EmployeeRequest request = EmployeeRequest.builder()
        .firstName("John")
        .lastName("Doe")
        .email("john.doe." + System.currentTimeMillis() + "@test.com")
        .phone("1234567890")
        .departmentId("DEPT-001")
        .position("Software Engineer")
        .employmentType(EmploymentType.FULL_TIME)
        .startDate(LocalDate.now().plusDays(7).toString())
        .build();
    
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
        .contentType(ContentType.JSON)
        .body(request)
    .when()
        .post(apiVersion + "/employees");
    
    response.then()
        .statusCode(201)
        .body("success", equalTo(true))
        .body("data.id", notNullValue())
        .body("data.firstName", equalTo(request.getFirstName()))
        .body("data.lastName", equalTo(request.getLastName()))
        .body("data.email", equalTo(request.getEmail()));
    
    // Store ID for cleanup
    String createdId = response.jsonPath().getString("data.id");
    testDataIds.add(createdId);
}

// ============ CREATE WITH VALIDATION ERROR ============
@Test
public void testCreateEmployee_WithMissingFields_Returns400() {
    EmployeeRequest request = EmployeeRequest.builder()
        .firstName("John")
        // Missing required fields
        .build();
    
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
        .contentType(ContentType.JSON)
        .body(request)
    .when()
        .post(apiVersion + "/employees");
    
    response.then()
        .statusCode(400)
        .body("success", equalTo(false))
        .body("errors", notNullValue());
}

// ============ CREATE DUPLICATE ============
@Test
public void testCreateEmployee_WithDuplicateEmail_Returns409() {
    // First create
    EmployeeRequest request = createValidEmployeeRequest();
    
    Response firstResponse = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
        .contentType(ContentType.JSON)
        .body(request)
    .when()
        .post(apiVersion + "/employees");
    
    firstResponse.then().statusCode(201);
    String createdId = firstResponse.jsonPath().getString("data.id");
    testDataIds.add(createdId);
    
    // Try duplicate
    Response secondResponse = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
        .contentType(ContentType.JSON)
        .body(request)
    .when()
        .post(apiVersion + "/employees");
    
    secondResponse.then()
        .statusCode(409)
        .body("success", equalTo(false));
}
```

### 7.4 PUT Test Patterns

```java
// ============ UPDATE RESOURCE ============
@Test
public void testUpdateEmployee_ReturnsUpdatedEmployee() {
    // Create employee first
    String employeeId = createTestEmployee();
    
    // Update request
    Map<String, Object> updateData = new HashMap<>();
    updateData.put("position", "Senior Software Engineer");
    updateData.put("phone", "9876543210");
    
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
        .contentType(ContentType.JSON)
        .body(updateData)
    .when()
        .put(apiVersion + "/employees/" + employeeId);
    
    response.then()
        .statusCode(200)
        .body("success", equalTo(true))
        .body("data.position", equalTo("Senior Software Engineer"))
        .body("data.phone", equalTo("9876543210"));
}
```

### 7.5 DELETE Test Patterns

```java
// ============ DELETE RESOURCE ============
@Test
public void testDeleteEmployee_Returns204() {
    // Create employee first
    String employeeId = createTestEmployee();
    
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
    .when()
        .delete(apiVersion + "/employees/" + employeeId);
    
    response.then()
        .statusCode(204);
    
    // Verify deletion
    given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
    .when()
        .get(apiVersion + "/employees/" + employeeId)
    .then()
        .statusCode(404);
}

// ============ DELETE NOT FOUND ============
@Test
public void testDeleteEmployee_NotFound_Returns404() {
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
    .when()
        .delete(apiVersion + "/employees/INVALID-ID");
    
    response.then()
        .statusCode(404);
}
```

---

## 8. Data-Driven API Testing

### 8.1 DataProvider from Excel

```java
@DataProvider(name = "employeeCreateData")
public Object[][] employeeCreateData() {
    return ExcelHelper.readExcel(
        "testdata/EmployeeAPIData.xlsx",
        "CreateEmployee"
    );
}

@Test(dataProvider = "employeeCreateData")
public void testCreateEmployee_DataDriven(
        String firstName,
        String lastName,
        String email,
        String department,
        String expectedStatus,
        String expectedError
) {
    EmployeeRequest request = EmployeeRequest.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .departmentId(department)
        .build();
    
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
        .contentType(ContentType.JSON)
        .body(request)
    .when()
        .post(apiVersion + "/employees");
    
    if ("SUCCESS".equals(expectedStatus)) {
        response.then()
            .statusCode(201)
            .body("success", equalTo(true));
        String id = response.jsonPath().getString("data.id");
        testDataIds.add(id);
    } else {
        response.then()
            .statusCode(400)
            .body("success", equalTo(false));
    }
}
```

### 8.2 DataProvider from JSON

```java
@DataProvider(name = "leaveRequestData")
public Object[][] leaveRequestData() {
    return new Object[][] {
        { "ANNUAL", "2026-07-01", "2026-07-05", 5, 201, true },
        { "SICK", "2026-07-10", "2026-07-10", 1, 201, true },
        { "ANNUAL", "2026-07-01", "2026-06-30", 0, 400, false }, // Invalid range
        { "INVALID", "2026-07-01", "2026-07-05", 0, 400, false }
    };
}

@Test(dataProvider = "leaveRequestData")
public void testCreateLeaveRequest_DataDriven(
        String leaveType,
        String startDate,
        String endDate,
        int expectedDays,
        int expectedStatus,
        boolean shouldSucceed
) {
    LeaveRequest request = LeaveRequest.builder()
        .leaveType(leaveType)
        .startDate(startDate)
        .endDate(endDate)
        .build();
    
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
        .contentType(ContentType.JSON)
        .body(request)
    .when()
        .post(apiVersion + "/leave/requests");
    
    response.then().statusCode(expectedStatus);
    
    if (shouldSucceed) {
        response.then()
            .body("success", equalTo(true))
            .body("data.days", equalTo(expectedDays));
    }
}
```

---

## 9. Error Handling Patterns

### 9.1 Common Error Codes

```java
public class ApiErrorCodes {
    
    // Authentication errors (AUTH_xxx)
    public static final String AUTH_001 = "AUTH_001"; // Invalid credentials
    public static final String AUTH_002 = "AUTH_002"; // Account locked
    public static final String AUTH_003 = "AUTH_003"; // Session expired
    public static final String AUTH_004 = "AUTH_004"; // Token invalid
    public static final String AUTH_005 = "AUTH_005"; // Unauthorized
    
    // Employee errors (EMP_xxx)
    public static final String EMP_001 = "EMP_001"; // Required field missing
    public static final String EMP_002 = "EMP_002"; // Invalid format
    public static final String EMP_003 = "EMP_003"; // Duplicate email
    public static final String EMP_404 = "EMP_404"; // Employee not found
    
    // Leave errors (LEAVE_xxx)
    public static final String LEAVE_001 = "LEAVE_001"; // Insufficient balance
    public static final String LEAVE_002 = "LEAVE_002"; // Date conflict
    public static final String LEAVE_003 = "LEAVE_003"; // Invalid dates
    public static final String LEAVE_404 = "LEAVE_404"; // Leave not found
}
```

### 9.2 Error Assertion Helper

```java
public class ApiAssertions {
    
    public static void assertSuccess(Response response) {
        response.then()
            .body("success", equalTo(true));
    }
    
    public static void assertError(Response response, String expectedCode) {
        response.then()
            .body("success", equalTo(false))
            .body("errorCode", equalTo(expectedCode));
    }
    
    public static void assertValidationError(Response response, String field) {
        response.then()
            .statusCode(400)
            .body("success", equalTo(false))
            .body("errors.find { it.field == '" + field + "' }.message", notNullValue());
    }
    
    public static void assertNotFound(Response response) {
        response.then()
            .statusCode(404)
            .body("success", equalTo(false));
    }
    
    public static void assertUnauthorized(Response response) {
        response.then()
            .statusCode(401);
    }
    
    public static void assertForbidden(Response response) {
        response.then()
            .statusCode(403);
    }
}
```

### 9.3 Retry Pattern for Flaky APIs

```java
public class ApiRetryHelper {
    
    public static Response executeWithRetry(Supplier<Response> apiCall, int maxRetries) {
        Response response = null;
        int attempts = 0;
        
        while (attempts < maxRetries) {
            try {
                response = apiCall.get();
                
                // If successful response (not 5xx), return
                if (response.statusCode() < 500) {
                    return response;
                }
                
                Log.warn("API returned 5xx error, retrying: " + response.statusCode());
                
            } catch (Exception e) {
                Log.warn("API call failed, retrying: " + e.getMessage());
            }
            
            attempts++;
            if (attempts < maxRetries) {
                sleep(1000 * attempts); // Exponential backoff
            }
        }
        
        throw new ApiRetryException("API call failed after " + maxRetries + " attempts");
    }
    
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

---

## 10. Performance Testing

### 10.1 Response Time Assertions

```java
@Test
public void testApiResponseTime() {
    long startTime = System.currentTimeMillis();
    
    Response response = given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
    .when()
        .get(apiVersion + "/employees");
    
    long responseTime = System.currentTimeMillis() - startTime;
    
    response.then()
        .statusCode(200);
    
    // Assert response time
    assertTrue(
        "API response time should be less than 2 seconds",
        responseTime < 2000
    );
    
    Log.info("API response time: " + responseTime + "ms");
}

// ============ USING RESPONSE TIME VALIDATION ============
@Test
public void testGetEmployees_ResponseTime() {
    given()
        .baseUri(baseUrl)
        .headers(AuthHelper.getAuthHeaders())
    .when()
        .get(apiVersion + "/employees")
    .then()
        .statusCode(200)
        .time(lessThan(2000L)); // Assert response time < 2 seconds
}
```

### 10.2 Rate Limiting

```java
@Test
public void testRateLimiting() {
    // Make multiple requests
    for (int i = 0; i < 110; i++) {
        Response response = given()
            .baseUri(baseUrl)
            .headers(AuthHelper.getAuthHeaders())
        .when()
            .get(apiVersion + "/employees");
        
        if (response.statusCode() == 429) {
            Log.info("Rate limit reached after " + i + " requests");
            
            // Extract rate limit headers
            int remaining = Integer.parseInt(
                response.getHeader("X-Rate-Limit-Remaining")
            );
            long resetTime = Long.parseLong(
                response.getHeader("X-Rate-Limit-Reset")
            );
            
            assertTrue(remaining >= 0);
            break;
        }
        
        assertEquals("Request " + i + " should succeed", 200, response.statusCode());
    }
}
```

---

## 11. Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-05-10 | Senior QA Engineer | Initial API rules |
