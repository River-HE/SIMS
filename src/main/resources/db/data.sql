insert into student(stu_id, stu_name, stu_gender, stu_birthday, stu_class_id)
values ('stu001', 'A', 'M', '1997-01-26', '01');
insert into student(stu_id, stu_name, stu_gender, stu_birthday, stu_class_id)
values ('stu002', 'AB', 'F', '1998-01-26', '01');
insert into student(stu_id, stu_name, stu_gender, stu_birthday, stu_class_id)
values ('stu003', 'ABC', 'F', '1999-01-26', '01');
insert into student(stu_id, stu_name, stu_gender, stu_birthday, stu_class_id)
values ('stu004', 'AA', 'M', '1995-01-26', '02');

insert into course(cour_id, cour_name, credit)
values ('c1', 'Chinese', 3);
insert into course(cour_id, cour_name, credit)
values ('c2', 'English', 4);
insert into course(cour_id, cour_name, credit)
values ('c3', 'Math', 5);

insert into class(class_id, class_name, establishment)
values ('01', 'CST', '2015-09-01');
insert into class(class_id, class_name, establishment)
values ('02', 'CST', '2015-09-01');
-- insert into class(class_id,class_name,establishment) values ('02','网工',"2016-09-01");
-- insert into class(class_id,class_name,establishment) values ('03','软工',"2017-09-01");

insert into grade(stu_id, cour_id, score)
values ('stu001', 'c1', 90);
insert into grade(stu_id, cour_id, score)
values ('stu001', 'c2', 91);
insert into grade(stu_id, cour_id, score)
values ('stu001', 'c3', 92);

insert into grade(stu_id, cour_id, score)
values ('stu002', 'c1', 90);
insert into grade(stu_id, cour_id, score)
values ('stu002', 'c2', 91);
insert into grade(stu_id, cour_id, score)
values ('stu002', 'c3', 91);

insert into grade(stu_id, cour_id, score)
values ('stu003', 'c1', 92);
insert into grade(stu_id, cour_id, score)
values ('stu003', 'c2', 92);
insert into grade(stu_id, cour_id, score)
values ('stu003', 'c3', 91);

insert into grade(stu_id, cour_id, score)
values ('stu004', 'c1', 90);
insert into grade(stu_id, cour_id, score)
values ('stu004', 'c2', 95);
insert into grade(stu_id, cour_id, score)
values ('stu004', 'c3', 96);