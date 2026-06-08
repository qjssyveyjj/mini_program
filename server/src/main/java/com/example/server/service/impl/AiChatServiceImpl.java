package com.example.server.service.impl;

import com.example.server.entity.ChatMessage;
import com.example.server.mapper.ChatMessageMapper;
import com.example.server.service.AiChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AI智能客服业务实现类
 *
 * @author Java1234
 */
@Service
public class AiChatServiceImpl implements AiChatService {

    private final ChatClient chatClient;
    private final ChatMessageMapper chatMessageMapper;

    public AiChatServiceImpl(ChatClient chatClient, ChatMessageMapper chatMessageMapper) {
        this.chatClient = chatClient;
        this.chatMessageMapper = chatMessageMapper;
    }

    /**
     * 调用百炼平台大模型进行对话，并保存对话记录
     */
    @Override
    public String chat(Long userId, String message) {
        ChatMessage userMsg = new ChatMessage();
        userMsg.setUserId(userId);
        userMsg.setRole("user");
        userMsg.setContent(message);
        chatMessageMapper.insert(userMsg);

        String reply = chatClient.prompt()
                .user(message)
                .call()
                .content();

        ChatMessage assistantMsg = new ChatMessage();
        assistantMsg.setUserId(userId);
        assistantMsg.setRole("assistant");
        assistantMsg.setContent(reply);
        chatMessageMapper.insert(assistantMsg);

        return reply;
    }

    @Override
    public List<ChatMessage> getHistory(Long userId) {
        return chatMessageMapper.findByUserId(userId, 50);
    }

    @Override
    public void clearHistory(Long userId) {
        chatMessageMapper.deleteByUserId(userId);
    }
}
