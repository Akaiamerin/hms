package com.hms.service.impl;
import com.hms.entity.CourseSelection;
import com.hms.mapper.CourseSelectionMapper;
import com.hms.service.CourseSelectionService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class CourseSelectionServiceImpl implements CourseSelectionService {
    @Resource
    private CourseSelectionMapper courseSelectionMapper;
    @Override
    public int insertCourseSelection(CourseSelection courseSelection) {
        return courseSelectionMapper.insertCourseSelection(courseSelection);
    }
    @Override
    public int deleteCourseSelectionById(Integer id) {
        return courseSelectionMapper.deleteCourseSelectionById(id);
    }
    @Override
    public int updateCourseSelectionById(CourseSelection courseSelection) {
        return courseSelectionMapper.updateCourseSelectionById(courseSelection);
    }
    @Override
    public CourseSelection selectCourseSelectionById(Integer id) {
        return courseSelectionMapper.selectCourseSelectionById(id);
    }
    @Override
    public List<CourseSelection> selectCourseSelectionByCourseId(Integer courseId) {
        return courseSelectionMapper.selectCourseSelectionByCourseId(courseId);
    }
    @Override
    public List<CourseSelection> selectCourseSelectionByTeacherId(Integer teacherId) {
        return courseSelectionMapper.selectCourseSelectionByTeacherId(teacherId);
    }
    @Override
    public List<CourseSelection> selectCourseSelectionByStudentId(Integer studentId) {
        return courseSelectionMapper.selectCourseSelectionByStudentId(studentId);
    }
    @Override
    public List<CourseSelection> selectAllCourseSelection() {
        return courseSelectionMapper.selectAllCourseSelection();
    }
}