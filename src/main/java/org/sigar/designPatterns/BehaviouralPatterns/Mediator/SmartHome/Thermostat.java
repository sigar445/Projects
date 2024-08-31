package org.sigar.designPatterns.BehaviouralPatterns.Mediator.SmartHome;

public class Thermostat extends Device{
    public Thermostat(SmartHomeMediator mediator){
        super(mediator);
    }

//    @Override
//    void triggerEvent(Event event) {
//        System.out.println("Event triggered: " + event);
//        smartHomeMediator.notify(this,event);
//    }


    @Override
    public void handleEvent(Event event) {
        if (event == Event.LEAVE_HOME) {
            adjustTemperature();
        }
    }

    public void adjustTemperature() {
        System.out.println("Thermostat: Adjusting temperature for energy saving.");
    }

}
