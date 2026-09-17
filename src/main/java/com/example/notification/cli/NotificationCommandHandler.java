package com.example.notification.cli;

import com.example.notification.domain.*;
import com.example.notification.factory.*;
import com.example.notification.service.NotificationService;

public class NotificationCommandHandler {

    private final NotificationService service = new NotificationService();
    private final NotificationFactoryRegister register = new NotificationFactoryRegister();


    public void handle(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: notify send --type ...");
            return;
        }
        if ("send".equals(args[0])) send(args);
        else if ("types".equals(args[0])) System.out.println("email sms push");
        else System.out.println("Unknown command");
    }

    private void send(String[] args) {
        String type = value(args, "--type");
        String recipient = value(args, "--recipient");
        String subject = value(args, "--subject");
        String message = value(args, "--message");

        // TODO: Replace this construction switch with Factory Method creators.

        NotificationFactory factory = register.getNotificationFactory(type);
        Notification notification = factory.createNotification(recipient, subject, message);
        service.send(notification);
    }

    private String value(String[] args, String key) {
        for (int i = 0; i < args.length - 1; i++)
            if (key.equals(args[i]))
                return args[i + 1];
        return "";
    }
}