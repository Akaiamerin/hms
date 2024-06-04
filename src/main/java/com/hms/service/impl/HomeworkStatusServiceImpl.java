package com.hms.service.impl;
import com.hms.entity.HomeworkStatus;
import com.hms.mapper.HomeworkStatusMapper;
import com.hms.service.HomeworkStatusService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class HomeworkStatusServiceImpl implements HomeworkStatusService {
    @Resource
    private HomeworkStatusMapper homeworkStatusMapper;
    @Override
    public int insertHomeworkStatus(HomeworkStatus homeworkStatus) {
        return homeworkStatusMapper.insertHomeworkStatus(homeworkStatus);
    }
    @Override
    public int deleteHomeworkStatusById(Integer id) {
        return homeworkStatusMapper.deleteHomeworkStatusById(id);
    }
    @Override
    public int updateHomeworkStatusById(HomeworkStatus homeworkStatus) {
        return homeworkStatusMapper.updateHomeworkStatusById(homeworkStatus);
    }
    @Override
    public HomeworkStatus selectHomeworkStatusById(Integer id) {
        return homeworkStatusMapper.selectHomeworkStatusById(id);
    }
    @Override
    public HomeworkStatus selectHomeworkStatusByTitle(String title) {
        return homeworkStatusMapper.selectHomeworkStatusByTitle(title);
    }
    @Override
    public List<HomeworkStatus> selectAllHomeworkStatus() {
        return homeworkStatusMapper.selectAllHomeworkStatus();
    }
}