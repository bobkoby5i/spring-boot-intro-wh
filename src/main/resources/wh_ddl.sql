DROP TABLE items;
CREATE TABLE items
(
    id                     SERIAL PRIMARY KEY,
    name                   varchar(15),
    description            varchar(30),
    qty                    int,
		price									 decimal(100,2)
);

insert into items (name, description, qty, price) values('monitor', 'monitor samsung', 20, 450);
insert into items (name, description, qty, price) values('pc', 'dell', 3, 800);
insert into items (name, description, qty, price) values('laptop', 'hp', 10, 1500);
insert into items (name, description, qty, price) values('mouse', 'logitech', 10, 29.99);

wh_db=# select * from items;
 id |  name   |   description   | qty |  price
----+---------+-----------------+-----+---------
  1 | monitor | monitor samsung |  20 |  450.00
  2 | pc      | dell            |   3 |  800.00
  3 | laptop  | hp              |  10 | 1500.00
  4 | mouse   | logitech        |  10 |   29.99
(4 rows)



DROP TABLE items;
CREATE TABLE items
(
    id                     int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name                   varchar(15),
    description            varchar(30),
    qty                    int,
        price                 decimal(10,2)
);
