package com.hms.service.impl;
import com.hms.entity.Course;
import com.hms.mapper.CourseMapper;
import com.hms.service.CourseService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Override
    public int insertCourse(Course course) {
        return courseMapper.insertCourse(course);
    }
    @Override
    public int deleteCourseById(Integer id) {
        return courseMapper.deleteCourseById(id);
    }
    @Override
    public int updateCourseById(Course course) {
        return courseMapper.updateCourseById(course);
    }
    @Override
    public Course selectCourseById(Integer id) {
        return courseMapper.selectCourseById(id);
    }
    @Override
    public Course selectCourseByNo(String no) {
        return courseMapper.selectCourseByNo(no);
    }
    @Override
    public List<Course> selectCourseByTeacherId(Integer teacherId) {
        return courseMapper.selectCourseByTeacherId(teacherId);
    }
    @Override
    public List<Course> selectAllCourse() {
        return courseMapper.selectAllCourse();
    }
}