drop table if exists product_detail;
drop table if exists product;
drop table if exists product_category;

create table product_category
(
    id   int primary key auto_increment comment '主键',
    name varchar(255) not null comment '名称'
);

create table product
(
    id          int primary key auto_increment comment '主键',
    name        varchar(255) not null comment '名称',
    category_id int          not null comment '产品分类id',
    constraint product_product_category_id_fk foreign key (category_id) references product_category (id)
);

create table product_detail
(
    id         int primary key auto_increment comment '主键',
    product_id int comment '产品id' references product (id),
    name       varchar(255) not null comment '名称',
    content    longtext     not null comment '内容'
);


