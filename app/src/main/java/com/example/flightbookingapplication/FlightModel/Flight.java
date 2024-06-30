package com.example.flightbookingapplication.FlightModel;

import android.os.Build;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flight {
    public String number;
    public String origin;
    public String destination;
    public String departure_date;
    public String departure_time;

    public String arrival_date;
    public String arrival_time;
    private FlightSeat[] seats;

    public String getFlightNumber() {
        return number;
    }

    public FlightSeat[] getSeats() {
        return seats;
    }

    public Flight(String number, String origin, String destination, String departure_date, String departure_time, FlightSeat[] seats, String arrival_date, String arrival_time) {
        this.number = number;
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.seats = seats;
    }

    public boolean checkCity() {
        // Define a list of valid cities
        List<String> validCities = Arrays.asList("NYC", "LDN", "SFO", "LAX", "BOS");
        return validCities.contains(origin) && validCities.contains(destination);
    }


    public boolean checkDate() {
        LocalDate currentDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }
        LocalDate departureDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            departureDate = LocalDate.parse(departure_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return !departureDate.isBefore(currentDate);
        }
        return false;
    }


    public boolean checkTime() {
        // Assuming flights operate between 5 AM and 11 PM
        LocalTime startTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startTime = LocalTime.of(5, 0);
        }
        LocalTime endTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            endTime = LocalTime.of(23, 0);
        }
        LocalTime departureTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            departureTime = LocalTime.parse(departure_time, DateTimeFormatter.ofPattern("HH:mm"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return !departureTime.isBefore(startTime) && !departureTime.isAfter(endTime);
        }
        return false;
    }


    public String formatted_date() {
        LocalDate date = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date = LocalDate.parse(departure_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        }
        return null;
    }

    public String formatted_time() {
        // example 10:00 AM
        LocalTime time = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            time = LocalTime.parse(departure_time, DateTimeFormatter.ofPattern("HH:mm"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        }
        return null;
    }

    public String abbreviated_city() {
        Map<String, String> cityAbbreviations = new HashMap<>();
        cityAbbreviations.put("New York City", "NYC");
        cityAbbreviations.put("London", "LDN");
        cityAbbreviations.put("San Francisco", "SFO");
        cityAbbreviations.put("Los Angeles", "LAX");
        cityAbbreviations.put("Boston", "BOS");

        String originAbbreviation = cityAbbreviations.getOrDefault(origin, origin);
        String destinationAbbreviation = cityAbbreviations.getOrDefault(destination, destination);

        return originAbbreviation + " - " + destinationAbbreviation;
    }


}
