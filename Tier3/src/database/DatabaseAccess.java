package database;

import domain.User;

import java.sql.*;

public class DatabaseAccess implements DatabaseAccessInterface{
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "98765";
    private Connection connection;

    @Override
    public Connection connect() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) { e.printStackTrace(); }
        return connection;
    }

    @Override
    public void disconnect() throws SQLException {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public synchronized User login(String username, String password) throws SQLException {
        User user;
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            disconnect();
            while (resultSet.next()) {
                int personID = resultSet.getInt(1);
                int securityLevel = resultSet.getInt(4);
                user = new User(personID, username, password, securityLevel);
                return user;
            }
        } catch (SQLException e) {
            System.out.println("There was an error logging in!" + e);
        }
        return null;
    }
}

