package mainPackage;

import trainPackage.Train;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // визначити методи, що створюють масив об'єктів. Задати критерії вибору даних та вивести ці дані на консоль ( використати метод toString() ). Для кожного критерію створити окремий метод
    public static void main(String[] args) {
        Map<Integer, Integer> seats1 = new HashMap<>();
        seats1.put(1, 10); // Coupe seats
        seats1.put(2, 5);  // Reserved seats
        seats1.put(3, 2);  // Premium seats
        Train train1 = new Train(1, 1, LocalTime.parse("10:00"), seats1);

        Map<Integer, Integer> seats2 = new HashMap<>();
        seats2.put(1, 15); // Coupe seats
        seats2.put(2, 8);  // Reserved seats
        seats2.put(3, 3);  // Premium seats
        Train train2 = new Train(2, 2, LocalTime.parse("14:30"), seats2);

        Map<Integer, Integer> seats3 = new HashMap<>();
        seats3.put(1, 8);  // Coupe seats
        seats3.put(2, 3);  // Reserved seats
        seats3.put(3, 1);  // Premium seats
        Train train3 = new Train(3, 3, LocalTime.parse("18:00"), seats3);

        Map<Integer, Integer> seats4 = new HashMap<>();
        Train train4 = new Train(1, 4, LocalTime.parse("12:00"), seats4);

        Map<Integer, Integer> seats5 = new HashMap<>();
        seats5.put(2, 10);
        seats5.put(3, 5);
        Train train5 = new Train(2, 5, LocalTime.parse("16:00"), seats5);

        Map<Integer, Integer> seats6 = new HashMap<>();
        seats6.put(1, 8);
        seats6.put(2, 12);
        seats6.put(3, 2);
        Train train6 = new Train(3, 6, LocalTime.parse("10:00"), seats6);

        ArrayList<Train> trains = new ArrayList<>();
//        Train testTrain = createTrain();
        trains.add(train1);
        trains.add(train2);
        trains.add(train3);
        trains.add(train4);
        trains.add(train5);
        trains.add(train6);
        System.out.println(trains);

        System.out.println("Criteria 1: trains going to Lviv: \n" + getTrainsByCriteria1(1, trains));
        System.out.println("Criteria 2: trains going to Lviv after 11:00: \n" + getTrainsByCriteria2(1, LocalTime.parse("11:00"), trains));
        System.out.println("Criteria 3: trains going to Lviv and have free seats: \n" + getTrainsByCriteria3(1, trains));

    }

    static ArrayList<Train> getTrainsByCriteria1(int destinationId, ArrayList<Train> trains) {
        ArrayList<Train> matchingTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getDestinationId() == destinationId) {
                matchingTrains.add(train);
            }
        }
        return matchingTrains;
    }

    // Find trains by destination and after departure time
    static ArrayList<Train> getTrainsByCriteria2(int destinationId, LocalTime startingDepartureTime, ArrayList<Train> trains) {
        ArrayList<Train> matchingTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getDestinationId() == destinationId) {
                if (train.getDepartureTime().isAfter(startingDepartureTime)) {
                    matchingTrains.add(train);
                }
            }
        }
        return matchingTrains;
    }

    // Find trains by destination and have free places
    static ArrayList<Train> getTrainsByCriteria3(int destinationId, ArrayList<Train> trains) {
        ArrayList<Train> matchingTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getDestinationId() == destinationId) {
                if (train.calculateSeats() > 0) {
                    matchingTrains.add(train);
                }
            }
        }
        return matchingTrains;
    }

    static Train createTrain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter destination ID (1: Lviv, 2: Kyiv, 3: Odesa): ");
        int destinationId = scanner.nextInt();
        if (destinationId < 1 || destinationId > 3) {
            throw new IllegalArgumentException("Invalid destination ID. Please enter 1, 2, or 3.");
        }

        System.out.println("Enter train ID: ");
        int trainId = scanner.nextInt();
        if (trainId <= 0) {
            throw new IllegalArgumentException("Train ID must be positive.");
        }

        System.out.println("Enter departure time (HH:MM format): ");
        String timeString = scanner.next();
        LocalTime departureTime = LocalTime.parse(timeString);

        System.out.println("Enter the number of coupe seats: ");
        int coupeSeats = scanner.nextInt();

        System.out.println("Enter the number of reserved seats: ");
        int reservedSeats = scanner.nextInt();

        System.out.println("Enter the number of premium seats: ");
        int premiumSeats = scanner.nextInt();

        if (coupeSeats < 0 || reservedSeats < 0 || premiumSeats < 0) {
            throw new IllegalArgumentException("Seat amount cannot be negative.");
        }

        // Create a map with the entered seat counts
        Map<Integer, Integer> seats = new HashMap<>();
        seats.put(1, coupeSeats);
        seats.put(2, reservedSeats);
        seats.put(3, premiumSeats);

        return new Train(destinationId, trainId, departureTime, seats);

    }
}