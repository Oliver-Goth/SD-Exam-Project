# MTOGO Legacy Monolithic System

### Overview
MTOGO is a **legacy monolithic food delivery platform** being modernized using **Spring Boot**, **React**, and **MySQL**.  
This version containerizes the existing monolith with Docker to simplify deployment, testing, and future migration to microservices.


##  Project Overview
This system contains:

### **1. Backend Service (Port 8080)**
Provides REST APIs:
- `/api/restaurants`
- `/api/orders`
- `/api/menu-items`
- `/api/payments`

### **2. API Gateway (Port 9000)**
Routes external traffic to the backend service.

### **3. MySQL Database (Port 3307)**
Stores application data.

---

#  How to Run the Project

## 1. Clone the repository
```bash
git clone https://github.com/YOUR_REPO_HERE
cd MTOGO
2. Build the backend & gateway JAR files
Run Maven inside each service:
cd backend
mvn clean package -DskipTests
cd ..

cd gateway
mvn clean package -DskipTests
cd ..
This creates:
•	backend/target/backend-0.0.1-SNAPSHOT.jar
•	gateway/target/gateway-0.0.1-SNAPSHOT.jar

 3. Start everything using Docker Compose
From the project root:
docker-compose build
docker-compose up
This starts all services:
Service	Port	Description
Gateway	9000	External API entry
Backend	8080	Main API service
MySQL	3307	Database

4. Test the APIs
Direct backend call:
http://localhost:8080/api/restaurants
Through Gateway (recommended):
http://localhost:9000/api/restaurants
Both should return the same result.
 Stop the System
docker-compose down
To remove database volumes:
docker-compose down --volumes


## 🏗️ Project Structure

```
mtogo/
├── README.md
├── .gitignore
└── backend/
    ├── pom.xml                          # Maven configuration with dependencies
    ├── Dockerfile                       # Docker image configuration
    ├── mvnw & mvnw.cmd                  # Maven wrapper (Windows & Unix)
    ├── HELP.md                          # Spring Boot setup help
    │
    ├── src/
    │   ├── main/
    │   │   ├── java/com/mtogo/
    │   │   │   ├── MtogoBackendApplication.java    # Main Spring Boot entry point
    │   │   │   │
    │   │   │   ├── adapter/                        # Adapter pattern implementations
    │   │   │   │   └── (Adapter classes here)
    │   │   │   │
    │   │   │   ├── controller/                     # REST API Controllers
    │   │   │   │   └── (Controller classes here)
    │   │   │   │
    │   │   │   ├── service/                        # Business logic & Services
    │   │   │   │   └── (Service classes here)
    │   │   │   │
    │   │   │   ├── factory/                        # Factory pattern implementations
    │   │   │   │   └── (Factory classes here)
    │   │   │   │
    │   │   │   ├── model/                          # Domain models / Entities / JPA
    │   │   │   │   └── (Entity classes here)
    │   │   │   │
    │   │   │   ├── dto/                            # Data Transfer Objects (DTOs)
    │   │   │   │   └── (DTO classes here)
    │   │   │   │
    │   │   │   └── config/                         # Configuration classes
    │   │   │       └── (Config classes - Security, DB, etc.)
    │   │   │
    │   │   └── resources/
    │   │       ├── application.properties          # Main Spring Boot configuration
    │   │       ├── application-mysql.properties    # MySQL profile config (when ready)
    │   │       ├── static/                         # Static assets (CSS, JS, images)
    │   │       ├── templates/                      # HTML templates (if using Thymeleaf)
    │   │       └── db/
    │   │           └── migration/                  # Flyway database migrations
    │   │
    │   └── test/
    │       └── java/com/mtogo/
    │           ├── MtogoBackendApplicationTests.java    # Main application tests
    │           │
    │           ├── adapter/
    │           │   └── OrderAdapterTest.java            # Adapter layer tests
    │           │
    │           ├── controller/
    │           │   └── OrderControllerTest.java         # REST controller tests
    │           │
    │           ├── factory/
    │           │   └── OrderFactoryTest.java            # Factory tests
    │           │
    │           └── service/
    │               └── OrderServiceTest.java            # Service layer tests
    │
    └── target/                         # Compiled output (Maven build artifacts)
        ├── classes/                    # Compiled classes
        ├── generated-sources/          # Generated code
        ├── test-classes/               # Compiled test classes
        └── backend-0.0.1-SNAPSHOT.jar  # Packaged Spring Boot JAR
