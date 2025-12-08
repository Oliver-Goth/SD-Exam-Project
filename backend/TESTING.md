# MTOGO Backend - Testing Strategy & Documentation

## Overview

This document describes the comprehensive testing strategy for the MTOGO backend application, including test structure, execution procedures, and quality metrics.

---

## Test Architecture

### Test Layers

The test suite is organized into four distinct layers:

#### 1. **Unit Tests (Service Layer)**
- **Location:** `src/test/java/com/mtogo/service/`
- **Framework:** JUnit 5 (Jupiter) + Mockito
- **Purpose:** Test business logic in isolation with mocked dependencies
- **Files:**
  - `CustomerServiceTest.java` - 4 tests
  - `OrderServiceTest.java` - 6 tests
  - `MenuItemServiceTest.java` - 7 tests
  - `PaymentServiceTest.java` - 5 tests
  - `RestaurantServiceTest.java` - 5 tests
  - `DeliveryAgentServiceTest.java` - 1 test
  - `FeedbackServiceTest.java` - 2 tests
- **Total:** 30 unit tests

#### 2. **Controller Tests (Web Layer)**
- **Location:** `src/test/java/com/mtogo/controller/`
- **Framework:** Spring Test + MockMvc
- **Purpose:** Test REST endpoints with mocked services
- **Files:**
  - `OrderControllerTest.java` - 3 tests (GET, authorization scenarios)
  - `CustomerControllerTest.java` - 3 tests (GET, POST endpoints)
  - `RestaurantControllerTest.java` - 3 tests (GET endpoints)
  - `PaymentControllerTest.java` - 1 test (POST with CSRF)
- **Total:** 10 controller tests
- **Features:**
  - MockMvc for HTTP request simulation
  - `@WithMockUser` for authentication testing
  - CSRF token handling for POST/PUT/DELETE requests
  - JSON path validation for response bodies

#### 3. **Factory Tests (Object Creation)**
- **Location:** `src/test/java/com/mtogo/factory/`
- **Framework:** JUnit 5
- **Purpose:** Test factory pattern for object creation
- **Files:**
  - `CustomerFactoryTest.java` - 4 tests (boundary values, null handling)
  - `OrderFactoryTest.java` - 1 test
  - `PaymentFactoryTest.java` - 1 test
- **Total:** 6 factory tests
- **Testing Strategy:** Equivalence partitioning and boundary value analysis

#### 4. **Integration Tests**
- **Location:** `src/test/java/com/mtogo/`
- **Framework:** Spring Boot Test + H2 Database
- **Purpose:** Test application with real database and full Spring context
- **Files:**
  - `MtogoBackendApplicationTests.java` - 1 test (context loading)
  - `OrderIntegrationTest.java` - 1 test (E2E order operations)
- **Total:** 2 integration tests
- **Database:** H2 in-memory (automatic initialization via Hibernate)

#### 5. **Adapter Tests (DTO Conversion)**
- **Location:** `src/test/java/com/mtogo/adapter/`
- **Purpose:** Test entity-to-DTO conversion logic
- **Files:**
  - `OrderAdapterTest.java` - 1 test
- **Total:** 1 adapter test

---

## Test Statistics

| Layer | Count | Framework | Scope |
|-------|-------|-----------|-------|
| Unit (Service) | 30 | JUnit 5 + Mockito | Isolated business logic |
| Controller | 10 | Spring Test + MockMvc | REST endpoints |
| Factory | 6 | JUnit 5 | Object creation |
| Integration | 2 | Spring Boot Test + H2 | Full application |
| Adapter | 1 | JUnit 5 | DTO conversion |
| **TOTAL** | **49** | - | - |

---

## Running Tests

### Run All Tests
```bash
mvn clean test
```
- Executes all 49 tests
- Generates JaCoCo coverage report
- Runs PMD quality checks

### Run Specific Test Classes
```bash
# Service tests only
mvn test -Dtest=CustomerServiceTest

# Controller tests only
mvn test -Dtest=OrderControllerTest

# Multiple test classes
mvn test -Dtest=CustomerServiceTest,OrderControllerTest
```

