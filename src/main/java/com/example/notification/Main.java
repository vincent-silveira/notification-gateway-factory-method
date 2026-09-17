package com.example.notification;

import com.example.notification.cli.NotificationCommandHandler;

public class Main {
    public static void main(String[] args) {
        new NotificationCommandHandler().handle(args);
    }
}