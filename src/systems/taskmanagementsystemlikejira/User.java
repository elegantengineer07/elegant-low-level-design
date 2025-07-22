package systems.taskmanagementsystemlikejira;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;

    // Question:
    // Does user needs a relationship with Tasks

    public User(String name, String email) {
        this.id = "random uuid"; // but will be uuid
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
