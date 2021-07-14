package database;

import domain.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseAccessInterface {
    Connection connect() throws SQLException;
    void disconnect() throws SQLException;

    User login(String username, String password) throws SQLException;
    User register(String username, String password) throws SQLException;
    User updateUser(int id, String username, String password) throws SQLException;
}
