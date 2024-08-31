package org.sigar.designPatterns.BehaviouralPatterns.Mediator.ChatRoom;

import lombok.AllArgsConstructor;

@AllArgsConstructor
abstract class User {

    protected ChatMediator chatMediator;
    protected String name;

    public abstract void send(String message);
    public abstract void receive(String message);
}
