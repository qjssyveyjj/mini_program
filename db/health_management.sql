-- ============================================================
-- Java1234微信小程序个人健康管理系统 - 数据库初始化脚本
-- MySQL 8.0  端口: 3308
-- 密码统一明文: 123456
-- ============================================================

CREATE DATABASE IF NOT EXISTS health_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE health_management;

-- 后台管理员表
DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    username    VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名',
    password    VARCHAR(100) NOT NULL COMMENT '密码(明文)',
    nickname    VARCHAR(50)  DEFAULT NULL COMMENT '昵称',
    avatar      VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '后台管理员表';

-- 小程序用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id            BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    username      VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名',
    password      VARCHAR(100) NOT NULL COMMENT '密码(明文)',
    nickname      VARCHAR(50)  DEFAULT NULL COMMENT '昵称',
    avatar        VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
    gender        TINYINT      DEFAULT 0 COMMENT '性别:0未知 1男 2女',
    birthday      DATE         DEFAULT NULL COMMENT '生日',
    height        DECIMAL(5,2) DEFAULT NULL COMMENT '身高(cm)',
    target_weight DECIMAL(5,2) DEFAULT NULL COMMENT '目标体重(kg)',
    phone         VARCHAR(20)  DEFAULT NULL COMMENT '手机号',
    status        TINYINT      DEFAULT 1 COMMENT '状态:0禁用 1正常',
    create_time   DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '小程序用户表';

-- 健康档案记录表
DROP TABLE IF EXISTS health_record;
CREATE TABLE health_record (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id         BIGINT       NOT NULL COMMENT '用户ID',
    height          DECIMAL(5,2) DEFAULT NULL COMMENT '身高(cm)',
    weight          DECIMAL(5,2) DEFAULT NULL COMMENT '体重(kg)',
    bmi             DECIMAL(5,2) DEFAULT NULL COMMENT 'BMI指数',
    systolic        INT          DEFAULT NULL COMMENT '收缩压(mmHg)',
    diastolic       INT          DEFAULT NULL COMMENT '舒张压(mmHg)',
    blood_sugar     DECIMAL(5,2) DEFAULT NULL COMMENT '血糖(mmol/L)',
    heart_rate      INT          DEFAULT NULL COMMENT '心率(次/分)',
    record_date     DATE         NOT NULL COMMENT '测量日期',
    remark          VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_time     DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id),
    INDEX idx_record_date (record_date)
) COMMENT '健康档案记录表';

-- 饮食记录表
DROP TABLE IF EXISTS diet_record;
CREATE TABLE diet_record (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id     BIGINT       NOT NULL COMMENT '用户ID',
    meal_type   VARCHAR(20)  NOT NULL COMMENT '餐别:早餐/午餐/晚餐/加餐',
    food_name   VARCHAR(100) NOT NULL COMMENT '食物名称',
    calories    INT          DEFAULT 0 COMMENT '热量(千卡)',
    record_date DATE         NOT NULL COMMENT '记录日期',
    remark      VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id),
    INDEX idx_record_date (record_date)
) COMMENT '饮食记录表';

-- 运动记录表
DROP TABLE IF EXISTS exercise_record;
CREATE TABLE exercise_record (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id         BIGINT       NOT NULL COMMENT '用户ID',
    exercise_type   VARCHAR(50)  NOT NULL COMMENT '运动类型',
    duration        INT          DEFAULT 0 COMMENT '运动时长(分钟)',
    calories_burned INT          DEFAULT 0 COMMENT '消耗热量(千卡)',
    record_date     DATE         NOT NULL COMMENT '记录日期',
    remark          VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_time     DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id),
    INDEX idx_record_date (record_date)
) COMMENT '运动记录表';

-- 睡眠记录表
DROP TABLE IF EXISTS sleep_record;
CREATE TABLE sleep_record (
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id      BIGINT       NOT NULL COMMENT '用户ID',
    sleep_start  TIME         DEFAULT NULL COMMENT '入睡时间',
    sleep_end    TIME         DEFAULT NULL COMMENT '起床时间',
    duration     DECIMAL(4,1) DEFAULT NULL COMMENT '睡眠时长(小时)',
    quality      TINYINT      DEFAULT 3 COMMENT '质量评分:1-5',
    record_date  DATE         NOT NULL COMMENT '记录日期',
    remark       VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_time  DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id),
    INDEX idx_record_date (record_date)
) COMMENT '睡眠记录表';

-- 健康提醒表
DROP TABLE IF EXISTS health_reminder;
CREATE TABLE health_reminder (
    id            BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id       BIGINT       NOT NULL COMMENT '用户ID',
    reminder_type VARCHAR(20)  NOT NULL COMMENT '类型:喝水/吃药/体检/运动',
    title         VARCHAR(100) NOT NULL COMMENT '提醒标题',
    content       VARCHAR(255) DEFAULT NULL COMMENT '提醒内容',
    remind_time   TIME         NOT NULL COMMENT '提醒时间',
    is_enabled    TINYINT      DEFAULT 1 COMMENT '是否启用:0否 1是',
    create_time   DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id)
) COMMENT '健康提醒表';

