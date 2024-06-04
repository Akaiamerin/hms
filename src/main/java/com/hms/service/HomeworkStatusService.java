package com.hms.service;
import com.hms.entity.HomeworkStatus;
import java.util.List;
public interface HomeworkStatusService {
    int insertHomeworkStatus(HomeworkStatus homeworkStatus);
    int deleteHomeworkStatusById(Integer id);
    int updateHomeworkStatusById(HomeworkStatus homeworkStatus);
    HomeworkStatus selectHomeworkStatusById(Integer id);
    HomeworkStatus selectHomeworkStatusByTitle(String title);
    List<HomeworkStatus> selectAllHomeworkStatus();
}