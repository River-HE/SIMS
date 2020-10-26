create table if not exists class
(
    class_id      varchar primary key not null comment '班级号',
    class_name    varchar             not null comment '班级名',
    establishment date                not null comment '成立日期'
);