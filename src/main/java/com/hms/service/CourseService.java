package com.hms.service;
import com.hms.entity.Course;
import java.util.List;
public interface CourseService {
    int insertCourse(Course course);
    int deleteCourseById(Integer id);
    int updateCourseById(Course course);
    Course selectCourseById(Integer id);
    Course selectCourseByNo(String no);
    List<Course> selectCourseByTeacherId(Integer teacherId);
    List<Course> selectAllCourse();
}