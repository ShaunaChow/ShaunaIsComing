create database ShaunaIsComing;

use ShaunaIsComing

-- 创建用户表
create table users (
  id int primary key  auto_increment,
  phonenum varchar(11) unique ,
  password varchar(30) ,
  name varchar (20) ,
  home varchar (20) ,
  registdata date ,
  type int
);

create index phoneindex on users (phonenum);

insert into users
(phonenum, password, name, home, registdata, type)
values
(17318588134,'zxf1023778132','Master','/',now(),0);

insert into users
(phonenum, password, name, home, registdata, type)
values
(15869100891,'AAaa1234','System','/',now(),0);

insert into users
(phonenum, password, name, home, registdata, type)
values
(13248139750,'LTR2004110','System','/',now(),0);

select * from users;

update users set name='User' where id not in (1,2,3);


-- 创建角色表
create table roles (
  id int primary key auto_increment,
  roleName varchar(50) default null,
  description varchar(255) default null,
  createTime timestamp,
  updateTime timestamp,
  unique key unique_roleName (roleName)
);

-- 创建角色用户关联表
create table user_role (
  userId int not null ,
  roleId int not null ,
  createTime timestamp ,
  creator varchar(255),
  primary key (userId, roleId)
);

-- 创建权限表
create table permission (
  id int primary key auto_increment,
  code varchar(32) not null comment '权限标识符',
  description varchar(255) default null,
  url varchar(128) not null comment 'url地址'
);

-- 创建角色权限关联表
create table role_permission (
  roleId int not null ,
  permissionId int not null ,
  createTime timestamp ,
  creator varchar(255),
  primary key (roleId, permissionId)
);

insert into roles
(roleName,createTime,updateTime)
values
('System',now(),now()),
('User',now(),now());

insert into user_role
(userId,roleId,createTime,creator)
values
(1,1,now(),'ShaunaChow'),
(2,1,now(),'ShaunaChow'),
(3,1,now(),'ShaunaChow'),
(5,2,now(),'ShaunaChow'),
(6,2,now(),'ShaunaChow'),
(7,2,now(),'ShaunaChow');

insert into permission
(code,url)
values
('1','/r/r1'),
('2','/r/r2');