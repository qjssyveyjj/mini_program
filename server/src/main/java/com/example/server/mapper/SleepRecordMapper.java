package com.example.server.mapper;

import com.example.server.entity.SleepRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 睡眠记录数据访问接口
 *
 * @author Java1234
 */
@Mapper
public interface SleepRecordMapper {

    SleepRecord findById(@Param("id") Long id);

    List<SleepRecord> findPage(@Param("userId") Long userId, @Param("keyword") String keyword,
                               @Param("offset") int offset, @Param("limit") int limit);

    long count(@Param("userId") Long userId, @Param("keyword") String keyword);

    int insert(SleepRecord record);

    int update(SleepRecord record);

    int deleteById(@Param("id") Long id);

    long countAll();
}
