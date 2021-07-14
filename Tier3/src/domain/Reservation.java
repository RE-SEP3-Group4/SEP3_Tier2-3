package domain;

public class Reservation {
    int userID, date;

    public Reservation(int userID, int date) {
        this.userID = userID;
        this.date = date;
    }

    public int getUserID() {
        return userID;
    }

    public int getDate() {
        return date;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
