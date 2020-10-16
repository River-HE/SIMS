package tech.yangxm.sims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.yangxm.sims.mapper.StudentMapper;
import tech.yangxm.sims.pojo.Student;
import tech.yangxm.sims.service.StudentService;

import java.util.List;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findById(String id) {
        return studentMapper.selectById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<Student> findAllByName(String name) {
        return studentMapper.selectList(new QueryWrapper<Student>()
                .like("stu_name", "%" + name + "%"));
    }

    @Override
    public List<Student> findAllByAges(int age1, int age2) {
        return studentMapper.selectList(new QueryWrapper<Student>()
                .apply("year(CURRENT_DATE()) - year(stu_birthday) >= {0}" +
                        "and year(CURRENT_DATE()) - year(stu_birthday) <= {1}", age1, age2));
//      SELECT * FROM STUDENT WHERE DATEDIFF(YEAR, STU_BIRTHDAY, CURRENT_DATE()) BETWEEN 18 AND 24;
//
//      SELECT DATEDIFF(YEAR, STU_BIRTHDAY, CURRENT_DATE()) AS AGE, STU_NAME FROM STUDENT;
    }

    @Override
    public int addStudent(Student stu) {
        try {
            return studentMapper.insert(stu);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    @Transactional
    public int addStudents(List<Student> students) {
        for (Student student : students) {
            studentMapper.insert(student);
        }
        return students.size();
    }

    @Override
    public int deleteStudentById(String id) {
        return studentMapper.deleteById(id);
    }

    @Override
    public int updateStudentById(Student stu) {
        return studentMapper.updateById(stu);
    }
}