DROP TABLE IF EXISTS TBL_ENTITY1;

CREATE TABLE TBL_ENTITY1 (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(250) NOT NULL
);

INSERT INTO TBL_ENTITY1 (ID, NAME) VALUES
  (1, 'Entity11'), (2, 'Entity12'), (3, 'Entity13');