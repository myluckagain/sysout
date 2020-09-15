insert into topic (id, title) values (-1,'title1');
insert into topic (id, title) values (-2,'duplicated title');
insert into topic (id, title) values (-3,'duplicated title');

insert into comment (id, text, topic_id) values (-4, 'text1', -1);
insert into comment (id, text, topic_id) values (-5, 'text2', -1);
insert into comment (id, text, topic_id) values (-6, 'text3', -1);
