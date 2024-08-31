package org.sigar.designPatterns.BehaviouralPatterns.Mediator.SmartHome;

public class Tablet extends Device{
    public Tablet(SmartHomeMediator mediator) {
        super(mediator);
    }

//    @Override
//    void triggerEvent(Event event) {
//        System.out.println(this.getClass() + " :triggered");
//        smartHomeMediator.notify(this,event);
//
//    }

    @Override
    void handleEvent(Event event) {
        if(event.equals(Event.SUNDAY_MORNING))
            loadNews();
    }
    public void loadNews(){
        System.out.println("Tablet: Loading the latest Globe and Mail.");
    }
}
