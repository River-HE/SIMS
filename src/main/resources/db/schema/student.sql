create table if not exists student
(
    stu_id       varchar primary key                      not null comment '学号',
    stu_name     varchar                                  not null comment '姓名',
    stu_gender   enum ('F','M') default 'M'               not null comment '性别',
    stu_birthday date                                     not null comment '生日',
    stu_class_id varchar                                  not null comment '班级',
    stu_add_time timestamp      default current_timestamp not null comment '学生注册时间'
);