package org.sigar.chat.websocket;

import org.sigar.chat.channel.CommunicationChannel;
import org.sigar.chat.channel.MessageHandler;
import org.sigar.chat.core.ChatMessageBroadcaster;
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
        ChatMessageBroadcaster.broadcastMessageToSubscribers(simpMessagingTemplate,destination,message);
    }

    @Override
    public void receiveMessage(String source, MessageHandler messageHandler) {
        System.out.println("WebSocket subscriptions handled by Spring controllers");

    }
}
