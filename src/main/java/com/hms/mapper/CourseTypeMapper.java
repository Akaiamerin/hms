package com.hms.mapper;
import com.hms.entity.CourseType;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface CourseTypeMapper {
    @Insert("INSERT INTO course_type (id, title) VALUES (NULL, #{title})")
    int insertCourseType(CourseType courseType);
    @Delete("DELETE FROM course_type WHERE id = #{id}")
    int deleteCourseTypeById(Integer id);
    @Update("UPDATE course_type SET title = #{title} WHERE id = #{id}")
    int updateCourseTypeById(CourseType courseType);
    @Select("SELECT * FROM course_type WHERE id = #{id}")
    CourseType selectCourseTypeById(Integer id);
    @Select("SELECT * FROM course_type WHERE title = #{title}")
    CourseType selectCourseTypeByTitle(String title);
    @Select("SELECT * FROM course_type")
    List<CourseType> selectAllCourseType();
}