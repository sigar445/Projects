package org.sigar.designPatterns.CreationalPatterns.FactoryMethod;

public class Motorcycle extends Vehicle{

    @Override
    public void drive() {
        System.out.println("Drive Checking Motorcycle");
    }
}
