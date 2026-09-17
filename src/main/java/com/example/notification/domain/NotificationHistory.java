package com.example.notification.domain;

import java.util.*;

public class NotificationHistory {
    private final List<Notification> sent = new ArrayList<>();

    public void add(Notification n) {
        sent.add(n);
    }

    public List<Notification> all() {
        return List.copyOf(sent);
    }
}