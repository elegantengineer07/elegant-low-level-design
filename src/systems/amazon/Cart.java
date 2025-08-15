package systems.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private Map<String, OrderItem> items;

    public Cart() {
        this.items = new ConcurrentHashMap<>();
    }

    public void addItem(Product product, int quantity) {
        String prodId = product.getId();

        if(items.containsKey(prodId)) {
            quantity = quantity + items.get(prodId).getProductQuantity();
        }

        items.put(prodId, new OrderItem(product, quantity));
    }

    public void removeItem(String productId) {
        items.remove(productId);
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public void emptyCart() {
        items.clear();
    }
}
