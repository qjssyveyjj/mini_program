package com.example.server.mapper;

import com.example.server.entity.ExerciseRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 运动记录数据访问接口
 *
 * @author Java1234
 */
@Mapper
public interface ExerciseRecordMapper {

    ExerciseRecord findById(@Param("id") Long id);

    List<ExerciseRecord> findPage(@Param("userId") Long userId, @Param("keyword") String keyword,
                                  @Param("offset") int offset, @Param("limit") int limit);

    long count(@Param("userId") Long userId, @Param("keyword") String keyword);

    int insert(ExerciseRecord record);

    int update(ExerciseRecord record);

    int deleteById(@Param("id") Long id);

    long countAll();
}
