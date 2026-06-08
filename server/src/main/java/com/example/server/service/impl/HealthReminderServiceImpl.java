package com.example.server.service.impl;

import com.example.server.common.PageResult;
import com.example.server.entity.HealthReminder;
import com.example.server.mapper.HealthReminderMapper;
import com.example.server.service.HealthReminderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 健康提醒业务实现类
 *
 * @author Java1234
 */
@Service
public class HealthReminderServiceImpl implements HealthReminderService {

    private final HealthReminderMapper healthReminderMapper;

    public HealthReminderServiceImpl(HealthReminderMapper healthReminderMapper) {
        this.healthReminderMapper = healthReminderMapper;
    }

    @Override
    public PageResult<HealthReminder> findPage(Long userId, String keyword, int page, int size) {
        int offset = (page - 1) * size;
        List<HealthReminder> records = healthReminderMapper.findPage(userId, keyword, offset, size);
        long total = healthReminderMapper.count(userId, keyword);
        return new PageResult<>(total, records);
    }

    @Override
    public HealthReminder findById(Long id) { return healthReminderMapper.findById(id); }

    @Override
    public void save(HealthReminder reminder) { healthReminderMapper.insert(reminder); }

    @Override
    public void update(HealthReminder reminder) { healthReminderMapper.update(reminder); }

    @Override
    public void delete(Long id) { healthReminderMapper.deleteById(id); }
}