-- 健康资讯文章表
DROP TABLE IF EXISTS article;
CREATE TABLE article (
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    title        VARCHAR(200) NOT NULL COMMENT '文章标题',
    cover_image  VARCHAR(255) DEFAULT NULL COMMENT '封面图URL',
    category     VARCHAR(50)  DEFAULT NULL COMMENT '分类',
    content      TEXT         NOT NULL COMMENT '文章内容',
    view_count   INT          DEFAULT 0 COMMENT '浏览量',
    status       TINYINT      DEFAULT 1 COMMENT '状态:0下架 1发布',
    publish_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    create_time  DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '健康资讯文章表';

-- AI对话历史表
DROP TABLE IF EXISTS chat_message;
CREATE TABLE chat_message (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id     BIGINT       NOT NULL COMMENT '用户ID',
    role        VARCHAR(20)  NOT NULL COMMENT '角色:user/assistant',
    content     TEXT         NOT NULL COMMENT '消息内容',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id)
) COMMENT 'AI对话历史表';

-- ============================================================
-- 测试数据
-- ============================================================

-- 管理员
INSERT INTO admin (username, password, nickname) VALUES
('admin', '123456', '系统管理员');

-- 用户
INSERT INTO user (username, password, nickname, gender, birthday, height, target_weight, phone, status) VALUES
('zhangsan', '123456', '张三', 1, '1998-05-15', 175.00, 70.00, '13800138001', 1),
('lisi',     '123456', '李四', 2, '1999-08-20', 162.00, 55.00, '13800138002', 1),
('wangwu',   '123456', '王五', 1, '1997-03-10', 180.00, 75.00, '13800138003', 1),
('zhaoliu',  '123456', '赵六', 2, '2000-11-25', 165.00, 52.00, '13800138004', 1),
('sunqi',    '123456', '孙七', 1, '1996-07-08', 172.00, 68.00, '13800138005', 1);

-- 健康档案
INSERT INTO health_record (user_id, height, weight, bmi, systolic, diastolic, blood_sugar, heart_rate, record_date, remark) VALUES
(1, 175.00, 72.50, 23.67, 120, 80, 5.20, 72, '2026-06-01', '状态良好'),
(1, 175.00, 72.00, 23.51, 118, 78, 5.10, 70, '2026-06-03', '体重略有下降'),
(1, 175.00, 71.80, 23.44, 122, 82, 5.30, 75, '2026-06-05', '正常'),
(2, 162.00, 56.00, 21.34, 110, 70, 4.80, 68, '2026-06-02', '偏瘦'),
(2, 162.00, 55.50, 21.15, 108, 68, 4.90, 66, '2026-06-04', '接近目标'),
(3, 180.00, 78.00, 24.07, 125, 85, 5.50, 78, '2026-06-01', '血压偏高'),
(3, 180.00, 77.50, 23.92, 123, 83, 5.40, 76, '2026-06-06', '有所改善'),
(4, 165.00, 53.00, 19.47, 105, 65, 4.60, 65, '2026-06-03', '健康'),
(5, 172.00, 70.00, 23.66, 115, 75, 5.00, 70, '2026-06-02', '正常');

-- 饮食记录
INSERT INTO diet_record (user_id, meal_type, food_name, calories, record_date, remark) VALUES
(1, '早餐', '牛奶+全麦面包', 350, '2026-06-06', '营养均衡'),
(1, '午餐', '鸡胸肉沙拉', 480, '2026-06-06', '低脂高蛋白'),
(1, '晚餐', '清蒸鱼+蔬菜', 420, '2026-06-06', '清淡'),
(1, '早餐', '燕麦粥+鸡蛋', 320, '2026-06-07', ''),
(1, '午餐', '牛肉饭', 650, '2026-06-07', '偶尔放纵'),
(2, '早餐', '豆浆+包子', 380, '2026-06-06', ''),
(2, '午餐', '蔬菜汤+米饭', 450, '2026-06-06', ''),
(3, '早餐', '三明治', 400, '2026-06-06', ''),
(3, '晚餐', '火锅', 800, '2026-06-06', '聚餐'),
(4, '午餐', '水果沙拉', 280, '2026-06-06', '轻食'),
(5, '早餐', '酸奶+坚果', 300, '2026-06-06', '');

-- 运动记录
INSERT INTO exercise_record (user_id, exercise_type, duration, calories_burned, record_date, remark) VALUES
(1, '跑步', 30, 280, '2026-06-06', '晨跑5公里'),
(1, '游泳', 45, 350, '2026-06-04', '自由泳'),
(1, '瑜伽', 60, 200, '2026-06-02', '放松身心'),
(2, '快走', 40, 180, '2026-06-06', '公园散步'),
(2, '跳绳', 20, 150, '2026-06-03', ''),
(3, '篮球', 60, 400, '2026-06-06', '和朋友打球'),
(3, '骑行', 50, 320, '2026-06-01', ''),
(4, '普拉提', 45, 220, '2026-06-05', ''),
(5, '健身', 60, 380, '2026-06-06', '力量训练'),
(5, '跑步', 25, 230, '2026-06-04', '');