```

### 📦 Key Package Descriptions

| Package | Purpose |
|---------|---------|
| **adapter/** | Adapter pattern to integrate external systems or legacy code |
| **controller/** | REST API endpoints and request/response handling |
| **service/** | Core business logic and domain operations |
| **factory/** | Factory pattern for creating complex objects |
| **model/** | JPA entity classes representing database tables |
| **dto/** | Data Transfer Objects for API contracts and internal communication |
| **config/** | Spring configuration (Security, DataSource, Bean definitions, etc.) |

---

##  Backend (Spring Boot)

### 🔧 Requirements
- Java 17+
- Maven 3.9+
- Docker Desktop (latest)
- (Optional) MySQL if running locally without Docker

### How to Run Locally (Without Docker)
1. Open a terminal in the `backend` folder.
2. Run:
   ```bash
   ./mvnw spring-boot:run
   Once started, visit:
   http://localhost:8080/api/test
   MTOGO backend is running!

### How to Run Locally no image yet (With Docker)
docker-compose up --build
backend:
  build: ./backend
cd backend
mvn clean package -DskipTests
backend/target/backend-0.0.1-SNAPSHOT.jar
docker-compose --env-file .env.local up --build
docker ps
CONTAINER ID   IMAGE             STATUS          PORTS
abc1234        mtogo_backend     Up 1 min        0.0.0.0:8080->8080/tcp
def5678        mysql:8           Up 1 min        0.0.0.0:3307->3306/tcp

### How to Rebuild and Run Locallu inside Docker
docker-compose down -v
Stop containers: docker-compose down
Rebuild backend image: docker-compose build --no-cache
cd backend
mvn clean package -DskipTests
cd ..
docker compose up --build

---

##  API Endpoints

### Base URL

http://localhost:8080/api


All endpoints support CORS from http://localhost:3000 (React frontend).

---

###  **Restaurants** (/api/restaurants)

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| GET | /api/restaurants | Get all active restaurants | curl http://localhost:8080/api/restaurants |
| GET | /api/restaurants/{id} | Get restaurant by ID | curl http://localhost:8080/api/restaurants/1 |
| POST | /api/restaurants | Create a new restaurant | See example below |
| DELETE | /api/restaurants/{id} | Delete a restaurant | curl -X DELETE http://localhost:8080/api/restaurants/1 |

**POST Example - Create Restaurant:**
``ash
curl -X POST http://localhost:8080/api/restaurants \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pizza Palace",
    "address": "123 Main St",
    "phone": "555-1234",
    "isActive": true
  }'
``

---

###  **Customers** (/api/customers)

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| GET | /api/customers | Get all customers | curl http://localhost:8080/api/customers |
| GET | /api/customers/{id} | Get customer by ID | curl http://localhost:8080/api/customers/1 |
| POST | /api/customers | Create a new customer | See example below |
| POST | /api/customers/guest | Create guest customer (no account) | See example below |
| DELETE | /api/customers/{id} | Delete a customer | curl -X DELETE http://localhost:8080/api/customers/1 |

**POST Example - Create Customer:**
``ash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "555-5678",
    "address": "456 Oak Ave"
  }'
``

**POST Example - Create Guest Customer:**
``ash
curl -X POST http://localhost:8080/api/customers/guest \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Guest",
    "phone": "555-9999",
    "email": "guest@example.com",
    "address": "789 Elm St"
  }'
``

---

###  **Menu Items** (/api/menu-items)

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| GET | /api/menu-items/restaurant/{restaurantId} | Get menu items for a restaurant | curl http://localhost:8080/api/menu-items/restaurant/1 |
| POST | /api/menu-items/restaurant/{restaurantId} | Add menu item to restaurant | See example below |

