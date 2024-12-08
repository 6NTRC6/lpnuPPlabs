package trainPackage;

import java.time.LocalTime;
import java.util.Map;

public class Train {
    private int destinationId;
    private int trainId;
    private LocalTime departureTime;
    private Map<Integer, Integer> seats;

    public int getDestinationId() {
        return destinationId;
    }

    public String getDestinationName() {
        return switch (destinationId) {
            case 1 -> "Lviv";
            case 2 -> "Kyiv";
            case 3 -> "Odesa";
            default -> "Undefined";
        };
    }

    public void setDestination(int destinationId) {
        this.destinationId = destinationId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getSeatsInfo() {
        return "All seats: " + this.calculateSeats() + ", coupe seats: " + this.getSeatsCount(1) + ", reserved seats: " + this.getSeatsCount(2) + ", premium seats: " + this.getSeatsCount(3);
    }

    public int getSeatsCount(int seatType) {
        return this.seats.getOrDefault(seatType, 0);
    }

    public void setSeats(Map<Integer, Integer> seats) {
        this.seats = seats;
    }

//    public int calculateSeats() {
//        return getSeatsCount(1) + getSeatsCount(2) + getSeatsCount(3);
//    }

    public int calculateSeats() {
        int sum = 0;
        for (int i = 0; i < seats.size(); i++) {
            sum += seats.get(i);
        }
    }


    public Train(int destinationId, int trainId, LocalTime departureTime, Map<Integer, Integer> seats) {
        this.destinationId = destinationId;
        this.trainId = trainId;
        this.departureTime = departureTime;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Train №" + getTrainId() +
                ": Destination: " + this.getDestinationName() +
                ", departure time = " + this.getDepartureTime() +
                ", seats = " + this.getSeatsInfo() +
                "\n";
    }
}
