package com.example.server.common;

import java.util.List;

/**
 * 分页查询结果封装类
 *
 * @author Java1234
 */
public class PageResult<T> {

    /** 总记录数 */
    private long total;

    /** 当前页数据列表 */
    private List<T> records;

    /**
     * 默认构造方法
     */
    public PageResult() {
    }

    /**
     * 构造分页结果
     *
     * @param total   总记录数
     * @param records 数据列表
     */
    public PageResult(long total, List<T> records) {
        this.total = total;
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
