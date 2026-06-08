package com.example.server.service;

import com.example.server.common.PageResult;
import com.example.server.entity.User;

/**
 * 用户业务接口
 *
 * @author Java1234
 */
public interface UserService {

    User login(String username, String password);

    User register(User user);

    User findById(Long id);

    PageResult<User> findPage(String keyword, int page, int size);

    void save(User user);

    void update(User user);

    void delete(Long id);
}
