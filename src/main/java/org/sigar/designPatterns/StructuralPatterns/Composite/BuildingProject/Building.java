package org.sigar.designPatterns.StructuralPatterns.Composite.BuildingProject;

import java.util.LinkedList;

public class Building implements Structure {
    private final LinkedList<Structure> structures;
    private final String address;
    Building(String address){
        structures = new LinkedList<>();
        this.address = address;
    }
    public int addStructure(Structure structure){
        structures.add(structure);
        return structures.size()-1;
    }
    public Structure getStructure(int id){
        return structures.get(id);
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
        System.out.println("You are currently located in " + this.address + ". This has ");
        structures.forEach(structure -> System.out.println(structure.getName()));
    }

    @Override
    public String getName() {
        return this.address;
    }
}
