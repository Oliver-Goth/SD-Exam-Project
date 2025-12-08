# MTOGO Backend API Documentation

## Overview

This document provides comprehensive API endpoint documentation for the MTOGO food delivery backend. All endpoints require authentication via HTTP Basic Auth or token-based auth (configured in SecurityConfig).

**Base URL:** `http://localhost:8080`

---

## Authentication

### HTTP Basic Authentication
All endpoints require authentication. Use the following credentials:

```
Username: testuser
Password: testpassword
```

### Example Request with Basic Auth
```bash
curl -u testuser:testpassword http://localhost:8080/api/customers
```

### In JavaScript/Fetch
```javascript
const headers = new Headers({
    'Authorization': 'Basic ' + btoa('testuser:testpassword'),
    'Content-Type': 'application/json'
});
```

### In Postman
1. Select "Basic Auth" in Authorization tab
2. Enter username: `testuser`
3. Enter password: `testpassword`

---

## Customer Endpoints

### 1. Get All Customers
```http
GET /api/customers
```

**Authentication:** Required (`@WithMockUser`)

**Response:** 200 OK
```json
[
    {
        "id": 1,
        "name": "John Doe",
        "email": "john@example.com",
        "phone": "555-1234",
        "address": "123 Main St"
    },
    {
        "id": 2,
        "name": "Jane Smith",
        "email": "jane@example.com",
        "phone": "555-5678",
        "address": "456 Oak Ave"
    }
]
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/customers
```

---

### 2. Get Customer by ID
```http
GET /api/customers/{id}
```

**Parameters:**
- `id` (path) - Customer ID (Long)

**Response:** 200 OK
```json
{
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "555-1234",
    "address": "123 Main St"
}
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/customers/1
```

**Error Response:** 200 OK (with null body if not found)

---

### 3. Create Customer
```http
POST /api/customers
Content-Type: application/json
```

**Request Body:**
```json
{
    "name": "Alice Johnson",
    "email": "alice@example.com",
    "phone": "555-9999",
    "address": "789 Pine Rd"
}
```

**Response:** 200 OK
```json
{
    "id": 3,
    "name": "Alice Johnson",
    "email": "alice@example.com",
    "phone": "555-9999",
    "address": "789 Pine Rd"
}
```

**Example:**
```bash
curl -X POST -u testuser:testpassword \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@test.com","phone":"555-9999","address":"City"}' \
  http://localhost:8080/api/customers
```

**Validation Rules:**
- `name`: Required, string
- `email`: Required, valid email format
- `phone`: Required, string
- `address`: Required, string

---

## Order Endpoints

### 1. Get All Orders
```http
GET /api/orders
```

**Authentication:** Required

**Response:** 200 OK
```json
[
    {
        "id": 1,
        "customerId": 1,
        "restaurantId": 2,
        "totalPrice": 45.99,
        "status": "PENDING",
        "items": [
            {
                "id": 1,
                "name": "Burger",
                "price": 12.99,
                "quantity": 2
            }
        ]
    }
]
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/orders
```

---

### 2. Get Order by ID
```http
GET /api/orders/{id}
```

**Parameters:**
- `id` (path) - Order ID (Long)

**Response:** 200 OK
```json
{
    "id": 1,
    "customerId": 1,
    "restaurantId": 2,
    "totalPrice": 45.99,
    "status": "PENDING",
    "items": [
        {
            "id": 1,
            "name": "Burger",
            "price": 12.99,
            "quantity": 2
        }
    ]
}
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/orders/1
```

---

### 3. Create Order
```http
POST /api/orders
Content-Type: application/json
```

**Request Body:**
```json
{
    "customerId": 1,
    "restaurantId": 2,
    "items": [
        {
            "menuItemId": 5,
            "quantity": 2,
            "price": 12.99
        }
    ],
    "totalPrice": 25.98
}
```

**Response:** 200 OK
```json
{
    "id": 5,
    "customerId": 1,
    "restaurantId": 2,
    "totalPrice": 25.98,
    "status": "PENDING"
}
```

**Example:**
```bash
curl -X POST -u testuser:testpassword \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 1,
    "restaurantId": 2,
    "items": [{"menuItemId": 5, "quantity": 2, "price": 12.99}],
    "totalPrice": 25.98
  }' \
  http://localhost:8080/api/orders
```

**Validation Rules:**
- `customerId`: Required, must exist
- `restaurantId`: Required, must exist
- `items`: Required, non-empty array
- `totalPrice`: Required, positive decimal

---

## Restaurant Endpoints

### 1. Get All Active Restaurants
```http
GET /api/restaurants
```

**Authentication:** Required

**Response:** 200 OK
```json
[
    {
        "id": 1,
        "name": "Pizza Palace",
        "email": "contact@pizzapalace.com",
        "phone": "555-1111",
        "address": "123 Food Street",
        "active": true,
        "menuItems": [
            {
                "id": 1,
                "name": "Margherita Pizza",
                "price": 14.99
            }
        ]
    }
]
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/restaurants
```

