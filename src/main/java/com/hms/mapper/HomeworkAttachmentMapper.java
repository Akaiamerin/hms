package com.hms.mapper;
import com.hms.entity.HomeworkAttachment;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface HomeworkAttachmentMapper {
    @Insert("INSERT INTO homework_attachment (id, homework_id, title, attachment, create_time) VALUES (NULL, #{homeworkId}, #{title}, #{attachment}, #{createTime})")
    int insertHomeworkAttachment(HomeworkAttachment homeworkAttachment);
    @Delete("DELETE FROM homework_attachment WHERE id = #{id}")
    int deleteHomeworkAttachmentById(Integer id);
    @Update("UPDATE homework_attachment SET homework_id = #{homeworkId}, title = #{title}, attachment = #{attachment}, create_time = #{createTime} WHERE id = #{id}")
    int updateHomeworkAttachmentById(HomeworkAttachment homeworkAttachment);
    @Select("SELECT * FROM homework_attachment WHERE id = #{id}")
    @Results({
            @Result(column = "homework_id", property = "homeworkId"),
            @Result(column = "create_time", property = "createTime")
    })
    HomeworkAttachment selectHomeworkAttachmentById(Integer id);
    @Select("SELECT * FROM homework_attachment WHERE homework_id = #{homeworkId}")
    @Results({
            @Result(column = "homework_id", property = "homeworkId"),
            @Result(column = "create_time", property = "createTime")
    })
    HomeworkAttachment selectHomeworkAttachmentByHomeworkId(Integer homeworkId);
    @Select("SELECT * FROM admin")
    @Results({
            @Result(column = "homework_id", property = "homeworkId"),
            @Result(column = "create_time", property = "createTime")
    })
    List<HomeworkAttachment> selectAllHomeworkAttachment();
}