package systems.basics;

// OOPs concepts in Java
// 1. Encapsulation
// 2. Inheritance
// 3. Abstraction
// 4. Polymorphism (Overloading and Overriding)
// 5. UML Relationships (Aggregation, Composition, Realization)
// 6. Factory Design Pattern

// Encapsulation
// A class should hide its internal state and require all interaction to be performed through an object's methods
// This is done to protect the integrity of the object's data and to prevent unintended interference and misuse
// Example of Encapsulation in Java

// Encapsulation
class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



// Inheritance;
class Animal {
    void sound() {
        System.out.println("sounding");
    }
}

class Cat extends Animal {
    void meow() {
        System.out.println("meow");
    }
}

// Abstraction
interface Payment {
    void pay(double amount);
}

class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via credit");
    }
}

class CashPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via cash");
    }
}

// Polymorphism (Overloading and Overriding)

// Method Overloading: Same method name, different parameters (compile-time polymorphism)
/**
 * Logger provides simple logging functionality for applications.
 * <p>
 * In production, logging is essential for monitoring, debugging, and auditing.
 * Overloaded methods allow flexibility in logging messages with or without codes,
 * which can represent error types, status codes, or other identifiers.
 * Typically, production loggers would support configurable log levels,
 * output to files or external systems, and thread safety.
 */
class Logger {
    // Overloaded method: logs a message
    void log(String message) {
        System.out.println("LOG: " + message);
    }

    // Overloaded method: logs a message with a code
    void log(String message, int code) {
        System.out.println("LOG " + message + " " + code);
    }
}

// Method Overriding: Subclass provides specific implementation for a method defined in its superclass (run-time polymorphism)
class Animal2 {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal2 {
    // Overriding the sound() method of Animal2
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

// Example usage:
// Animal2 animal = new Dog();
// animal.sound(); // Output: Dog barks

class PaymentFactory {
    public static Payment getPaymentMethod(String type) {
        switch (type.toLowerCase()) {
            case "card":
                return new CreditCardPayment();
            case "cash":
                return new CashPayment();
            default:
                throw new IllegalArgumentException("Not supported");
        }
    }
}

public class OOPs {
    public static void main(String args[]) {
        Student student = new Student();
        student.setName("Elegant Engineer");

        // encapsulation idea
        // student.name = "abc";

        System.out.println(student.getName());

        // inheritance
        Cat cat = new Cat();
        cat.sound();
        cat.meow();

        // Abstraction
        Payment cardPay = new CreditCardPayment();
        Payment cashPay = new CashPayment();

        cardPay.pay(100);
        cashPay.pay(100);

        Payment payment = PaymentFactory.getPaymentMethod("cash");
        payment.pay(100);

        // Factory Design
        Logger logger = new Logger();
        logger.log("just message");
        logger.log("message", 10);
    }
}
