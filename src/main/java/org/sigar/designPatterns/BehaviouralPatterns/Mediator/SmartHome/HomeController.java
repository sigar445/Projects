package org.sigar.designPatterns.BehaviouralPatterns.Mediator.SmartHome;

import java.util.HashSet;
import java.util.Set;

public class HomeController implements SmartHomeMediator {
    Set<Device> devices;
    public HomeController(){
        devices = new HashSet<>();
    }

    @Override
    public void notify(Device sender, Event event) {

        for (Device d : devices){
            d.handleEvent(event);
        }

    }

    @Override
    public void registerDevice(Device device) {
        devices.add(device);
    }
}
