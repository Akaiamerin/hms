package com.hms.service.impl;
import com.hms.entity.Admin;
import com.hms.entity.Student;
import com.hms.entity.Teacher;
import com.hms.entity.User;
import com.hms.mapper.AdminMapper;
import com.hms.mapper.StudentMapper;
import com.hms.mapper.TeacherMapper;
import com.hms.mapper.UserMapper;
import jakarta.annotation.Resource;
import java.util.Objects;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private StudentMapper studentMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int splitIndex = username.indexOf('&');
        String role = username.substring(0, splitIndex);
        username = username.substring(splitIndex + 1, username.length());
        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            if (Objects.equals(role, "admin") == true) {
                Admin admin = adminMapper.selectAdminByNo(username);
                if (admin == null) {
                    throw new BadCredentialsException("用户名或编号或密码错误");
                }
                user = userMapper.selectUserById(admin.getUserId());
            }
            else if (Objects.equals(role, "teacher") == true) {
                Teacher teacher = teacherMapper.selectTeacherByNo(username);
                if (teacher == null) {
                    throw new BadCredentialsException("用户名或编号或密码错误");
                }
                user = userMapper.selectUserById(teacher.getUserId());
            }
            else if (Objects.equals(role, "student") == true) {
                Student student = studentMapper.selectStudentByNo(username);
                if (student == null) {
                    throw new BadCredentialsException("用户名或编号或密码错误");
                }
                user = userMapper.selectUserById(student.getUserId());
            }
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}