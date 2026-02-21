package systemsv2.bookmyshow;

public class Theater {
    private String id;
    private String name;
    private String location; // string just for test
    private List<Show> shows;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Show> getShows() {
        return shows;
    }
}
