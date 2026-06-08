package com.example.server.mapper;

import com.example.server.entity.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AI对话消息数据访问接口
 *
 * @author Java1234
 */
@Mapper
public interface ChatMessageMapper {

    List<ChatMessage> findByUserId(@Param("userId") Long userId, @Param("limit") int limit);

    int insert(ChatMessage message);

    int deleteByUserId(@Param("userId") Long userId);
}
