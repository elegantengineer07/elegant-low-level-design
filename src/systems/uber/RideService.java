package systems.uber;

import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class RideService {
    private static RideService instance;
    private Map<String, Rider> riders;
    private Map<String, Driver> drivers;
    private Map<String, Ride> rides;

    private Queue<Ride> requestedRides;

    public static synchronized RideService getInstance() {
        if(instance == null) {
            instance = new RideService();
        }

        return instance;
    }

    private RideService() {
        riders = new ConcurrentHashMap<>();
        drivers = new ConcurrentHashMap<>();
        rides = new ConcurrentHashMap<>();
        requestedRides = new ConcurrentLinkedDeque<>();
    }

    public void registerRider(Rider rider) {
        riders.putIfAbsent(rider.getId(), rider);
    }

    public void registerDriver(Driver driver) {
        drivers.putIfAbsent(driver.getId(), driver);
    }

    // Rider can request a Ride
    // Driver can accept a Ride 
    // Driver completes a Ride 
    // Rider/Driver cancels a ride;

    public void requestRide(Rider rider, Location pickup, Location drop) {
        String rideId = "RIDE" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Ride ride = new Ride(rideId, rider, null, pickup, drop, RideStatus.REQUESTED, 0);

        requestedRides.add(ride);

        // TODO: once we have added the ride, we need to update the drivers
        // TODO: notify the drivers
    }

    public void acceptRide(Driver driver, Ride ride) {

        // validations 

        ride.setDriver(driver);
        driver.setStatus(DriverStatus.IN_RIDE);
        ride.setStatus(RideStatus.ACCEPTED);

        // notify the rider
    }


    public void completeRide(Ride ride) {
        ride.setStatus(RideStatus.COMPLETED);

        // calculate the fare()
        // process the payment 
        // make driver available
    }








}
