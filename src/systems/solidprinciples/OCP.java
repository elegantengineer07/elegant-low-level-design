package systems.solidprinciples;

/*
Open/Closed Principle (OCP):
Definition: Classes should be open for extension but closed for modification.
Explanation: You should be able to add new functionality to a class by extending it without changing its existing code.
Example: If you have a Shape class, you can add new shapes (like Circle or Rectangle) by extending Shape, without modifying the Shape class itself.
*/

// Violation of OCP

class NotificationService {
    public void sendEmail(String message) {
        // otp
    }

    public void sendSMS(String message) {
        // send otp
    }

    // adding new method here is modifiying the class which is bad
}

// Following OCP
interface Notification {
    public void send(String message);
}

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        // send via email
    }
}

class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        // send via sms;
    }
}


public class OCP {
    public static void main(String args[]) {
        Notification emailService = new EmailNotification();
        Notification smsService = new EmailNotification();
        emailService.send("abc");
        smsService.send("abc");
    }
}
