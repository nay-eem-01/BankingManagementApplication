# Banking API - Spring Boot Application

A comprehensive RESTful API for banking operations built with Spring Boot 3.5.6, featuring user authentication with JWT
tokens, account management, fund transfers, and transaction history with pagination and filtering.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Project Setup](#project-setup)
- [Database Configuration](#database-configuration)
- [Building and Running](#building-and-running)
- [API Documentation](#api-documentation)
- [Authentication](#authentication)
- [Error Handling](#error-handling)

---

## Features

✅ **User Authentication**

- User registration (Sign Up) with email validation
- User login (Sign In) with JWT token generation
- Secure password hashing using BCrypt

✅ **Bank Account Management**

- Initialize bank account for authenticated users
- One account per user limitation
- Deposit funds functionality
- View account balance

✅ **Fund Transfer**

- Transfer funds between accounts
- Balance validation before transfer
- Transaction history tracking
- Unique transaction ID generation

✅ **Transaction Management**

- View all transactions with pagination
- Filter transactions by sender and recipient accounts
- Sort transactions in ascending/descending order
- Transaction history with timestamps

✅ **Security**

- JWT token-based authentication
- Spring Security integration
- BCrypt password encryption
- Authorization filters for protected endpoints

✅ **Data Validation**

- Input validation using Jakarta Validation annotations
- Email format validation
- Amount validation
- Account existence verification

---

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java 21** or higher
- **Maven 3.8.0** or higher
- **PostgreSQL 12** or higher
- **Git** (optional, for version control)

### Verify Installation

```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check PostgreSQL version
psql --version
```

## Project Setup

1. Clone or Download the Project

```bash
git clone <[repository-url](https://github.com/nay-eem-01/internship-first-project)>
cd firstProject
```

Or extract the downloaded ZIP file

2. Project Structure

```bash
firstProject/
├── src/
│ ├── main/
│ │ ├── java/org/example/firstproject/
│ │ │ ├── controller/
│ │ │ │ ├── AuthController.java
│ │ │ │ ├── BankAccountController.java
│ │ │ │ └── TransactionController.java
│ │ │ ├── service/
│ │ │ ├── entity/
│ │ │ ├── repository/
│ │ │ ├── model/
│ │ │ │ ├── request/
│ │ │ │ └── response/
│ │ │ ├── config/
│ │ │ ├── utils/
│ │ │ ├── specification/
│ │ │ ├── constant/
│ │ │ └── FirstProjectApplication.java
│ │ └── resources/
│ │ └── application.properties
│ └── test/
├── pom.xml
└── README.md
```

## Database Configuration

1. Create PostgreSQL Database
   Open your PostgreSQL client and execute:

Create database

```sql --
CREATE
DATABASE intern_project;
```

Connect to database (in psql)

```sql --
\c intern_project;
```

Verify connection

``` sql --
SELECT datname FROM pg_database WHERE datname = 'intern_project';
```

2. Update Application Configuration

Edit

### `src/main/resources/application.properties`

```properties
spring.application.name=firstProject
# PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/intern_project
spring.datasource.username=postgres
spring.datasource.password=your password
# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
# Server Configuration
server.port=8080
# JWT Configuration
jwt.secret=your-secret-key-here-make-it-long-and-secure
jwt.expiration=86400000
jwt.refreshToken.expiration=604800000
# Logging Configuration
logging.level.root=INFO
logging.level.org.example.bankingManagementApplication=DEBUG
```

**Note: Replace postgres and 'your password' ,with your actual PostgreSQL credentials**

# Clean and install dependencies

1. mvn clean install
2. Build the Project
   mvn clean package
3. Run the Application

# Option 1: Using Maven

```bash
mvn spring-boot:run
```

# Option 2: Using Java directly (after building)

```bash
java -jar target/firstProject-0.0.1-SNAPSHOT.jar
```

4. Verify Application is Running

# Open a new terminal and test

```bash
curl -X GET http://localhost:8080/health
```

# You should get a response indicating the application is running

The application will start on http://localhost:8080

### API Documentation

Base URL
http://localhost:8080/api

# Authentication Endpoints

## API Endpoints

### 1. User Sign Up

**Endpoint:** `POST /api/auth/signup`

**Request Body:**

```json
{
  "name": "abc",
  "email": "abc@gmail.com",
  "password": "1234"
}
```

**Response Body:**

```json
{
    "status": "CREATED",
    "message": "User created successfully",
    "payload": null,
    "success": true
}
```

### 2. User Sign In (Login)

**Endpoint:** `POST /api/auth/login`

**Request Body:**

```json
{
  "email": "nayeem@gmail.com",
  "password": "password"
}
```

**Response Body:**

```json

{
  "status": "OK",
  "message": "User logged in successfully",
  "payload": {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoiYWNjZXNzIiwic3ViIjoibmF5ZWVtQGdtYWlsLmNvbSIsImlhdCI6MTc2MDQ0MzQyNCwiZXhwIjoxNzYwNDQzNTQ0fQ.6CdunDTB1LDDY9p22WRfAFNPoJrHR0D3SGkmY9BIIpE",
    "tokenType": "Bearer",
    "userResponse": {
      "id": 52,
      "createdBy": "current user",
      "lastModifiedBy": "current user",
      "createdDate": "13-10-2025 10:46:43",
      "lastModifiedDate": "13-10-2025 10:46:43",
      "name": "Nayeem Ahmed",
      "email": "nayeem@gmail.com"
    }
  },
  "success": true
}

```

---

## Bank Account Endpoints

### 3. Initialize Bank Account

**Endpoint:** `POST /api/accounts/init`

**Response Body:**

```json

{
    "status": "CREATED",
    "message": "Account created successfully",
    "payload": {
        "accountNumber": "ACC352",
        "balance": 0.0
    },
    "success": true
}

```

### 4. Deposit Funds

**Endpoint:** `POST /api/accounts/deposit`

**Request Body:**

```json
{
  "amount": "1000.00"
}
```

**Response Body:**

```json
{
    "status": "OK",
    "message": "Successfully deposited",
    "payload": {
        "accountNumber": "ACC352",
        "balance": 1000.00
    },
    "success": true
}
```

### 5. View Account Balance

**Endpoint:** `GET /api/accounts/balance`

**Response Body:**

```json
{
    "status": "OK",
    "message": "Remaining balance",
    "payload": {
        "accountNumber": "ACC52",
        "balance": 5560.00
    },
    "success": true
}
```

### 6. Transfer Funds

**Endpoint:** `POST /api/accounts/transfer`

**Request Body:**

```json
{
  "toAccountNumber": "ACC52",
  "amount": "0.00"
}
```

**Response Body:**

```json
{
    "status": "OK",
    "message": "Balance transferred successfully",
    "payload": {
        "transactionId": "5d552221-14e9-48db-ab2f-dd8613ab37a2",
        "fromAccountNumber": "ACC352",
        "toAccountNumber": "ACC52",
        "amount": 5000.00
    },
    "success": true
}
```

---

## Transaction Endpoints

### 7. Get All Transactions (Paginated)

**Endpoint:** `GET /api/accounts/transactions`

**Response Body:**

```json
{
    "status": "OK",
    "message": "Transactions loaded successfully",
    "payload": {
        "data": [
            {
                "transactionId": "f541e4ca-5219-414c-9564-6a8e357b1859",
                "fromAccountNumber": "ACC52",
                "toAccountNumber": "ACC102",
                "amount": 300.00
            },
            {
                "transactionId": "349bd24a-c5c8-4890-be45-35dd00125304",
                "fromAccountNumber": "ACC52",
                "toAccountNumber": "ACC102",
                "amount": 20.00
            }
        ],
        "pageNo": 0,
        "pageSize": 10,
        "totalElement": 2,
        "totalPage": 1,
        "hasNext": false,
        "hasPrevious": false
    },
    "success": true
}
```

# Authentication
### Using JWT Token

Include in Request Header:

```bash
curl -X GET http://localhost:8080/api/accounts/balance \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..."
```

### Token Expiration

- Access Token: 24 hours (86400000 ms)
- Expired tokens return 401 Unauthorized

# Error Handling

**HTTP Status Codes**
----------------------

| Status                      | Meaning                         | Example                                   |
|-----------------------------|---------------------------------|-------------------------------------------|
| `200 OK`                    | Successful request              | The request was processed successfully    |
| `201 Created`               | Resource created successfully   | A new user or resource was created        |
| `400 Bad Request`           | Validation error, invalid input | Missing or malformed request data         |
| `401 Unauthorized`          | Invalid/expired token           | Authentication required or token expired  |
| `403 Forbidden`             | Insufficient permissions        | User lacks the required role or privilege |
| `404 Not Found`             | Resource not found              | Requested resource does not exist         |
| `409 Conflict`              | Duplicate account or email      | User already exists or data conflict      |
| `500 Internal Server Error` | Server error                    | Unexpected error occurred on the server   |


