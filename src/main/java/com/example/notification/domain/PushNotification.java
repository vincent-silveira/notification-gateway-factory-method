package com.example.notification.domain;

public record PushNotification(String recipient, String subject, String message) implements Notification {
    public void send() {
        System.out.println("PUSH -> " + recipient + ": " + subject + " / " + message);
    }
}