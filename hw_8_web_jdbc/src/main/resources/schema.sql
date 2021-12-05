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
    
price         bigint       not null,
    
instock       bit          null

);

create table my_schema.order_product
(

id            bigint auto_increment primary key,

order_id      bigint       not null,

product_id    bigint       not null

);


insert into my_schema.orders values (1, 'Customer 1', b'0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (2, 'Customer 2', b'0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (3, 'Customer 3', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (4, 'Customer 4', b'0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.orders values (5, 'Customer 5', b'1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into my_schema.products values(1, 'Product 1', '100', b'1');

insert into my_schema.products values(2, 'Product 2', '200', b'1');

insert into my_schema.products values(3, 'Product 3', '300', b'0');

insert into my_schema.products values(4, 'Product 4', '400', b'1');

insert into my_schema.products values(5, 'Product 5', '500', b'0');

