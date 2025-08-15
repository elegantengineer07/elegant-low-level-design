package systems.amazon;

import java.util.List;

public class AmazonServiceUI {
    public static void main(String args[]) {
        AmazonService amazon = AmazonService.getAmazonService();

        Customer customer1 = new Customer("Yuvraj Singh", "abc@gmail.com");
        Customer customer2 = new Customer("Leo Messi", "xyz@gmail.com");

        Product product1 = new Product("Iphone 16 phone", 1, 1, ProductCategory.ELECTRONICS);
        Product product2 = new Product("Samsung fold phone", 2, 2, ProductCategory.ELECTRONICS);



        amazon.registerCustomer(customer1);
        amazon.registerCustomer(customer2);

        amazon.addProduct(product1);
        amazon.addProduct(product2);

        // Use factory pattern to avoid this here;
        Payment payment1 = new UpiPayment();
        Payment payment2 = new CreditCardPayment();


        Cart cart1 = new Cart();
        cart1.addItem(product1, 1);
        amazon.placeOrder(customer1, cart1, payment1);

        // Cart cart2 = new Cart();
        // cart2.addItem(product2, 1);
        // amazon.placeOrder(customer2, cart2, payment2);

        // search for phone
        System.out.println(amazon.searchProducts("ph"));

        // search for anything else;


    }
}
