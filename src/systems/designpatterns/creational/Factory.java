package systems.designpatterns.creational;
// Creates objects without exposing the instantiation logic.
// Various Notifications, Various Payments
interface Shape {
    void area();
}

class Circle implements Shape {
    public void area() {
        System.out.println("circle area");
    }
}

class Square implements Shape {
    public void area() {
        System.out.println("square area");
    }
}

class ShapeFactory {
    public static Shape getShape(String type) {
        if(type.equals("Circle")) return new Circle();
        if(type.equals("Square")) return new Square();

        return null; // throw arguments
    }
}

public class Factory {
    public static void main(String args[]) {
        // Naive way
        Circle circle = new Circle();
        Square square = new Square();

        String userInput = "Circle";
        Shape shape = ShapeFactory.getShape(userInput);


    }
}
