# Notification Gateway – Factory Method Design Pattern

## Problem Statement

The Notification Gateway is a CLI-based Java application that simulates sending notifications through multiple communication channels.

The application currently supports the following notification types:

* Email
* SMS
* Push Notification

A user can issue commands through the command line to send a notification. Based on the requested notification type, the application creates the appropriate notification object and delegates it to the notification service for sending.

The current implementation places notification object creation directly inside the command handler using conditional logic.

For example, the command handler determines whether the requested type is `email`, `sms`, or `push`, and then directly instantiates the corresponding concrete class.

This approach works for the current number of notification types but creates unnecessary coupling between command processing and notification creation.

---

## Current Behavior

The application accepts commands in the following format:

```text
send --type <type> --recipient <recipient> --subject <subject> --message <message>
```

Examples:

```bash
send --type email --recipient user@example.com --subject "Welcome" --message "Hello World"
```

```bash
send --type sms --recipient +919876543210 --message "Your OTP is 123456"
```

```bash
send --type push --recipient device-123 --subject "Alert" --message "You have a new notification"
```

The application also supports listing available notification types:

```bash
types
```

---

# Existing Design

The current notification flow is:

```text
CLI Arguments

     |
     v
NotificationCommandHandler
     |
     | Determines notification type
     | Creates concrete notification object
     v
Notification
     |
     v
NotificationService
     |
     v
Send Notification
```

The command handler is responsible for both interpreting user commands and constructing notification objects.

---

# Design Challenge

The application needs to support multiple notification channels while keeping the notification creation process maintainable and extensible.

Currently, adding a new notification type requires modifying the existing command handler and adding another conditional branch.

For example, introducing a new `WhatsAppNotification` would require changes to the logic that currently handles Email, SMS, and Push notifications.

As the number of notification types grows, this approach can lead to:

* Increasing conditional logic.
* Tight coupling between the CLI layer and concrete notification classes.
* More difficult maintenance.
* Reduced flexibility when introducing new notification channels.

The goal of this exercise is to identify and address this object creation problem using an appropriate creational design pattern.

---

# Additional Domain Component: Notification History

The project also contains a `NotificationHistory` component.

This component represents the possibility of maintaining a record of notifications that have been sent by the application.

However, in the current implementation, notification history is not actively used by the notification flow.

There is currently no integration between sending a notification and recording it in notification history.

This component exists in the project domain but is outside the primary scope of the current Factory Method exercise.

Its presence may represent a potential future extension, such as:

* Recording sent notifications.
* Retrieving previously sent notifications.
* Auditing notification activity.
* Supporting notification delivery history.

For the purpose of this exercise, the primary focus remains notification object creation and the separation of responsibilities between command handling and notification construction.

---

# Scope of the Exercise

The exercise focuses on refactoring the notification creation mechanism without changing the existing CLI behavior.

The following functionality should remain unchanged:

* Command-line interface.
* Supported notification types.
* Notification service behavior.
* Existing notification sending workflow.

The primary area of improvement is the way notification objects are created.

---

# Objective

Refactor the existing notification creation logic using the **Factory Method Design Pattern**.

The implementation should demonstrate how object creation can be separated from the code that uses those objects.

The refactored design should allow the system to support new notification types with minimal impact on existing command-processing logic.

---

# Constraints

* Use Java 17 or higher.
* Maintain the existing Maven project structure.
* Preserve the current CLI commands and arguments.
* Do not change the expected behavior of existing notification types.
* Keep `NotificationHistory` out of scope unless explicitly required for a future extension.

---

# Expected Learning Outcomes

By completing this exercise, the developer should understand:

* The problem of tightly coupled object creation.
* When a Factory Method pattern is appropriate.
* The difference between object creation and object usage.
* How polymorphism can simplify extensible designs.
* How to improve maintainability without changing external application behavior.
