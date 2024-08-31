package org.sigar.designPatterns.BehaviouralPatterns.Mediator.ChatRoom;

public class ChatUser extends User {

    public ChatUser(ChatMediator mediator,String name){
        super(mediator,name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " send : "  + message);
        chatMediator.sendMessage(message,this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + " receives: " + message);
    }
}
