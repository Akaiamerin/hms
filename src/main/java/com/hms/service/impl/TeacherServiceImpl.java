package com.hms.service.impl;
import com.hms.entity.Teacher;
import com.hms.mapper.TeacherMapper;
import com.hms.service.TeacherService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public int insertTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }
    @Override
    public int deleteTeacherById(Integer id) {
        return teacherMapper.deleteTeacherById(id);
    }
    @Override
    public int updateTeacherById(Teacher teacher) {
        return teacherMapper.updateTeacherById(teacher);
    }
    @Override
    public Teacher selectTeacherById(Integer id) {
        return teacherMapper.selectTeacherById(id);
    }
    @Override
    public Teacher selectTeacherByUserId(Integer userId) {
        return teacherMapper.selectTeacherByUserId(userId);
    }
    @Override
    public Teacher selectTeacherByNo(String no) {
        return teacherMapper.selectTeacherByNo(no);
    }
    @Override
    public List<Teacher> selectAllTeacher() {
        return teacherMapper.selectAllTeacher();
    }
}