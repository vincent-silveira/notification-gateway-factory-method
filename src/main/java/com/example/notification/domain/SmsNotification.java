package com.example.notification.domain;
public record SmsNotification(String recipient, String message) implements Notification {
  public void send() { System.out.println("SMS -> " + recipient + ": " + message); }
}