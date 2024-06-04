package com.hms.service;
import com.hms.entity.HomeworkRecord;
import java.util.List;
public interface HomeworkRecordService {
    int insertHomeworkRecord(HomeworkRecord homeworkRecord);
    int deleteHomeworkRecordById(Integer id);
    int updateHomeworkRecordById(HomeworkRecord homeworkRecord);
    HomeworkRecord selectHomeworkRecordById(Integer id);
    HomeworkRecord selectHomeworkRecordByHomeworkId(Integer homeworkId);
    List<HomeworkRecord> selectAllHomeworkRecord();
}