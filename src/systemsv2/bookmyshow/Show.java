package systemsv2.bookmyshow;

import systems.bookmyshow.Seat;

import java.time.LocalDateTime;
import java.util.List;

public class Show {
    private String id;
    private String theaterId;
    private String movieId;
    private String screen;
    private LocalDateTime dateTime;
    private List<Seat> seats;

    public String getId() {
        return id;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getScreen() {
        return screen;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
