package com.hms.service;
import com.hms.entity.CourseType;
import java.util.List;
public interface CourseTypeService {
    int insertCourseType(CourseType courseType);
    int deleteCourseTypeById(Integer id);
    int updateCourseTypeById(CourseType courseType);
    CourseType selectCourseTypeById(Integer id);
    CourseType selectCourseTypeByTitle(String title);
    List<CourseType> selectAllCourseType();
}