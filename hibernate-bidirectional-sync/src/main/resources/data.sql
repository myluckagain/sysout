insert into topic (id, title) values (-1,'title1');

insert into comment (id, text, topic_id) values (-4, 'text1', -1);
insert into comment (id, text, topic_id) values (-5, 'text2', -1);
insert into comment (id, text, topic_id) values (-6, 'text3', -1);


insert into users (id, name) values (-11,'Ivan');
insert into users (id, name) values (-12, 'John');
insert into users (id, name) values (-13, 'Petr');

insert into user_details (phone, id) values ('154623',  -11);
insert into user_details (phone, id) values ('435',  -12);
insert into user_details (phone, id) values ('3454',  -13);