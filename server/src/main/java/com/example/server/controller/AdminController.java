package com.example.server.controller;

import com.example.server.common.R;
import com.example.server.entity.Admin;
import com.example.server.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 管理员登录控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 管理员登录
     *
     * @param params 包含username和password
     * @return 登录结果
     */
    @PostMapping("/login")
    public R login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        Admin admin = adminService.login(username, password);
        if (admin == null) {
            return R.error("用户名或密码错误");
        }
        return R.ok("登录成功", admin);
    }
}
