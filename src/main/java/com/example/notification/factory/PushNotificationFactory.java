package com.example.notification.factory;

import com.example.notification.domain.Notification;
import com.example.notification.domain.PushNotification;

public class PushNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification(String recipient, String subject, String message) {
        return new PushNotification(recipient,subject, message);
    }
}
