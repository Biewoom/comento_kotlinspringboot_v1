CREATE TABLE country
(
    zip_code NUMBER(100) NOT NULL UNIQUE ,
    name VARCHAR(45) NOT NULL UNIQUE,
    capital_city VARCHAR(45) NOT NULL,
    PRIMARY KEY (zip_code)
);