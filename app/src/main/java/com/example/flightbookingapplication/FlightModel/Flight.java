package com.example.flightbookingapplication.FlightModel;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flight implements Parcelable {
    public String number;
    public String origin;
    public String destination;
    public String departure_date;
    public String departure_time;
    public String arrival_date;
    public String arrival_time;
    private FlightSeat[][] seats;

    public Flight(String number, String origin, String destination, String departure_date, String departure_time, FlightSeat[][] seats, String arrival_date, String arrival_time) {
        this.number = number;
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.seats = seats;
    }

    protected Flight(Parcel in) {
        number = in.readString();
        origin = in.readString();
        destination = in.readString();
        departure_date = in.readString();
        departure_time = in.readString();
        arrival_date = in.readString();
        arrival_time = in.readString();

        // Read the 2D array from the parcel
        int rows = in.readInt();
        int cols = in.readInt();
        seats = new FlightSeat[rows][cols];
        for (int i = 0; i < rows; i++) {
            in.readTypedArray(seats[i], FlightSeat.CREATOR);
        }
    }

    public static final Creator<Flight> CREATOR = new Creator<Flight>() {
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(number);
        parcel.writeString(origin);
        parcel.writeString(destination);
        parcel.writeString(departure_date);
        parcel.writeString(departure_time);
        parcel.writeString(arrival_date);
        parcel.writeString(arrival_time);

        // Write the 2D array to the parcel
        int rows = seats.length;
        int cols = seats[0].length;
        parcel.writeInt(rows);
        parcel.writeInt(cols);
        for (int i = 0; i < rows; i++) {
            parcel.writeTypedArray(seats[i], flags);
        }
    }

    // Other methods...

    public String getFlightNumber() {
        return number;
    }

    public FlightSeat[][] getSeats() {
        return seats;
    }

    public static boolean checkCityOrigin(String origin) {
        List<String> validCities = Arrays.asList("New York City", "London", "San Francisco", "Los Angeles", "Boston");
        return validCities.contains(origin);
    }

    public static ArrayList<String> getValidCities() {
        ArrayList<String> validCities = new ArrayList<>();
        validCities.add("New York City");
        validCities.add("London");
        validCities.add("San Francisco");
        validCities.add("Los Angeles");
        validCities.add("Boston");
        return validCities;
    }

    public static String[] validCities = {"New York City", "London", "San Francisco", "Los Angeles", "Boston"};

    public static boolean checkCityDestination(String destination) {
        List<String> validCities = Arrays.asList("New York City", "London", "San Francisco", "Los Angeles", "Boston");
        return validCities.contains(destination);
    }

    public static boolean checkDepartureDate(String departure_date) {
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

    public static boolean checkArrivalDate(String departure_date, String arrival_date) {
        LocalDate currentDate = null;
        LocalDate arrivalDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }
        LocalDate departureDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            departureDate = LocalDate.parse(departure_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            arrivalDate = LocalDate.parse(arrival_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return !departureDate.isBefore(currentDate) && departureDate.isBefore(arrivalDate);
        }
        return false;
    }

    public boolean checkTime() {
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
        LocalTime time = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            time = LocalTime.parse(departure_time, DateTimeFormatter.ofPattern("HH:mm"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        }
        return null;
    }

    public static String abbreviated_city(String city) {
        Map<String, String> cityAbbreviations = new HashMap<>();
        cityAbbreviations.put("New York City", "NYC");
        cityAbbreviations.put("London", "LDN");
        cityAbbreviations.put("San Francisco", "SFO");
        cityAbbreviations.put("Los Angeles", "LAX");
        cityAbbreviations.put("Boston", "BOS");

        return cityAbbreviations.getOrDefault(city, city);
    }
}
