package systems.basics;
import java.util.List;

// Aggregation (weak has-a)
// Company has engineers but engineer can exist without company as well
class Engineer{
    private String skill;
}
class Company {
    List<Engineer> engineerList;
}

// Composition
// House has rooms and there is no point of rooms without house.
class House {
    List<Room> rooms;
}

class Room {
    String name;
}

// Relaization
interface Payment1 {
    void pay(double amount);
}

class CreditCardPayment1 implements Payment1 {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via credit");
    }
}

class CashPayment1 implements Payment1 {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via cash");
    }
}

public class UMLRelationships {
}
