# MTOGO Backend

Spring Boot 3.3 + MySQL service with Docker support.

## Prerequisites
- Java 17
- Maven 3.9+
- Docker + Docker Compose

## Local build (Maven)
```bash
cd backend
mvn clean package
```
Jar lands in `backend/target/`.

## Environment variables (passwords)
Passwords and ports are kept in a local `.env` (ignored by Git). Sample:
```
BACKEND_PORT=8080
GATEWAY_PORT=9000
MYSQL_PORT=3307

```
Before running, create your own `.env` in the repo root and set the values you want. The backend reads `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, and `SPRING_DATASOURCE_PASSWORD`; Docker Compose passes these from `.env`.

**For new user:** after fetching the repo from GitHub, you must create their own `.env` file in the project root and fill in their preferred passwords/ports. The file is git-ignored so their secrets stay local.


## Run with Docker Compose
```bash
docker compose --env-file .env up --build
```
- Backend: http://localhost:${BACKEND_PORT} (defaults to 8080)
- MySQL: localhost:${MYSQL_PORT} (defaults to 3307)

## API endpoints (default port 8080)
Replace the port if you set a different `BACKEND_PORT`.
- Customers: `http://localhost:8080/api/customers` (GET, POST), `.../customers/{id}` (GET, DELETE), `.../customers/legacy` (POST), `.../customers/guest` (POST), `.../customers/login` (POST)
- Restaurants: `http://localhost:8080/api/restaurants` (GET, POST), `.../restaurants/{id}` (GET, DELETE)
- Menu items: `http://localhost:8080/api/menu-items/restaurant/{restaurantId}` (GET, POST)
- Orders: `http://localhost:8080/api/orders` (GET, POST), `.../orders/{id}` (GET, DELETE), `.../orders/status/{status}` (GET), `.../orders/guest` (POST)
- Payments: `http://localhost:8080/api/payments` (POST), `.../payments/order/{orderId}` (GET)
- Delivery agents: `http://localhost:8080/api/agents/active` (GET)
- Feedback: `http://localhost:8080/api/feedback/order/{orderId}` (POST, GET)

## Run the backend jar without Docker
```bash
cd backend
SPRING_DATASOURCE_URL="jdbc:mysql://127.0.0.1:3307/mtogo?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true" \
SPRING_DATASOURCE_USERNAME="mtogo_user" \
SPRING_DATASOURCE_PASSWORD="secret" \
java -jar target/*.jar
```

