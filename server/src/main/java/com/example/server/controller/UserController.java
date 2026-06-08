package com.example.server.controller;

import com.example.server.common.PageResult;
import com.example.server.common.R;
import com.example.server.entity.User;
import com.example.server.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户管理控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 小程序用户登录
     */
    @PostMapping("/login")
    public R login(@RequestBody Map<String, String> params) {
        User user = userService.login(params.get("username"), params.get("password"));
        if (user == null) return R.error("用户名或密码错误");
        return R.ok("登录成功", user);
    }

    /**
     * 小程序用户注册
     */
    @PostMapping("/register")
    public R register(@RequestBody User user) {
        try {
            return R.ok("注册成功", userService.register(user));
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 分页查询用户列表
     */
    @GetMapping("/page")
    public R page(@RequestParam(defaultValue = "") String keyword,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "10") int size) {
        PageResult<User> result = userService.findPage(keyword, page, size);
        return R.ok(result);
    }

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    public R findById(@PathVariable Long id) {
        return R.ok(userService.findById(id));
    }

    /**
     * 新增用户
     */
    @PostMapping
    public R save(@RequestBody User user) {
        try {
            userService.save(user);
            return R.ok("添加成功");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 更新用户
     */
    @PutMapping
    public R update(@RequestBody User user) {
        userService.update(user);
        return R.ok("更新成功");
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        userService.delete(id);
        return R.ok("删除成功");
    }
}
