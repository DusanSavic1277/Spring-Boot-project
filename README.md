Auth Demo - Spring Boot Application

This project is a backend application built using Spring Boot that demonstrates how to implement user authentication and authorization using JSON Web Tokens (JWT). 
The main goal of the project is to provide a simple and clear example of how modern backend systems handle user login, registration, and secure access to protected resources.
The application allows users to create an account, log in using their credentials, and receive a JWT token which is then used to authenticate future requests. This approach ensures that the system remains stateless and scalable, which is a key principle in modern web development.The project follows a typical layered architecture, where responsibilities are separated into different components. 
Controllers are responsible for handling HTTP requests and exposing API endpoints. Services contain the business logic, including authentication and token generation. 
The repository layer is responsible for data access, while entity classes represent the core domain models of the application. Security is handled using Spring Security, which integrates with JWT to protect endpoints and manage access control. One of the key features of this project is secure password handling. 
User passwords are never stored in plain text, but instead are encrypted using a password encoder. During login, the system validates the credentials and generates a JWT token, which is returned to the client and used for subsequent requests.

This project is a good starting point for understanding how authentication works in real-world applications. 
It can be further extended by adding features such as role-based authorization, database integration with systems like MySQL or PostgreSQL, refresh tokens, and more advanced security configurations.

The application can be run using any Java IDE such as IntelliJ IDEA, NetBeans, or Eclipse, or directly from the command line using Maven. Once started, the API can be tested using tools like Postman.

This project is intended for educational purposes and is especially useful for developers who are learning Spring Boot, Spring Security, and REST API development.
