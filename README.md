# Calculator Spring Backend

A Spring Boot REST API backend for a calculator application with user authentication, JWT-based security, and calculation history persistence.

## Link to Frontend

[Calculator Vue Frontend](https://github.com/giliannereyes/calculator-vue-frontend)

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.2**
- **Spring Security** - JWT-based authentication
- **Spring Data JPA** - Database persistence
- **H2 Database** - Embedded database for development
- **Maven** - Build management

## How to Run

### Prerequisites

- Java 21 or higher
- Maven 3.6+ or the Maven Wrapper (`mvnw`)

### Steps

1. Clone the repository
   ```bash
   git clone https://github.com/giliannereyes/calculator-vue-backend.git
   cd calculator-spring
   ```

2. Run the application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
   (On Windows, use `mvnw.cmd` instead of `./mvnw`)

3. The application will start on `http://localhost:8080`

### API Endpoints

- `POST /api/auth/login` - Authenticate user and receive JWT token
- `POST /api/auth/register` - Register a new user
- `GET /api/calculations` - Get user's calculation history (requires authentication)
- `POST /api/calculations` - Save a calculation (requires authentication)
- `GET /api/h2-console` - H2 Database Console (for development)

### Default H2 Console

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/calculatordb`
- Username: `sa`
- Password: (empty)