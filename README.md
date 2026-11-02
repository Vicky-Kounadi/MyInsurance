# Vehicle Insurance Management API
Backend development exercise using Java 21, Spring Boot and Hibernate.

### Technologies
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven

### Features
- Create insurance policies.
- Retrieve all policies.
- Retrieve a policy by policy number.
- Retrieve policies by client.
- Retrieve policy status.
- Update policy status.
- Delete policies.
- Request validation and exception handling.
- Automatic monthly premium assignment based on the selected product.

### Project structure
- `controller` – REST endpoints.
- `service` – Business logic.
- `repository` – Database operations.
- `entity` – Database entities.
- `dto` – Request and response objects.
- `exception` – Custom exceptions and global exception handling.

### Run
- Open the project.
- Run `mvn spring-boot:run`.
- Application runs at `http://localhost:8080`.
- H2 Console: `http://localhost:8080/h2-console`.

### Database
- H2 in-memory database.
- JDBC URL: `jdbc:h2:mem:insurance_db`.
- Username: `sa`.
- Password: empty.

### Future improvements
- Create / Retrieve / Update / Delete clients, vehicles and products.
- More sophisticated premium calculation.
- JMS messaging.
- SOAP integration.
- File operations.