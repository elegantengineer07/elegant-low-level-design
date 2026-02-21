package systemsv2.bookmyshow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

// user can get a theatres for a movie;
// user can search for shows of movie
// user can book a ticket for a movie (show)
public class BookMyShow {
    private List<Theater> theaters;
    private HashMap<String, Movie> movies;
    private HashMap<String, Show> shows;
    private HashMap<String, Ticket> tickets;
    private HashMap<String, List<Show>> showsByMovieId;

    public BookMyShow(List<Theater> theaters) {
        this.theaters = theaters;
        this.movies = new HashMap<>();
        this.tickets = new HashMap<>();

        for(Theater theater: theaters) {
            for(Show show: theater.getShows()) {
                String movieId = show.getMovieId();
                showsByMovieId.putIfAbsent(movieId, new ArrayList<>());
                showsByMovieId.get(movieId).add(show);
            }
        }
    }

    public List<Show> searchMovie(String name) {
        // error handling
        if(name == "") return new ArrayList<>();

        name = name.toLowerCase();
        List<Show> availableShows = new ArrayList<>();
        LocalDateTime timeNow = LocalDateTime.now();

        // first get all the matching moviews;
        for(Movie movie: movies.values()) {
            if(movie.getName().contains(name)) {
                // this is a one movie, now get its shows;
                if(showsByMovieId.containsKey(movie.getId())) {
                    // there are shows for this moview;
                    for(Show show: showsByMovieId.get(movie.getId())) {
                        if(show.getDateTime().isAfter(timeNow)) {
                            availableShows.add(show);
                        }
                    }
                }

            }
        }

        return availableShows;
    }

    public Ticket book(String showId, List<Seat> seats) {
        if(showId == null || seats == null || seats.isEmpty()) {
            throw new IllegalArgumentException("Invalid booking");
        }

        // get show by id;
        Show show = shows.get(showId);
        if(show == null) {
            throw new NoSuchElementException("Show not found+");
        }

        Ticket ticket = new Ticket()
    }


}
