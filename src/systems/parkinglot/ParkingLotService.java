package systems.parkinglot;

public class ParkingLotService {
    public static void main(String args[]) {
        ParkingLot parkingLotInstance = ParkingLot.getInstance();

        parkingLotInstance.addLevel(new Level(0,1));

        // naive way
        Vehicle car = new Car("abc");
        Vehicle motorcycle = new Bike("abc");

        // factory pattern - to have minimal changes and avoid object instantiation in client;
        Vehicle goodCar = VehicleFactory.createVehicle("abc", VehicleType.CAR);
        Vehicle goodBike = VehicleFactory.createVehicle("cde", VehicleType.BIKE);

        // park vechile
        parkingLotInstance.parkVehicle(goodCar);
        parkingLotInstance.parkVehicle(goodBike);


        parkingLotInstance.parkVehicle(car); // will fail;
        parkingLotInstance.displayAvailability();

        parkingLotInstance.unparkVehicle(goodCar);
        parkingLotInstance.displayAvailability();

        parkingLotInstance.parkVehicle(car);

    }
}
