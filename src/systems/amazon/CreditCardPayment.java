package systems.amazon;

public class CreditCardPayment implements Payment {
    public boolean processPayment(int amount) {
        System.out.println("Paid via Credit Card");

        return true;
    }
}
