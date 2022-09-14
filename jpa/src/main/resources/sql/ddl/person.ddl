CREATE TABLE person
(
    person_id NUMBER(255) NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    gender TINYINT NOT NULL, -- 0 = UnKnown, 1 = Male, 2 = Female
    age NUMBER(10),
    height NUMBER(10),
    weight NUMBER(10),
    is_married BOOL,
    company VARCHAR(100),
    country VARCHAR(100) NOT NULL,
    PRIMARY KEY (person_id)
);