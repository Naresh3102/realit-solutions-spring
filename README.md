# Spring Boot String Manipulation API

## Overview

This Spring boot application demonstrates how to manipulate strings within a predefined JSON model and save the modified JSON to a database. The application exposes a REST API endpoint thattakes dynamic inputs as parameters, performsstring replacements within the JSON model, and returns the modified JSON model. The modified JSON model is also saved in the database.

## Features

- Accepts dynamic string inputs for modification.
- Performs string replacement within a JSON model.
- Saves the modified JSON model to a database.
- Provides a REST API for interacting with the application.

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- MySQL database

## Setup

1. ** Clone the repository**

   ```bash
   git clone https://gothub.com/Naresh3102/realit-solutions-spring.git
   cd realit-solutions-spring
   ```

2. **Configure the database**

   Update the `scr/main/resources/application.properties` file with your MySQL database details:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. **Build the project:**

   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Modify JSON Model

- **URL:** `/api/modify`
- **Method:** `POST`
- **Parameters:** `inputs` (String) - A comma-separated string of key-value pairs in the format `"key1:::value1,key2:::value2"`
- **Response:** The modified JSOn model and its ID from the database.

#### Example Request

```http
POST http://localhost:8080/api/modify?inputs="New:::NewDocument,Open:::OpenFile"
```

#### Example Response

```json
{
  "id": 1,
  "jsonModel": "{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"NewDocument\",\"onclick\":\"CreateDoc()\"},{\"value\":\"OpenFile\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Save\",\"onclick\":\"SaveDoc()\"}]}}}"
}
```

## Implementation Details

### Controller

The `MenuController` handles the `/api/modify` endpoint. It receives the `inputs` parameter, processes the JSON model, performs string replacements, and saves the modified JSON model to the database.

### Entity

The `Menu` entity represents the JSON model stored in the database.

### Repository

The `MenuRepository` interface extends `JpaRepository` to provide CRUD operations for the `Menu` entity.

## Testing with Postman

1. Open Postman.
2. Create a new POST request.
3. Enter the URL: `http://localhost:8080/api/modify?inputs="New:::NewDocument,Open:::OpenFile"`.
4. Send the request.
5. Verify the response: You should see the modified JSON model with the changes applied.
