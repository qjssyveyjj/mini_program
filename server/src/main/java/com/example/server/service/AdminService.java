package com.example.server.service;

import com.example.server.entity.Admin;

/**
 * 管理员业务接口
 *
 * @author Java1234
 */
public interface AdminService {

    /**
     * 管理员登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 管理员信息
     */
    Admin login(String username, String password);
}
