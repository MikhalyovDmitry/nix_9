CREATE SCHEMA my_schema;

create table my_schema.orders
(
    
id            bigint auto_increment primary key,
    
customer      varchar(255) not null,
    
delivered     bit          null,
    
created       datetime(6)  null,
    
updated       datetime(6)  null

);



create table my_schema.products
(
    
id            bigint auto_increment primary key,
    
name          varchar(255) not null,
    
price         decimal(10, 2)       not null,
    
instock       bit          null

);

create table my_schema.order_product
(

id            bigint auto_increment primary key,

order_id      bigint       not null,

product_id    bigint       not null

);


insert into my_schema.orders values (1, 'ФОП Калиниченко С.И', b'0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (2, 'ФОП Федотов В.В', b'0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (3, 'ФОП Линдеман Т.А.', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (4, 'ТОВ АкваСити', b'0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (5, 'ФОП Синцов С.П', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (6, 'ФОП Безручка О.Е', b'0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (7, 'ТОВ Эко Сити', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (8, 'ФОП Близнюк С.С', b'0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (9, 'ФОП Седов Л.Г', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (10, 'ТОВ Фудс Юнайтед', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (11, 'ТОВ ТНВ', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (12, 'ФОП Бойко Д.И', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (13, 'ТОВ Афина-Групп', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (14, 'ФОП Краснова Е.Е', b'0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.products values(1, 'Коньяк Шустов 3* 0.25л', '65.32', b'1');

insert into my_schema.products values(2, 'Коньяк Шустов 4* 0.25л', '68.43', b'1');

insert into my_schema.products values(3, 'Коньяк Шустов 5* 0.25л', '74.68', b'1');

insert into my_schema.products values(4, 'Коньяк Шустов 3* 0.5л', '123.19', b'1');

insert into my_schema.products values(5, 'Коньяк Шустов 4* 0.5л', '131.26', b'0');

insert into my_schema.products values(6, 'Коньяк Шустов 5* 0.5л', '155.65', b'1');

insert into my_schema.products values(7, 'Коньяк Арарат 3* 0.25л', '77.75', b'1');

insert into my_schema.products values(8, 'Коньяк Арарат 4* 0.25л', '83.54', b'1');

insert into my_schema.products values(9, 'Коньяк Арарат 5* 0.25л', '95.23', b'1');

insert into my_schema.products values(10, 'Коньяк Арарат 3* 0.5л', '153.87', b'1');

insert into my_schema.products values(11, 'Коньяк Арарат 4* 0.5л', '162.76', b'0');

insert into my_schema.products values(12, 'Коньяк Арарат 5* 0.5л', '195.34', b'1');

insert into my_schema.products values(13, 'Виски Hankey Bannister 0.5', '289.87', b'1');

insert into my_schema.products values(14, 'Виски Hankey Bannister 0.7', '399.99', b'1');

insert into my_schema.products values(15, 'Виски Scottish Leader 0.5', '255.66', b'1');

insert into my_schema.products values(16, 'Виски Scottish Leader 0.7', '320.55', b'1');


