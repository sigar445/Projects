package org.sigar.designPatterns.BehaviouralPatterns.ChainOfResponcibility.Logger;


class ConsoleLogger extends Logger {

    public ConsoleLogger(int level) {
        setLevel(level);
    }

    @Override
    protected void write(String message) {
        System.out.println("Console::Logger: " + message);
    }
}
