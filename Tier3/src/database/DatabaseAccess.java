package database;

import domain.Payment;
import domain.Reservation;
import domain.User;

import java.sql.*;
import java.util.List;

public class DatabaseAccess implements DatabaseAccessInterface {
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

    @Override
    public User register(String username, String password) throws SQLException {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(username, password, securityLevel) VALUES(?,?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, 2);
            statement.execute();
            disconnect();
        } catch (SQLException e) {
            return null;
        }
        return login(username, password);
    }

    @Override
    public User updateUser(int id, String username, String password) throws SQLException {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ?, password = ? WHERE id = ?;");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, id);
            statement.executeUpdate();
            disconnect();
        } catch (SQLException e) {
            return null;
        }
        return login(username, password);
    }

    @Override
    public List<Reservation> getReservations(int userID) throws SQLException {
        return null;
    }

    @Override
    public List<Reservation> createReservation(int userID, int date) throws SQLException {
        return null;
    }

    @Override
    public List<Payment> getPayments(int userID) throws SQLException {
        return null;
    }

    @Override
    public List<Payment> createPayment(int userID, int date, int period) throws SQLException {
        return null;
    }
}

