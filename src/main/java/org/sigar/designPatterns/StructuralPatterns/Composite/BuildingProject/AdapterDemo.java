package org.sigar.designPatterns.StructuralPatterns.Composite.BuildingProject;

public class AdapterDemo {
    public static void main(String[] args) {
        Building home = new Building("#101 LANE 23");
        Building groundFloor = new Building("Ground floor");
        groundFloor.addStructure(new Room("Lobby"));
        groundFloor.addStructure(new Room("Kitchen"));
        groundFloor.addStructure(new Room("Hall"));


        Building firstFloor = new Building("First floor");
        firstFloor.addStructure(new Room("Lobby"));
        firstFloor.addStructure(new Room("Kitchen"));
        firstFloor.addStructure(new Room("Hall"));

       int gId =  home.addStructure(groundFloor);
       int fId =  home.addStructure(firstFloor);
      //  home.location();
        home.getStructure(gId).location();


    }
}
