package org.sigar.designPatterns.BehaviouralPatterns.Observer;

public interface Subject {

    void register(Observer observer);
    void remove(Observer observer);
    void notifyObservers();
}
