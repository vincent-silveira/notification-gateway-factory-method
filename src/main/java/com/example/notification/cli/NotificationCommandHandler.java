package com.example.notification.cli;

import com.example.notification.domain.*;
import com.example.notification.service.NotificationService;

public class NotificationCommandHandler {
    private final NotificationService service = new NotificationService();

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
        String type = value(args, "--type"), recipient = value(args, "--recipient");
        String subject = value(args, "--subject"), message = value(args, "--message");
        Notification notification;
        // TODO: Replace this construction switch with Factory Method creators.
        if ("email".equals(type)) notification = new EmailNotification(recipient, subject, message);
        else if ("sms".equals(type)) notification = new SmsNotification(recipient, message);
        else if ("push".equals(type)) notification = new PushNotification(recipient, subject, message);
        else throw new IllegalArgumentException("Unsupported type: " + type);
        service.send(notification);
    }

    private String value(String[] args, String key) {
        for (int i = 0; i < args.length - 1; i++) if (key.equals(args[i])) return args[i + 1];
        return "";
    }
}