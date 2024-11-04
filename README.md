# OrganizeIt

OrganizeIt is a Spring Boot application designed to help you organize your shelves and drawers. It uses an H2 database for storage and provides endpoints to view and manage the contents of your shelves and drawers.

## Features

- Manage shelves and drawers
- View all shelves and their corresponding drawers
- RESTful endpoints for accessing and manipulating data
- Thymeleaf templates for dynamic web pages
- Static resources for a responsive and interactive UI

## Technologies Used

- Spring Boot
- H2 Database
- Spring Data JPA
- Thymeleaf
- HTML, JavaScript, CSS

## Prerequisites

- Java 8 or higher
- Maven or Gradle

## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/LeonSchampach/OrganizeIt.git
   cd OrganizeIt
   ```

2. **Build the project:**

    Using Maven:
   ```bash
   mvn clean install
   ```

   Using Gradle:
   ```bash
   gradle build
   ```

3. **Run the application:**

    Using Maven:
   ```bash
   mvn spring-boot:run
   ```

   Using Gradle:
   ```bash
   gradle bootRun
   ```

4. **Access the application:**

    Open your web browser and go to `http://localhost:8080`.

## Project Structure

* `src/main/java`: Contains the Java source code
  * `com.organizeit`: Main package containing all the code
    * `controller`: Contains the Spring controllers
    * `db.entity`: Contains the JPA entity classes
    * `db.repository`: Contains the Spring Data JPA repositories
    * `db.service`: Contains the service layer classes
* `src/main/resources`: Contains the the resources
  * `templates`: Contains Thymeleaf templates
  * `application.properties`: Spring Boot configuration file
  * `schema.sql`: SQL script for creating database schema
  * `data.sql`: SQL script for inserting initial data

 ## Example SQL Data
 
 Here is an example `schema.sql` file to initialize the H2 database.
 
 ❗ Note: At the moment the tables created in the `schema.sql` file get dropped after every restart for testing purposes. ❗

 `schema.sql`
 ```sql
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS drawer;
DROP TABLE IF EXISTS shelf;

DROP TABLE IF EXISTS ITEM;
DROP TABLE IF EXISTS DRAWER;
DROP TABLE IF EXISTS SHELF;
DROP TABLE IF EXISTS REL_USER_LIST;
DROP TABLE IF EXISTS SHELF_LIST;
DROP TABLE IF EXISTS END_USER;
DROP TABLE IF EXISTS USER_ROLE;

CREATE TABLE IF NOT EXISTS END_USER
(
    ID        BIGINT          NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS SHELF_LIST (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS REL_USER_LIST (
    USER_ID BIGINT NOT NULL,
    LIST_ID BIGINT NOT NULL,
    PRIMARY KEY (USER_ID, LIST_ID),
    CONSTRAINT FK_USER_ID FOREIGN KEY (USER_ID) REFERENCES END_USER(ID),
    CONSTRAINT FK_LIST_ID FOREIGN KEY (LIST_ID) REFERENCES SHELF_LIST(ID)
);

CREATE TABLE IF NOT EXISTS SHELF (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    ROOM VARCHAR(255),
    SHELF_LIST_ID BIGINT NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_SHELF_LIST FOREIGN KEY (SHELF_LIST_ID) REFERENCES SHELF_LIST(ID)
);

CREATE TABLE IF NOT EXISTS DRAWER (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    DRAWER_ORDER INT NOT NULL,
    SHELF_ID BIGINT,
    PRIMARY KEY (ID),
    CONSTRAINT FK_SHELF FOREIGN KEY (SHELF_ID) REFERENCES SHELF(ID)
);

CREATE TABLE IF NOT EXISTS ITEM (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    DESC VARCHAR(255),
    QUANTITY FLOAT NOT NULL,
    DRAWER_ID BIGINT,
    PRIMARY KEY (ID),
    CONSTRAINT FK_DRAWER FOREIGN KEY (DRAWER_ID) REFERENCES DRAWER(ID)
);
```


`data.sql`

In this file data can be inserted everytime the application is started. If you dont want the database to have any data in the beginning you can leave the file empty or delete it.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Feel free to submit issues, fork the repository and send pull requests. For major changes, please open an issue first to discuss what you would like to change.

## Contact

For any questions suggestions, please contact leon.schampach@gmail.com.
