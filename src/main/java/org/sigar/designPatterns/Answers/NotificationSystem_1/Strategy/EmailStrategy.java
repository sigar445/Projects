package org.sigar.designPatterns.Answers.NotificationSystem_1.Strategy;

public class EmailStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending by EMAIL " + message);
    }
}
