package org.sigar.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sigar.chat.channel.CommunicationChannel;
import org.sigar.chat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

//@CrossOrigin(origins = "http://localhost:63342")
@Controller
public class ChatController {
    private final CommunicationChannel communicationChannel;


    public ChatController(CommunicationChannel communicationChannel) {
        this.communicationChannel = communicationChannel;
    }

    @MessageMapping("/{chatId}/sendMessage")
    public void sendMessage(@DestinationVariable String chatId, ChatMessage message) throws JsonProcessingException {
        String serializedMessage = new ObjectMapper().writeValueAsString(message);
        System.out.printf("Got chatId as %s\n",chatId);
        communicationChannel.sendMessage(chatId, serializedMessage);
    }
}