package com.example.flightbookingapplication.FlightModel;

import java.util.ArrayList;

public class FlightContainer {
    private ArrayList<Flight> flights;
    public FlightContainer() {
        flights = new ArrayList<Flight>();
    }
    public void addFlight(Flight flight) {
        flights.add(flight);
    }
    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

}
