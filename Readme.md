# Banking Application

This is a RESTful Banking Application built with **Spring Boot** and **MySQL**. The application allows users to create accounts, deposit and withdraw funds, transfer money, and fetch account transactions.

## Features
- Create, update, and delete bank accounts
- Deposit and withdraw funds
- Transfer funds between accounts
- Fetch account transactions
- RESTful API with JSON responses

## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Web
- MySQL Database
- Lombok (optional)

## Installation

### Prerequisites
- Java 21 installed
- MySQL installed and running
- Maven installed

### Steps to Run
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/banking-app.git
   cd banking-app
   ```
2. Configure MySQL database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

### Account Management
- **Create Account:** `POST /api/accounts`
- **Get Account by ID:** `GET /api/accounts/{id}`
- **Get All Accounts:** `GET /api/accounts`
- **Delete Account:** `DELETE /api/accounts/{id}`

### Transactions
- **Deposit Funds:** `PUT /api/accounts/{id}/deposit`
- **Withdraw Funds:** `PUT /api/accounts/{id}/withdraw`
- **Transfer Funds:** `POST /api/accounts/transfer`
- **Get Account Transactions:** `GET /api/accounts/{id}/transactions`

## License
This project is licensed under the MIT License.

## Author
Yogesh Kumar - [GitHub Profile](https://github.com/Yogeshkumar440)

