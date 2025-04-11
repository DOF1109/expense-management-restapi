# Development steps

### 1. Create the database in MySQL workbench

### 2. Configure the database in `application.properties`

### 3. Add necessary dependencies in `pom.xml`
* Spring Boot Starter Web
* Lombok
* MySQL Driver (MySQL Connector J)
* Spring Data JPA (Spring Boot Starter Data JPA)
* Model Mapper (modelmapper.org)

### 4. Create IO objects
* Request and response objects interact with the UI layer
* React app will send the json data, and we will bind the values to the request object
* Response object will be converted to json and sent back to the UI app

### 5. Create DTO object
* Design pattern for Data Transfer Object
* Transfer data between layers
* Map DTO to Entity and vice versa
* Sometimes contains the sensitive data
* Never return as response to UI app

### 6. Create JPA entity
* The entity class is mapped to a database table

### 7. Create JPA repository
* Interface that extends `JpaRepository` or `CrudRepository`
* Perform CRUD operations
* Provides additional methods for pagination, sorting, finder, query, etc

### 8. Create Service
* Business Logic
* Interact with JPA repository (database layer)
* Define the service interface and implementation

### 9. Create REST Controller
* Expose the API
* It contains handler methods that map to a URI paths using `@RequestMapping` annotation with HTTP methods

### 10. Handle Exceptions
* Create a ErrorObject (IO package)
* Create a ResourceNotFoundException (exception package)
* Create a GlobalExceptionHandler (exception package)
