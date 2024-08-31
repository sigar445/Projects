package org.sigar.designPatterns.BehaviouralPatterns.Mediator.ChatRoom;

import java.util.HashSet;
import java.util.Set;

public class ChatRoom implements ChatMediator{

    Set<User> users;
    public ChatRoom(){
        users = new HashSet<>();
    }
    @Override
    public void sendMessage(String message, User sender) {

       // sender.send(message);
        for(User user : users){
            if(user == sender) continue;
            user.receive(message);
        }

    }

    @Override
    public void addUser(User newUser) {
        users.add(newUser);
    }
}
