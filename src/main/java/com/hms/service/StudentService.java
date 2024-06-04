package com.hms.service;
import com.hms.entity.Student;
import java.util.List;
public interface StudentService {
    int insertStudent(Student student);
    int deleteStudentById(Integer id);
    int updateStudentById(Student student);
    Student selectStudentById(Integer id);
    Student selectStudentByUserId(Integer userId);
    Student selectStudentByNo(String no);
    List<Student> selectAllStudent();
}