-- 睡眠记录
INSERT INTO sleep_record (user_id, sleep_start, sleep_end, duration, quality, record_date, remark) VALUES
(1, '23:00:00', '07:00:00', 8.0, 4, '2026-06-06', '睡眠充足'),
(1, '23:30:00', '06:30:00', 7.0, 3, '2026-06-05', '略少'),
(2, '22:30:00', '06:30:00', 8.0, 5, '2026-06-06', '质量很好'),
(3, '00:30:00', '08:00:00', 7.5, 3, '2026-06-06', '熬夜了'),
(4, '23:00:00', '07:30:00', 8.5, 5, '2026-06-06', ''),
(5, '23:15:00', '06:45:00', 7.5, 4, '2026-06-06', '');

-- 健康提醒
INSERT INTO health_reminder (user_id, reminder_type, title, content, remind_time, is_enabled) VALUES
(1, '喝水', '上午喝水提醒', '记得喝一杯温水，促进新陈代谢', '09:00:00', 1),
(1, '喝水', '下午喝水提醒', '下午茶时间，补充水分', '15:00:00', 1),
(1, '运动', '晚间运动提醒', '该去跑步了，保持运动习惯', '18:30:00', 1),
(2, '吃药', '维生素补充', '记得服用维生素C', '08:00:00', 1),
(2, '体检', '年度体检提醒', '建议预约年度健康体检', '10:00:00', 1),
(3, '喝水', '早起喝水', '起床后喝一杯温水', '07:30:00', 1),
(4, '运动', '瑜伽时间', '每天练习30分钟瑜伽', '19:00:00', 1),
(5, '吃药', '钙片补充', '睡前服用钙片', '21:00:00', 0);

-- 健康资讯
INSERT INTO article (title, cover_image, category, content, view_count, status, publish_time) VALUES
('科学饮食：每日营养搭配指南', '/uploads/article1.jpg', '饮食健康',
 '均衡饮食是健康的基石。每天应摄入适量的碳水化合物、蛋白质和脂肪。建议多吃蔬菜水果，减少高糖高盐食物的摄入。早餐要吃好，午餐要吃饱，晚餐要吃少。', 256, 1, '2026-05-20 10:00:00'),
('运动健身：新手入门指南', '/uploads/article2.jpg', '运动健身',
 '对于运动新手，建议从低强度运动开始，如快走、慢跑。每周运动3-5次，每次30-60分钟。运动前做好热身，运动后做好拉伸，避免运动损伤。', 189, 1, '2026-05-22 14:00:00'),
('优质睡眠的10个秘诀', '/uploads/article3.jpg', '睡眠健康',
 '良好的睡眠对身体健康至关重要。保持规律作息，睡前避免使用电子设备，营造舒适的睡眠环境。成年人每天应保证7-9小时的睡眠时间。', 312, 1, '2026-05-25 09:00:00'),
('高血压的预防与管理', '/uploads/article4.jpg', '疾病预防',
 '高血压是常见的慢性病。预防高血压应注意低盐饮食、规律运动、控制体重、戒烟限酒。已确诊患者应遵医嘱服药，定期监测血压。', 178, 1, '2026-05-28 11:00:00'),
('心理健康：如何缓解压力', '/uploads/article5.jpg', '心理健康',
 '现代生活节奏快，压力普遍存在。缓解压力的方法包括：规律运动、冥想放松、与朋友交流、培养兴趣爱好。如压力过大，建议寻求专业心理咨询。', 145, 1, '2026-06-01 16:00:00'),
('BMI指数：了解你的体重是否健康', '/uploads/article6.jpg', '健康知识',
 'BMI（身体质量指数）= 体重(kg) / 身高(m)²。BMI在18.5-23.9之间为正常范围，24-27.9为超重，28及以上为肥胖。定期监测BMI有助于体重管理。', 98, 1, '2026-06-03 10:00:00');

-- AI对话历史
INSERT INTO chat_message (user_id, role, content, create_time) VALUES
(1, 'user', '我每天应该喝多少水？', '2026-06-06 10:00:00'),
(1, 'assistant', '一般建议成年人每天饮水1500-2000毫升。具体需根据体重、活动量和气候调整。运动量大或天气炎热时应适当增加。', '2026-06-06 10:00:05'),
(1, 'user', '跑步后应该怎么拉伸？', '2026-06-06 11:00:00'),
(1, 'assistant', '跑步后建议进行5-10分钟拉伸：1.小腿拉伸（弓步压腿）2.大腿前侧拉伸 3.大腿后侧拉伸 4.臀部拉伸。每个动作保持20-30秒，有助于缓解肌肉酸痛。', '2026-06-06 11:00:08');
