# Notification Gateway

A Java CLI practice project for learning and applying the **Factory Method Design Pattern**.

This project was generated as a starter codebase for Low-Level Design practice. It is intentionally designed with architectural shortcomings so that the learner can identify the problems, refactor the code, and improve the design using an appropriate creational design pattern.

---

## Project Status

**Status:** Starter Project / Intentionally Incomplete

This repository is not intended to represent a final production-ready implementation.

The existing code is functional enough to demonstrate the application's basic behavior, but parts of the architecture have been deliberately simplified or coupled. These areas are intended to be discovered and refactored as part of the learning exercise.

---

## Learning Objective

The primary objective of this project is to understand when and how to apply the **Factory Method Pattern**.

By completing this exercise, you should be able to:

* Identify tightly coupled object-creation logic.
* Understand the difference between object creation and business logic.
* Recognize when conditional construction logic becomes difficult to maintain.
* Apply the Factory Method Pattern to delegate object creation.
* Introduce abstractions without overengineering the solution.
* Add new notification types with minimal changes to existing code.
* Write tests that verify behavior after refactoring.

---

## Problem Statement

The application represents a simple notification gateway.

A user can request that a message be sent through different notification channels:

* Email
* SMS
* Push Notification

The application accepts commands from the command line, creates the appropriate notification object, and sends the notification through a service layer.

### Example Commands

```text
send email user@example.com "Welcome to the platform"
send sms +919876543210 "Your OTP is 123456"
send push device-123 "You have a new message"
```

The exact command syntax may be adjusted while completing the project.

---

## Current Architecture

The current implementation contains notification-related domain classes and a command handler.

A simplified version of the current flow is:

```text
User Input
    |
    v
NotificationCommandHandler
    |
    |-- if / else or switch
    |
    |-- new EmailNotification(...)
    |-- new SmsNotification(...)
    |-- new PushNotification(...)
    |
    v
NotificationService
    |
    v
Notification
```

The command handler currently knows which concrete notification class must be instantiated.

This creates unnecessary coupling between the input-handling layer and the concrete notification implementations.

---

## Intentional Design Flaw

The main architectural issue is direct object construction inside the command-handling logic.

For example, the code currently follows this type of approach:

```java
if (type.equals("email")) {
    notification = new EmailNotification(...);
} else if (type.equals("sms")) {
    notification = new SmsNotification(...);
} else if (type.equals("push")) {
    notification = new PushNotification(...);
}
```

This approach works for a small number of notification types, but becomes increasingly difficult to maintain as new channels are added.

Potential problems include:

* The command handler depends on every concrete notification class.
* Adding a new notification type requires modifying existing conditional logic.
* Object creation is mixed with command parsing and application flow.
* The class becomes responsible for more than one concern.
* The design becomes harder to extend and test independently.

These issues are intentionally present for refactoring practice.

---

## Your Task

Refactor the existing implementation using the **Factory Method Design Pattern**.

The goal is to move notification object creation away from the command-handling logic and introduce an abstraction for creating notification objects.

You should not simply hide the existing `if/else` statement inside another class without considering the design. The purpose of the exercise is to understand the responsibilities of the Product, Creator, and concrete Creator classes.

---

## Expected Refactoring Direction

The exact implementation is intentionally left for you to decide.

A possible target structure could look like:

```text
Notification
├── EmailNotification
├── SmsNotification
└── PushNotification

NotificationCreator
├── EmailNotificationCreator
├── SmsNotificationCreator
└── PushNotificationCreator
```

The application flow should eventually resemble:

```text
User Input
    |
    v
NotificationCommandHandler
    |
    v
NotificationCreator
    |
    v
Concrete Notification
    |
    v
NotificationService
```

The command handler should focus primarily on interpreting the command and coordinating the application flow rather than knowing how every concrete notification is constructed.

---

## Project Structure

```text
notification-gateway/
├── pom.xml
├── DESIGN.md
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── example/
    │               └── notification/
    │                   ├── Main.java
    │                   ├── cli/
    │                   │   └── NotificationCommandHandler.java
    │                   ├── domain/
    │                   │   ├── Notification.java
    │                   │   ├── EmailNotification.java
    │                   │   ├── SmsNotification.java
    │                   │   ├── PushNotification.java
    │                   │   └── NotificationHistory.java
    │                   └── service/
    │                       └── NotificationService.java
    │
    └── test/
        └── java/
            └── com/
                └── example/
                    └── notification/
                        └── NotificationCommandHandlerTest.java
```

