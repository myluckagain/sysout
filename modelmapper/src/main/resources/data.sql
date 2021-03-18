insert into user (id, email, nickname, password, role, locked) values (1,'admin@example.com', 'admin', 'password', 'ROLE_ADMIN', false);
insert into user (id, email, nickname, password, role, locked) values (2,'user@example.com', 'bob', 'password', 'ROLE_USER', false);

insert into post (id, title, text, created_date_time, user_id) values (3,'Super Post3', 'Super Text3', CURRENT_TIMESTAMP(), 1);
insert into post (id, title, text, created_date_time, user_id) values (4,'Super Post4', 'Super Text4', CURRENT_TIMESTAMP(), 1);
insert into post (id, title, text, created_date_time, user_id) values (5,'Super Post5', 'Super Text5', CURRENT_TIMESTAMP(), 1);