---

### 2. Get Restaurant by ID
```http
GET /api/restaurants/{id}
```

**Parameters:**
- `id` (path) - Restaurant ID (Long)

**Response:** 200 OK
```json
{
    "id": 1,
    "name": "Pizza Palace",
    "email": "contact@pizzapalace.com",
    "phone": "555-1111",
    "address": "123 Food Street",
    "active": true,
    "menuItems": [
        {
            "id": 1,
            "name": "Margherita Pizza",
            "price": 14.99
        }
    ]
}
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/restaurants/1
```

---

### 3. Create Restaurant
```http
POST /api/restaurants
Content-Type: application/json
```

**Request Body:**
```json
{
    "name": "Burger King",
    "email": "contact@burgerking.com",
    "phone": "555-2222",
    "address": "456 Fast Food Ave",
    "active": true
}
```

**Response:** 200 OK
```json
{
    "id": 5,
    "name": "Burger King",
    "email": "contact@burgerking.com",
    "phone": "555-2222",
    "address": "456 Fast Food Ave",
    "active": true
}
```

**Example:**
```bash
curl -X POST -u testuser:testpassword \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Burger King",
    "email": "contact@burgerking.com",
    "phone": "555-2222",
    "address": "456 Fast Food Ave",
    "active": true
  }' \
  http://localhost:8080/api/restaurants
```

---

## Menu Item Endpoints

### 1. Get All Menu Items
```http
GET /api/menu-items
```

**Authentication:** Required

**Response:** 200 OK
```json
[
    {
        "id": 1,
        "name": "Margherita Pizza",
        "description": "Classic Italian pizza",
        "price": 14.99,
        "restaurantId": 1,
        "available": true
    }
]
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/menu-items
```

---

### 2. Get Menu Item by ID
```http
GET /api/menu-items/{id}
```

**Parameters:**
- `id` (path) - Menu Item ID (Long)

**Response:** 200 OK
```json
{
    "id": 1,
    "name": "Margherita Pizza",
    "description": "Classic Italian pizza",
    "price": 14.99,
    "restaurantId": 1,
    "available": true
}
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/menu-items/1
```

---

### 3. Create Menu Item
```http
POST /api/menu-items
Content-Type: application/json
```

**Request Body:**
```json
{
    "name": "Pepperoni Pizza",
    "description": "Pizza with pepperoni",
    "price": 16.99,
    "restaurantId": 1,
    "available": true
}
```

**Response:** 200 OK
```json
{
    "id": 5,
    "name": "Pepperoni Pizza",
    "description": "Pizza with pepperoni",
    "price": 16.99,
    "restaurantId": 1,
    "available": true
}
```

**Example:**
```bash
curl -X POST -u testuser:testpassword \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pepperoni Pizza",
    "description": "Pizza with pepperoni",
    "price": 16.99,
    "restaurantId": 1,
    "available": true
  }' \
  http://localhost:8080/api/menu-items
```

---

## Payment Endpoints

### 1. Create Payment
```http
POST /api/payments
Content-Type: application/json
```

**Request Body:**
```json
{
    "orderId": 1,
    "amount": 45.99,
    "method": "CARD",
    "status": "PAID"
}
```

**Response:** 200 OK
```json
{
    "id": 1,
    "orderId": 1,
    "amount": 45.99,
    "method": "CARD",
    "status": "PAID",
    "createdAt": "2025-11-23T12:00:00"
}
```

**Example:**
```bash
curl -X POST -u testuser:testpassword \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": 1,
    "amount": 45.99,
    "method": "CARD",
    "status": "PAID"
  }' \
  http://localhost:8080/api/payments
```

**Supported Methods:**
- `CARD` - Credit/Debit card
- `PAYPAL` - PayPal
- `CASH` - Cash on delivery

**Payment Status:**
- `PENDING` - Awaiting payment
- `PAID` - Payment completed
- `FAILED` - Payment failed
- `REFUNDED` - Refund issued

---

## Delivery Agent Endpoints

### 1. Get All Delivery Agents
```http
GET /api/delivery-agents
```

**Authentication:** Required

**Response:** 200 OK
```json
[
    {
        "id": 1,
        "name": "John Driver",
        "email": "john@delivery.com",
        "phone": "555-3333",
        "available": true
    }
]
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/delivery-agents
```

---

### 2. Get Delivery Agent by ID
```http
GET /api/delivery-agents/{id}
```

**Parameters:**
- `id` (path) - Delivery Agent ID (Long)

**Response:** 200 OK
```json
{
    "id": 1,
    "name": "John Driver",
    "email": "john@delivery.com",
    "phone": "555-3333",
    "available": true
}
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/delivery-agents/1
```

---

### 3. Create Delivery Agent
```http
POST /api/delivery-agents
Content-Type: application/json
```

**Request Body:**
```json
{
    "name": "Jane Rider",
    "email": "jane@delivery.com",
    "phone": "555-4444",
    "available": true
}
```

