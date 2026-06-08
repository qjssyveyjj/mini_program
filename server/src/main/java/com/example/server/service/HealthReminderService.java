package com.example.server.service;

import com.example.server.common.PageResult;
import com.example.server.entity.HealthReminder;

public interface HealthReminderService {
    PageResult<HealthReminder> findPage(Long userId, String keyword, int page, int size);
    HealthReminder findById(Long id);
    void save(HealthReminder reminder);
    void update(HealthReminder reminder);
    void delete(Long id);
}
