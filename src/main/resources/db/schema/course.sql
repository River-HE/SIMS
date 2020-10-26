create table if not exists course
(
    cour_id   varchar primary key not null comment '课程号',
    cour_name varchar             not null comment '课程名',
    credit    int comment '学分'
);