package com.example.server.service.impl;

import com.example.server.common.PageResult;
import com.example.server.entity.DietRecord;
import com.example.server.mapper.DietRecordMapper;
import com.example.server.service.DietRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 饮食记录业务实现类
 *
 * @author Java1234
 */
@Service
public class DietRecordServiceImpl implements DietRecordService {

    private final DietRecordMapper dietRecordMapper;

    public DietRecordServiceImpl(DietRecordMapper dietRecordMapper) {
        this.dietRecordMapper = dietRecordMapper;
    }

    @Override
    public PageResult<DietRecord> findPage(Long userId, String keyword, int page, int size) {
        int offset = (page - 1) * size;
        List<DietRecord> records = dietRecordMapper.findPage(userId, keyword, offset, size);
        long total = dietRecordMapper.count(userId, keyword);
        return new PageResult<>(total, records);
    }

    @Override
    public DietRecord findById(Long id) { return dietRecordMapper.findById(id); }

    @Override
    public void save(DietRecord record) { dietRecordMapper.insert(record); }

    @Override
    public void update(DietRecord record) { dietRecordMapper.update(record); }

    @Override
    public void delete(Long id) { dietRecordMapper.deleteById(id); }
}
