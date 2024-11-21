package org.sigar.chat.http;

import org.sigar.chat.channel.CommunicationChannel;
import org.sigar.chat.channel.MessageHandler;
import org.sigar.chat.core.ChatMessageBroadcaster;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "chat.communication.channel", havingValue = "http")
public class HttpCommunicationChannel implements CommunicationChannel {

    private final SimpMessagingTemplate messagingTemplate;

    public HttpCommunicationChannel(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @Override
    public void connect() {
        System.out.println("HTTP channel connected");
    }

    @Override
    public void disconnect() {
        System.out.println("HTTP channel disconnected");
    }

    @Override
    public void sendMessage(String destination, String message) {
        ChatMessageBroadcaster.broadcastMessageToSubscribers(messagingTemplate,destination,message);
    }

    @Override
    public void receiveMessage(String source, MessageHandler messageHandler) {
       // throw new UnsupportedOperationException("HTTP does not support continuous message subscription");
        System.out.println("HTTP does not support continuous message subscription,handling via stomp");
    }
}
