# Quizzo - Java MongoDB Quiz Application

Quizzo is a desktop-based Quiz Management System developed using Java Swing for the GUI and MongoDB for the backend database. 
It supports admin and user roles, quiz creation, participation, and result management through a clean and interactive interface.

## ✨ Features

### 👤 User Module
- Register/Login securely
- Take quizzes using topic ID
- View quiz results and scores

### 🛠 Admin Module
- Login to admin panel
- Add new quiz questions by topic
- View all registered users
- View quiz results for users

## 🧠 Technologies & Tools

| Category             | Technology Used                                          |
|----------------------|----------------------------------------------------------|
| Programming Language | Java (Swing-based GUI)                                   |
| Database             | MongoDB (NoSQL)                                          |
| Build Tool           | Maven                                                    |
| GUI Framework        | Java Swing (AWT/Swing components)                        |
| Architecture         | MVC pattern with modular packages                        |
| Authentication       | Role-based login (Admin/User) via `AuthService`          |
| Business Logic       | Custom Java services (`QuizService`, `UserService`)      |
| Deployment           | Executable JAR via Maven Exec Plugin                     |
| Version Control      | Git, GitHub                                               |


## 📦 Modules Overview

| Module     | Description                                                                |
|------------|----------------------------------------------------------------------------|
| `db`       | MongoDB database connector (`MongoConnection`)                             |
| `gui`      | Swing-based graphical panels for all user interactions                     |
| `model`    | POJOs representing data (`User`, `Result`, `Question`)                     |
| `service`  | Business logic (authentication, quiz handling, user and result services)   |
| `Main`     | Entry point for launching the GUI application                              |


## 🧰 Features

- 🔐 **Secure Login System**: Role-based login for admins and users  
- 📝 **Quiz Creation**: Admin can add questions under unique topic IDs  
- 🎮 **Quiz Participation**: Users take quizzes by entering topic ID  
- 📊 **Results Panel**: Both admin and users can view result reports  
- 🔎 **User Management**: Admin can view registered users and quiz questions  
- 📁 **Persistent Data**: All records stored in MongoDB with fast access


## 🗃️ Database Schema Overview

Collections:
- `users`: Contains registered user details and roles  
- `questions`: Stores quiz questions mapped by topic ID  
- `results`: Tracks quiz attempts and scores per user  

## 🚀 Run Locally

### Prerequisites
- Java 17+
- MongoDB (local or remote)
- Maven
