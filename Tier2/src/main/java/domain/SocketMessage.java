package domain;

import java.io.Serializable;

public class SocketMessage implements Serializable {
    private User user;
    private String cmd, str1, str2;
    private int int1, int2;

    public SocketMessage(String cmd) { this.cmd = cmd; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public int getInt1() { return int1; }

    public void setInt1(int int1) { this.int1 = int1; }

    public int getInt2() { return int2; }

    public void setInt2(int int2) { this.int2 = int2; }
}
