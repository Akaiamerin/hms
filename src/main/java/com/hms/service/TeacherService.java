package com.hms.service;
import com.hms.entity.Teacher;
import java.util.List;
public interface TeacherService {
    int insertTeacher(Teacher teacher);
    int deleteTeacherById(Integer id);
    int updateTeacherById(Teacher teacher);
    Teacher selectTeacherById(Integer id);
    Teacher selectTeacherByUserId(Integer userId);
    Teacher selectTeacherByNo(String no);
    List<Teacher> selectAllTeacher();
}