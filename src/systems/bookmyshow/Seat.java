package systems.bookmyshow;

enum SeatType {
    BASIC,
    STANDARD,
    PREMIUM
}

enum SeatStatus {
    AVAILABLE,
    BOOKED
}

public class Seat {
    private String id;
    private int row;
    private int column;
    private SeatType type;
    private double price;
    private SeatStatus status;

    public Seat(String id, int row, int column, SeatType type, double price) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.type = type;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getColumn() {
        return column;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public SeatType getType() {
        return type;
    }

    public int getRow() {
        return row;
    }
}
