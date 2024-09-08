package org.sigar.designPatterns.Answers.NotificationSystem_1.Strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class NotificationService {
    private NotificationStrategy strategy;

    public void send(String message){
        strategy.sendNotification(message);
    }
}
