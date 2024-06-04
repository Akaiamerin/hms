package com.hms.service.impl;
import com.hms.entity.User;
import com.hms.mapper.UserMapper;
import com.hms.service.UserService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }
    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }
    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }
    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }
}