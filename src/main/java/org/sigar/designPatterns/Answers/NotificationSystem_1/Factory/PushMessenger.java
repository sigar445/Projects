package org.sigar.designPatterns.Answers.NotificationSystem_1.Factory;

public class PushMessenger implements Messenger{
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Message through PUSH" + message);
    }
}
