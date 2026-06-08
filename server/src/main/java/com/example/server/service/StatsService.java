package com.example.server.service;

import java.util.Map;

/**
 * 数据统计业务接口
 *
 * @author Java1234
 */
public interface StatsService {

    /**
     * 获取首页统计概览数据
     *
     * @return 统计数据
     */
    Map<String, Object> getOverview();
}
