package systems.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int floor;
    private List<ParkingSpot> parkingSpots;

    public Level(int floor, int numSpots) {
        this.floor = floor;
        parkingSpots = new ArrayList<>();

        // numSpots for each kind of parkingSpots
        // bike
        for(int i=0;i<numSpots;i++) {
            parkingSpots.add(new ParkingSpot(i+1, VehicleType.BIKE));
        }

        // cars
        for(int i=numSpots;i<2*numSpots;i++) {
            parkingSpots.add(new ParkingSpot(i+1, VehicleType.CAR));
        }

        // truck
//        for(int i=0;i<numSpots;i++) {
//            parkingSpots.add(new ParkingSpot(i+1, VehicleType.TRUCK));
//        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        for(ParkingSpot parkingSpot: parkingSpots) {
            try {
                parkingSpot.parkVehicle(vehicle);
                return true;
            } catch (Exception e) {
                // System.out.println("Exception raised" + e.getMessage());
            }
        }

        System.out.println("No spots available");
        return false;
    }

    // can be optimized to decrease time complexity
    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for(ParkingSpot parkingSpot: parkingSpots) {
            if(!parkingSpot.isAvailable() && parkingSpot.getParkedVehicle().equals(vehicle)) {
                parkingSpot.unparkVehicle();
                return true;
            }
        }

        return false;
    }

    public void displayAvailability() {
        for(ParkingSpot spot: parkingSpots) {
            if(spot.isAvailable()) {
                System.out.println("Parking Spot "+ spot.getSpotNumber() +" is available for type " + spot.getVehicleType());
            } else {
                System.out.println("Parking Spot "+ spot.getSpotNumber() +" is occupied by vechile " + spot.getParkedVehicle().licensePlate);
            }
        }
    }

}
