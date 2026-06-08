package com.example.server.service;

import com.example.server.common.PageResult;
import com.example.server.entity.DietRecord;

public interface DietRecordService {
    PageResult<DietRecord> findPage(Long userId, String keyword, int page, int size);
    DietRecord findById(Long id);
    void save(DietRecord record);
    void update(DietRecord record);
    void delete(Long id);
}