**POST Example - Create Menu Item:**
``ash
curl -X POST http://localhost:8080/api/menu-items/restaurant/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Margherita Pizza",
    "description": "Classic pizza with tomato and mozzarella",
    "price": 12.99,
    "restaurantId": 1
  }'
``

---

###  **Orders** (/api/orders)

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| GET | /api/orders | Get all orders | curl http://localhost:8080/api/orders |
| GET | /api/orders/{id} | Get order by ID | curl http://localhost:8080/api/orders/1 |
| GET | /api/orders/status/{status} | Get orders by status | curl http://localhost:8080/api/orders/status/pending |
| POST | /api/orders | Create a new order | See example below |
| POST | /api/orders/guest | Create order as guest | See example below |
| DELETE | /api/orders/{id} | Delete an order | curl -X DELETE http://localhost:8080/api/orders/1 |

**POST Example - Create Order:**
``ash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 1,
    "restaurantId": 1,
    "items": [
      {
        "menuItemId": 1,
        "quantity": 2
      }
    ],
    "status": "pending",
    "totalAmount": 25.98
  }'
``

**POST Example - Create Guest Order:**
``ash
curl -X POST http://localhost:8080/api/orders/guest \
  -H "Content-Type: application/json" \
  -d '{
    "restaurantId": 1,
    "items": [
      {
        "menuItemId": 1,
        "quantity": 1
      }
    ],
    "status": "pending",
    "totalAmount": 12.99
  }'
``

---

###  **Payments** (/api/payments)

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| GET | /api/payments/order/{orderId} | Get payment by order ID | curl http://localhost:8080/api/payments/order/1 |
| POST | /api/payments | Create a payment | See example below |

**POST Example - Create Payment:**
``ash
curl -X POST http://localhost:8080/api/payments \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": 1,
    "amount": 25.98,
    "paymentMethod": "credit_card",
    "status": "completed"
  }'
``

---

###  **Delivery Agents** (/api/agents)

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| GET | /api/agents/active | Get all active delivery agents | curl http://localhost:8080/api/agents/active |

---

###  **Feedback** (/api/feedback)

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| POST | /api/feedback/order/{orderId} | Submit feedback for an order | See example below |
| GET | /api/feedback/order/{orderId} | Get feedback for an order | curl http://localhost:8080/api/feedback/order/1 |

**POST Example - Create Feedback:**
``ash
curl -X POST http://localhost:8080/api/feedback/order/1 \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": 1,
    "rating": 5,
    "comment": "Great food and fast delivery!",
    "customerId": 1
  }'
``

---

###  **Test Endpoint**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/test | Health check - returns "MTOGO backend is running!" |

---

##  Complete File Structure

