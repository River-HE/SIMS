create table if not exists user
(
    username        varchar(255) primary key,
    password        varchar(20)                       not null,
    is_admin        int                               not null default 0,
    status          enum ('NORMAL','FREEZE','DELETE') not null default 'NORMAL',
    add_time        timestamp                         not null default current_timestamp,
    pwd_error_times int                               not null default 0,
    last_change_pwd timestamp
);