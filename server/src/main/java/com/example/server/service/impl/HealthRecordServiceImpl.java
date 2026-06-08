package com.example.server.service.impl;

import com.example.server.common.PageResult;
import com.example.server.entity.HealthRecord;
import com.example.server.mapper.HealthRecordMapper;
import com.example.server.service.HealthRecordService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 健康档案业务实现类
 *
 * @author Java1234
 */
@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    private final HealthRecordMapper healthRecordMapper;

    public HealthRecordServiceImpl(HealthRecordMapper healthRecordMapper) {
        this.healthRecordMapper = healthRecordMapper;
    }

    @Override
    public PageResult<HealthRecord> findPage(Long userId, String keyword, int page, int size) {
        int offset = (page - 1) * size;
        List<HealthRecord> records = healthRecordMapper.findPage(userId, keyword, offset, size);
        long total = healthRecordMapper.count(userId, keyword);
        return new PageResult<>(total, records);
    }

    @Override
    public HealthRecord findById(Long id) {
        return healthRecordMapper.findById(id);
    }

    @Override
    public void save(HealthRecord record) {
        calculateBmi(record);
        healthRecordMapper.insert(record);
    }

    @Override
    public void update(HealthRecord record) {
        calculateBmi(record);
        healthRecordMapper.update(record);
    }

    @Override
    public void delete(Long id) {
        healthRecordMapper.deleteById(id);
    }

    /**
     * 根据身高体重自动计算BMI
     *
     * @param record 健康记录
     */
    private void calculateBmi(HealthRecord record) {
        if (record.getHeight() != null && record.getWeight() != null
                && record.getHeight().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal heightM = record.getHeight().divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
            BigDecimal bmi = record.getWeight().divide(heightM.multiply(heightM), 2, RoundingMode.HALF_UP);
            record.setBmi(bmi);
        }
    }
}
