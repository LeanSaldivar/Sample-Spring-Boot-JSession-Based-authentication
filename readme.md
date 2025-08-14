# Session-Based Authentication Testing Project

## Overview
This project is an experimental implementation of session-based authentication in Spring Boot, designed to test various cookie settings and their effectiveness against CSRF (Cross-Site Request Forgery) attacks. It serves as my practical understanding for session management and security configurations in Spring Security.

## Prerequisites
- Java 21
- Maven
- Docker and Docker Compose
- PostgreSQL (via Docker)
- Your favorite IDE (IntelliJ IDEA recommended)

## Project Setup

### 1. Clone the Repository
```bash
git clone https://github.com/LeanSaldivar/Sample-Spring-Boot-JSession-Based-authentication.git
```

### 2. Configure Docker
Create a `docker-compose.yml` file in the project root:

```yaml
  postgres:
    container_name: postgres-spring-boot
    image: postgres:latest
    environment:
      POSTGRES_USER: YOURUSERNAME
      POSTGRES_PASSWORD: YOURPASSWORD
      POSTGRES_DB: YOURDBNAME
      PGDATA: /YOURDBNAME/postgres
    volumes:
      - db:/YOURDBNAME/postgres
    ports:
      - "5452:5432"
    networks:
      - YOURDBNAME
    restart: unless-stopped

networks:
  YOURDBNAME:
    driver: bridge

volumes:
  db:
```

### 3. Configure Application Properties
Create `application.properties` in `src/main/resources`: properties

```properties
spring.application.name=csrf
logging.level.org.springframework.security=TRACE
logging.level.com.lean.Practice=DEBUG

#Docker postgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5452/YOURDBNAME
spring.datasource.username=YOURUSERNAME
spring.datasource.password=YOURPASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
#spring.sql.init.mode=always
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true

# Session configuration
spring.session.store-type=jdbc
spring.session.jdbc.initialize-schema=always
spring.session.timeout=30m

# Session cookie configuration
server.servlet.session.cookie.name=JSESSIONID
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.secure=true
server.servlet.session.cookie.path=/
server.servlet.session.cookie.same-site=Lax
```

## Security Features Implemented
- Session-based authentication
- JDBC session storage
- Secure cookie configuration
- Custom session timeout
- Maximum sessions per user
- Prevention of concurrent logins

## Testing the Application

### 1. Session Cookie Settings
The application uses the following security-focused cookie configurations:
- HTTP-only cookies
- Secure flag enabled
- SameSite attribute set to Lax
- Custom session timeout (30 minutes)

### 2. CSRF Protection Testing
To test CSRF protection:
1. Authenticate with valid credentials
2. Observe the session cookie in browser dev tools
3. Verify cookie attributes (httpOnly, secure, SameSite)
4. Test cross-origin requests to verify CSRF protection

## Development Notes

### Important Files to Configure Locally
1. `application-local.properties` (for local development)
2. `application-dev.properties` (for development environment)
3. `.env` (for environment variables)

These files are git-ignored and need to be created locally.

### Database Migrations
The application uses Hibernate's auto-ddl for development. For production, consider using proper migration tools like Flyway or Liquibase.

## Maintenance

### Logs
Logs are stored in the `logs/` directory and are git-ignored. Configure log rotation as needed.

### Docker Data
PostgreSQL data is persisted in `docker/data/` and is git-ignored.

## Security Considerations
This is an experimental project for testing purposes. For production use:
- Implement proper password hashing
- Add rate limiting
- Configure proper CORS settings
- Implement audit logging
- Use environment variables for sensitive data
- Consider using a session store like Redis for better scalability

## Notes
- This project is still incomplete and, I will continue to keep working in this project if time allows me

## License
This project is licensed under the [MIT License](LICENSE).
