package org.sigar.chat;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

//@CrossOrigin(origins = "http://localhost:63342")
//@Controller
public class ChatController {

//    @MessageMapping("/{chatId}/sendMessage")
//    @SendTo("/topic/messages/{chatId}")
//    public ChatMessage sendMessage(@PathVariable String chatId, ChatMessage message) {
//        System.out.println("Recieved message in chat room "+ chatId + ": " + message);
//        return message;  // The message will be broadcasted to all subscribers
//    }
}
//    @MessageMapping("/sendMessage")
//    @SendTo("/topic/messages")
