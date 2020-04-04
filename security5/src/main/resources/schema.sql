create table my_user(
 login varchar_ignorecase(50) not null primary key,
 password varchar_ignorecase(50) not null,
 position  varchar_ignorecase(50) not null not null,
 authority varchar_ignorecase(50) not null not null
 );