package systems.amazon;

import java.util.UUID;

public class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.id = "CUSTOMER" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 

}
