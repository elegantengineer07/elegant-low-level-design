package systems.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AmazonService {
    // Singleton instance should be static
    private static AmazonService instance;
    private Map<String, Customer> customers;
    private Map<String, Product> products;
    private Map<String, Order> orders;

    // To get singleton, this should be public
    public static synchronized AmazonService getAmazonService() {
        if(instance == null) {
            instance = new AmazonService();
        }

        return instance;
    }

    // Constructor should be private
    private AmazonService() {
         customers = new ConcurrentHashMap<>();
         products = new ConcurrentHashMap<>();
         orders = new ConcurrentHashMap<>();
    }

    public void registerCustomer(Customer customer) {
        customers.putIfAbsent(customer.getId(), customer);
    }

    public void addProduct(Product product) {
        products.putIfAbsent(product.getId(), product);
    }

    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public synchronized Order placeOrder(Customer customer, Cart cart, Payment payment) {
        List<OrderItem> inStockItems = new ArrayList<>();

        for(OrderItem item: cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getProductQuantity();
            if(product.isAvailable(quantity)) {
                product.updateStock(-quantity);
                inStockItems.add(item);
            }
        }


        if(inStockItems.isEmpty()) {
            throw new IllegalStateException("No available products in card");
        }

        // generate a UUID for order, for now I will generate random id;
        String orderId = "ORDER" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Order order = new Order(orderId, customer.getId(), inStockItems);

        orders.put(orderId, order);
        order.setOrderStatus(OrderStatus.PAYMENT_PENDING);

        // empty the cart;
        cart.emptyCart(); 

        if(payment.processPayment(order.getOrderAmount())) {
            order.setOrderStatus(OrderStatus.PLACED);
        } else {
            order.setOrderStatus(OrderStatus.CANCELLED);

            for(OrderItem oi: order.getItems()) {
                oi.getProduct().updateStock(oi.getProductQuantity());
            }
        }

        return order;
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    public List<Product> searchProducts(String keyword) {
        List<Product> filteredProducts = new ArrayList<>();

        for(Product product: products.values()) {
            if(product.getName().toLowerCase().contains(keyword)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

}
