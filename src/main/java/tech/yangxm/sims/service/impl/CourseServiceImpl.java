package tech.yangxm.sims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yangxm.sims.mapper.CourseMapper;
import tech.yangxm.sims.pojo.Course;
import tech.yangxm.sims.service.CourseService;

import java.util.List;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Course findById(String id) {
        return courseMapper.selectById(id);
    }

    @Override
    public Course findByName(String name) {
        return courseMapper.selectOne(new QueryWrapper<Course>().like("cour_name", name));
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    public int deleteCourseById(String id) {
        return courseMapper.deleteById(id);
    }

    @Override
    public int updateCourseById(Course course) {
        return courseMapper.updateById(course);
    }
}
