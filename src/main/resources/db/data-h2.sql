DELETE
FROM user;

INSERT INTO user (id, name, age, email, user_id, dept_no)
VALUES (1, 'Jone', 18, 'test1@baomidou.com', 1, 20),
       (2, 'Jack', 20, 'test2@baomidou.com', 2, 10),
       (3, 'Tom', 28, 'test3@baomidou.com', 3, 10),
       (4, 'Sandy', 21, 'test4@baomidou.com', 4, 10),
       (5, 'Billie', 24, 'test5@baomidou.com', 5, 10);


INSERT INTO dept (id, dept_name, dept_no)
VALUES (1, '研发', 10),
       (2, '财务', 20),
       (3, '财务-1', 20),
       (4, '财务-2', 20);


INSERT INTO user_bill (id, create_date, money, user_id)
VALUES (1, '2022-10-11', 1001, 2),
       (2, '2022-10-12', 1201, 1),
       (3, '2022-10-12', 1202, 1);
