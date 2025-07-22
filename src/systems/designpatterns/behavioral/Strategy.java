package systems.designpatterns.behavioral;

interface PaymentStrategy {
    void pay(double amount);
}

class CCpayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("CC pay");
    }
}

class UpiPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("UPi Pay");
    }
}

class Cart {
    private PaymentStrategy strategy;
    public Cart(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(double amount) {
        strategy.pay(amount);
    }
}

public class Strategy {
    public static void main(String args[]) {
        Cart groceryCart = new Cart(new UpiPayment());
        groceryCart.setStrategy(new CCpayment());
        groceryCart.checkout(10);
    }
}
