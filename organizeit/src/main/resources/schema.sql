DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS drawer;
DROP TABLE IF EXISTS shelf;

CREATE TABLE /*IF NOT EXISTS*/ shelf (
                        id LONG NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        room VARCHAR(255),
                        PRIMARY KEY (id)
);

CREATE TABLE /*IF NOT EXISTS*/ drawer (
                        id LONG NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        shelf_id LONG,
                        PRIMARY KEY (id),
                        CONSTRAINT fk_shelf FOREIGN KEY (shelf_id) REFERENCES shelf(id)
);
CREATE TABLE /*IF NOT EXISTS*/ item (
                        id LONG NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        desc VARCHAR(255),
                        quantity int NOT NULL,
                        drawer_id LONG,
                        PRIMARY KEY (id),
                        CONSTRAINT fk_drawer FOREIGN KEY (drawer_id) REFERENCES drawer(id)
);
