package com.hms.service.impl;
import com.hms.entity.Student;
import com.hms.mapper.StudentMapper;
import com.hms.service.StudentService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public int insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }
    @Override
    public int deleteStudentById(Integer id) {
        return studentMapper.deleteStudentById(id);
    }
    @Override
    public int updateStudentById(Student student) {
        return studentMapper.updateStudentById(student);
    }
    @Override
    public Student selectStudentById(Integer id) {
        return studentMapper.selectStudentById(id);
    }
    @Override
    public Student selectStudentByUserId(Integer userId) {
        return studentMapper.selectStudentByUserId(userId);
    }
    @Override
    public Student selectStudentByNo(String no) {
        return studentMapper.selectStudentByNo(no);
    }
    @Override
    public List<Student> selectAllStudent() {
        return studentMapper.selectAllStudent();
    }
}