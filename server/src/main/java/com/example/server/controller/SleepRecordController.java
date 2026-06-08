package com.example.server.controller;

import com.example.server.common.R;
import com.example.server.entity.SleepRecord;
import com.example.server.service.SleepRecordService;
import org.springframework.web.bind.annotation.*;

/**
 * 睡眠记录控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/sleep")
public class SleepRecordController {

    private final SleepRecordService sleepRecordService;

    public SleepRecordController(SleepRecordService sleepRecordService) {
        this.sleepRecordService = sleepRecordService;
    }

    @GetMapping("/page")
    public R page(@RequestParam(required = false) Long userId,
                  @RequestParam(defaultValue = "") String keyword,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "10") int size) {
        return R.ok(sleepRecordService.findPage(userId, keyword, page, size));
    }

    @PostMapping
    public R save(@RequestBody SleepRecord record) {
        sleepRecordService.save(record);
        return R.ok("添加成功");
    }

    @PutMapping
    public R update(@RequestBody SleepRecord record) {
        sleepRecordService.update(record);
        return R.ok("更新成功");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sleepRecordService.delete(id);
        return R.ok("删除成功");
    }
}
