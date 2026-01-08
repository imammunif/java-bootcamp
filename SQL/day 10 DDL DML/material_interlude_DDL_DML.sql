CREATE TABLE address(
	id SERIAL PRIMARY KEY,
	addressName VARCHAR(200)
);

CREATE TABLE person(
	id SERIAL PRIMARY KEY,
	full_name VARCHAR(20)
	--address INT REFERENCE address(id)
);

ALTER TABLE person ADD COLUMN address_id INT;

ALTER TABLE person
	ADD CONSTRAINT address_fk FOREIGN KEY(address_id)
	REFERENCES address(id);

INSERT INTO address(addressname)
	VALUES
	('Jl. Tebet Raya');

SELECT id, addressname FROM address a;

SELECT * FROM address a ORDER BY a.addressname ASC;

SELECT * FROM address a WHERE id = 2;

SELECT * FROM address a WHERE a.id NOT IN (2, 4, 5);

SELECT * FROM address a WHERE a.id NOT BETWEEN 2 AND 5;

SELECT * FROM address a WHERE a.addressname LIKE '%Pancoran%';

SELECT * FROM address a WHERE LOWER(a.addressname) LIKE '%tebet%';

SELECT * FROM address a WHERE a.addressname ILIKE '%tebet%';

SELECT * FROM address a WHERE a.addressname ILIKE '%tebet_____'; --includes space

SELECT * FROM address LIMIT 2




