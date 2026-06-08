package com.example.server.mapper;

import com.example.server.entity.DietRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 饮食记录数据访问接口
 *
 * @author Java1234
 */
@Mapper
public interface DietRecordMapper {

    DietRecord findById(@Param("id") Long id);

    List<DietRecord> findPage(@Param("userId") Long userId, @Param("keyword") String keyword,
                              @Param("offset") int offset, @Param("limit") int limit);

    long count(@Param("userId") Long userId, @Param("keyword") String keyword);

    int insert(DietRecord record);

    int update(DietRecord record);

    int deleteById(@Param("id") Long id);

    long countAll();
}
