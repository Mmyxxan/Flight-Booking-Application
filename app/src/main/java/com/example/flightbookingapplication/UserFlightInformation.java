package com.example.flightbookingapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class UserFlightInformation implements Parcelable {
    // Attributes
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private String passenger;
    private String baby;
    private String dog;
    private String luggage;
    private int classType;
    private int transportType;
    private String dayOfWeek;

    // Constructor
    public UserFlightInformation(String origin, String destination, String departureDate, String returnDate,
                                 String passenger, String baby, String dog, String luggage,
                                 int classType, int transportType, String dayOfWeek) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.passenger = passenger;
        this.baby = baby;
        this.dog = dog;
        this.luggage = luggage;
        this.classType = classType;
        this.transportType = transportType;
        this.dayOfWeek = dayOfWeek;
    }

    // Parcelable implementation
    protected UserFlightInformation(Parcel in) {
        origin = in.readString();
        destination = in.readString();
        departureDate = in.readString();
        returnDate = in.readString();
        passenger = in.readString();
        baby = in.readString();
        dog = in.readString();
        luggage = in.readString();
        classType = in.readInt();
        transportType = in.readInt();
        dayOfWeek = in.readString();
    }

    public static final Creator<UserFlightInformation> CREATOR = new Creator<UserFlightInformation>() {
        @Override
        public UserFlightInformation createFromParcel(Parcel in) {
            return new UserFlightInformation(in);
        }

        @Override
        public UserFlightInformation[] newArray(int size) {
            return new UserFlightInformation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(origin);
        dest.writeString(destination);
        dest.writeString(departureDate);
        dest.writeString(returnDate);
        dest.writeString(passenger);
        dest.writeString(baby);
        dest.writeString(dog);
        dest.writeString(luggage);
        dest.writeInt(classType);
        dest.writeInt(transportType);
        dest.writeString(dayOfWeek);
    }

    // Getters and Setters
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getBaby() {
        return baby;
    }

    public void setBaby(String baby) {
        this.baby = baby;
    }

    public String getDog() {
        return dog;
    }

    public void setDog(String dog) {
        this.dog = dog;
    }

    public String getLuggage() {
        return luggage;
    }

    public void setLuggage(String luggage) {
        this.luggage = luggage;
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public int getTransportType() {
        return transportType;
    }

    public void setTransportType(int transportType) {
        this.transportType = transportType;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
