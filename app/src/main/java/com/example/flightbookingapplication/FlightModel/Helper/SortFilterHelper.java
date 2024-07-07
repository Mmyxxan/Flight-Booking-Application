package com.example.flightbookingapplication.FlightModel.Helper;

import java.util.HashMap;

public class SortFilterHelper {
    public static HashMap<String, Integer> sortOptions;
    public static String[] options = {"Arrival time", "Departure time", "Price", "Lowest fare", "Duration"};
    public static int getSortOptionsFromString(String sortOption) {
        if (sortOptions == null) {
            sortOptions = new HashMap<>();
            for (int i = 0; i < options.length; i++) {
                sortOptions.put(options[i], i);
            }
        }
        return sortOptions.get(sortOption);
    }
}
