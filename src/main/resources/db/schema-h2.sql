DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id      int primary key auto_increment,
    name    VARCHAR,
    age     INT,
    email   VARCHAR,
    user_id VARCHAR,
    dept_no   VARCHAR
);

DROP TABLE IF EXISTS dept;
CREATE TABLE dept
(
    id        int primary key auto_increment,
    dept_name VARCHAR,
    dept_no   VARCHAR
);
