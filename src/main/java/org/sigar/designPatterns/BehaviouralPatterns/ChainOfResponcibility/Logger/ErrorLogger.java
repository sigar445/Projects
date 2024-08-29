package org.sigar.designPatterns.BehaviouralPatterns.ChainOfResponcibility.Logger;
class ErrorLogger extends Logger {

    public ErrorLogger(int level) {
        setLevel(level);
    }

    @Override
    protected void write(String message) {
        System.out.println("Error::Logger: " + message);
    }
}

