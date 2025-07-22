package systems.bookmyshow;

public class Movie {
    // use encapsulation to protect the internal state of the object
    private String id;
    private String title;
    private String genre;
    private int minDuration;

    public Movie(String id, String title, String genre, int minDuration) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.minDuration = minDuration;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getMinDuration() {
        return minDuration;
    }
}
