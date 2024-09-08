package org.sigar.designPatterns.Answers.NotificationSystem_1.Factory;

import org.sigar.designPatterns.Answers.NotificationSystem_1.Strategy.NotificationService;
import org.sigar.designPatterns.Answers.NotificationSystem_1.Strategy.NotificationStrategy;

public class FactoryDemo {
    public static void main(String[] args) {
        MessengerFactory emailFactory = new EmailMessengerFactory();
        emailFactory.sendNotification(" Hello via Email!");

        MessengerFactory smsFactory = new SMSMessengerFactory();
        smsFactory.sendNotification(" Hello via SMS!");
    }
}
