package com.hms.mapper;
import com.hms.entity.CourseSelection;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface CourseSelectionMapper {
    @Insert("INSERT INTO course_selection (id, course_id, teacher_id, student_id, create_time) VALUES (NULL, #{courseId}, #{teacherId}, #{studentId}, #{createTime})")
    int insertCourseSelection(CourseSelection courseSelection);
    @Delete("DELETE FROM course_selection WHERE id = #{id}")
    int deleteCourseSelectionById(Integer id);
    @Update("UPDATE course_selection SET course_id = #{courseId}, teacher_id = #{teacherId}, student_id = #{studentId}, create_time = #{createTime} WHERE id = #{id}")
    int updateCourseSelectionById(CourseSelection courseSelection);
    @Select("SELECT * FROM course_selection WHERE id = #{id}")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "teacher_id", property = "teacherId"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "create_time", property = "createTime")
    })
    CourseSelection selectCourseSelectionById(Integer id);
    @Select("SELECT * FROM course_selection WHERE course_id = #{courseId}")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "teacher_id", property = "teacherId"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "create_time", property = "createTime")
    })
    List<CourseSelection> selectCourseSelectionByCourseId(Integer courseId);
    @Select("SELECT * FROM course_selection WHERE teacher_id = #{teacherId}")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "teacher_id", property = "teacherId"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "create_time", property = "createTime")
    })
    List<CourseSelection> selectCourseSelectionByTeacherId(Integer teacherId);
    @Select("SELECT * FROM course_selection WHERE student_id = #{studentId}")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "teacher_id", property = "teacherId"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "create_time", property = "createTime")
    })
    List<CourseSelection> selectCourseSelectionByStudentId(Integer studentId);
    @Select("SELECT * FROM course_selection")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "teacher_id", property = "teacherId"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "create_time", property = "createTime")
    })
    List<CourseSelection> selectAllCourseSelection();
}