# Fleet GPS Tracking Microservice

## Overview

The Fleet GPS Tracking Microservice is a Spring Boot-based application designed to manage vehicle tracking and GPS logging. It provides RESTful APIs for adding vehicles, logging GPS data, and retrieving vehicle locations and history.

## Feature

- [x] Basic Validation
- [x] Error Handling
- [x] Speed Violation Service (speed > 100 km/h)
- [ ] JWT-Based Token Authentication
- [x] Schedule Task (Clean up GPS Logs older than X days)
- [x] API-Docs (http://localhost:8080/api/api-docs) & Swagger (http://localhost:8080/api/swagger-ui/index.html)
- [x] Database Migration (Flyway)
- [ ] Dockerfile or docker-compose

## Technologies Used

- Java 17
- Spring Boot 2.4.5
- PostgreSQL 17
- JPA (Hibernate)
- Maven

## Setup Instructions

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- PostgreSQL database

### 1. Clone Repository

```bash
git clone https://github.com/rezivaapra/Fleet-GPS-Tracking-Microservice.git
cd Fleet-GPS-Tracking-Microservice
```

### 2. Configure Database

- Create a PostgreSQL database for the application.
- Update the `application.properties` file with your database connection details: 

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=none
```

### 3. Run Database Migrations

- Ensure that Flyway is configured correctly in your `application.properties`:

```properties
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

- Run the application to execute the Flyway migrations:

```bash
mvn spring-boot:run
```

### 4. Build the Application

To build the application, run:

```bash
mvn clean install
```

### 5. Run the Application

You can run the application using the following command:

```bash
mvn spring-bot:run
```

### 6. Access the API Endpoints

The applicaion will be available at `http://localhost:8080/api`. You can use tools like Postman or curl to interact with the API.

## Example API Endpoints

- Add Vehicle: `POST /vehicle`
  - Request Body: `{"plateNumber": "ABC123", "name": "Truck", "type": "Cargo"}`
- Add GPS Log: `POST /gps`
  - Request Body: `{"vehicleId": 1, "latitude": 12.34, "longitude": 56.78, "speed": 60.0}`
- Get Last Location: `GET /vehicle/{id}/last-location`
- Get Vehicle History: `GET /vehicle/{id}/history?from=2025-05-01&to=2025-05-31`

## [Documentation](https://drive.google.com/drive/folders/1An2FDiCwXL-Av8fYG3_TklFYjZC7qL3B)