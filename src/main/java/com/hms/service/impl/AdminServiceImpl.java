package com.hms.service.impl;
import com.hms.entity.Admin;
import com.hms.mapper.AdminMapper;
import com.hms.service.AdminService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public int insertAdmin(Admin admin) {
        return adminMapper.insertAdmin(admin);
    }
    @Override
    public int deleteAdminById(Integer id) {
        return adminMapper.deleteAdminById(id);
    }
    @Override
    public int updateAdminById(Admin admin) {
        return adminMapper.updateAdminById(admin);
    }
    @Override
    public Admin selectAdminById(Integer id) {
        return adminMapper.selectAdminById(id);
    }
    @Override
    public Admin selectAdminByUserId(Integer userId) {
        return adminMapper.selectAdminByUserId(userId);
    }
    @Override
    public Admin selectAdminByNo(String no) {
        return adminMapper.selectAdminByNo(no);
    }
    @Override
    public List<Admin> selectAllAdmin() {
        return adminMapper.selectAllAdmin();
    }
}