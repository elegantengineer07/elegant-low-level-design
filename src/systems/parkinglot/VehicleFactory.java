package systems.parkinglot;

public class VehicleFactory {
    public static Vehicle createVehicle(String licensePlate, VehicleType type) {
        switch (type) {
            case VehicleType.CAR -> {
                return new Car(licensePlate);
            }
            case VehicleType.BIKE -> {
                return new Bike(licensePlate);
            }
            default -> throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}
