# Job Posting Application

A RESTful job posting platform built with Spring Boot, enabling secure user registration, authentication, company management, and job post creation and filtering.

## Features

- **User Authentication & Authorization**
    - User signup and login
    - Separate registration and login flows for Admins and Employers (Job Posters)
    - JWT-based authentication for secure access control

- **Company Management** (Admin only)
    - Create, update, delete, and retrieve companies
    - Pagination support for company listings

- **Job Poster Management** (Admin only)
    - List and manage registered job posters with pagination

- **Job Post Management** (Job Poster only)
    - Create new job posts associated with the poster's company
    - View job posts created by the authenticated job poster
    - Filtering and pagination of job posts (filtering endpoint planned)

## Technology Stack

- Java 17+
- Spring Boot
- Spring Security with JWT
- Spring Data JPA 
- RESTful API design

## API Endpoints Overview

### Authentication (`/auth`)

| Endpoint             | Method | Description                      |
|----------------------|--------|----------------------------------|
| `/signup`            | POST   | Register a new user             |
| `/register-admin`    | POST   | Register a new admin user       |
| `/register-employer` | POST   | Register a new employer (job poster) |
| `/login`             | POST   | Authenticate a user             |
| `/login-admin`       | POST   | Authenticate an admin           |

### Company Management (`/api/companies`) — Admin only

| Endpoint          | Method | Description                    |
|-------------------|--------|--------------------------------|
| `/`               | POST   | Create a new company           |
| `/{id}`           | PUT    | Update an existing company     |
| `/{id}`           | DELETE | Delete a company               |
| `/`               | GET    | Get paginated list of companies|
| `/{id}`           | GET    | Get a company by ID            |

### Job Poster Management (`/job-posters`) — Admin only

| Endpoint | Method | Description                      |
|----------|--------|----------------------------------|
| `/`      | GET    | List all job posters (paginated) |
| `/{id}`  | DELETE | Delete a job poster (not implemented) |

### Job Posts (`/job-posts`) — Job Poster only

| Endpoint                     | Method | Description                            |
|------------------------------|--------|--------------------------------------|
| `/created-by/job-poster`     | GET    | Get job posts created by the job poster's company (paginated) |
| `/filter`                    | GET    | Filter job posts (not implemented)   |
| `/create`                   | POST   | Create a new job post                 |

## Security

- Role-based access control with roles: `ADMIN`, `JOB_POSTER`, and standard users.
- Endpoints restricted by roles using Spring Security annotations.
- JWT token authentication to protect sensitive endpoints.

## Getting Started

1. Clone the repository.
2. Configure your database and JWT secret in `application.properties`.
3. Build and run the application using Maven or your preferred IDE.
4. Use a REST client (Postman, curl) to interact with the API.

