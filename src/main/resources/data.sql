DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS customer_address;

CREATE TABLE customer (
	customer_id INT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL
);

CREATE TABLE customer_address (
	address_id INT AUTO_INCREMENT PRIMARY KEY,
	customer_id INT REFERENCES customer(customer_id),
	street VARCHAR(30) NOT NULL,
	city VARCHAR(30) NOT NULL,
	state VARCHAR(30) NOT NULL,
	zipcode INT NOT NULL
);

INSERT INTO customer (first_name, last_name) VALUES
	('Tom', 'Brady'),
	('Sam', 'Doan'),
	('Rob', 'Winchester'),
	('Jerry', 'Donald');
	
INSERT INTO customer_address (customer_id, street, city, state, zipcode) VALUES
	(1, '5 Roper Rd', 'Plano', 'TX', 75039),
	(2, '1532 Washington Ave', 'Richardson', 'TX', 75064),
	(3, '2203 Ranch Trail', 'Irving', 'TX', 75081),
	(4, '66 Newyork St', 'Frisco', 'TX', 75070);
	
	
	