package com.example.server.mapper;

import com.example.server.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员数据访问接口
 *
 * @author Java1234
 */
@Mapper
public interface AdminMapper {

    /**
     * 根据用户名查询管理员
     *
     * @param username 用户名
     * @return 管理员信息
     */
    Admin findByUsername(@Param("username") String username);
}
