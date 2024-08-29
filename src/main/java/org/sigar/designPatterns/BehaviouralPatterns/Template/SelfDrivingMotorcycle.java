package org.sigar.designPatterns.BehaviouralPatterns.Template;

public class SelfDrivingMotorcycle extends SelfDrivingVehicle{
    @Override
    void accelerate() {
        System.out.println("Accelerating Motorcycle");
    }

    @Override
    void steer() {
        System.out.println("Steering Motorcylce");
    }

    @Override
    void drive() {
        System.out.println("Driving Motorcycle");
    }
}
