package org.sigar.chat.redis;

import org.sigar.chat.channel.CommunicationChannel;
import org.sigar.chat.channel.MessageHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "chat.communication.channel", havingValue = "redis")
public class RedisCommunicationChannel implements CommunicationChannel {

    private final RedisTemplate<String,String> redisTemplate;
    private final RedisMessageListenerContainer messageListenerContainer;

    public RedisCommunicationChannel(RedisTemplate<String, String> redisTemplate,RedisMessageListenerContainer messageListenerContainer) {
        this.redisTemplate = redisTemplate;
        this.messageListenerContainer = messageListenerContainer;


    }

    @Override
    public void connect() {
        System.out.println("Redis Channel Connected");

    }

    @Override
    public void disconnect() {
        System.out.println("Redis channel disconnected");
        messageListenerContainer.stop();
    }

    @Override
    public void sendMessage(String destination, String message) {
        redisTemplate.convertAndSend(destination,message);
    }

    @Override
    public void receiveMessage(String source, MessageHandler messageHandler) {
        messageListenerContainer.addMessageListener((message, pattern) -> {
            String channel = new String(message.getChannel());
            String body    = new String(message.getBody());
            messageHandler.handleMessage(channel,body);
        },new PatternTopic(source));
    }
}
