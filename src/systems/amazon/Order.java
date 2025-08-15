package systems.amazon;

import java.util.List;

enum OrderStatus {
    PAYMENT_PENDING, PLACED, SHIPPED, DELIVERED, CANCELLED
}

public class Order {
    private String id;
    private String customerId;
    private int orderAmount;
    private List<OrderItem> items;
    //private Map<String, OrderItem> orderItems;
    private OrderStatus orderStatus;

    public Order(String id, String customerId, List<OrderItem> orderItems) {
        this.id = id;
        this.customerId = customerId;
        this.items = orderItems;
        this.orderStatus = OrderStatus.PAYMENT_PENDING;
        this.orderAmount = generateOrderAmount();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getOrderAmount() {
        return orderAmount;
    }


    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int generateOrderAmount() {
        int amount = 0;
        for(OrderItem orderItem: items) {
            amount += orderItem.getProduct().getPrice() * orderItem.getProductQuantity();
        }

        return amount;
    }

}
