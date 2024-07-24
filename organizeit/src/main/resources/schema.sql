DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS drawer;
DROP TABLE IF EXISTS shelf;
DROP TABLE IF EXISTS END_USER;
DROP TABLE IF EXISTS USER_ROLE;

CREATE TABLE IF NOT EXISTS USER_ROLE
(
    ID             INT          NOT NULL AUTO_INCREMENT,
    IDENTIFIER VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(1000) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS END_USER
(
    ID        INT          NOT NULL AUTO_INCREMENT,
    FIRSTNAME VARCHAR(100) NOT NULL,
    LASTNAME  VARCHAR(100) NOT NULL,
    MAIL      VARCHAR(100) NOT NULL,
    PASSWORD  VARCHAR(100) NOT NULL,
    USER_ROLE_ID INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (USER_ROLE_ID) REFERENCES USER_ROLE (ID)
);

CREATE TABLE IF NOT EXISTS shelf (
                        id LONG NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        room VARCHAR(255),
                        PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS drawer (
                        id LONG NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        shelf_id LONG,
                        PRIMARY KEY (id),
                        CONSTRAINT fk_shelf FOREIGN KEY (shelf_id) REFERENCES shelf(id)
);

CREATE TABLE IF NOT EXISTS item (
                        id LONG NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        desc VARCHAR(255),
                        quantity int NOT NULL,
                        drawer_id LONG,
                        PRIMARY KEY (id),
                        CONSTRAINT fk_drawer FOREIGN KEY (drawer_id) REFERENCES drawer(id)
);
