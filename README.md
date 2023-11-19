# CabForYou

This is the README for the CabForYou Backend, a Java Spring-based application designed to support and manage an online cab booking system. This backend is responsible for handling various operations, such as user authentication, cab booking, and more.

## Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Usage](#usage)


## Introduction

The CabForYou is a critical component of an online cab booking system. It provides the necessary functionalities to create, manage, and conduct online cabs, drivers, customers securely. This backend is built on the Java Spring framework, providing a robust and scalable architecture to support various booking related operations.

## Technologies Used

- **Backend:**
    - Java with Spring Boot framework.

- **Database:**
    - PostgreSQL for secure and efficient data storage.

- **RESTful API:**
    - Implements RESTful principles for smooth communication.

## Features

- **User Roles:**
   - **Customer:** Can book, modify, and cancel rides using the provided API endpoints.
   - **Planner:** Manages drivers, rides, and customer data through API calls.
   - **Driver:** Accesses personalized data through API endpoints.
- **Ride Management:**
   - Customers can book, modify, and cancel rides using the exposed API endpoints.
- **Driver Management:**
   - Planner can add drivers.
   - Drivers can view and update their personal information.
- **Customer Profile:**
   - Customers can create profiles.
- User authentication and authorization.
- Booking requests, including creating, updating, and deleting bookings.
- User registration.



## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 21.
- Apache Maven for building the project.
- A database system such as MySQL, PostgreSQL.
- An integrated development environment (IDE) like IntelliJ.

## Getting Started

To get started with the CabForYou Backend, follow these steps:

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/Mehmoli/cab-for-you.git
   ```

2. Open the project in your preferred IDE.

3. Configure the database connection in `src/main/resources/application.properties`.

4. Build and run the application.

   ```bash
   mvn spring-boot:run
   ```

The CabForYou backend should now be up and running, ready to handle CabForYou operations.

## Configuration

Before running the application, make sure to configure the database connection and other properties in the `application.properties` file. You can also set up environment-specific configurations for development, testing, and production.
If you use PostgreSQl, just change the `spring.datasource.username=username`and `spring.datasource.password=password` to your own username and password.
## Usage

The CabForYou Backend provides a set of RESTful APIs that can be accessed by frontend applications or other services.

Thank you for using the CabForYou. If you have any questions or encounter any issues, please don't hesitate to [contact us](mailto:contact@example.com).