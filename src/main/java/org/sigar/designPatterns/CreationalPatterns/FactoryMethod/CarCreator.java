package org.sigar.designPatterns.CreationalPatterns.FactoryMethod;

public class CarCreator extends VehicleCreator{
    @Override
    public Vehicle getVehicle() {
        return new Car();
    }
}
