package com.example.flightbookingapplication.FlightModel;

public class FilterHelper {

    private static final TimeRange[] TIME_RANGES = {
            new TimeRange("00:00", "06:00"),  // position 0
            new TimeRange("06:00", "12:00"),  // position 1
            new TimeRange("12:00", "18:00"),  // position 2
            new TimeRange("18:00", "23:59"),  // position 3
            new TimeRange("00:00", "23:59")   // position -1 (default)
    };

    public static int getPositionFromData(String startTime, String endTime) {
        for (int i = 0; i < TIME_RANGES.length; i++) {
            TimeRange timeRange = TIME_RANGES[i];
            if (timeRange.getStartTime().equals(startTime) && timeRange.getEndTime().equals(endTime)) {
                return i < 4 ? i : -1; // Map position 4 to -1
            }
        }
        // Handle invalid startTime and endTime if necessary
        return -1; // Or any other value indicating invalid input
    }
}
