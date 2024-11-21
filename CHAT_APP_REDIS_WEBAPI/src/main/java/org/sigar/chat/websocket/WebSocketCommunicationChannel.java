package org.sigar.chat.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sigar.chat.channel.CommunicationChannel;
import org.sigar.chat.channel.MessageHandler;
import org.sigar.chat.model.ChatMessage;
import org.sigar.chat.redis.ChatMessageListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "chat.communication.channel", havingValue = "websocket")
public class WebSocketCommunicationChannel implements CommunicationChannel {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketCommunicationChannel(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void connect() {
        System.out.println("Websocket channel connected");
    }

    @Override
    public void disconnect() {
        System.out.println("Websocket channel disconnected");
    }

    @Override
    public void sendMessage(String destination,String message) {
        ChatMessageListener.sendMessageToSubscribers(simpMessagingTemplate,destination,message);
    }

    @Override
    public void receiveMessage(String source, MessageHandler messageHandler) {
        System.out.println("WebSocket subscriptions handled by Spring controllers");

    }
}
