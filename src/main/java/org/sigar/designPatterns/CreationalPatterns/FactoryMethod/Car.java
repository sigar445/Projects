package org.sigar.designPatterns.CreationalPatterns.FactoryMethod;

public class Car extends Vehicle{
    @Override
    public void drive() {
        System.out.println("Drive Checking Car");
    }
}
