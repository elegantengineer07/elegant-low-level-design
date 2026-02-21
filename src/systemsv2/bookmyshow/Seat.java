package systemsv2.bookmyshow;

public class Seat {
    private String id;
    private SeatStatus status;

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
