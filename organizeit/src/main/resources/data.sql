-- Insert data into the shelf table and capture the generated IDs using session variables
INSERT INTO shelf (name, room) VALUES ('Schreibtisch', 'Leons Zimmer');
SET @schreibtischId = (SELECT id FROM shelf WHERE name = 'Schreibtisch');

INSERT INTO shelf (name, room) VALUES ('Shelf2', 'RoomB');
SET @shelf2Id = (SELECT id FROM shelf WHERE name = 'Shelf2');

INSERT INTO shelf (name, room) VALUES ('Shelf3', 'RoomC');
SET @shelf3Id = (SELECT id FROM shelf WHERE name = 'Shelf3');

-- Insert data into the drawer table using the captured shelf IDs
INSERT INTO drawer (name, shelf_id) VALUES ('Schreibtischlade', @schreibtischId);
SET @d1Id = (SELECT id FROM drawer WHERE name ='Schreibtischlade');
INSERT INTO drawer (name, shelf_id) VALUES ('Drawer2', @schreibtischId);
INSERT INTO drawer (name, shelf_id) VALUES ('Drawer3', @shelf2Id);
INSERT INTO drawer (name, shelf_id) VALUES ('Drawer4', @shelf2Id);
INSERT INTO drawer (name, shelf_id) VALUES ('Drawer5', @shelf3Id);
INSERT INTO drawer (name, shelf_id) VALUES ('Drawer6', @shelf3Id);


-- Insert data into the item table
INSERT INTO item (name, desc, quantity, drawer_id) VALUES ('USB-Stick', '8GB, HTL-Wels Diplomarbeits USB-Stick', 1, @d1Id);