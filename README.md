\# Task Manager API



A RESTful task management backend built with Java 21, Spring Boot, and PostgreSQL.



\## Tech Stack



\- Java 21

\- Spring Boot and Spring Data JPA

\- PostgreSQL

\- Maven

\- JUnit

\- Docker and Docker Compose

\- Swagger / OpenAPI



\## Features



\- REST API for task management

\- PostgreSQL database integration

\- Automated backend tests

\- Containerized application setup

\- Interactive API documentation



\## Run Locally



\### Prerequisites



\- Java 21

\- Docker Desktop

\- Git



\### Setup



```bash

git clone https://github.com/ayush5002466/task-manager.git

cd task-manager

docker compose up --build

```



\### Swagger UI



Once the application starts, visit:



http://localhost:8080/swagger-ui/index.html



\## Run Tests



On Windows:



```powershell

.\\mvnw.cmd test

```



\## Cloud Deployment



\*\*Status: Not deployed.\*\*



The application is containerized and can be prepared for future cloud deployment. A possible AWS architecture could use Amazon ECS or EC2 for the application, Amazon RDS for PostgreSQL, and CloudWatch for monitoring.



Cloud deployment is deferred to avoid ongoing AWS costs. The project can be run and tested locally without creating AWS resources.



\## Security



\- Never commit database passwords or other secrets.

\- Use environment variables for sensitive configuration.

\- Restrict database access in production.



\## Future Improvements



\- Authentication and authorization

\- Pagination and filtering

\- CI/CD automation

\- Integration testing

\- Cloud deployment



\## Author



Ayush Saraswat



GitHub: https://github.com/ayush5002466

