CREATE SEQUENCE hibernate_sequence START 100;
create table animal
(
    id       bigint primary key,
    name     varchar(255)
);

insert into animal (id, name)
values (1,'animal1'),
       (2,'animal2'),
       (3,'animal3');



