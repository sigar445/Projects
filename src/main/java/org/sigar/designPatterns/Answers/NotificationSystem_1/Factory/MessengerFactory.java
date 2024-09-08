package org.sigar.designPatterns.Answers.NotificationSystem_1.Factory;


import javax.management.Notification;

//Other Implementation would have been to have this class as interface
// and implement SMS_MESSANGER_FACTORY, EMAIL_MESSANGER_FACTORY and others
public abstract class MessengerFactory {
    public abstract Messenger createMessenger();
    private final Messenger messenger  = createMessenger();
    public void sendNotification(String message){
        messenger.sendMessage(message);
    }

}
