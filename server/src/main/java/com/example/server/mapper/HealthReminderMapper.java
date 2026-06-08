package com.example.server.mapper;

import com.example.server.entity.HealthReminder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 健康提醒数据访问接口
 *
 * @author Java1234
 */
@Mapper
public interface HealthReminderMapper {

    HealthReminder findById(@Param("id") Long id);

    List<HealthReminder> findPage(@Param("userId") Long userId, @Param("keyword") String keyword,
                                  @Param("offset") int offset, @Param("limit") int limit);

    long count(@Param("userId") Long userId, @Param("keyword") String keyword);

    int insert(HealthReminder reminder);

    int update(HealthReminder reminder);

    int deleteById(@Param("id") Long id);

    long countAll();
}
