package org.sigar.chat.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.sigar.chat.model.ChatMessage;
import org.sigar.chat.channel.CommunicationChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final CommunicationChannel communicationChannel;

    @Autowired
    public ChatMessageListener(SimpMessagingTemplate messagingTemplate, CommunicationChannel communicationChannel) {
        this.messagingTemplate = messagingTemplate;
        this.communicationChannel = communicationChannel;
    }

    @PostConstruct
    public void setUpListeners() {
        // Listen to messages on the Redis channel and forward them to WebSocket clients
        communicationChannel.receiveMessage("*", (destination, message) -> {
           sendMessageToSubscribers(messagingTemplate,destination,message);
        });
    }
    public static void sendMessageToSubscribers(SimpMessagingTemplate messagingTemplate, String destination, String message){
        try {
            // Deserialize the message (optional, depends on your message format)
            ChatMessage chatMessage = new ObjectMapper().readValue(message, ChatMessage.class);

            // Build the WebSocket destination
            String messageDestination = String.format("/topic/messages/%s", destination);

            // Send the message to WebSocket clients
            messagingTemplate.convertAndSend(messageDestination, chatMessage);

            System.out.println("Message forwarded to WebSocket: " + message + " destination: " + messageDestination);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}

