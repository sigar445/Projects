package org.sigar.designPatterns.Answers.NotificationSystem_1.Factory;

public class SMSMessenger implements Messenger{
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Message through SMS" + message);
    }
}
