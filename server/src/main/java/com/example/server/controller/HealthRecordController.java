package com.example.server.controller;

import com.example.server.common.R;
import com.example.server.entity.HealthRecord;
import com.example.server.service.HealthRecordService;
import org.springframework.web.bind.annotation.*;

/**
 * 健康档案控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/health-record")
public class HealthRecordController {

    private final HealthRecordService healthRecordService;

    public HealthRecordController(HealthRecordService healthRecordService) {
        this.healthRecordService = healthRecordService;
    }

    @GetMapping("/page")
    public R page(@RequestParam(required = false) Long userId,
                  @RequestParam(defaultValue = "") String keyword,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "10") int size) {
        return R.ok(healthRecordService.findPage(userId, keyword, page, size));
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable Long id) {
        return R.ok(healthRecordService.findById(id));
    }

    @PostMapping
    public R save(@RequestBody HealthRecord record) {
        healthRecordService.save(record);
        return R.ok("添加成功");
    }

    @PutMapping
    public R update(@RequestBody HealthRecord record) {
        healthRecordService.update(record);
        return R.ok("更新成功");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        healthRecordService.delete(id);
        return R.ok("删除成功");
    }
}
