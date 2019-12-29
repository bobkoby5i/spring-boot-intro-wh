-- PostgreSQL old version for lesson 10 with thymeleaf frontend
DROP TABLE items;
truncate table items;
CREATE TABLE items
(
    id                     SERIAL PRIMARY KEY,
    name                   varchar(15) not NULL,
    description            varchar(30) not NULL,
    qty                    int not NULL,
	price				   decimal(100,2) not NULL,
    created_at             timestamptz DEFAULT NOW() not NULL,
    updated_at             timestamptz DEFAULT NOW() not NULL
);

insert into items (name, description, qty, price, created_at) values('monitor', 'monitor samsung', 20, 450,now());
insert into items (name, description, qty, price, created_at) values('pc', 'dell', 3, 800,now());
insert into items (name, description, qty, price, created_at) values('laptop', 'hp', 10, 1500,now());
insert into items (name, description, qty, price, created_at) values('mouse', 'logitech', 10, 29.99,now());

select * from items order by id;
select * from items;
 id |  name   |   description   | qty |  price  |          created_at           |          updated_at
----+---------+-----------------+-----+---------+-------------------------------+-------------------------------
  1 | monitor | monitor samsung |  20 |  450.00 | 2019-12-29 11:02:02.887917+01 | 2019-12-29 11:02:02.887917+01
  2 | pc      | dell            |   3 |  800.00 | 2019-12-29 11:02:02.887917+01 | 2019-12-29 11:02:02.887917+01
  3 | laptop  | hp              |  10 | 1500.00 | 2019-12-29 11:02:02.887917+01 | 2019-12-29 11:02:02.887917+01
  4 | mouse   | logitech        |  10 |   29.99 | 2019-12-29 11:02:02.887917+01 | 2019-12-29 11:02:02.887917+01
  5 | Kubek   | Kubek starbucks |  10 |   21.00 | 2019-12-29 11:02:19.992513+01 | 2019-12-29 11:02:51.990521+01
(5 rows)

-- MySQL old version for lesson 1
DROP TABLE items;
CREATE TABLE items
(
    id                     int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name                   varchar(15),
    description            varchar(30),
    qty                    int,
        price                 decimal(10,2)
);
