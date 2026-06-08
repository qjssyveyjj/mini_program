package com.example.server.service;

import com.example.server.entity.ChatMessage;

import java.util.List;

/**
 * AI智能客服业务接口
 *
 * @author Java1234
 */
public interface AiChatService {

    /**
     * 发送消息并获取AI回复
     *
     * @param userId  用户ID
     * @param message 用户消息
     * @return AI回复内容
     */
    String chat(Long userId, String message);

    /**
     * 获取用户对话历史
     *
     * @param userId 用户ID
     * @return 对话消息列表
     */
    List<ChatMessage> getHistory(Long userId);

    /**
     * 清空用户对话历史
     *
     * @param userId 用户ID
     */
    void clearHistory(Long userId);
}
