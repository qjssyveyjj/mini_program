package com.example.server.service.impl;

import com.example.server.common.PageResult;
import com.example.server.entity.ExerciseRecord;
import com.example.server.mapper.ExerciseRecordMapper;
import com.example.server.service.ExerciseRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运动记录业务实现类
 *
 * @author Java1234
 */
@Service
public class ExerciseRecordServiceImpl implements ExerciseRecordService {

    private final ExerciseRecordMapper exerciseRecordMapper;

    public ExerciseRecordServiceImpl(ExerciseRecordMapper exerciseRecordMapper) {
        this.exerciseRecordMapper = exerciseRecordMapper;
    }

    @Override
    public PageResult<ExerciseRecord> findPage(Long userId, String keyword, int page, int size) {
        int offset = (page - 1) * size;
        List<ExerciseRecord> records = exerciseRecordMapper.findPage(userId, keyword, offset, size);
        long total = exerciseRecordMapper.count(userId, keyword);
        return new PageResult<>(total, records);
    }

    @Override
    public ExerciseRecord findById(Long id) { return exerciseRecordMapper.findById(id); }

    @Override
    public void save(ExerciseRecord record) { exerciseRecordMapper.insert(record); }

    @Override
    public void update(ExerciseRecord record) { exerciseRecordMapper.update(record); }

    @Override
    public void delete(Long id) { exerciseRecordMapper.deleteById(id); }
}
