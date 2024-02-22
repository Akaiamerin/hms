package com.hms.service;
import com.hms.entity.pojo.CourseSelection;
import java.util.List;
public interface CourseSelectionService {
    int insertCourseSelection(CourseSelection courseSelection);
    int deleteCourseSelectionById(Integer id);
    int updateCourseSelectionById(CourseSelection courseSelection);
    CourseSelection selectCourseSelectionById(Integer id);
    List<CourseSelection> selectCourseSelectionByCourseId(Integer courseId);
    List<CourseSelection> selectCourseSelectionByTeacherId(Integer teacherId);
    List<CourseSelection> selectCourseSelectionByStudentId(Integer studentId);
    List<CourseSelection> selectAllCourseSelection();
}