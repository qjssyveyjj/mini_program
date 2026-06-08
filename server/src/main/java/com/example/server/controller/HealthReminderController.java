package com.example.server.controller;

import com.example.server.common.R;
import com.example.server.entity.HealthReminder;
import com.example.server.service.HealthReminderService;
import org.springframework.web.bind.annotation.*;

/**
 * 健康提醒控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/reminder")
public class HealthReminderController {

    private final HealthReminderService healthReminderService;

    public HealthReminderController(HealthReminderService healthReminderService) {
        this.healthReminderService = healthReminderService;
    }

    @GetMapping("/page")
    public R page(@RequestParam(required = false) Long userId,
                  @RequestParam(defaultValue = "") String keyword,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "10") int size) {
        return R.ok(healthReminderService.findPage(userId, keyword, page, size));
    }

    @PostMapping
    public R save(@RequestBody HealthReminder reminder) {
        healthReminderService.save(reminder);
        return R.ok("添加成功");
    }

    @PutMapping
    public R update(@RequestBody HealthReminder reminder) {
        healthReminderService.update(reminder);
        return R.ok("更新成功");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        healthReminderService.delete(id);
        return R.ok("删除成功");
    }
}
