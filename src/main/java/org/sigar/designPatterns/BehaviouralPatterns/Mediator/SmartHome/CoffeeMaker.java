package org.sigar.designPatterns.BehaviouralPatterns.Mediator.SmartHome;

public class CoffeeMaker extends Device{

    public CoffeeMaker(SmartHomeMediator mediator) {
        super(mediator);
    }

    @Override
    void triggerEvent(Event event) {
        System.out.println(this.getClass().getSimpleName() + " :triggered");
        smartHomeMediator.notify(this,event);
    }

    public void makeCoffee(){
        System.out.println("Coffee making started");

    }
    @Override
    void handleEvent(Event event) {
        if(event.equals(Event.ALARM_GOES_OFF)){
            makeCoffee();
        }
    }
}
