package org.sigar.designPatterns.BehaviouralPatterns.Mediator.ChatRoom;

public interface ChatMediator {

    public void sendMessage(String message, User user);
    public void addUser(User user);
}
