CREATE TABLE IF NOT EXISTS USER (
     id integer not null,
     email varchar(255) not null,
     nickname varchar(255) not null,
     password varchar(255) not null,
     role varchar(255) not null,
     locked boolean,
     primary key (id)
);
ALTER TABLE
    USER
    ADD CONSTRAINT EMAIL_UNIQUE
        UNIQUE (email);
ALTER TABLE
    USER
    ADD CONSTRAINT NICKNAME_UNIQUE
        UNIQUE (nickname);


CREATE TABLE IF NOT EXISTS POST (
     id integer not null,
     title varchar(255),
     text clob,
     created_date_time timestamp,
     user_id integer not null,
     primary key (id)
);
ALTER TABLE POST
ADD FOREIGN KEY (user_id) REFERENCES USER(id);

create sequence hiber_sequence MINVALUE 10;