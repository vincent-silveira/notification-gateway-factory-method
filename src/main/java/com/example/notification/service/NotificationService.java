package com.example.notification.service;

import com.example.notification.domain.*;

public class NotificationService {
    private final NotificationHistory history = new NotificationHistory();

    public void send(Notification notification) {
        notification.send();
        history.add(notification);
    }

    public NotificationHistory history() {
        return history;
    }
}