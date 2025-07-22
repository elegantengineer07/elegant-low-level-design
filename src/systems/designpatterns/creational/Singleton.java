package systems.designpatterns.creational;

// Creational Design Pattern - Object creation logic
// Ensures that a class has only one instance
// static keyword is leveraged
// Used to make DB connections, Logger


// Not singleton
class NotSingleton1 {
    public NotSingleton1() {

    }
}

class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {}; // private constructor

    public static Singleton1 getInstance() {
        if(instance == null) {
            instance = new Singleton1();
        }

        return instance;
    }
}

class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {}; // private constructor

    public static synchronized Singleton2 getInstance() {
        if(instance == null) {
            instance = new Singleton2();
        }

        return instance;
    }
}


public class Singleton {
    public static void main(String args[]) {
        Singleton1 instance = Singleton1.getInstance();
    }
}

