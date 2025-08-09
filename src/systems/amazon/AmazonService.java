package systems.amazon;

import java.util.Map;
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
}
