-- 把表结构给GPT，可以让它帮忙写SQL，以此来检验表设计是否合理
create table users
(
    id       int primary key auto_increment comment '主键',
    username varchar(255) not null comment '用户名'
);

create table relation_user
(
    id      int primary key auto_increment comment '主键',
    user_id int          not null comment '用户id',
    account varchar(255) not null comment '账号',
    pwd     varchar(255) not null comment '密码',
    type    int          not null comment '类型 1:微信 2:qq 3:微博'
);

insert into users (id, username)
values (1, '小明');
insert into users (id, username)
values (2, '小红');
insert into relation_user (id, user_id, account, pwd, type)
values (1, 1, 'wechat', '123', 1),
       (2, 1, 'qq', '123', 2),
       (3, 1, 'weibo', '123', 3)
;

insert into relation_user (id, user_id, account, pwd, type)
values (4, 1, 'weibo2', '123', 3);
insert into relation_user (id, user_id, account, pwd, type)
values (5, 2, 'weibo33', '123', 3);

#怎么实现查询用户的所有账号，作为一行记录
SELECT u.id,
       u.username,
       MAX(CASE WHEN r.type = 1 THEN r.account END) AS wechat_account,
       MAX(CASE WHEN r.type = 2 THEN r.account END) AS qq_account,
       MAX(CASE WHEN r.type = 3 THEN r.account END) AS weibo_account
FROM users u
         LEFT JOIN relation_user r ON u.id = r.user_id
where r.type in (1, 2, 3)
GROUP BY u.id,
         u.username;

SELECT u.id, u.username
FROM users u
         INNER JOIN relation_user r1 ON u.id = r1.user_id AND r1.type = 1 -- 微信账号
         INNER JOIN relation_user r2 ON u.id = r2.user_id AND r2.type = 2 -- QQ账号
         INNER JOIN relation_user r3 ON u.id = r3.user_id AND r3.type = 3; -- 微博账号

SELECT u.id,
       u.username,
       (SELECT r.account FROM relation_user r WHERE r.user_id = u.id AND r.type = 1) AS wechat_account,
       (SELECT r.account FROM relation_user r WHERE r.user_id = u.id AND r.type = 2) AS qq_account,
       (SELECT r.account FROM relation_user r WHERE r.user_id = u.id AND r.type = 3) AS weibo_account
FROM users u;

SELECT u.id,
       u.username
FROM users u
         LEFT JOIN relation_user r ON u.id = r.user_id
GROUP BY u.id,
         u.username;

SELECT u.id,
       u.username,
       (SELECT r.account FROM relation_user r WHERE r.user_id = u.id AND r.type = 1) AS wechat_account,
       (SELECT r.account FROM relation_user r WHERE r.user_id = u.id AND r.type = 2) AS qq_account,
       (SELECT r.account FROM relation_user r WHERE r.user_id = u.id AND r.type = 3) AS weibo_account
FROM users u
WHERE EXISTS (SELECT 1
              FROM relation_user ru
              WHERE ru.user_id = u.id
                AND ru.type IN (1, 2, 3)
              GROUP BY ru.user_id
              HAVING COUNT(DISTINCT ru.type) = 3);

SELECT u.id,
       u.username,
       r.id                    AS account_id,
       GROUP_CONCAT(r.account) AS qq_accounts
FROM users u
         INNER JOIN relation_user r ON u.id = r.user_id AND r.type = 2 -- QQ账号
GROUP BY u.id, u.username, r.id
HAVING COUNT(DISTINCT r.account) > 1;

select *
from users u
where exists(select 1
             from relation_user ru
             where ru.user_id = u.id
               and ru.type in (1, 2));

select *
from users u
where exists(select 1
             from relation_user ru
             where ru.user_id = u.id
               and ru.type in (1, 2));

SELECT u.id,
       u.username
FROM users u
WHERE EXISTS (SELECT 1
              FROM relation_user ru
              WHERE ru.user_id = u.id
                AND ru.type IN (2)
              GROUP BY ru.user_id
              HAVING COUNT(DISTINCT ru.type) = 1);



