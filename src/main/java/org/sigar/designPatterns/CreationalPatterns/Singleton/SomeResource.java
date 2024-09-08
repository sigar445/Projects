package org.sigar.designPatterns.CreationalPatterns.Singleton;

public class SomeResource {
    public SomeResource(){
        System.out.println("Resource constructed");
    }
    public void displayMessage(String message){
        System.out.println("Displaying message from resource " + message);
    }
}
