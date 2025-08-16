package systems.uber;

public class Driver {
    private String id;
    private String name;
    private String phone;
    private Location location;
    private String license;
    private DriverStatus status;
    // ... more prop like vehicle, etc can be added 

    public Driver(String id, String name, String phone, String license, Location location, DriverStatus status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.license = license;
        this.location = location;
        this.status = status;
    }


    // Note to myself:
    // Ignore the getters/setters based on discussion with interviewer
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

    public String getLicense() {
        return license;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public void updateLocation(Location newLocation) {
        this.location = newLocation;
    }

}
