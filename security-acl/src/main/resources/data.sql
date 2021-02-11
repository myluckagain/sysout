INSERT INTO document(id,content) VALUES
(1,'Document 1'),
(2,'Document 2'),
(3,'Document 3');


INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'user1'),
(2, 1, 'user2'),
(3, 1, 'admin');

INSERT INTO acl_class (id, class) VALUES
(1, 'ru.sysout.model.Document');


INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),
(2, 1, 2, NULL, 3, 0),
(3, 1, 3, NULL, 3, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 3, 1, 1, 1, 1),
(3, 1, 3, 3, 2, 1, 1, 1),
(4, 2, 1, 2, 1, 1, 1, 1),
(5, 2, 2, 3, 1, 1, 1, 1),
(6, 2, 3, 3, 2, 1, 1, 1),
(7, 3, 1, 3, 1, 1, 1, 1),
(8, 3, 2, 3, 2, 1, 1, 1);
