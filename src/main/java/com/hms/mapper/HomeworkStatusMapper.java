package com.hms.mapper;
import com.hms.entity.HomeworkStatus;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface HomeworkStatusMapper {
    @Insert("INSERT INTO homework_status (id, title) VALUES (NULL, #{title})")
    int insertHomeworkStatus(HomeworkStatus homeworkStatus);
    @Delete("DELETE FROM homework_status WHERE id = #{id}")
    int deleteHomeworkStatusById(Integer id);
    @Update("UPDATE homework_status SET title = #{title} WHERE id = #{id}")
    int updateHomeworkStatusById(HomeworkStatus homeworkStatus);
    @Select("SELECT * FROM homework_status WHERE id = #{id}")
    HomeworkStatus selectHomeworkStatusById(Integer id);
    @Select("SELECT * FROM homework_status WHERE title = #{title}")
    HomeworkStatus selectHomeworkStatusByTitle(String title);
    @Select("SELECT * FROM homework_status")
    List<HomeworkStatus> selectAllHomeworkStatus();
}