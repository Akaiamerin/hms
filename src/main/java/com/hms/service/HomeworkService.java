package com.hms.service;
import com.hms.entity.Homework;
import java.util.List;
public interface HomeworkService {
    int insertHomework(Homework homework);
    int deleteHomeworkById(Integer id);
    int updateHomeworkById(Homework homework);
    Homework selectHomeworkById(Integer id);
    List<Homework> selectHomeworkByTeacherId(Integer teacherId);
    List<Homework> selectHomeworkByStudentId(Integer studentId);
    List<Homework> selectAllHomework();
}