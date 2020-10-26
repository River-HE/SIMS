package tech.yangxm.sims.service;

import tech.yangxm.sims.pojo.Course;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(String id);

    Course findByName(String name);

    int addCourse(Course course);

    int deleteCourseById(String id);

    int updateCourseById(Course course);
}
