package com.example.notification.factory;

import com.example.notification.domain.EmailNotification;
import com.example.notification.domain.Notification;

public class EmailNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification(String recipient, String subject, String message) {
        return new EmailNotification(recipient,subject, message);
    }
}
