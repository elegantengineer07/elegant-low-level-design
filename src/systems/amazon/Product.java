package systems.amazon;

enum ProductCategory {
    CLOTHING, ELECTRONICS, HOUSEHOLD
}

public class Product {
    private String id;
    private String name;
    private int price;
    private int stock;
    private ProductCategory ProductCategory;

    public Product(String name, int price, int stock, ProductCategory productCategory) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.ProductCategory = productCategory;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // Current approach: synchronized methods
    // Pros: Simple, thread-safe for single JVM, prevents race conditions on stock.
    // Cons: Can be a bottleneck under high concurrency, not scalable for distributed systems.

    public synchronized boolean isAvailable() {
        return stock > 0;
    }

    public synchronized void updateStock(int delta) {
        this.stock = this.stock + delta;
    }


    // Future approach: AtomicInteger for stock management
    // Pros: Non-blocking, allows concurrent updates, better performance under high load.
    // Cons: Requires careful handling of atomic operations, may not prevent all

}
