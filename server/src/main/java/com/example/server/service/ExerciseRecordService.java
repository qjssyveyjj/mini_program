package com.example.server.service;

import com.example.server.common.PageResult;
import com.example.server.entity.ExerciseRecord;

public interface ExerciseRecordService {
    PageResult<ExerciseRecord> findPage(Long userId, String keyword, int page, int size);
    ExerciseRecord findById(Long id);
    void save(ExerciseRecord record);
    void update(ExerciseRecord record);
    void delete(Long id);
}
