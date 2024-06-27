# OrganizeIt

OrganizeIt is a Spring Boot application designed to help you organize your shelves and drawers. It uses an H2 database for storage and provides a web interface to view and manage the contents of your shelves and drawers.

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
   git clone https://github.com/your-username/OrganizeIt.git
   cd OrganizeIt

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
 
 Here are example `schema.sql` and `data.sql` files to initialize the H2 database.

 `schema.sql`
 ```sql
CREATE TABLE IF NOT EXISTS shelf (
                       name VARCHAR(255) NOT NULL,
                       room VARCHAR(255),
                       PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS drawer (
                        name VARCHAR(255) NOT NULL,
                        shelf_name VARCHAR(255),
                        PRIMARY KEY (name),
                        CONSTRAINT fk_shelf FOREIGN KEY (shelf_name) REFERENCES shelf(name)
);
CREATE TABLE IF NOT EXISTS item (
                        id LONG NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        desc VARCHAR(255),
                        drawer_name VARCHAR(255),
                        PRIMARY KEY (name),
                        CONSTRAINT fk_drawer FOREIGN KEY (drawer_name) REFERENCES drawer(name)
);
```

`data.sql`
 ```sql
-- Insert data into the shelf table
INSERT INTO shelf (name, room) VALUES ('Schreibtisch', 'Leons Zimmer');
INSERT INTO shelf (name, room) VALUES ('Shelf2', 'RoomB');
INSERT INTO shelf (name, room) VALUES ('Shelf3', 'RoomC');

-- Insert data into the drawer table
INSERT INTO drawer (name, shelf_name) VALUES ('Schreibtischlade', 'Schreibtisch');
INSERT INTO drawer (name, shelf_name) VALUES ('Drawer2', 'Schreibtisch');
INSERT INTO drawer (name, shelf_name) VALUES ('Drawer3', 'Shelf2');
INSERT INTO drawer (name, shelf_name) VALUES ('Drawer4', 'Shelf2');
INSERT INTO drawer (name, shelf_name) VALUES ('Drawer5', 'Shelf3');
INSERT INTO drawer (name, shelf_name) VALUES ('Drawer6', 'Shelf3');

-- Insert data into the item table
INSERT INTO item (name, desc, drawer_name) VALUES ('USB-Stick', '8GB, HTL-Wels Diplomarbeits USB-Stick', 'Schreibtischlade');
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Feel free to submit issues, fork the repository and send pull requests. For major changes, please open an issue first to discuss what you would like to change.

## Contact

For any questions suggestions, please contact leon.schampach@gmail.com.
