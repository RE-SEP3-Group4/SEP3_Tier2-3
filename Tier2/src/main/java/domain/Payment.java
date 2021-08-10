package domain;

import java.io.Serializable;

public class Payment implements Serializable {
    private int userID;
    private String startDate, endDate;

    public Payment(int userID, String startDate, String endDate) {
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
