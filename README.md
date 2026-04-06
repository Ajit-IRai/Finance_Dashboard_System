# 💰 Finance Dashboard System

## 📌 Overview

The **Finance Dashboard System** is a Spring Boot-based backend application designed to manage financial transactions and provide real-time insights through a dashboard. It supports role-based users and maintains a clean layered architecture for scalability and maintainability.

---

## 🚀 Features

* 👤 User Management (Admin / Analyst)
* 🛡️ Role-based Access Structure
* 💸 Transaction Management (Credit / Debit)
* 📊 Dashboard Summary (Total Credit, Debit, Balance)
* ⚠️ Global Exception Handling
* 🧱 Layered Architecture (Controller → Service → DAO → Repository)

---

## 🏗️ Tech Stack

* **Backend:** Spring Boot (Java)
* **Database:** MySQL / PostgreSQL
* **ORM:** Spring Data JPA (Hibernate)
* **Build Tool:** Maven
* **Testing Tool:** Postman

---

## 📂 Project Structure

```
finance-dashboard-system
│── controller
│── service
│── dao
│── repository
│── entity
│── dto
│── exception
│── config
│── FinanceDashboardSystemApplication.java
```

---

## ⚙️ Setup Instructions

### 🔧 Prerequisites

* Java 17+
* Maven
* MySQL / PostgreSQL
* Postman

### ▶️ Steps to Run

1. Clone the repository:

```
git clone https://github.com/Ajit-IRai/Finance_Dashboard_System.git
cd finance-dashboard-system
```

2. Configure database in `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. Build the project:

```
mvn clean install
```

4. Run the application:

```
mvn spring-boot:run
```

5. Access APIs:

```
http://localhost:8080/
```

---

## 🔗 API Endpoints

### 👤 User APIs

* `POST /users` → Create user
* `GET /users/{id}` → Get user by ID
* `GET /users` → Get all users

---

### 🛡️ Role APIs

* `POST /roles` → Create role
* `GET /roles` → Get all roles

---

### 💰 Transaction APIs

* `POST /transactions` → Add transaction
* `GET /transactions` → Get all transactions

---

### 📊 Dashboard API

* `GET /dashboard/summary` → Get financial summary

---

## 🧠 Business Logic

* Each user is assigned a role (Admin / Analyst)
* Transactions are linked to users
* Dashboard calculates:

  * Total Credit
  * Total Debit
  * Remaining Balance

---

## ⚠️ Exception Handling

Handled using global exception handler:

* `ResourceNotFoundException`
* `IdNotFoundException`
* `NoRecordFoundException`

Ensures proper HTTP responses and clean error messages.

---

## 🧾 Assumptions

* Each user has one role
* Email must be unique
* Transactions always belong to a valid user
* Dashboard data is calculated dynamically
* No authentication implemented

---

## ⚖️ Tradeoffs

* Simplicity prioritized over scalability
* DAO layer added for separation (optional in Spring Boot)
* No pagination (for simplicity)
* No authentication (focus on core logic)

---

## 📌 Future Enhancements

* 🔐 JWT Authentication & Authorization
* 📄 Pagination & Filtering
* 📊 Advanced Analytics
* 🧪 Unit Testing
* 🌐 Frontend Integration (React)

---

## 🎯 One-Line Description

> A Spring Boot-based financial management system with role-based users, transaction tracking, and real-time dashboard analytics.

---

## 👨‍💻 Author

**Ajit Rai**

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
