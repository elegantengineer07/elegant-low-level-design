package systems.uber;

// Notes
// Either we could have full reference or id reference for other classes like Rider/Driver
public class Ride {
    private String id;
    private Rider rider;
    private Driver driver; 
    private Location pickup;
    private Location drop;
    private RideStatus status;
    private int fare; // can be double but keeping int for simplicity;

    public Ride(String id, Rider rider, Driver driver, Location pickup, Location drop, RideStatus status, int fare) {
        this.id = id;
        this.rider = rider;
        this.driver = driver;
        this.pickup = pickup;
        this.drop = drop;
        this.status = status;
        this.fare = fare;
    }

    public String getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getPickup() {
        return pickup;
    }

    public Location getDrop() {
        return drop;
    }

    public RideStatus getStatus() {
        return status;
    }

    public int getFare() {
        return fare;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public void updateFare(int fare) {
        this.fare = fare;
    }

    public void updatePickup(Location pickup) {
        this.pickup = pickup;
    }

    public void updateDrop(Location drop) {
        this.drop = drop;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
