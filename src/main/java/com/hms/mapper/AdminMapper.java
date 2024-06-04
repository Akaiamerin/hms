package com.hms.mapper;
import com.hms.entity.Admin;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface AdminMapper {
    @Insert("INSERT INTO admin (id, user_id, no, name) VALUES (NULL, #{userId}, #{no}, #{name})")
    int insertAdmin(Admin admin);
    @Delete("DELETE FROM admin WHERE id = #{id}")
    int deleteAdminById(Integer id);
    @Update("UPDATE admin SET user_id = #{userId}, no = #{no}, name = #{name} WHERE id = #{id}")
    int updateAdminById(Admin admin);
    @Select("SELECT * FROM admin WHERE id = #{id}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Admin selectAdminById(Integer id);
    @Select("SELECT * FROM admin WHERE user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Admin selectAdminByUserId(Integer userId);
    @Select("SELECT * FROM admin WHERE no = #{no}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Admin selectAdminByNo(String no);
    @Select("SELECT * FROM admin")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    List<Admin> selectAllAdmin();
}