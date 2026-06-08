package com.example.server.mapper;

import com.example.server.entity.HealthRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 健康档案数据访问接口
 *
 * @author Java1234
 */
@Mapper
public interface HealthRecordMapper {

    HealthRecord findById(@Param("id") Long id);

    List<HealthRecord> findPage(@Param("userId") Long userId, @Param("keyword") String keyword,
                                @Param("offset") int offset, @Param("limit") int limit);

    long count(@Param("userId") Long userId, @Param("keyword") String keyword);

    int insert(HealthRecord record);

    int update(HealthRecord record);

    int deleteById(@Param("id") Long id);

    long countAll();

    List<Map<String, Object>> countByDate(@Param("days") int days);
}
