package com.example.notification.domain;

public record EmailNotification(String recipient, String subject, String message) implements Notification {
    public void send() {
        System.out.println("EMAIL -> " + recipient + ": " + subject + " / " + message);
    }
}