# EmployWise Java Assignment

## Introduction
This Spring Boot project is designed to fulfill the requirements outlined in the EmployWise Java Assignment. The application uses MongoDB as the NoSQL database and provides various API endpoints for managing employees.

## Project Structure
The project follows a standard Spring Boot project structure with the main components being:

- **Controllers:** Handle incoming HTTP requests and define API endpoints.
- **Models:** Define the data models, particularly the `Employee` class.
- **Repositories:** Interface for MongoDB operations, specifically the `EmployeeRepository`.
# Setup Instructions

## Initial Setup
1. **Install MongoDB:**
    - Follow the [official MongoDB installation guide](https://docs.mongodb.com/manual/administration/install-community/) to install MongoDB on your system.

### Setup MongoDB in Spring Boot

#### 1. Add MongoDB Dependencies

In your `pom.xml` file, add the MongoDB dependencies:

```xml
<!-- MongoDB dependencies -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency> 
```

### MongoDB Connection
Goto -> Resources -> Application.Properties and write DatabaseName

spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=YourDataBaseName

# API Documentation
## Entry Level
### a. Add Employee to Database
- **Endpoint url:** ``` http://localhost:8080/employee/add ```
- **Request JSON:**
 ```JSON
{
  "id": "string","name": "string",
  "phoneNumber": "string",
  "emailId": "string",
  "reportsTo": "string",
  "profileImage": "string"
  }
```

### b. Get All Employees
- **Endpoint url:** ``` http://localhost:8080/employee/getAll ```

### c.  Delete Employee by ID
- **Endpoint url:** ``` http://localhost:8080/employee/deleteById/{id} ```

### d. Update Employee by ID
- **Endpoint url:** ``` http://localhost:8080/employee/updateById/{id} ```
- **Request JSON:**
 ```JSON
{
  "id": "string","name": "string",
  "phoneNumber": "string",
  "emailId": "string",
  "reportsTo": "string",
  "profileImage": "string"
  }
```
## Intermediate Level
### a. Get nth Level Manager of an Employee
- **Endpoint url:** ``` http://localhost:8080/employee/manager/{employeeId}/{level}```

### b. Get Employees with Pagination and Sorting
- **EndPoint url:** ```http://localhost:8080/employee/all?page=0&size=10&sortBy=employeeName```

# Swagger Integration
#### If you don't want to read API documentation you can just use SWAGGER UI to access all the API's, I have already integrated SWAGGER
##### for using SWAGGER UI, you need to run the Application and use ths
url: ``` http://localhost:8080/swagger-ui/index.html#/ ```
 
in any of your BROWSER