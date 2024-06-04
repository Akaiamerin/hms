package com.hms.mapper;
import com.hms.entity.HomeworkRecord;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface HomeworkRecordMapper {
    @Insert("INSERT INTO homework_record (id, homework_id, score, teacher_comment) VALUES (NULL, #{homeworkId}, #{score}, #{teacherComment})")
    int insertHomeworkRecord(HomeworkRecord homeworkRecord);
    @Delete("DELETE FROM homework_record WHERE id = #{id}")
    int deleteHomeworkRecordById(Integer id);
    @Update("UPDATE homework_record SET homework_id = #{homeworkId}, score = #{score}, teacher_comment = #{teacherComment} WHERE id = #{id}")
    int updateHomeworkRecordById(HomeworkRecord homeworkRecord);
    @Select("SELECT * FROM homework_record WHERE id = #{id}")
    @Results({
            @Result(column = "homework_id", property = "homeworkId"),
            @Result(column = "teacher_comment", property = "teacherComment")
    })
    HomeworkRecord selectHomeworkRecordById(Integer id);
    @Select("SELECT * FROM homework_record WHERE homework_id = #{homeworkId}")
    @Results({
            @Result(column = "homework_id", property = "homeworkId"),
            @Result(column = "teacher_comment", property = "teacherComment")
    })
    HomeworkRecord selectHomeworkRecordByHomeworkId(Integer homeworkId);
    @Select("SELECT * FROM homework_record")
    @Results({
            @Result(column = "homework_id", property = "homeworkId"),
            @Result(column = "teacher_comment", property = "teacherComment")
    })
    List<HomeworkRecord> selectAllHomeworkRecord();
}