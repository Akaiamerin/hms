package com.hms.service.impl;
import com.hms.entity.HomeworkAttachment;
import com.hms.mapper.HomeworkAttachmentMapper;
import com.hms.service.HomeworkAttachmentService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class HomeworkAttachmentServiceImpl implements HomeworkAttachmentService {
    @Resource
    HomeworkAttachmentMapper homeworkAttachmentMapper;
    @Override
    public int insertHomeworkAttachment(HomeworkAttachment homeworkAttachment) {
        return homeworkAttachmentMapper.insertHomeworkAttachment(homeworkAttachment);
    }
    @Override
    public int deleteHomeworkAttachmentById(Integer id) {
        return homeworkAttachmentMapper.deleteHomeworkAttachmentById(id);
    }
    @Override
    public int updateHomeworkAttachmentById(HomeworkAttachment homeworkAttachment) {
        return homeworkAttachmentMapper.updateHomeworkAttachmentById(homeworkAttachment);
    }
    @Override
    public HomeworkAttachment selectHomeworkAttachmentById(Integer id) {
        return homeworkAttachmentMapper.selectHomeworkAttachmentById(id);
    }
    @Override
    public HomeworkAttachment selectHomeworkAttachmentByHomeworkId(Integer homeworkId) {
        return homeworkAttachmentMapper.selectHomeworkAttachmentByHomeworkId(homeworkId);
    }
    @Override
    public List<HomeworkAttachment> selectAllHomeworkAttachment() {
        return homeworkAttachmentMapper.selectAllHomeworkAttachment();
    }
}