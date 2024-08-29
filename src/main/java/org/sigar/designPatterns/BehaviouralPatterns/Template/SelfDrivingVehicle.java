package org.sigar.designPatterns.BehaviouralPatterns.Template;

public abstract class SelfDrivingVehicle {

    public final void driveToDestination(){
            accelerate();
            steer();
            drive();
            reachDestination();
    }

    abstract void accelerate();
    abstract void steer();
    abstract void drive();
    private  void reachDestination(){
        System.out.println("Destination reached");
    }
}
