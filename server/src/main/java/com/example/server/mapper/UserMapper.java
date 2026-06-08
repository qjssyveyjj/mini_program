package com.example.server.mapper;

import com.example.server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户数据访问接口
 *
 * @author Java1234
 */
@Mapper
public interface UserMapper {

    User findById(@Param("id") Long id);

    User findByUsername(@Param("username") String username);

    List<User> findPage(@Param("keyword") String keyword, @Param("offset") int offset, @Param("limit") int limit);

    long count(@Param("keyword") String keyword);

    int insert(User user);

    int update(User user);

    int deleteById(@Param("id") Long id);

    long countAll();

    List<Map<String, Object>> countByGender();
}
