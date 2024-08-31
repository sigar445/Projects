package org.sigar.designPatterns.BehaviouralPatterns.Mediator.SmartHome;

public interface SmartHomeMediator {
    public void notify(Device device,Event event);
    public void registerDevice(Device device);
}
