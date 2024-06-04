package com.hms.service.impl;
import com.hms.entity.Homework;
import com.hms.mapper.HomeworkMapper;
import com.hms.service.HomeworkService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Resource
    private HomeworkMapper homeworkMapper;
    @Override
    public int insertHomework(Homework homework) {
        return homeworkMapper.insertHomework(homework);
    }
    @Override
    public int deleteHomeworkById(Integer id) {
        return homeworkMapper.deleteHomeworkById(id);
    }
    @Override
    public int updateHomeworkById(Homework homework) {
        return homeworkMapper.updateHomeworkById(homework);
    }
    @Override
    public Homework selectHomeworkById(Integer id) {
        return homeworkMapper.selectHomeworkById(id);
    }
    @Override
    public List<Homework> selectHomeworkByTeacherId(Integer teacherId) {
        return homeworkMapper.selectHomeworkByTeacherId(teacherId);
    }
    @Override
    public List<Homework> selectHomeworkByStudentId(Integer studentId) {
        return homeworkMapper.selectHomeworkByStudentId(studentId);
    }
    @Override
    public List<Homework> selectAllHomework() {
        return homeworkMapper.selectAllHomework();
    }
}