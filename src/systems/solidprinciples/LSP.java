package systems.solidprinciples;

/*
Liskov Substitution Principle (LSP):
Definition: Subclasses should be substitutable for their base classes.
Explanation: Objects of a derived class should be able to replace objects of the base class without affecting the correctness of the program.
Example: If Bird is a base class and Penguin is a subclass, you shouldn't violate the behavior of Bird when substituting it with Penguin (e.g., assuming all birds can fly wouldn't work because penguins can't fly).
 */

class Bird {
    public void fly() {
        System.out.println("Birds can fly");
    }
}

class Crow extends Bird {
    public void fly() {
        System.out.println("Crow is flying");
    }
}

class Penguin extends Bird {
    public void fly() {
        throw new UnsupportedOperationException("can't fly");
    }
}

public class LSP {
    public static void main(String args[]) {
        Bird bird0 = new Crow();
        Bird bird1 = new Penguin();

        bird0.fly();
        // bird1.fly();
    }
}
