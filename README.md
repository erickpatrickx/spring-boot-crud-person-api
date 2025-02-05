# Person Management API

## Description

The **Person Management API** is a Spring Boot application that provides a RESTful interface for managing person entities. It supports standard CRUD operations, allowing clients to create, retrieve, update, and delete person records.

## Features

- **Create Person**: Add a new person to the database.
- **Retrieve Person**: Fetch details of a specific person by ID.
- **Update Person**: Modify existing person information.
- **Delete Person**: Remove a person from the database.
- **List Persons**: Retrieve a list of all persons.

## Technologies Used

- **Spring Boot**: Framework for building the application.
- **Spring Data JPA**: For data persistence and repository management.
- **H2 Database**: In-memory database for development and testing.
- **Maven**: Build and dependency management.

## Prerequisites

- Java 8 or higher installed.
- Maven installed.

## Getting Started

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/erickpatrickx/crudSpring.git
   cd crudSpring
   ```

2. **Build the Application:**

   ```bash
   mvn clean install
   ```

3. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

   The application will start and be accessible at `http://localhost:8080`.

## API Endpoints

The API provides the following endpoints for managing persons:

- **Create a Person**

  - **URL:** `/api/persons`
  - **Method:** `POST`
  - **Request Body:**

    ```json
    {
      "name": "John Doe",
      "age": 30,
      "email": "john.doe@example.com"
    }
    ```

  - **Response:** Returns the created person object with an assigned ID.

- **Retrieve a Person by ID**

  - **URL:** `/api/persons/{id}`
  - **Method:** `GET`
  - **Response:** Returns the person object with the specified ID.

- **Update a Person**

  - **URL:** `/api/persons/{id}`
  - **Method:** `PUT`
  - **Request Body:**

    ```json
    {
      "name": "Jane Doe",
      "age": 31,
      "email": "jane.doe@example.com"
    }
    ```

  - **Response:** Returns the updated person object.

- **Delete a Person**

  - **URL:** `/api/persons/{id}`
  - **Method:** `DELETE`
  - **Response:** Returns a confirmation message upon successful deletion.

- **List All Persons**

  - **URL:** `/api/persons`
  - **Method:** `GET`
  - **Response:** Returns a list of all person objects.

## Database Configuration

By default, the application uses an in-memory H2 database. To use a different database, update the `application.properties` file with your database configurations.

## Testing

The application includes unit tests for the service layer. To run the tests, execute:

```bash
mvn test
```


