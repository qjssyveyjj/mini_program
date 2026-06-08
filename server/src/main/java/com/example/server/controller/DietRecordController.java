package com.example.server.controller;

import com.example.server.common.R;
import com.example.server.entity.DietRecord;
import com.example.server.service.DietRecordService;
import org.springframework.web.bind.annotation.*;

/**
 * 饮食记录控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/diet")
public class DietRecordController {

    private final DietRecordService dietRecordService;

    public DietRecordController(DietRecordService dietRecordService) {
        this.dietRecordService = dietRecordService;
    }

    @GetMapping("/page")
    public R page(@RequestParam(required = false) Long userId,
                  @RequestParam(defaultValue = "") String keyword,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "10") int size) {
        return R.ok(dietRecordService.findPage(userId, keyword, page, size));
    }

    @PostMapping
    public R save(@RequestBody DietRecord record) {
        dietRecordService.save(record);
        return R.ok("添加成功");
    }

    @PutMapping
    public R update(@RequestBody DietRecord record) {
        dietRecordService.update(record);
        return R.ok("更新成功");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        dietRecordService.delete(id);
        return R.ok("删除成功");
    }
}
