package domain;

import java.io.Serializable;

public class User implements Serializable {
    private String username, password;
    private Integer id, securityLevel;

    public User() {}

    public User(Integer id, String username, String password, Integer securityLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.securityLevel = securityLevel;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }
}
