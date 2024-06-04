package com.hms.mapper;
import com.hms.entity.Teacher;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface TeacherMapper {
    @Insert("INSERT INTO teacher (id, user_id, no, name) VALUES (NULL, #{userId}, #{no}, #{name})")
    int insertTeacher(Teacher teacher);
    @Delete("DELETE FROM teacher WHERE id = #{id}")
    int deleteTeacherById(Integer id);
    @Update("UPDATE teacher SET user_id = #{userId}, no = #{no}, name = #{name} WHERE id = #{id}")
    int updateTeacherById(Teacher teacher);
    @Select("SELECT * FROM teacher WHERE id = #{id}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Teacher selectTeacherById(Integer id);
    @Select("SELECT * FROM teacher WHERE user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Teacher selectTeacherByUserId(Integer userId);
    @Select("SELECT * FROM teacher WHERE no = #{no}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Teacher selectTeacherByNo(String no);
    @Select("SELECT * FROM teacher")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    List<Teacher> selectAllTeacher();
}