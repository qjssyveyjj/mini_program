package com.example.server.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 睡眠记录实体类
 *
 * @author Java1234
 */
public class SleepRecord {

    private Long id;
    private Long userId;
    private LocalTime sleepStart;
    private LocalTime sleepEnd;
    private BigDecimal duration;
    private Integer quality;
    private LocalDate recordDate;
    private String remark;
    private LocalDateTime createTime;
    private String nickname;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public LocalTime getSleepStart() { return sleepStart; }
    public void setSleepStart(LocalTime sleepStart) { this.sleepStart = sleepStart; }
    public LocalTime getSleepEnd() { return sleepEnd; }
    public void setSleepEnd(LocalTime sleepEnd) { this.sleepEnd = sleepEnd; }
    public BigDecimal getDuration() { return duration; }
    public void setDuration(BigDecimal duration) { this.duration = duration; }
    public Integer getQuality() { return quality; }
    public void setQuality(Integer quality) { this.quality = quality; }
    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}
