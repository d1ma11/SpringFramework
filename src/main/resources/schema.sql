CREATE TABLE if NOT EXISTS Taco_Order (
    id IDENTITY,
    delivery_Name VARCHAR(50) NOT NULL,
    delivery_Street VARCHAR(50) NOT NULL,
    delivery_City VARCHAR(50) NOT NULL,
    delivery_State VARCHAR(2) NOT NULL,
    delivery_Zip VARCHAR(10) NOT NULL,
    cc_number VARCHAR(16) NOT NULL,
    cc_expiration VARCHAR(5) NOT NULL,
    cc_cvv VARCHAR(3) NOT NULL,
    placed_at timestamp NOT NULL
);

CREATE TABLE if NOT EXISTS Taco (
    id identity,
    name VARCHAR(50) NOT NULL,
    taco_order bigint NOT NULL,
    taco_order_key bigint NOT NULL,
    created_at timestamp NOT NULL
);

CREATE TABLE if NOT EXISTS Ingredient_Ref (
    ingredient VARCHAR(4) NOT NULL,
    taco bigint NOT NULL,
    taco_key bigint NOT NULL
);

CREATE TABLE if NOT EXISTS Ingredient (
    id VARCHAR(4) NOT NULL PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10) NOT NULL
);

ALTER TABLE Taco ADD FOREIGN KEY (taco_order) REFERENCES Taco_Order(id);
ALTER TABLE Ingredient_Ref ADD FOREIGN KEY (ingredient) REFERENCES Ingredient(id);