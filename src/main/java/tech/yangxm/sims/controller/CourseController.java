package tech.yangxm.sims.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yangxm.sims.exception.StudentParseException;
import tech.yangxm.sims.pojo.Course;
import tech.yangxm.sims.service.CourseService;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService cc;

    @GetMapping("/courses")
    public List<Course> findAll() {
        return cc.findAll();
    }

    @GetMapping("/course")
    public Course findById(@RequestParam("id") String id) {
        return cc.findById(id);
    }

    @GetMapping("/course/name")
    public Course findByName(@RequestParam("name") String name) {
        return cc.findByName(name);
    }

    @PostMapping("/course")
    public int addCourse(@RequestBody Course course) {
        return cc.addCourse(course);
    }

    @DeleteMapping("/course/id/{id}")
    public int deleteCourseById(@PathVariable String id) {
        return cc.deleteCourseById(id);
    }

    @PutMapping("/course")
    public int updateCourseById(@RequestBody Course course) {
        return cc.updateCourseById(course);
    }
}