The structure may change during refactoring.

Adding new interfaces, abstract classes, creators, factories, or tests is expected if they support a better design.

---

## Technology Stack

* Java 17+
* Maven
* JUnit 5
* Command Line Interface
* Object-Oriented Design Principles
* Creational Design Patterns

---

## Getting Started

### Prerequisites

Make sure the following are installed:

```text
Java 17 or later
Maven 3.8 or later
Git
```

Verify the installations:

```bash
java -version
mvn -version
git --version
```

---

## Running the Project

Clone the repository:

```bash
git clone https://github.com/<your-username>/notification-gateway-factory-method.git
cd notification-gateway-factory-method
```

Compile the project:

```bash
mvn compile
```

Run the tests:

```bash
mvn test
```

Run the application through your IDE or configure the Maven execution command according to your environment.

---

## Suggested Learning Workflow

### Step 1: Understand the Existing Code

Read the following classes before making changes:

```text
NotificationCommandHandler
NotificationService
Notification
EmailNotification
SmsNotification
PushNotification
```

Understand how a command moves through the application.

### Step 2: Identify the Coupling

Find all locations where concrete notification objects are created.

Ask yourself:

* Which class decides what object to create?
* Which class should own object creation?
* What happens if another notification channel is added?
* How many existing classes would need modification?

### Step 3: Design the Factory Method Solution

Identify:

| Factory Method Role | Possible Project Class                                     |
| ------------------- | ---------------------------------------------------------- |
| Product             | `Notification`                                             |
| Concrete Products   | `EmailNotification`, `SmsNotification`, `PushNotification` |
| Creator             | A notification creator abstraction                         |
| Concrete Creators   | Email, SMS, and Push notification creators                 |

The names and exact structure are part of the design exercise.

### Step 4: Refactor Incrementally

Avoid changing the entire project at once.

A possible sequence:

1. Introduce the creator abstraction.
2. Move notification creation into creator classes.
3. Update the command handler to use creators.
4. Remove unnecessary direct construction from the handler.
5. Run the tests.
6. Add tests for the new creation behavior.
7. Verify that the application still works.

### Step 5: Add a New Notification Type

As an extension exercise, add another channel such as:

```text
WhatsAppNotification
```

or:

```text
SlackNotification
```

Evaluate how many existing classes need to change after your refactoring.

This is one of the main ways to verify whether the new design has reduced coupling.

---

## Design Questions to Answer

Before considering the refactoring complete, document your answers to these questions:

1. What was the original design problem?
2. Which class was responsible for creating concrete notification objects?
3. Why is that responsibility problematic?
4. What is the Product abstraction in this implementation?
5. What is the Creator abstraction?
6. What responsibilities belong to the concrete creators?
7. How does the new design reduce coupling?
8. How would you add a new notification channel?
9. What are the trade-offs of using Factory Method here?
10. Would a Simple Factory or Dependency Injection approach also be suitable? Why or why not?

---

## Definition of Done

The exercise can be considered complete when:

* The application still supports the existing notification channels.
* Direct notification construction has been removed from the appropriate application-flow class.
* The Factory Method Pattern is clearly represented in the design.
* Notification creation responsibilities are separated from command handling.
* Existing tests pass.
* Additional tests cover the refactored creation behavior.
* A new notification type can be added without modifying unrelated application logic.
* The README or design notes explain the architectural changes.

---

## Suggested Git Commit History

Commit your work incrementally so the evolution of the design is visible.

Example:

```text
Add generated notification gateway starter
Identify direct notification construction coupling
Introduce notification creator abstraction
Implement concrete notification creators
Refactor command handler to use Factory Method
Add tests for notification creation
Add support for a new notification channel
Update design documentation
```

---

## Important Note

This repository is intentionally generated as a practice project.

The architectural flaws are part of the exercise. Do not assume that every existing class or package structure is final. You are expected to question the current design, make changes, introduce abstractions where justified, and document the reasoning behind your decisions.

The objective is not only to make the code work.

The objective is to understand **why the original design was difficult to extend and how the Factory Method Pattern improves that design**.

---

## License

This project is intended for educational and practice purposes.
