CREATE TABLE IF NOT EXISTS REPORT
(
    id    BIGINT primary key,
    published BOOLEAN
);

CREATE TABLE IF NOT EXISTS ADDRESS
(
    id    BIGINT primary key,
    name VARCHAR(250)
);