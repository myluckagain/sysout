insert into groups(group_name, id) values('users', 0);
insert into groups(group_name, id) values('admins', 1);

insert into group_authorities(group_id, authority) values(0, 'ROLE_USER');
insert into group_authorities(group_id, authority) values(1, 'ROLE_ADMIN');

insert into group_members(username, group_id) values('user',0);
insert into group_members(username, group_id) values('admin',1);



insert into my_user(login, password, authority) values('user',  'password', 'ROLE_A');
insert into my_user(login, password, authority) values('admin',  'password', 'ROLE_B');