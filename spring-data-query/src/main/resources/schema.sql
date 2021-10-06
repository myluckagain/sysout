CREATE TABLE IF NOT EXISTS USER (
                                     id BIGINT  primary key ,
                                     email varchar(255) not null,
                                     nickname varchar(255) not null,
                                     password varchar(255) not null,
                                     role varchar(255) not null,
                                     locked boolean
                                 );

CREATE TABLE IF NOT EXISTS POST (
                                    id bigint primary key ,
                                    title varchar(255),
                                    text varchar(255),
                                    user_id bigint references USER(id)
);


create sequence  IF NOT EXISTS hiber_sequence MINVALUE 10;