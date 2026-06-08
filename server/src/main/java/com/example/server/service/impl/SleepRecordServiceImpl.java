package com.example.server.service.impl;

import com.example.server.common.PageResult;
import com.example.server.entity.SleepRecord;
import com.example.server.mapper.SleepRecordMapper;
import com.example.server.service.SleepRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 睡眠记录业务实现类
 *
 * @author Java1234
 */
@Service
public class SleepRecordServiceImpl implements SleepRecordService {

    private final SleepRecordMapper sleepRecordMapper;

    public SleepRecordServiceImpl(SleepRecordMapper sleepRecordMapper) {
        this.sleepRecordMapper = sleepRecordMapper;
    }

    @Override
    public PageResult<SleepRecord> findPage(Long userId, String keyword, int page, int size) {
        int offset = (page - 1) * size;
        List<SleepRecord> records = sleepRecordMapper.findPage(userId, keyword, offset, size);
        long total = sleepRecordMapper.count(userId, keyword);
        return new PageResult<>(total, records);
    }

    @Override
    public SleepRecord findById(Long id) { return sleepRecordMapper.findById(id); }

    @Override
    public void save(SleepRecord record) { sleepRecordMapper.insert(record); }

    @Override
    public void update(SleepRecord record) { sleepRecordMapper.update(record); }

    @Override
    public void delete(Long id) { sleepRecordMapper.deleteById(id); }
}
