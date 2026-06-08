package com.example.server.entity;

import java.time.LocalDateTime;

/**
 * 后台管理员实体类
 *
 * @author Java1234
 */
public class Admin {

    /** 主键ID */
    private Long id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 昵称 */
    private String nickname;
    /** 头像URL */
    private String avatar;
    /** 创建时间 */
    private LocalDateTime createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
