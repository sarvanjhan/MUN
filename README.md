# MUN Delegation Management System

A Java-based web application for managing Model United Nations (MUN) delegations, committees, participating countries, attendance, and reports.

## Project Overview

The MUN Delegation Management System is designed to simplify the administrative tasks involved in organizing Model United Nations conferences. It provides a centralized platform for managing delegate information, organizing committees, maintaining country records, tracking attendance, and generating reports.

The application features a web-based interface with a dashboard that provides an overview of key conference information.

## Features

### Dashboard
- View the total number of delegates, countries, and committees.
- Monitor overall attendance statistics.
- Access the main management modules from a centralized interface.

### Delegate Management
- Register, view, search, edit, and delete delegate records.
- View details such as college, assigned country, and committee.

### Country Management
- Manage participating countries.
- Organize countries by region.

### Committee Management
- Manage MUN committees.
- View committee names, codes, types, and assigned delegates.
- Organize committees such as UNGA, UNSC, UNHRC, WHO, and UNEP.

### Attendance Management
- Record delegate attendance by date.
- Mark delegates as present or absent.
- Review attendance records and participation statistics.

### Reports
- View overall attendance statistics.
- Review individual delegate attendance percentages.
- Print attendance reports when required.

### Authentication
- Access the application through a sign-in page for authorized users.

## Technologies Used

- **Programming Language:** Java
- **Backend Framework:** Spring Boot
- **Web Framework:** Spring MVC
- **Build Tool:** Apache Maven
- **Database Access:** JDBC
- **Frontend:** HTML, CSS, and JavaScript
- **Database:** Check the project's configuration files to confirm the database used by this version.

## Prerequisites

- Java Development Kit (JDK) compatible with the project
- Git, for cloning the repository
- A web browser
- Internet access for downloading Maven dependencies on the first run

## Installation and Setup

### 1. Clone the Repository

Replace `YOUR-USERNAME` with the GitHub username that owns this repository.

```bash
git clone https://github.com/YOUR-USERNAME/MUN-Delegation-Management-System.git
cd MUN-Delegation-Management-System
```

### 2. Verify Java

```bash
java -version
```

### 3. Run the Application

On Windows, open a terminal in the project directory and execute:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux, execute:

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

Wait until Spring Boot reports that the application has started successfully.

### 4. Open the Application

Open your browser and visit:

http://localhost:8080

The application should display the sign-in page.

## Project Structure

The main project files are organized as follows:

```text
MUN-Delegation-Management-System/
├── src/
│   └── main/
│       ├── java/          # Java source code
│       └── resources/     # Application configuration and web resources
├── database/              # Database scripts, if included
├── pom.xml                # Maven dependencies and build configuration
├── mvnw                   # Maven wrapper for macOS/Linux
├── mvnw.cmd               # Maven wrapper for Windows
└── README.md              # Project documentation
```

The exact folder structure may vary depending on the project version.

## Java Concepts

The project may demonstrate concepts such as object-oriented programming, classes and objects, inheritance, polymorphism, exception handling, collections, JDBC, Spring Boot web development, and CRUD operations. Confirm these against the source code before making a formal presentation.

## Troubleshooting

- Ensure Java is installed and available in your terminal.
- Run commands from the directory containing `pom.xml` and `mvnw.cmd`.
- Check terminal output if the application fails to start.
- Ensure the configured database is available and initialized if required.
- If port `8080` is already in use, stop the other application using it or configure a different port.

## Team and Contributors

**Project:** MUN Delegation Management System  
**Project Type:** Academic Java Project

**Team Members:**
- Add team member name 1
- Add team member name 2
- Add team member name 3
- Add team member name 4

Replace these placeholders with the actual team member names and contributions.

## Academic Purpose

This project was developed to demonstrate the application of Java programming, backend development, database connectivity, and web technologies in a practical management system.
