package org.sigar.designPatterns.CreationalPatterns.FactoryMethod;

public class TestVehicleCreator {
    public static void main(String[] args) {
        VehicleCreator creator = new CarCreator();
        Vehicle vehicle = creator.orderVehicle();
        vehicle.drive();
    }
}
