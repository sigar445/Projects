package org.sigar.designPatterns.BehaviouralPatterns.Mediator.SmartHome;

abstract class Device {

    protected SmartHomeMediator smartHomeMediator;

    public Device(SmartHomeMediator mediator){
        this.smartHomeMediator = mediator;
        mediator.registerDevice(this);
    }
    void triggerEvent(Event event) {
        System.out.println(this.getClass().getSimpleName() + " : triggered");
        smartHomeMediator.notify(this,event);
    }
   // abstract void triggerEvent(Event event);
    abstract void handleEvent(Event event);



}
