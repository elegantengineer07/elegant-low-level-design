package systems.uber;

public class Rider {
    private String id;
    private String name;
    private String phone; 
    private Location location;

    public Rider(String id, String name, String phone, Location location) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Location getLocation() {
        return location;
    }

    public void updateLocation(Location newLocation) {
        this.location = location;
    }

}
