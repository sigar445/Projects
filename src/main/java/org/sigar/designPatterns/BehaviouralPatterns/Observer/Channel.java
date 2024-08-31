package org.sigar.designPatterns.BehaviouralPatterns.Observer;

import java.util.HashSet;
import java.util.Set;

public class Channel implements Subject{
    private Set<Observer> observers;
    private String channelName;
    private String status;
    public Channel(String channelName){
        observers = new HashSet<>();
        this.channelName = channelName;

    }
    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for(Observer observer : observers){
            observer.update(status);
        }
    }
    public void setStatus(String status) {
        this.status = status;
        notifyObservers(); // Notify observers whenever there's a change in status
    }


}
