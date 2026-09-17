package com.example.notification.factory;

import com.example.notification.domain.Notification;
import com.example.notification.domain.SmsNotification;

public class SmsNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification(String recipient, String subject, String message) {
        return new SmsNotification(recipient, message);
    }
}
