create table infra_codegen_table
(
    id            bigint auto_increment primary key comment '编号',
    table_name    varchar(200) default '' not null comment '表名称',
    table_comment varchar(500) default '' not null comment '表描述',
    remark        varchar(500)            null comment '备注',
    class_name    varchar(100) default '' not null comment '类名称',
    class_comment varchar(50)             not null comment '类描述',
    author        varchar(50)             not null comment '作者'
);