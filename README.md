# Notes Online - Spring Boot Application

## Overview

The "Notes Online" Spring Boot application is designed to help users manage and organize their notes effectively. It provides a user-friendly platform for creating, editing, deleting and downloading notes with priority settings.

## Key Features

1. **User Registration and Authentication:**
   - Users can register accounts with a valid email address and password.
   - Authentication ensures secure access to user data.

2. **Note Management:**
   - Create new notes with a title, text content, date and priority level (Low, Medium, High).
   - Edit and delete notes as needed.

3. **User-Friendly Interface:**
   - Utilizes Bootstrap for a clean and responsive UI.
   - Form validations and user feedback enhance the user experience.

4. **Security Measures:**
   - Implements Spring Security for user authentication and authorization.
   - Passwords are securely hashed using BCrypt.

## Technologies Used
- **Java 21:** The programming language used for developing the application.
- **Spring Boot:** Simplifies Java application development for production-ready applications.
- **Hibernate:** An object-relational mapping framework for Java applications.
- **Spring Security:** Provides comprehensive security services for Java EE-based enterprise software applications.
- **Spring Data JPA:** Simplifies the implementation of data access layers in Spring applications.
- **PostgreSQL:** An open-source relational database management system.
- **Flyway:** Database migration tool for version control of database schemas.
- **Docker:** A platform for developing, shipping, and running applications in containers.
- **Thymeleaf:** Server-side Java template engine for web environments.
- **Bootstrap:** Popular front-end framework for responsive web design.

## How to Use

1. **Login and Registration:**
   - Visit the registration page (`/register`) to create a new account.
   - Access the login page (`/login`) to log in with your registered credentials.
  
  ![login](https://github.com/Scream274/NotesOnline/assets/108679243/100c0a6d-e578-42b3-b0a4-0240bc775d92)

2. **Note Management:**
   - Navigate to the main page (`/profile`) to create, edit, delete and download notes.

  
  ![profile](https://github.com/Scream274/NotesOnline/assets/108679243/93d88bb6-0c2a-48ba-9220-f71df3579bcd)

3. **Logout:**
   - Use the "Logout" button to securely log out from your account.

## Note

For a complete understanding of the application, refer to the provided source code. Ensure that the necessary dependencies are configured for a smooth execution.

