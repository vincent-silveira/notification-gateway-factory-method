package com.example.notification;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationCommandHandlerTest {
    @Test
    void applicationStarts() {
        assertDoesNotThrow(() -> new com.example.notification.cli.NotificationCommandHandler().handle(new String[]{"types"}));
    }
    // TODO: Add tests for creators after refactoring.
}