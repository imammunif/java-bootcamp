CREATE TABLE driver(
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(50),
    plat_no VARCHAR(10)
);

CREATE TABLE item(
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(50),
    price float
);

CREATE TABLE food_order(
    id SERIAL PRIMARY KEY,
    order_no VARCHAR(10),
    driver_id INT NOT NULL,

    FOREIGN KEY(driver_id) REFERENCES driver(id)
);

CREATE TABLE food_order_dtl(
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    qty int NOT NULL,

    FOREIGN KEY(order_id) REFERENCES food_order(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
);

-- DML
INSERT INTO 
    driver(full_name, plat_no) 
VALUES
    ('Budi', 'B 1234 ABC'),
    ('Jono', 'B 1235 ABD'),
    ('Joni', 'B 1236 ABC'),
    ('Budi', 'B 1237 ABE'),
    ('Ani', 'B 1238 ABC');

INSERT INTO 
    item(item_name, price) 
VALUES
    ('Item 1', 1000),
    ('Item 2', 2000),
    ('Item 3', 3000),
    ('Item 4', 4000),
    ('Item 5', 5000);

INSERT INTO 
    food_order(order_no, driver_id) 
VALUES
    ('TRX001', 1),
    ('TRX002', 2);

INSERT INTO 
    food_order_dtl(order_id, item_id, qty) 
VALUES
    (1, 1, 10),
    (1, 2, 5),
    (2, 1, 6),
    (2, 2, 7),
    (2, 3, 1);