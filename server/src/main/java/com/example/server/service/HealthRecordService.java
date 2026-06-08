package com.example.server.service;

import com.example.server.common.PageResult;
import com.example.server.entity.HealthRecord;

public interface HealthRecordService {
    PageResult<HealthRecord> findPage(Long userId, String keyword, int page, int size);
    HealthRecord findById(Long id);
    void save(HealthRecord record);
    void update(HealthRecord record);
    void delete(Long id);
}
