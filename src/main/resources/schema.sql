SET MODE MYSQL;

DROP TABLE IF EXISTS holidays;
DROP TABLE IF EXISTS businessDays;
DROP TABLE IF EXISTS stores;


CREATE TABLE stores (
id int auto_increment primary key,
name varchar(50) not null,
owner varchar(20) NOT NULL,
description varchar(500) NOT NULL,
level int NOT NULL,
address varchar(500),
phone varchar(50)
);

CREATE TABLE businessDays (
id int NOT NULL,
day varchar(30) NOT NULL,
open time not null,
close time not null,
CONSTRAINT FK_storeIdForBusinessDays FOREIGN KEY (id) REFERENCES stores (id) 
ON DELETE CASCADE ON UPDATE CASCADE
);

create table holidays (
id int not null,
holidays datetime not null,
CONSTRAINT FK_storeIdForHolidays FOREIGN KEY (id) REFERENCES stores (id) 
ON DELETE CASCADE ON UPDATE CASCADE
);