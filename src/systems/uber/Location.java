package systems.uber;

public class Location {
    // Note to myself: 
    // This should be double but keeping int for simplicity sake, Call it out in an interview
    private int latitude;
    private int longitude;

    public Location(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
