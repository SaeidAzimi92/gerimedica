# Saeid Azimi 


# Gerimedica Assignment

This is the tech assignment of Gerimedica company.


## Requirements

For building and running the application you need:

* Install [OpenJDK 11](https://openjdk.java.net/projects/jdk/11/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.gerimedica.assignment.GerimedicaApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Backend

**Run Backend**

1. Clean and build with Maven
   ```sh
   $ ./mvn clean install
   ```
2. Run the backend project app on local with Maven
   ```sh
   $ ./mvn spring-boot:run
   ```
   > It will be running at http://localhost:8083

3. (Optional) Generate and run jar file only for backend purposes
   ```sh
   $ ./mvn clean package
   ```
   > The jar is created under the $project/target/ folder
   ```sh
   $ java -jar assignment-1.0.0.jar
   ```

## Test

1. Execute bundle of unit and integration tests
   ```sh
   $ ./mvn test
   ```

## Usage
 

## Swagger UI

Open Swagger UI
1. After runing the project open this link :
http://localhost:8083/swagger-ui.html
 

## Contact

[Saeid Azimi](https://www.linkedin.com/in/saeedazimi)