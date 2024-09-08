package org.sigar.designPatterns.Answers.NotificationSystem_1.Factory;

public class EmailMessengerFactory extends MessengerFactory{

    @Override
    public Messenger createMessenger() {
        return new EMAILMessenger();
    }
}
