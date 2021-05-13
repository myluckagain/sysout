
DROP TABLE IF EXISTS CATEGORY;
CREATE TABLE IF NOT EXISTS CATEGORY(
                                     ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     NAME VARCHAR(255)
);

DROP TABLE IF EXISTS ANIMAL;
CREATE TABLE IF NOT EXISTS ANIMAL(
                                     ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     NAME VARCHAR(255),
                                     category_id bigint,
                                     foreign key (category_id) references CATEGORY(id)
);

CREATE SEQUENCE HIBERNATE_SEQUENCE MINVALUE 1;