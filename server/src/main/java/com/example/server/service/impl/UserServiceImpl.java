package com.example.server.service.impl;

import com.example.server.common.PageResult;
import com.example.server.entity.User;
import com.example.server.mapper.UserMapper;
import com.example.server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务实现类
 *
 * @author Java1234
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && password.equals(user.getPassword()) && user.getStatus() == 1) {
            user.setPassword(null);
            return user;
        }
        return null;
    }

    @Override
    public User register(User user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        user.setPassword("123456");
        user.setStatus(1);
        userMapper.insert(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userMapper.findById(id);
        if (user != null) user.setPassword(null);
        return user;
    }

    @Override
    public PageResult<User> findPage(String keyword, int page, int size) {
        int offset = (page - 1) * size;
        List<User> records = userMapper.findPage(keyword, offset, size);
        records.forEach(u -> u.setPassword(null));
        long total = userMapper.count(keyword);
        return new PageResult<>(total, records);
    }

    @Override
    public void save(User user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword("123456");
        }
        if (user.getStatus() == null) user.setStatus(1);
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }
}
