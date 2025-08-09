package systems.amazon;

public class OrderItem {
    private String id;
    private Product product;
    private int productQuantity;

    public OrderItem(Product product, int productQuantity) {
        this.product = product;
        this.productQuantity = productQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
