package org.sigar.designPatterns.Answers.NotificationSystem_1.Strategy;

public class StrategyDemo {
    public static void main(String[] args) {
        NotificationService service = new NotificationService(new EmailStrategy());
        String message = "Saying Hello";
        service.send(message);
        service.setStrategy(new SMSStrategy());
        service.send(message);
    }
}
