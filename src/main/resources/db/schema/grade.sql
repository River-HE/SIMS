create table if not exists grade
(
    stu_id  varchar not null comment '学号',
    cour_id varchar not null comment '课程号',
    score   number  not null comment '分数',
    primary key (stu_id, cour_id)
);