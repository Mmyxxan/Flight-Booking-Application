package com.example.flightbookingapplication.FlightModel;

public class FlightSeat {
    private int seatNumber;
    private boolean isAvailable;
    private int seatType;
    private int price;

    public FlightSeat(int seatNumber, int seatType, int price) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
        this.isAvailable = true;
    }
    // create setter, getter methods
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setAvailable() {
        this.isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }

    public int getSeatType() {
        return seatType;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
