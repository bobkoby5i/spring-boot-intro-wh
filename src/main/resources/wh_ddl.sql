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

-- Schema for security implementation
create table users(
	username varchar(50) not null primary key,
	password varchar(150) not null,
	enabled boolean
);

create table authorities(
	username varchar(50) NOT null,
	authority varchar(50) NOT null,
	FOREIGN KEY (username) REFERENCES users(username)
);

create unique index idx_auth_username on authorities (username, authority);


--password='password'
--encodedPassword='$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC'
insert into users(username, password, enabled) values('user', '$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC', true);
insert into users(username, password, enabled) values('admin', '$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC', true);

insert into authorities(username, authority) values('user','ROLE_USER');
insert into authorities(username, authority) values('admin','ROLE_ADMIN');

wh_db=# select * from users;
 username |                           password                           | enabled
----------+--------------------------------------------------------------+---------
 user     | $2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC | t
 admin    | $2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC | t
(2 rows)


wh_db=# select * from authorities;
 username | authority
----------+------------
 user     | ROLE_USER
 admin    | ROLE_ADMIN
(2 rows)


-- JPA SECURITY
DROP TABLE USERS;
create table users(
    id                    SERIAL PRIMARY KEY,
    userName              varchar(50) not null,
    password              varchar(150) not null,
    enabled               boolean,
    accountNonExpired     boolean,
    accountNonLocked      boolean,
    credentialsNonExpired boolean,
    roles                 varchar(150) not null
);
insert into users(username, password, enabled,accountNonExpired,accountNonLocked,credentialsNonExpired,roles) values('user', '$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC', true, true, true, true,'ROLE_USER,ROLE_ADMIN'));

1	bob	$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC	true	true	true	true	ROLE_USER,ROLE_ADMIN
2	user	$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC	true	true	true	true	ROLE_USER
3	admin	$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC	true	true	true	true	ROLE_ADMIN


--
-- MySQL old version for lesson 1
--
DROP TABLE items;
CREATE TABLE items
(
    id                     int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name                   varchar(15),
    description            varchar(30),
    qty                    int,
    price                  decimal(10,2)
);


