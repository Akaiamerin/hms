package com.hms.service;
import com.hms.entity.HomeworkAttachment;
import java.util.List;
public interface HomeworkAttachmentService {
    int insertHomeworkAttachment(HomeworkAttachment homeworkAttachment);
    int deleteHomeworkAttachmentById(Integer id);
    int updateHomeworkAttachmentById(HomeworkAttachment homeworkAttachment);
    HomeworkAttachment selectHomeworkAttachmentById(Integer id);
    HomeworkAttachment selectHomeworkAttachmentByHomeworkId(Integer homeworkId);
    List<HomeworkAttachment> selectAllHomeworkAttachment();
}