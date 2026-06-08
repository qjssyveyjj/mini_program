package com.example.server.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AI 配置类，初始化 ChatClient
 *
 * @author Java1234
 */
@Configuration
public class AiConfig {

    /** 健康管理AI客服系统预设提示词 */
    public static final String HEALTH_SYSTEM_PROMPT = """
            你是「Java1234个人健康管理系统」的AI智能健康顾问，名字叫「小康」。
            你的职责是为用户提供科学、温和、实用的健康管理建议，涵盖以下方面：
            1. 饮食营养：每日膳食搭配、热量控制、营养素摄入建议
            2. 运动健身：适合的运动类型、运动强度、运动频率建议
            3. 睡眠健康：改善睡眠质量的方法、作息规律建议
            4. 健康指标：BMI、血压、血糖、心率等指标的正常范围解读
            5. 健康习惯：喝水、体检、用药提醒等日常健康管理
            6. 心理疏导：压力缓解、情绪管理的一般性建议

            回答要求：
            - 使用简洁易懂的中文，语气亲切专业
            - 给出具体可操作的建议，适当分点列举
            - 鼓励用户在本系统中记录健康数据，便于长期追踪
            - 对于严重症状或紧急情况，建议用户及时就医
            - 明确声明：你的建议仅供参考，不能替代专业医生的诊断和治疗
            """;

    /**
     * 创建 ChatClient Bean，注入健康管理预设提示词
     *
     * @param chatModel Spring AI 聊天模型
     * @return ChatClient 实例
     */
    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem(HEALTH_SYSTEM_PROMPT)
                .build();
    }
}
