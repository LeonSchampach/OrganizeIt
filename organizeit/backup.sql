-- H2 2.2.224;
;             
CREATE USER IF NOT EXISTS "SA" SALT '441610865523f644' HASH 'f669d3ea851167e8a25be2116ce9e09043e6d2e342d1c3517115bd9fe60566ec' ADMIN;         
CREATE CACHED TABLE "PUBLIC"."DRAWER"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 7) DEFAULT ON NULL NOT NULL,
    "NAME" CHARACTER VARYING(255) NOT NULL,
    "SHELF_ID" BIGINT
);       
ALTER TABLE "PUBLIC"."DRAWER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");       
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.DRAWER;  
INSERT INTO "PUBLIC"."DRAWER" VALUES
(1, 'Schreibtischlade', 1),
(2, 'Drawer2', 1),
(3, 'Drawer3', 2),
(4, 'Drawer4', 2),
(5, 'Drawer5', 3),
(6, 'Drawer6', 3);         
ALTER TABLE "PUBLIC"."DRAWER" ADD CONSTRAINT "PUBLIC"."FK_SHELF" FOREIGN KEY("SHELF_ID") REFERENCES "PUBLIC"."SHELF"("ID") NOCHECK;           