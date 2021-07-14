package domain;

public class Payment {
    int userID, date, period;

    public Payment(int userID, int date, int period) {
        this.userID = userID;
        this.date = date;
        this.period = period;
    }

    public int getUserID() {
        return userID;
    }

    public int getDate() {
        return date;
    }

    public int getPeriod() {
        return period;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