### Run Tests with Coverage Report
```bash
mvn clean test jacoco:report
```
- Generates HTML coverage report at: `target/site/jacoco/index.html`
- Shows line coverage, branch coverage, and complexity metrics
- Analyzes 41 classes

### Run Code Quality Analysis
```bash
# PMD violations check
mvn pmd:check

# Generate detailed PMD report
mvn pmd:pmd

# Copy Paste Detection (duplicate code)
mvn pmd:cpd-check
```

### Run All Quality Checks
```bash
mvn clean test pmd:check jacoco:report
```

---

## Test Database Configuration

### Properties File
- **Location:** `src/test/resources/application-test.properties`
- **Profile:** Activated automatically by `@ActiveProfiles("test")`

### Database Details
```properties
spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.flyway.enabled=false
```

### Key Features
- **In-Memory Database:** H2 (no file I/O, fast execution)
- **Auto-Initialization:** Hibernate DDL creates/drops schema automatically
- **Flyway Disabled:** Migrations skipped in test environment
- **MySQL Compatibility:** MODE=MySQL ensures compatibility with production queries

---

## Testing Patterns & Best Practices

### Unit Test Pattern (Mockito)
```java
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    
    @Mock
    private CustomerRepository repository;
    
    @InjectMocks
    private CustomerService service;
    
    @Test
    void createCustomer_validInput_returnsCustomer() {
        // Arrange
        CustomerDTO dto = new CustomerDTO(...);
        when(repository.save(any())).thenReturn(customer);
        
        // Act
        Customer result = service.createCustomer(dto);
        
        // Assert
        assertNotNull(result);
        assertEquals("Expected", result.getName());
        verify(repository, times(1)).save(any());
    }
}
```

### Controller Test Pattern (MockMvc)
```java
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private OrderService service;
    
    @Test
    @WithMockUser(username = "testuser", password = "testpassword")
    void getOrderById_found_returnsOrder() throws Exception {
        when(service.getOrderById(1L)).thenReturn(orderDTO);
        
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }
}
```

### Integration Test Pattern
```java
@SpringBootTest
@ActiveProfiles("test")
class OrderIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private OrderRepository repository;
    
    @Test
    @WithMockUser
    void getAllOrders_returns200() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk());
    }
}
```

### Factory Test Pattern (Boundary Value Analysis)
```java
class CustomerFactoryTest {
    
    @Test
    void createGuestCustomer_minBoundaryName() {
        // Test shortest valid name (1 character)
        Customer customer = CustomerFactory.createGuestCustomer(
            "A", "12345", "a@test.com", "City"
        );
        assertEquals("A", customer.getName());
    }
    
    @Test
    void createGuestCustomer_nullName_allowed() {
        // Test edge case: null handling
        Customer customer = CustomerFactory.createGuestCustomer(
            null, "12345", "guest@test.com", "City"
        );
        assertNull(customer.getName());
    }
}
```

---

## Authentication Testing

### @WithMockUser
```java
@Test
@WithMockUser(username = "testuser", password = "testpassword")
void authenticatedEndpoint_withUser_returns200() throws Exception {
    // User is authenticated
    mockMvc.perform(get("/api/orders"))
            .andExpect(status().isOk());
}
```

### Unauthenticated Requests
```java
@Test
void authenticatedEndpoint_noUser_returns401() throws Exception {
    // No @WithMockUser annotation
    mockMvc.perform(get("/api/orders"))
            .andExpect(status().isUnauthorized());
}
```

### CSRF Token for POST/PUT/DELETE
```java
@Test
@WithMockUser
void createOrder_withCSRFToken_returns200() throws Exception {
    mockMvc.perform(post("/api/orders")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto))
            .with(SecurityMockMvcRequestPostProcessors.csrf()))
            .andExpect(status().isOk());
}
```

---

## Code Coverage Metrics

### JaCoCo Report
Run code coverage analysis:
```bash
mvn clean test jacoco:report
```

**Report Location:** `target/site/jacoco/index.html`

**Coverage Breakdown:**
- **Line Coverage:** % of lines executed by tests
- **Branch Coverage:** % of decision branches executed
- **Complexity:** Cyclomatic complexity per class

**Current Status:**
- 41 classes analyzed
- All critical business logic covered
- Adapters and DTOs tested
- Factory patterns tested

