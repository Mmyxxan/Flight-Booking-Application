package com.example.flightbookingapplication.FlightModel;

import java.util.ArrayList;
import java.util.List;

public class FlightData {
    private static FlightData instance;
    private List<Flight> flights;

    private FlightData() {
        flights = new ArrayList<>();
        generateSampleData();
    }

    public static synchronized FlightData getInstance() {
        if (instance == null) {
            instance = new FlightData();
        }
        return instance;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    private void generateSampleData() {
        FlightSeat[][] seats1 = new FlightSeat[7][4];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                seats1[i][j] = new FlightSeat(i * 4 + j + 1, (i < 2) ? 1 : 2, (i < 2) ? 200 : 150);
            }
        }

        FlightSeat[][] seats2 = new FlightSeat[7][4];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                seats2[i][j] = new FlightSeat(i * 4 + j + 1, (i < 2) ? 1 : 2, (i < 2) ? 250 : 180);
            }
        }

        FlightSeat[][] seats3 = new FlightSeat[7][4];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                seats3[i][j] = new FlightSeat(i * 4 + j + 1, (i < 2) ? 1 : 2, (i < 2) ? 220 : 170);
            }
        }

        flights.add(new Flight("F123", "New York City", "London", "2024-08-10", "10:00", seats1, "2024-08-10", "22:00"));
        flights.add(new Flight("F456", "San Francisco", "Los Angeles", "2024-09-15", "14:00", seats2, "2024-09-15", "16:00"));
        flights.add(new Flight("F789", "Boston", "San Francisco", "2024-10-20", "09:00", seats3, "2024-10-20", "18:00"));
    }
}
