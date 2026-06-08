package com.example.server.controller;

import com.example.server.common.R;
import com.example.server.service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据统计控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    /**
     * 获取首页统计概览数据
     */
    @GetMapping("/overview")
    public R overview() {
        return R.ok(statsService.getOverview());
    }
}