---

## Code Quality Metrics

### PMD Analysis
```bash
mvn pmd:check
```
- Checks for code quality violations
- Identifies unused variables/imports
- Reports complexity issues
- **Current Status:** 0 violations (PASS)

### Duplicate Code Detection
```bash
mvn pmd:cpd-check
```
- Detects copy-paste code
- Identifies duplicated logic
- **Current Status:** PASS

### Build Quality Gates
```bash
mvn clean test pmd:check pmd:cpd-check jacoco:report
```
- All tests must pass
- No PMD violations
- No duplicate code
- Code coverage report generated

---

## CI/CD Integration

### Full Quality Check Pipeline
```bash
#!/bin/bash
cd backend
mvn clean
mvn test
mvn pmd:check
mvn pmd:cpd-check
mvn jacoco:report
echo "All checks passed!"
```

### Expected Output
```
[INFO] Tests run: 49, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS - All 49 tests passing
[INFO] PMD version: 6.55.0
[INFO] BUILD SUCCESS - No PMD violations
[INFO] Analyzed bundle 'mtogo-backend' with 41 classes
[INFO] BUILD SUCCESS - Coverage report generated
```

---

## Troubleshooting

### Common Issues & Solutions

#### **Tests Fail: "H2 Schema Not Creating"**
- **Cause:** Database initialization issue
- **Solution:** Ensure `spring.jpa.hibernate.ddl-auto=create-drop` in test properties
- **Verify:** Check `application-test.properties` is in `src/test/resources`

#### **Authentication Tests Fail with 403 Forbidden**
- **Cause:** CSRF token missing on POST/PUT/DELETE
- **Solution:** Add `.with(SecurityMockMvcRequestPostProcessors.csrf())`
- **Example:** See CSRF Token for POST/PUT/DELETE section above

#### **Tests Fail: "Class Not Found"**
- **Cause:** Test class not in correct package or file not compiled
- **Solution:** Ensure test class is in `src/test/java/com/mtogo/` structure
- **Rebuild:** Run `mvn clean compile`

#### **PMD Reports False Positives**
- **Solution:** Configure PMD in `pom.xml` with specific rule sets
- **Alternative:** Add `@SuppressWarnings("pmd:RuleName")` if necessary

---

## Test Execution Time

| Phase | Time |
|-------|------|
| Compile | ~2s |
| Unit Tests (30) | ~1s |
| Controller Tests (10) | ~2s |
| Integration Tests (2) | ~1s |
| Factory Tests (6) | <1s |
| Adapter Tests (1) | <1s |
| JaCoCo Report | ~1s |
| PMD Check | ~2s |
| **Total** | ~10-12s |

---

## Best Practices for Writing New Tests

1. **Follow Naming Convention:** `methodName_scenario_expectedResult`
   - ✅ `createOrder_validInput_returnsOrder`
   - ❌ `test1`, `createOrderTest`

2. **Use Arrange-Act-Assert Pattern**
   ```java
   // Arrange - setup data and mocks
   OrderDTO dto = new OrderDTO(...);
   when(service.create(dto)).thenReturn(saved);
   
   // Act - execute method
   Order result = service.createOrder(dto);
   
   // Assert - verify results
   assertEquals(expected, result);
   ```

3. **Test One Behavior Per Test**
   - ✅ One assertion per test (or related assertions)
   - ❌ Multiple unrelated assertions

4. **Use Descriptive Test Data**
   - ✅ `new Customer("John Doe", "john@example.com", "555-1234")`
   - ❌ `new Customer("a", "b", "c")`

5. **Mock External Dependencies**
   - Always mock repositories, external APIs, services
   - Use `@Mock` for dependencies, `@InjectMocks` for class under test

6. **Test Edge Cases**
   - Null inputs
   - Empty collections
   - Boundary values
   - Invalid states

---

## References

- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Test Documentation](https://spring.io/guides/gs/testing-web/)
- [JaCoCo Maven Plugin](https://www.jacoco.org/jacoco/trunk/doc/maven.html)
- [PMD Documentation](https://pmd.github.io/)

---

## Contact & Support

For questions about the testing strategy, refer to this document or contact the development team.

**Last Updated:** November 23, 2025
