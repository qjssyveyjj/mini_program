package com.example.server.service;

import com.example.server.common.PageResult;
import com.example.server.entity.SleepRecord;

public interface SleepRecordService {
    PageResult<SleepRecord> findPage(Long userId, String keyword, int page, int size);
    SleepRecord findById(Long id);
    void save(SleepRecord record);
    void update(SleepRecord record);
    void delete(Long id);
}
