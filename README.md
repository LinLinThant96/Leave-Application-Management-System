# LEAVE APPLICATION MANAGEMENT SYSTEM

## OVERVIEW
A **Java**-based web application for employees to apply, update, or cancel leave applications. Managers can approve or reject applications, and admins manage employees, leave types, and the approval hierarchy. It also includes custom leave validations and compensation claim functionality.

## FEATURES
- Employee leave application management (apply, update, cancel)
- Manager approval/rejection of leave applications
- Admin management of employees, leave types, entitlements, and public holidays
- Compensation claims management
- Custom validations and date handling

## TECHNOLOGIES USED
- **Backend**: Java, Spring Boot, Spring Data JPA, Spring Security, Spring Validations, Spring Session
- **Frontend**: HTML, Thymeleaf, JavaScript, Bootstrap
- **Database**: MySQL

## USE CASES
- Manage Leave Types (CRUD)
- Manage Staff
- Manage Leave Entitlements
- Manage Approval Hierarchy
- Manage Public Holidays (CRUD)
- Leave Application CRUD (submit, update, cancel)
- Custom leave validations (date checks, helper class)
- View personal leave history
- Approve/Reject leave applications
- Compensation claim management

## HOW TO RUN

### PREREQUISITES
- **Java** 11+
- **Maven**
- **MySQL**

### SETUP INSTRUCTIONS
1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/leave-application-management.git
    ```
2. Navigate to the project directory:
    ```bash
    cd leave-application-management
    ```
3. Configure the MySQL database:
   - Create a database named `leave_management_system`.
   - Update the `application.properties` file with your MySQL username and password:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/leave_management_system
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```
4. Run the project using Maven:
    ```bash
    mvn spring-boot:run
    ```
5. Access the application in your browser at `http://localhost:8080`.
