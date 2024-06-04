package com.hms.service;
import com.hms.entity.Admin;
import java.util.List;
public interface AdminService {
    int insertAdmin(Admin admin);
    int deleteAdminById(Integer id);
    int updateAdminById(Admin admin);
    Admin selectAdminById(Integer id);
    Admin selectAdminByUserId(Integer userId);
    Admin selectAdminByNo(String no);
    List<Admin> selectAllAdmin();
}