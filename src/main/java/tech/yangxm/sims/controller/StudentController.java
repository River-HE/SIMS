package tech.yangxm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yangxm.sims.exception.StudentParseException;
import tech.yangxm.sims.model.StudentModel;
import tech.yangxm.sims.pojo.Student;
import tech.yangxm.sims.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public Student findById(@RequestParam("id") String id) {
        return studentService.findById(id);
    }

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/students/name/{name}")
    public List<Student> findAllByName(@PathVariable("name") String name) {
        return studentService.findAllByName(name);
    }

    @GetMapping("/students/age1/{age1}/age2/{age2}")
    public List<Student> findAllByAges(@PathVariable("age1") int age1, @PathVariable("age2") int age2) {
        return studentService.findAllByAges(age1, age2);
    }

    @PostMapping("/student")
    public int addStudent(@RequestBody StudentModel sm) {
        try {
            int res = studentService.addStudent(sm.toStudent());
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @PostMapping("/students")
    public int addStudent(@RequestBody List<StudentModel> sms) {
        List<Student> students = new ArrayList<>();
        for (StudentModel sm : sms) {
            try {
                students.add(sm.toStudent());
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        return studentService.addStudents(students);
    }

    @DeleteMapping("/student/id/{id}")
    public int deleteStudentById(@PathVariable("id") String id) {
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/student")
    public int updateStudentById(@RequestBody StudentModel sm) {

        if (!"".equals(sm.getName())) {
            return -1;
        }else{
            //
            sm.setName(null);
        }

        try {
            return studentService.updateStudentById(sm.toStudent());
        } catch (StudentParseException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
