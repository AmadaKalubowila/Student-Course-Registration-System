# Student-Course-Registration-System
A simplified Student Course Registration System using Java 17, Spring Boot

# Download project
- create new folder
- open git bash from folder
- execute "git clone <url>"
- open project from your IDE

# How to Run  

```bash
mvn spring-boot:run
```

# Technolgies Used

- Java Version: Java 17
- Framework: Spring Boot
- Build Tool: Maven or Gradle
- REST API
- MAP / List

# EndPoints

- POST /students – Register a new student
- POST /courses – Add a new course
- GET /courses – List all courses

- POST /students/{studentId}/register/{courseId} – Register for a course
- DELETE /students/{studentId}/drop/{courseId} – Drop a course
- GET /students/{studentId}/courses – List registered courses

## Extra EndPoints

- GET /students – List all students
- GET/students/{id} - Get student by id
- GET/courses/{id} - Get course by id

# Assumptions
- Unique course code and student email enforced.
- No limits on course seats.

# Explanation
 
- For in memory storage used lists
- used MVC architecture.(model / Controller / Service / DTO)
- There is defined Exceptions used in here.

# Testing

- Junit tests classes for service package. (StudentServiceTest / CourseServiceTest / RegistrationServiceTest )
- Each test class has 100% coverage.

# Docker Run

## optional instructions

- netstat -aon | findstr :8080  - check 8080port usage
- taskkill /PID <PID> /F        - terminate other process in port 8080
- docker build -t zdata-app     - build docker image

```bash
docker run -p 8080:8080 zdata-app

```
# Swagger UI

- browse : http://localhost:8080/swagger-ui/swagger-ui/index.html
