package database;

import domain.User;

public class DatabaseAccess implements DatabaseAccessInterface {
    @Override
    public synchronized User login(String username, String password) {
        User user;
        if (username.equals("Test") && password.equals("Test")) {
            return new User(1, username, password, 1);
        }
        return null;
    }
}
