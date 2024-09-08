package org.sigar.designPatterns.CreationalPatterns.Singleton;

public class SingletonTest {
    public static void main(String[] args) {
        Singleton singleton = Singleton.INSTANCE;
        singleton.displayVal();
        singleton.getResource();
        singleton.getResource();
        singleton.getResource();
        singleton.getResource();
        singleton.getResource();
        singleton.getResource().displayMessage("Hello");

        singleton.getResource().displayMessage("Hello");
        singleton.getResource().displayMessage("Hello");
    }
}
