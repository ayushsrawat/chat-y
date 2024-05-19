# ChatY

ChatY is a real-time chat application built with Spring Boot on the backend and a simple HTML/CSS/JavaScript frontend. It supports multiple users in a chat room, providing features like user authentication, message history, and real-time messaging using WebSockets.

## Features

- User Authentication: Log in and sign up functionality.
- Real-time Messaging: Send and receive messages instantly.
- Responsive Design: Compatible with various screen sizes.

## Technologies Used

- Backend: Spring Boot
- Frontend: HTML, CSS, JavaScript
- WebSocket: SockJS and STOMP
- Database: MySQL (in-memory database for development)
- Build Tool: Maven

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher

### Running the Application

1. **Clone the repository:**
    ```sh
    git clone https://github.com/theayushr/ChatY.git
    cd ChatY
    ```

2. **Build the project:**
    ```sh
    mvn clean install
    ```

3. **Run the application:**
    ```sh
    mvn spring-boot:run
    ```

4. **Access the application:**
   Open your browser and navigate to `http://localhost:8080`.

### Directory Structure

```plaintext
ChatY/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── spring/
│   │   │           └── chatapp/
│   │   │               ├── controllers/
│   │   │               ├── models/
│   │   │               ├── repositories/
│   │   │               ├── services/
│   │   │               └── websocket/
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   │   ├── css/
│   │   │   │   │   └── styles.css
│   │   │   │   ├── js/
│   │   │   │   │   └── app.js
│   │   │   │   └── html/
│   │   │   │       ├── index.html
│   │   │   │       ├── login.html
│   │   │   │       ├── signup.html
│   │   │   │       └── chat.html
│   │   │   ├── application.properties
│   │   │   └── schema.sql
│   └── test/
│       └── java/
│           └── com/
│               └── spring/
│                   └── chatapp/
├── .gitignore
├── pom.xml
└── README.md
