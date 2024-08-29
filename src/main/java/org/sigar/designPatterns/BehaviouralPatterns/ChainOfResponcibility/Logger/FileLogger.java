package org.sigar.designPatterns.BehaviouralPatterns.ChainOfResponcibility.Logger;

public class FileLogger extends Logger {

    public FileLogger(int level){
        setLevel(level);
    }
    @Override
    protected void write(String message) {
        System.out.println("FILE::Logger " + message);
    }
}
