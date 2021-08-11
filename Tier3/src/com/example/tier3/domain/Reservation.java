package com.example.tier3.domain;

public class Reservation {
    private int userID;
    private String date, hour;

    public Reservation(int userID, String date, String hour) {
        this.userID = userID;
        this.date = date;
        this.hour = hour;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
