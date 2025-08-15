package systems.amazon;

public class UpiPayment implements Payment {
    public boolean processPayment(int amount) {
        System.out.println("Payment via UPI");
        return true;
    }
}
