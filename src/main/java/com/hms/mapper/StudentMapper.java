package com.hms.mapper;
import com.hms.entity.Student;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface StudentMapper {
    @Insert("INSERT INTO student (id, user_id, no, name) VALUES (NULL, #{userId}, #{no}, #{name})")
    int insertStudent(Student student);
    @Delete("DELETE FROM student WHERE id = #{id}")
    int deleteStudentById(Integer id);
    @Update("UPDATE student SET user_id = #{userId}, no = #{no}, name = #{name} WHERE id = #{id}")
    int updateStudentById(Student student);
    @Select("SELECT * FROM student WHERE id = #{id}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Student selectStudentById(Integer id);
    @Select("SELECT * FROM student WHERE user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Student selectStudentByUserId(Integer userId);
    @Select("SELECT * FROM student WHERE no = #{no}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Student selectStudentByNo(String no);
    @Select("SELECT * FROM student")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    List<Student> selectAllStudent();
}