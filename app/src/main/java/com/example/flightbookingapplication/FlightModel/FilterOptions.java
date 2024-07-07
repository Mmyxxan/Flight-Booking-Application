package com.example.flightbookingapplication.FlightModel;

public class FilterOptions {
    private String departureStartTime;
    private String departureEndTime;
    private String arrivalStartTime;
    private String arrivalEndTime;
    private int minPrice;
    private int maxPrice;
    private String sortOption;

    public FilterOptions(String departureStartTime, String departureEndTime, String arrivalStartTime, String arrivalEndTime, int minPrice, int maxPrice, String sortOption) {
        this.departureStartTime = departureStartTime;
        this.departureEndTime = departureEndTime;
        this.arrivalStartTime = arrivalStartTime;
        this.arrivalEndTime = arrivalEndTime;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.sortOption = sortOption;
    }

    public String getDepartureStartTime() {
        return departureStartTime;
    }

    public String getDepartureEndTime() {
        return departureEndTime;
    }

    public String getArrivalStartTime() {
        return arrivalStartTime;
    }

    public String getArrivalEndTime() {
        return arrivalEndTime;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public String getSortOption() {
        return sortOption;
    }
}
