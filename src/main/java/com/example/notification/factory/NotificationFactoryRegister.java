package com.example.notification.factory;

import java.util.Map;

public class NotificationFactoryRegister {

    private Map<String, NotificationFactory> types = Map.of(
            "email", new EmailNotificationFactory(),
            "sms", new SmsNotificationFactory(),
            "push", new PushNotificationFactory()
    );

    public NotificationFactory getNotificationFactory(String type){
        NotificationFactory notificationFactory = types.get(type);

        if(notificationFactory == null){
            throw new IllegalArgumentException("Unsupported type: " + type);
        }

        return notificationFactory;
    }
}
