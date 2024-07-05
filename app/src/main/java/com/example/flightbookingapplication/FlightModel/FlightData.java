package com.example.flightbookingapplication.FlightModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightData {
    private static FlightData instance;
    private Map<Integer, FlightContainer> flightContainers;
    private Random random;
    private String startDate;

    private FlightData(long seed, String startDate) {
        flightContainers = new HashMap<>();
        random = new Random(seed);
        this.startDate = startDate;
        generateSampleDataForNext30Days();
    }

    public static synchronized FlightData getInstance(long seed, String startDate) {
        if (instance == null) {
            instance = new FlightData(seed, startDate);
        }
        return instance;
    }

    public Map<Integer, FlightContainer> getFlightContainers() {
        return flightContainers;
    }

    private void generateSampleDataForNext30Days() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(sdf.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        for (int day = 0; day < 30; day++) {
            FlightContainer flightContainer = new FlightContainer();
            int numberOfFlights = random.nextInt(4) + 1; // Between 1 and 4 flights

            for (int i = 0; i < numberOfFlights; i++) {
                Flight flight = generateRandomFlight(calendar);
                flightContainer.addFlight(flight);
            }

            flightContainers.put(day, flightContainer);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    private Flight generateRandomFlight(Calendar calendar) {
        String[] destinations = {"Tokyo", "London", "Boston"};
        String[] origins = {"Boston", "Tokyo", "London"};

        String origin = origins[random.nextInt(origins.length)];
        String destination;
        do {
            destination = destinations[random.nextInt(destinations.length)];
        } while (origin.equals(destination));

        String flightNumber = "F" + (random.nextInt(900) + 100);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());
        String departureTime = String.format("%02d:00", random.nextInt(24));
        String arrivalTime = String.format("%02d:00", (random.nextInt(24 - Integer.parseInt(departureTime.split(":")[0])) + Integer.parseInt(departureTime.split(":")[0])) % 24);

        int firstClassPrice = generateRandomPrice(true);
        int economyClassPrice = generateRandomPrice(false);

        FlightSeat[][] seats = new FlightSeat[7][4];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                int price = (i < 2) ? firstClassPrice : economyClassPrice;
                seats[i][j] = new FlightSeat(i * 4 + j + 1, (i < 2) ? 1 : 2, price);
            }
        }

        return new Flight(flightNumber, origin, destination, date, departureTime, seats, date, arrivalTime);
    }

    private int generateRandomPrice(boolean isFirstClass) {
        if (isFirstClass) {
            return random.nextInt(201) + 300; // Prices between 300 and 500 for first class
        } else {
            return random.nextInt(101) + 100; // Prices between 100 and 200 for economy class
        }
    }

    public static String getDayAbbreviation(String dayOfWeek) {
        switch (dayOfWeek) {
            case "Sunday":
                return "SU";
            case "Monday":
                return "MO";
            case "Tuesday":
                return "TU";
            case "Wednesday":
                return "WE";
            case "Thursday":
                return "TH";
            case "Friday":
                return "FR";
            case "Saturday":
                return "SA";
            default:
                return "";
        }
    }
}
//public class Main {
//    public static void main(String[] args) {
//        long seed = 12345L;
//        String startDate = "2024-07-01";
//        FlightData flightData = FlightData.getInstance(seed, startDate);
//
//        FlightContainer flightContainerForDay10 = flightData.getFlightContainers().get(9); // Get flights for the 10th day from the start date
//        if (flightContainerForDay10 != null) {
//            List<Flight> flightsForDay10 = flightContainerForDay10.getFlights();
//
//            for (Flight flight : flightsForDay10) {
//                System.out.println(flight.getFlightNumber() + " - " + flight.getOrigin() + " to " + flight.getDestination());
//                FlightSeat[][] seats = flight.getSeats();
//                for (FlightSeat[] row : seats) {
//                    for (FlightSeat seat : row) {
//                        System.out.println("Seat " + seat.getSeatNumber() + ": $" + seat.getPrice());
//                    }
//                }
//            }
//        }
//    }
//}
