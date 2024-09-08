package org.sigar.designPatterns.Answers.NotificationSystem_1.Strategy;

public class SMSStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending by SMS " + message);
    }
}
