package com.example.server.controller;

import com.example.server.common.R;
import com.example.server.entity.ExerciseRecord;
import com.example.server.service.ExerciseRecordService;
import org.springframework.web.bind.annotation.*;

/**
 * 运动记录控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/exercise")
public class ExerciseRecordController {

    private final ExerciseRecordService exerciseRecordService;

    public ExerciseRecordController(ExerciseRecordService exerciseRecordService) {
        this.exerciseRecordService = exerciseRecordService;
    }

    @GetMapping("/page")
    public R page(@RequestParam(required = false) Long userId,
                  @RequestParam(defaultValue = "") String keyword,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "10") int size) {
        return R.ok(exerciseRecordService.findPage(userId, keyword, page, size));
    }

    @PostMapping
    public R save(@RequestBody ExerciseRecord record) {
        exerciseRecordService.save(record);
        return R.ok("添加成功");
    }

    @PutMapping
    public R update(@RequestBody ExerciseRecord record) {
        exerciseRecordService.update(record);
        return R.ok("更新成功");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        exerciseRecordService.delete(id);
        return R.ok("删除成功");
    }
}