**Response:** 200 OK
```json
{
    "id": 5,
    "name": "Jane Rider",
    "email": "jane@delivery.com",
    "phone": "555-4444",
    "available": true
}
```

**Example:**
```bash
curl -X POST -u testuser:testpassword \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Rider",
    "email": "jane@delivery.com",
    "phone": "555-4444",
    "available": true
  }' \
  http://localhost:8080/api/delivery-agents
```

---

## Feedback Endpoints

### 1. Get All Feedback
```http
GET /api/feedback
```

**Authentication:** Required

**Response:** 200 OK
```json
[
    {
        "id": 1,
        "orderId": 1,
        "customerId": 1,
        "rating": 5,
        "comment": "Great service!",
        "createdAt": "2025-11-23T12:00:00"
    }
]
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/feedback
```

---

### 2. Get Feedback by ID
```http
GET /api/feedback/{id}
```

**Parameters:**
- `id` (path) - Feedback ID (Long)

**Response:** 200 OK
```json
{
    "id": 1,
    "orderId": 1,
    "customerId": 1,
    "rating": 5,
    "comment": "Great service!",
    "createdAt": "2025-11-23T12:00:00"
}
```

**Example:**
```bash
curl -u testuser:testpassword http://localhost:8080/api/feedback/1
```

---

### 3. Create Feedback
```http
POST /api/feedback
Content-Type: application/json
```

**Request Body:**
```json
{
    "orderId": 1,
    "customerId": 1,
    "rating": 5,
    "comment": "Excellent food and fast delivery!"
}
```

**Response:** 200 OK
```json
{
    "id": 5,
    "orderId": 1,
    "customerId": 1,
    "rating": 5,
    "comment": "Excellent food and fast delivery!",
    "createdAt": "2025-11-23T12:10:00"
}
```

**Example:**
```bash
curl -X POST -u testuser:testpassword \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": 1,
    "customerId": 1,
    "rating": 5,
    "comment": "Excellent food and fast delivery!"
  }' \
  http://localhost:8080/api/feedback
```

**Validation Rules:**
- `rating`: Integer 1-5 (required)
- `comment`: String (optional)
- `orderId`: Must exist
- `customerId`: Must exist

---

## Error Responses

### 401 Unauthorized
```json
{
    "status": 401,
    "error": "Unauthorized",
    "message": "Invalid credentials"
}
```

**When:** Missing or invalid authentication

---

### 400 Bad Request
```json
{
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed: name is required"
}
```

**When:** Invalid request body or missing required fields

---

### 404 Not Found
```json
{
    "status": 404,
    "error": "Not Found",
    "message": "Customer with ID 999 not found"
}
```

**When:** Resource doesn't exist (Note: some endpoints return 200 with null body)

---

## Quick Start - Frontend Integration

### 1. Setup JavaScript Fetch Wrapper
```javascript
const API_BASE = 'http://localhost:8080/api';
const AUTH = btoa('testuser:testpassword');

async function apiCall(endpoint, method = 'GET', body = null) {
    const options = {
        method,
        headers: {
            'Authorization': `Basic ${AUTH}`,
            'Content-Type': 'application/json'
        }
    };

    if (body) {
        options.body = JSON.stringify(body);
    }

    const response = await fetch(`${API_BASE}${endpoint}`, options);
    return response.json();
}
```

### 2. Fetch All Customers
```javascript
const customers = await apiCall('/customers');
console.log(customers);
```

### 3. Create New Order
```javascript
const order = await apiCall('/orders', 'POST', {
    customerId: 1,
    restaurantId: 2,
    items: [{ menuItemId: 5, quantity: 2, price: 12.99 }],
    totalPrice: 25.98
});
console.log(order);
```

### 4. Get Specific Restaurant
```javascript
const restaurant = await apiCall('/restaurants/1');
console.log(restaurant.menuItems);
```

---

## Testing Endpoints with Postman

### Step-by-step Setup:
1. **Create new request**
2. **Select HTTP method** (GET, POST, etc.)
3. **Enter URL:** `http://localhost:8080/api/customers`
4. **Go to Authorization tab**
5. **Select "Basic Auth"**
6. **Username:** `testuser`
7. **Password:** `testpassword`
8. **Click Send**

### Import Collection
You can import these endpoints into Postman by creating a collection with the following requests listed above.

---

## CORS Configuration

If calling from a different domain, ensure CORS is enabled in SecurityConfig:

```java
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()...
        return http.build();
    }
}
```

---

## Rate Limiting & Best Practices

- **No rate limiting** currently implemented
- Use reasonable request intervals (not more than 100/second)
- Implement client-side caching to reduce API calls
- Handle errors gracefully with retry logic

---

## Support

For API questions or issues:
1. Check this documentation
2. Review the test files in `src/test/java/com/mtogo/controller/`
3. Refer to TESTING.md for more details on how endpoints behave

**Last Updated:** November 23, 2025
