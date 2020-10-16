package tech.yangxm.sims.service;

import tech.yangxm.sims.pojo.Student;

import java.util.List;

public interface StudentService {

    Student findById(String id);
    List<Student> findAll();
    List<Student> findAllByName(String name);
    List<Student> findAllByAges(int age1,int age2);

    int addStudent(Student stu);

    int addStudents(List<Student> students);

    int deleteStudentById(String id);

    int updateStudentById(Student stu);
}
