package org.sigar.designPatterns.BehaviouralPatterns.ChainOfResponcibility.Logger;

public class LoggerDemo {
    private static Logger getLoggerChain(){

        Logger errorLogger = new ConsoleLogger(Logger.ERROR);
        Logger fileLogger = new FileLogger(Logger.DEBUG);
        Logger cosoleLogger = new ConsoleLogger(Logger.INFO);
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(cosoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        Logger loggerChain = getLoggerChain();

        loggerChain.logMessage(Logger.INFO, "This is an information.");
        System.out.println();
        loggerChain.logMessage(Logger.DEBUG, "This is a debug level information.");
        System.out.println();
        loggerChain.logMessage(Logger.ERROR, "This is an error information.");
    }
}
