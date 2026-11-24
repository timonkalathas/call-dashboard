# Call Dashboard REST API

A lightweight, layered RESTful API built with **Java 21** and **Spring Boot** for managing call records and agent notes. Designed with clear separation of concerns, boundary input validation, automated integration testing, and GitHub Actions CI.

## Architecture Overview

The application follows a 4-layer backend architecture:

1. **Models (`Call`, `Note`):** Domain entities with Jackson JSON annotations (`@JsonProperty`, `@JsonInclude`, `@JsonIgnore`) to map Java `camelCase` fields to REST `snake_case` contracts.
2. **Repository (`CallRepository`):** In-memory data access layer initialized with seed data, isolating storage concerns from business rules.
3. **Service (`CallService`):** Business logic layer handling active call filtering, call archiving, and unique note ID generation.
4. **Controller (`CallController`):** HTTP boundary layer managing REST endpoints, input validation (`400 Bad Request`), error handling (`404 Not Found`), and status codes (`200`, `201`).

---

## Getting Started

### Prerequisites
- Java 21+
- Maven Wrapper (`./mvnw` included)

### Run the Application
`./mvnw spring-boot:run`
The server starts on `http://localhost:8081` (configured in `src/main/resources/application.properties`).

### Run Automated Tests
`./mvnw test`

---

## API Endpoints

| Method | Endpoint | Description | Success Status |
| :--- | :--- | :--- | :--- |
| `GET` | `/calls` | Retrieve all non-archived calls | `200 OK` |
| `GET` | `/calls/{callId}` | Retrieve a single call with its notes | `200 OK` / `404 Not Found` |
| `PATCH` | `/calls/{callId}/archive` | Archive a call (`is_archived: true`) | `200 OK` / `404 Not Found` |
| `POST` | `/calls/{callId}/notes` | Add a note to a specific call | `201 Created` / `400` / `404` |

### Example Requests (`curl`)

- **List active calls:** `curl -s http://localhost:8081/calls`
- **Get a single call:** `curl -s http://localhost:8081/calls/1`
- **Add a note to a call:** `curl -s -X POST http://localhost:8081/calls/1/notes -H "Content-Type: application/json" -d '{"content": "Customer requested pricing details"}'`
- **Archive a call:** `curl -s -X PATCH http://localhost:8081/calls/1/archive`

---

## CI/CD

Every push and pull request triggers the **GitHub Actions** workflow (`.github/workflows/ci.yml`), which sets up JDK 21 and executes the full MockMvc test suite (`./mvnw -B test`).