``
mtogo/
 README.md                                    # This file - project documentation
 docker-compose.yml                           # Docker Compose configuration for multi-container setup

 backend/
    Dockerfile                               # Docker image for Spring Boot backend
    HELP.md                                  # Spring Boot help documentation
    pom.xml                                  # Maven project configuration & dependencies
    mvnw                                     # Maven wrapper for Unix/Linux
    mvnw.cmd                                 # Maven wrapper for Windows
   
    src/
       main/
          java/com/mtogo/
             MtogoBackendApplication.java         # Main Spring Boot application entry point
             TestController.java                  # Test endpoint controller
            
             adapter/                             # Adapter Pattern - Converts between domain and external models
                CustomerAdapter.java             # Maps Customer entity  CustomerDTO
                DeliveryAgentAdapter.java        # Maps DeliveryAgent entity  DeliveryAgentDTO
                FeedbackAdapter.java             # Maps Feedback entity  FeedbackDTO
                MenuItemAdapter.java             # Maps MenuItem entity  MenuItemDTO
                OrderAdapter.java                # Maps Order entity  OrderDTO
                PaymentAdapter.java              # Maps Payment entity  PaymentDTO
                RestaurantAdapter.java           # Maps Restaurant entity  RestaurantDTO
            
             config/                               # Configuration Classes
                SecurityConfig.java              # Spring Security configuration
            
             controller/                           # REST API Controllers - Handle HTTP requests
                CustomerController.java          # /api/customers endpoints
                DeliveryAgentController.java     # /api/agents endpoints
                FeedbackController.java          # /api/feedback endpoints
                MenuItemController.java          # /api/menu-items endpoints
                OrderController.java             # /api/orders endpoints
                PaymentController.java           # /api/payments endpoints
                RestaurantController.java        # /api/restaurants endpoints
            
             dto/                                  # Data Transfer Objects - API contracts
                CustomerDTO.java                 # Customer data for API transfer
                DeliveryAgentDTO.java            # DeliveryAgent data for API transfer
                FeedbackDTO.java                 # Feedback data for API transfer
                MenuItemDTO.java                 # MenuItem data for API transfer
                OrderDTO.java                    # Order data for API transfer
                PaymentDTO.java                  # Payment data for API transfer
                RestaurantDTO.java               # Restaurant data for API transfer
            
             factory/                              # Factory Pattern - Creates complex objects
                CustomerFactory.java             # Creates Customer objects with defaults
                OrderFactory.java                # Creates Order objects with defaults
                PaymentFactory.java              # Creates Payment objects with defaults
            
             model/                                # JPA Entity Classes - Database models
                Customer.java                    # Customer entity with @Entity annotation
                DeliveryAgent.java               # DeliveryAgent entity
                Feedback.java                    # Feedback entity
                MenuItem.java                    # MenuItem entity
                Order.java                       # Order entity
                Payment.java                     # Payment entity
                Restaurant.java                  # Restaurant entity
            
             repository/                           # Spring Data JPA Repositories - Database access
                CustomerRepository.java          # CRUD operations for Customer
                DeliveryAgentRepository.java     # CRUD operations for DeliveryAgent
                FeedbackRepository.java          # CRUD operations for Feedback
                MenuItemRepository.java          # CRUD operations for MenuItem
                OrderRepository.java             # CRUD operations for Order
                PaymentRepository.java           # CRUD operations for Payment
                RestaurantRepository.java        # CRUD operations for Restaurant
            
             service/                              # Business Logic Services - Core application logic
                 CustomerService.java             # Customer business logic
                 DeliveryAgentService.java        # DeliveryAgent business logic
                 FeedbackService.java             # Feedback business logic
                 MenuItemService.java             # MenuItem business logic
                 OrderService.java                # Order business logic
                 PaymentService.java              # Payment business logic
                 RestaurantService.java           # Restaurant business logic
         
          resources/                                # Application resources & configuration
              application.properties               # Main Spring Boot configuration
              application-mysql.properties         # MySQL profile configuration (for DB setup)
              static/                              # Static assets (CSS, JavaScript, images)
              templates/                           # HTML templates (if using Thymeleaf)
              db/
                  migration/                       # Flyway database migration scripts
      
       test/                                        # Unit & Integration Tests
           java/com/mtogo/
               MtogoBackendApplicationTests.java    # Main application tests
               adapter/
                  (Adapter layer tests)
               controller/
                  (REST controller tests)
               factory/
                  (Factory pattern tests)
               service/
                   (Business logic service tests)
   
    target/                                           # Maven build output (compiled artifacts)
        classes/
           com/mtogo/                               # Compiled Java classes
        generated-sources/
        generated-test-sources/
        maven-archiver/
        maven-status/
        test-classes/                                # Compiled test classes
        backend-0.0.1-SNAPSHOT.jar                   # Packaged Spring Boot JAR executable

 .gitignore                                            # Git ignore rules for version control
``

###  Architecture Layers Explained

| Layer | Purpose | Files |
|-------|---------|-------|
| **Controller** | Receives HTTP requests, validates input | CustomerController, OrderController, etc. |
| **Service** | Contains business logic, transactions | CustomerService, OrderService, etc. |
| **Repository** | Database access, queries | CustomerRepository, OrderRepository, etc. |
| **Model** | JPA entities, database schema | Customer, Order, Payment, etc. |
| **DTO** | Data transfer between layers | CustomerDTO, OrderDTO, etc. |
| **Adapter** | Entity  DTO conversion | CustomerAdapter, OrderAdapter, etc. |
| **Factory** | Creates complex objects with defaults | CustomerFactory, OrderFactory, etc. |
| **Config** | Spring configuration, security | SecurityConfig.java |
