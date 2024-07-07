package com.example.flightbookingapplication.FlightModel;

public class TimeRange {
    String startTime;
    String endTime;

    public TimeRange(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
