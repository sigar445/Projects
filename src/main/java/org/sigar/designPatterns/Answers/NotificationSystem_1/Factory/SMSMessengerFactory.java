package org.sigar.designPatterns.Answers.NotificationSystem_1.Factory;

public class SMSMessengerFactory extends MessengerFactory {
    @Override
    public Messenger createMessenger() {
        return new SMSMessenger();
    }
}
