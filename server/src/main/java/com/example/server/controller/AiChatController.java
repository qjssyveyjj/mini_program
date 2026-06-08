package com.example.server.controller;

import com.example.server.common.R;
import com.example.server.service.AiChatService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * AI智能客服控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/ai")
public class AiChatController {

    private final AiChatService aiChatService;

    public AiChatController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    /**
     * 发送消息给AI健康顾问
     */
    @PostMapping("/chat")
    public R chat(@RequestBody Map<String, Object> params) {
        Long userId = Long.valueOf(params.get("userId").toString());
        String message = params.get("message").toString();
        try {
            String reply = aiChatService.chat(userId, message);
            return R.ok("回复成功", Map.of("reply", reply));
        } catch (Exception e) {
            return R.error("AI服务暂时不可用: " + e.getMessage());
        }
    }

    /**
     * 获取对话历史
     */
    @GetMapping("/history/{userId}")
    public R history(@PathVariable Long userId) {
        return R.ok(aiChatService.getHistory(userId));
    }

    /**
     * 清空对话历史
     */
    @DeleteMapping("/history/{userId}")
    public R clearHistory(@PathVariable Long userId) {
        aiChatService.clearHistory(userId);
        return R.ok("已清空对话历史");
    }
}
