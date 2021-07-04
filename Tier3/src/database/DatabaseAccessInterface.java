package database;

import domain.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseAccessInterface {
    User login(String username, String password);
}
