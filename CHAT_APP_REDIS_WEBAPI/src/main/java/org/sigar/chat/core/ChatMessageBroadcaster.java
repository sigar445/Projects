package org.sigar.chat.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.sigar.chat.model.ChatMessage;
import org.sigar.chat.channel.CommunicationChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageBroadcaster {

    private final SimpMessagingTemplate messagingTemplate;
    private final CommunicationChannel communicationChannel;

    @Autowired
    public ChatMessageBroadcaster(SimpMessagingTemplate messagingTemplate, CommunicationChannel communicationChannel) {
        this.messagingTemplate = messagingTemplate;
        this.communicationChannel = communicationChannel;
    }

    @PostConstruct
    public void setUpListeners() {
        // Listen to messages on the Redis channel and forward them to WebSocket clients
        communicationChannel.receiveMessage("*",this::broadcastMessageToSubscribers);
    }
    public void broadcastMessageToSubscribers(String destination, String message){
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
    public static void broadcastMessageToSubscribers(SimpMessagingTemplate messagingTemplate, String destination, String message){
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

