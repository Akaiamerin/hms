package com.hms.service.impl;
import com.hms.entity.CourseType;
import com.hms.mapper.CourseTypeMapper;
import com.hms.service.CourseTypeService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    @Resource
    private CourseTypeMapper courseTypeMapper;
    @Override
    public int insertCourseType(CourseType courseType) {
        return courseTypeMapper.insertCourseType(courseType);
    }
    @Override
    public int deleteCourseTypeById(Integer id) {
        return courseTypeMapper.deleteCourseTypeById(id);
    }
    @Override
    public int updateCourseTypeById(CourseType courseType) {
        return courseTypeMapper.updateCourseTypeById(courseType);
    }
    @Override
    public CourseType selectCourseTypeById(Integer id) {
        return courseTypeMapper.selectCourseTypeById(id);
    }
    @Override
    public CourseType selectCourseTypeByTitle(String title) {
        return courseTypeMapper.selectCourseTypeByTitle(title);
    }
    @Override
    public List<CourseType> selectAllCourseType() {
        return courseTypeMapper.selectAllCourseType();
    }
}