package com.example.server.service.impl;

import com.example.server.entity.Admin;
import com.example.server.mapper.AdminMapper;
import com.example.server.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * 管理员业务实现类
 *
 * @author Java1234
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
     * 管理员登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 管理员信息，验证失败返回null
     */
    @Override
    public Admin login(String username, String password) {
        Admin admin = adminMapper.findByUsername(username);
        if (admin != null && password.equals(admin.getPassword())) {
            admin.setPassword(null);
            return admin;
        }
        return null;
    }
}
