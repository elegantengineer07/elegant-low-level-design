package systems.bookmyshow;

import java.time.LocalDateTime;
import java.util.Map;

public class Show {
    String id;
    Movie movie;
    Theatre theatre;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Map<String, Seat> seats;


    public Show(String id, Movie movie, Theatre theatre, LocalDateTime st, LocalDateTime et, Map<String, Seat> seats) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.startTime = st;
        this.endTime = et;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
