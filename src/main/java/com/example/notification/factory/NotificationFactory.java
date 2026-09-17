package com.example.notification.factory;

import com.example.notification.domain.Notification;

public interface NotificationFactory {

    Notification createNotification(String recipient, String subject, String message);

}
