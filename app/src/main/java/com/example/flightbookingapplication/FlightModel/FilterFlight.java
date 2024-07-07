package com.example.flightbookingapplication.FlightModel;

import java.util.*;

public class FilterFlight {
    public String[] options = {"Arrival time", "Departure time", "Price", "Duration"};

    private int classType;
    private FilterOptions filterOptions;

    public FilterOptions getFilterOptions() {
        return filterOptions;
    }

    public void setFilterOptions(FilterOptions filterOptions) {
        this.filterOptions = filterOptions;
    }

    public FilterFlight(int classType) {
        this.classType = classType;
        this.filterOptions = new FilterOptions("06:00", "12:00", "00:00", "06:00", 0, 500, options[2]);
    }

    public FlightContainer filterFlights(FlightContainer flights) {
        FlightContainer filteredFlights = new FlightContainer();

        // Filter by departure time
        filteredFlights = filterByDepartureTime(flights, filterOptions.getDepartureStartTime(), filterOptions.getDepartureEndTime());

        // Filter by arrival time
        filteredFlights = filterByArrivalTime(filteredFlights, filterOptions.getArrivalStartTime(), filterOptions.getArrivalEndTime());

        // Filter by price
        filteredFlights = filterByPrice(filteredFlights, filterOptions.getMinPrice(), filterOptions.getMaxPrice());

        // Sort by the selected option
        filteredFlights = filterBySortOption(filteredFlights, filterOptions.getSortOption());

        return filteredFlights;
    }

    private FlightContainer filterByDepartureTime(FlightContainer flights, String startTime, String endTime) {
        FlightContainer filteredFlights = new FlightContainer();
        for (Flight flight : flights.getFlights()) {
            if (isTimeInRange(flight.getDepartureTime(), startTime, endTime)) {
                filteredFlights.addFlight(flight);
            }
        }
        return filteredFlights;
    }

    private FlightContainer filterByArrivalTime(FlightContainer flights, String startTime, String endTime) {
        FlightContainer filteredFlights = new FlightContainer();
        for (Flight flight : flights.getFlights()) {
            if (isTimeInRange(flight.getArrivalTime(), startTime, endTime)) {
                filteredFlights.addFlight(flight);
            }
        }
        return filteredFlights;
    }

    private FlightContainer filterByPrice(FlightContainer flights, int minPrice, int maxPrice) {
        FlightContainer filteredFlights = new FlightContainer();
        for (Flight flight : flights.getFlights()) {
            int price = (classType == 1) ? flight.getHighestPrice() : flight.getCheapestPrice();
            if (price >= minPrice && price <= maxPrice) {
                filteredFlights.addFlight(flight);
            }
        }
        return filteredFlights;
    }

    private FlightContainer filterBySortOption(FlightContainer flights, String sortOption) {
        List<Flight> sortedFlights = new ArrayList<>(flights.getFlights());
        switch (sortOption) {
            case "Arrival time":
                sortedFlights.sort(Comparator.comparing(Flight::getArrivalTime));
                break;
            case "Departure time":
                sortedFlights.sort(Comparator.comparing(Flight::getDepartureTime));
                break;
            case "Price":
                if (classType == 1) {
                    sortedFlights.sort(Comparator.comparing(Flight::getHighestPrice));
                } else {
                    sortedFlights.sort(Comparator.comparing(Flight::getCheapestPrice));
                }
                break;
            case "Duration":
                sortedFlights.sort(Comparator.comparing(Flight::getDuration));
                break;
        }
        FlightContainer sortedFlightContainer = new FlightContainer();
        for (Flight flight : sortedFlights) {
            sortedFlightContainer.addFlight(flight);
        }
        return sortedFlightContainer;
    }

    private boolean isTimeInRange(String time, String startTime, String endTime) {
        int timeInt = Integer.parseInt(time.replace(":", ""));
        int startTimeInt = Integer.parseInt(startTime.replace(":", ""));
        int endTimeInt = Integer.parseInt(endTime.replace(":", ""));
        return timeInt >= startTimeInt && timeInt <= endTimeInt;
    }
}
