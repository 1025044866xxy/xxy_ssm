create database shop;
use shop;
create table User(id int unsigned not null auto_increment primary key,username varchar(20),password varchar(20));

create table goods(id int unsigned not null auto_increment primary key,goodsName VARCHAR(20),goodsNum int,goodsPrice DOUBLE ,goodsModel varchar(88));
insert into goods(goodsName ,goodsNum ,goodsPrice ,goodsModel) VALUES ("1",10,100,"img/1.jpg");

create table RootUser(id int unsigned not null auto_increment primary key,username varchar(20),password varchar(20));