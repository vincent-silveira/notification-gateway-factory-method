package com.example.notification.domain;

public interface Notification {
    String recipient();

    void send();
}