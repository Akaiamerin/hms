package com.hms.service.impl;
import com.hms.entity.HomeworkRecord;
import com.hms.mapper.HomeworkRecordMapper;
import com.hms.service.HomeworkRecordService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class HomeworkRecordServiceImpl implements HomeworkRecordService {
    @Resource
    private HomeworkRecordMapper homeworkRecordMapper;
    @Override
    public int insertHomeworkRecord(HomeworkRecord homeworkRecord) {
        return homeworkRecordMapper.insertHomeworkRecord(homeworkRecord);
    }
    @Override
    public int deleteHomeworkRecordById(Integer id) {
        return homeworkRecordMapper.deleteHomeworkRecordById(id);
    }
    @Override
    public int updateHomeworkRecordById(HomeworkRecord homeworkRecord) {
        return homeworkRecordMapper.updateHomeworkRecordById(homeworkRecord);
    }
    @Override
    public HomeworkRecord selectHomeworkRecordById(Integer id) {
        return homeworkRecordMapper.selectHomeworkRecordById(id);
    }
    @Override
    public HomeworkRecord selectHomeworkRecordByHomeworkId(Integer homeworkId) {
        return homeworkRecordMapper.selectHomeworkRecordByHomeworkId(homeworkId);
    }
    @Override
    public List<HomeworkRecord> selectAllHomeworkRecord() {
        return homeworkRecordMapper.selectAllHomeworkRecord();
    }
}