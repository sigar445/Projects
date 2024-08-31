package org.sigar.designPatterns.BehaviouralPatterns.Mediator.SmartHome;

public class SmartHomeDemo {
    public static void main(String[] args) {
        SmartHomeMediator mediator = new HomeController();

        Device thermostat = new Thermostat(mediator);
        Device coffeeMaker = new CoffeeMaker(mediator);
        Device tablet = new Tablet(mediator);

        // Events triggered
        coffeeMaker.triggerEvent(Event.ALARM_GOES_OFF);
        thermostat.triggerEvent(Event.LEAVE_HOME);
        tablet.triggerEvent(Event.SUNDAY_MORNING);
    }
}
