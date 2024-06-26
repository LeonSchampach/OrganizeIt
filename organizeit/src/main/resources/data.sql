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