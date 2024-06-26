DROP TABLE item;
DROP TABLE drawer;
DROP TABLE shelf;

CREATE TABLE /*IF NOT EXISTS*/ shelf (
                       name VARCHAR(255) NOT NULL,
                       room VARCHAR(255),
                       PRIMARY KEY (name)
);

CREATE TABLE /*IF NOT EXISTS*/ drawer (
                        name VARCHAR(255) NOT NULL,
                        shelf_name VARCHAR(255),
                        PRIMARY KEY (name),
                        CONSTRAINT fk_shelf FOREIGN KEY (shelf_name) REFERENCES shelf(name)
);
CREATE TABLE /*IF NOT EXISTS*/ item (
                        id LONG NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        desc VARCHAR(255),
                        drawer_name VARCHAR(255),
                        PRIMARY KEY (name),
                        CONSTRAINT fk_drawer FOREIGN KEY (drawer_name) REFERENCES drawer(name)
);
