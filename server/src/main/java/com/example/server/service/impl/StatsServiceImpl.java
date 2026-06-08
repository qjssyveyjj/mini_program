package com.example.server.service.impl;

import com.example.server.mapper.*;
import com.example.server.service.StatsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据统计业务实现类
 *
 * @author Java1234
 */
@Service
public class StatsServiceImpl implements StatsService {

    private final UserMapper userMapper;
    private final HealthRecordMapper healthRecordMapper;
    private final DietRecordMapper dietRecordMapper;
    private final ExerciseRecordMapper exerciseRecordMapper;
    private final SleepRecordMapper sleepRecordMapper;
    private final HealthReminderMapper healthReminderMapper;
    private final ArticleMapper articleMapper;

    public StatsServiceImpl(UserMapper userMapper, HealthRecordMapper healthRecordMapper,
                            DietRecordMapper dietRecordMapper, ExerciseRecordMapper exerciseRecordMapper,
                            SleepRecordMapper sleepRecordMapper, HealthReminderMapper healthReminderMapper,
                            ArticleMapper articleMapper) {
        this.userMapper = userMapper;
        this.healthRecordMapper = healthRecordMapper;
        this.dietRecordMapper = dietRecordMapper;
        this.exerciseRecordMapper = exerciseRecordMapper;
        this.sleepRecordMapper = sleepRecordMapper;
        this.healthReminderMapper = healthReminderMapper;
        this.articleMapper = articleMapper;
    }

    @Override
    public Map<String, Object> getOverview() {
        Map<String, Object> result = new HashMap<>();

        Map<String, Object> cards = new HashMap<>();
        cards.put("userCount", userMapper.countAll());
        cards.put("healthRecordCount", healthRecordMapper.countAll());
        cards.put("dietRecordCount", dietRecordMapper.countAll());
        cards.put("exerciseRecordCount", exerciseRecordMapper.countAll());
        cards.put("sleepRecordCount", sleepRecordMapper.countAll());
        cards.put("reminderCount", healthReminderMapper.countAll());
        cards.put("articleCount", articleMapper.countAll());
        result.put("cards", cards);

        result.put("healthTrend", healthRecordMapper.countByDate(7));

        result.put("genderDistribution", userMapper.countByGender());

        List<Map<String, Object>> recordTypes = List.of(
                Map.of("name", "健康档案", "value", healthRecordMapper.countAll()),
                Map.of("name", "饮食记录", "value", dietRecordMapper.countAll()),
                Map.of("name", "运动记录", "value", exerciseRecordMapper.countAll()),
                Map.of("name", "睡眠记录", "value", sleepRecordMapper.countAll())
        );
        result.put("recordTypes", recordTypes);

        return result;
    }
}
