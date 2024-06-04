package com.hms.mapper;
import com.hms.entity.Course;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface CourseMapper {
    @Insert("INSERT INTO course (id, course_type_id, no, title, teacher_id, detail) VALUES (NULL, #{courseTypeId}, #{no}, #{title}, #{teacherId}, #{detail})")
    int insertCourse(Course course);
    @Delete("DELETE FROM course WHERE id = #{id}")
    int deleteCourseById(Integer id);
    @Update("UPDATE course SET course_type_id = #{courseTypeId}, no = #{no}, title = #{title}, teacher_id = #{teacherId}, detail = #{detail} WHERE id = #{id}")
    int updateCourseById(Course course);
    @Select("SELECT * FROM course WHERE id = #{id}")
    @Results({
            @Result(column = "course_type_id", property = "courseTypeId"),
            @Result(column = "teacher_id", property = "teacherId")
    })
    Course selectCourseById(Integer id);
    @Select("SELECT * FROM course WHERE no = #{no}")
    @Results({
            @Result(column = "course_type_id", property = "courseTypeId"),
            @Result(column = "teacher_id", property = "teacherId")
    })
    Course selectCourseByNo(String no);
    @Select("SELECT * FROM course WHERE teacher_id = #{teacherId}")
    @Results({
            @Result(column = "course_type_id", property = "courseTypeId"),
            @Result(column = "teacher_id", property = "teacherId")
    })
    List<Course> selectCourseByTeacherId(Integer teacherId);
    @Select("SELECT * FROM course")
    @Results({
            @Result(column = "course_type_id", property = "courseTypeId"),
            @Result(column = "teacher_id", property = "teacherId")
    })
    List<Course> selectAllCourse();
}