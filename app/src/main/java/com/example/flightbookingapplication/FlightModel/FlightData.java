package com.example.flightbookingapplication.FlightModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FlightData {
    private static FlightData instance;
    private Map<Integer, FlightContainer> flightContainers;
    private Random random;

    private FlightData(long seed) {
        flightContainers = new HashMap<>();
        random = new Random(seed);
        generateSampleDataForJuly();
    }

    public static synchronized FlightData getInstance(long seed) {
        if (instance == null) {
            instance = new FlightData(seed);
        }
        return instance;
    }

    public Map<Integer, FlightContainer> getFlightContainers() {
        return flightContainers;
    }

    private void generateSampleDataForJuly() {
        for (int day = 1; day <= 31; day++) {
            FlightContainer flightContainer = new FlightContainer();
            int numberOfFlights = random.nextInt(4) + 1; // Between 1 and 4 flights

            for (int i = 0; i < numberOfFlights; i++) {
                Flight flight = generateRandomFlight(day);
                flightContainer.addFlight(flight);
            }

            flightContainers.put(day, flightContainer);
        }
    }

    private Flight generateRandomFlight(int day) {
        String[] destinations = {"New York City", "London", "San Francisco", "Los Angeles", "Boston"};
        String[] origins = {"Boston", "San Francisco", "Los Angeles", "New York City", "London"};

        String origin = origins[random.nextInt(origins.length)];
        String destination;
        do {
            destination = destinations[random.nextInt(destinations.length)];
        } while (origin.equals(destination));

        String flightNumber = "F" + (random.nextInt(900) + 100);
        String date = String.format("2024-07-%02d", day);
        String departureTime = String.format("%02d:00", random.nextInt(24));
        String arrivalTime = String.format("%02d:00", (random.nextInt(24 - Integer.parseInt(departureTime.split(":")[0])) + Integer.parseInt(departureTime.split(":")[0])) % 24);

        FlightSeat[][] seats = new FlightSeat[7][4];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                seats[i][j] = new FlightSeat(i * 4 + j + 1, (i < 2) ? 1 : 2, (i < 2) ? 200 : 150);
            }
        }

        return new Flight(flightNumber, origin, destination, date, departureTime, seats, date, arrivalTime);
    }
}


//public class Main {
//    public static void main(String[] args) {
//        long seed = 12345L;
//        FlightData flightData = FlightData.getInstance(seed);
//
//        FlightContainer flightContainerForDay10 = flightData.getFlightContainers().get(10); // Get flights for July 10th
//        List<Flight> flightsForDay10 = flightContainerForDay10.getFlights();
//
//        for (Flight flight : flightsForDay10) {
//            System.out.println(flight.getFlightNumber());
//        }
//    }
//}

