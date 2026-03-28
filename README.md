# <div align="center">🍕Ingredient Management with Spring Boot🍗</div>
Spring Boot REST API managing dishes, ingredients, and stock using JPA, PostgreSQL, 
clean architecture, constructor injection, and business logic separation principles.

---

## 🎯 Objective
The primary goal is to implement the **Single Responsibility Principle (SRP)** by clearly decoupling the different layers of the application:
* **Entities (JPA):** Data modeling and persistence mapping.
* **Repositories:** Database access and abstraction.
* **Services:** Business logic implementation.
* **Controllers:** REST API endpoints and request handling.

---

## 🛠 Technologies Used
* ☕ **Java**
* 🍃 **Spring Boot**
* 📊 **Spring Data JPA**
* 🐘 **PostgreSQL**
* 🌶️ **Lombok**

---

## ⚙️ Project Specifics
* 💉 **Constructor-based Dependency Injection:** Following clean code standards by avoiding \`@Autowired\`.
* 📦 **Lombok Integration:** Reducing boilerplate code for Entities and DTOs.
* 🕸️ **Relationship Management:** Complex mappings between entities (\`Dish\`, \`Ingredient\`, \`Stock\`, etc.).
* 🔄 **Unit Conversion:** Integrated \`UnitConverter\` logic.
* 🛡️ **Error Handling:** Custom exception management for robust API responses.
* 🌐 **REST Best Practices:** Standardized HTTP status codes and endpoint design.

---

## ✨ Main Features
* 🔍 **Retrieve all ingredients** or search by **ID**.
* 📈 **Stock Calculation:** Compute ingredient availability at a specific date with automatic unit conversion.
* 🍽️ **Dish Management:** Fetch all dishes and update their specific ingredients.

---

## 🏗 Architecture
The project follows a standard N-tier architecture to ensure scalability and maintainability:

**Flow:**
controller → service → repository → database
