insert into topic (id, title) values (-1,'title1');
insert into topic (id, title) values (-2, 'title2');
insert into topic (id, title) values (-3, 'title3');

insert into comment (id, text, topic_id) values (-4, 'text1', -1);
insert into comment (id, text, topic_id) values (-5, 'text2', -1);
insert into comment (id, text, topic_id) values (-6, 'text3', -1);


insert into good_topic (id, title) values (-11,'title1');
insert into good_topic (id, title) values (-12, 'title2');
insert into good_topic (id, title) values (-13, 'title3');

insert into good_comment (id, text, topic_id) values (-14, 'text1', -11);
insert into good_comment (id, text, topic_id) values (-15, 'text2', -11);
insert into good_comment (id, text, topic_id) values (-16, 'text3', -11);