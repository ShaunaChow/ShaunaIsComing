create database ShaunaIsComing;

use ShaunaIsComing

create table users (
  id int primary key  auto_increment,
  phonenum varchar(11) unique ,
  password varchar(30) ,
  name varchar (20) ,
  home varchar (12) ,
  registdata date ,
  type int
);

create index phoneindex on users (phonenum);

insert into users
(phonenum, password, name, home, registdata, type)
values
(17318588134,'zxf1023778132','Shauna','/',now(),0);

insert into users
(phonenum, password, name, home, registdata, type)
values
(15869100891,'AAaa1234','System','/',now(),0);