package org.sigar.designPatterns.StructuralPatterns.Composite.BuildingProject;

public class Room  implements Structure{
    String name;

    Room(String name) {
        this.name  = name;
    }

    @Override
    public void enter() {
        System.out.println("You have entered " + getName());
    }

    @Override
    public void exit() {
        System.out.println("You have exited from " + getName());
    }


    @Override
    public void location() {
        System.out.println("You are currently located in " + this.name);

    }

    @Override
    public String getName() {
        return this.name;
    }
}
