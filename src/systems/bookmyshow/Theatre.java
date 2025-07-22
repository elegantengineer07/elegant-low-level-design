package systems.bookmyshow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Theatre {
    String id;
    String name;
    String location;
    ArrayList<Show> shows;

    public Theatre(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.shows = new ArrayList<>();
    }

    public void addShow(Show show) {
        shows.add(show);
    }

}

