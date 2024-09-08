package org.sigar.designPatterns.CreationalPatterns.FactoryMethod;

public abstract class VehicleCreator {
    public Vehicle orderVehicle(){
        Vehicle vehicle = getVehicle();
        vehicle.drive();
        return vehicle;
    }
    public abstract Vehicle getVehicle();
}
