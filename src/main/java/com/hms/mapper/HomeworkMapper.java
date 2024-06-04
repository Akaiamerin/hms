package com.hms.mapper;
import com.hms.entity.Homework;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface HomeworkMapper {
    @Insert("INSERT INTO homework (id, course_id, teacher_id, student_id, homework_status_id, content, create_time, update_time, deadline) VALUES (NULL, #{courseId}, #{teacherId}, #{studentId}, #{homeworkStatusId}, #{content}, #{createTime}, #{updateTime}, #{deadline})")
    int insertHomework(Homework homework);
    @Delete("DELETE FROM homework WHERE id = #{id}")
    int deleteHomeworkById(Integer id);
    @Update("UPDATE homework SET course_id = #{courseId}, teacher_id = #{teacherId}, student_id = #{studentId}, homework_status_id = #{homeworkStatusId}, content = #{content}, create_time = #{createTime}, update_time = #{updateTime}, deadline = #{deadline} WHERE id = #{id}")
    int updateHomeworkById(Homework homework);
    @Select("SELECT * FROM homework WHERE id = #{id}")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "teacher_id", property = "teacherId"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "homework_status_id", property = "homeworkStatusId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    Homework selectHomeworkById(Integer id);
    @Select("SELECT * FROM homework WHERE teacher_id = #{teacherId}")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "teacher_id", property = "teacherId"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "homework_status_id", property = "homeworkStatusId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Homework> selectHomeworkByTeacherId(Integer teacherId);
    @Select("SELECT * FROM homework WHERE student_id = #{studentId}")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "teacher_id", property = "teacherId"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "homework_status_id", property = "homeworkStatusId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Homework> selectHomeworkByStudentId(Integer studentId);
    @Select("SELECT * FROM homework")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "teacher_id", property = "teacherId"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "homework_status_id", property = "homeworkStatusId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Homework> selectAllHomework();
}