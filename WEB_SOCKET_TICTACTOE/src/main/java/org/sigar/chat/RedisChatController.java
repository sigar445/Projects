package org.sigar.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RedisChatController {

        @Autowired
        private RedisTemplate<String, String> redisTemplate;

        @MessageMapping("/{chatId}/sendMessage")
        public void sendMessage(@DestinationVariable String chatId, ChatMessage message) throws JsonProcessingException {
            String serializedMessage = new ObjectMapper().writeValueAsString(message);
            System.out.printf("Got chatId as %s, sending to it ",chatId);
            redisTemplate.convertAndSend(chatId, serializedMessage);
        }
    }


