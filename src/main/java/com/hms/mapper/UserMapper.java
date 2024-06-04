package com.hms.mapper;
import com.hms.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (id, username, password, role) VALUES (NULL, #{username}, #{password}, #{role})")
    int insertUser(User user);
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUserById(Integer id);
    @Update("UPDATE user SET username = #{username}, password = #{password}, role = #{role} WHERE id = #{id}")
    int updateUserById(User user);
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUserById(Integer id);
    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectUserByUsername(String username);
    @Select("SELECT * FROM user")
    List<User> selectAllUser();
}