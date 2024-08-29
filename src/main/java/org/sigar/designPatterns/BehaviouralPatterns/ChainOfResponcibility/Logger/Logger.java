package org.sigar.designPatterns.BehaviouralPatterns.ChainOfResponcibility.Logger;

import lombok.Setter;

public abstract class Logger {

    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    @Setter
    protected int level;
    @Setter
    protected Logger nextLogger;

    public void logMessage(int level,String message){
        if(this.level == level){
            write(message);
        }
        else if(nextLogger != null)
            nextLogger.logMessage(level,message);
    }


    protected abstract void write(String message);

}
