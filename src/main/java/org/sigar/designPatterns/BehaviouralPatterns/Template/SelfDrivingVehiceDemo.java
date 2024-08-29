package org.sigar.designPatterns.BehaviouralPatterns.Template;

public class SelfDrivingVehiceDemo {

    public static void main(String[] args) {
        test();
    }
    public static void test(){

        SelfDrivingVehicle vehicle = new SelfDrivingMotorcycle();
        vehicle.driveToDestination();
    }
}
