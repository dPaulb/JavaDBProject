create table category(
category_id int not null,
category_name varchar(50),
constraint category_pk primary key(category_id));

create table publish(
publish_id int not null,
publish_name varchar(50),
constraint publish_pk primary key(publish_id));


create table book(
isbn int not null,
title varchar(50),
writer varchar(30),
price int,
stocknum int,
publish_id int,
category_id int,
constraint book_pk primary key(isbn),
CONSTRAINT book_fk_cateogry FOREIGN KEY(category_id) REFERENCES category(category_id),
CONSTRAINT book_fk_publish FOREIGN KEY(publish_id) REFERENCES publish(publish_id));

create table member(
userID varchar(50) not null,
userPassword varchar(50) not null,
userAge int not null,
userGender varchar(50) not null,
userEmail varchar(100) not null,
constraint user_pk primary key(userID));

select * from yuhan22.member;
