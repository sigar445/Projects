package org.sigar.chat.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sigar.chat.model.ChatMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

//@Component
public class RedisMessageListener implements MessageListener {
  //  private static Logger logger = Logger.getAnonymousLogger();
    private final SimpMessagingTemplate messagingTemplate;

    public RedisMessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String body    = new String(message.getBody());
        System.out.printf("\nReceived message on channel: %s\n",channel);
        System.out.printf("Message body: %s \n", body);

        ChatMessage chatMessage = null; // Deserialize JSON
        try {
            chatMessage = new ObjectMapper().readValue(body, ChatMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        messagingTemplate.convertAndSend("/topic/messages/" + channel, chatMessage);
    }
}
