package database;

import domain.Payment;
import domain.Reservation;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
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
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return null;
    }

    @Override
    public List<Reservation> getReservations(int userID) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations WHERE userid = ?");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            disconnect();
            while (resultSet.next()) {
                int uID = resultSet.getInt(1);
                int date = resultSet.getInt(2);
                reservations.add(new Reservation(uID, date));
            }
            return reservations;
        } catch (SQLException e) {
            System.out.println("There was an error getting the reservations!" + e);
        }
        return null;
    }

    @Override
    public List<Reservation> createReservation(int userID, int date) throws SQLException {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reservation(userid, date) VALUES(?,?)");
            statement.setInt(1, userID);
            statement.setInt(2, date);
            statement.execute();
            disconnect();
            return getReservations(userID);
        } catch (SQLException e) {
            System.out.println("There was an error creating the reservation!" + e);
        }
        return null;
    }

    @Override
    public boolean deleteReservation(Reservation reservation) throws SQLException {
        return false;
    }

    @Override
    public List<Payment> getPayments(int userID) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM payments WHERE userid = ?");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            disconnect();
            while (resultSet.next()) {
                int uID = resultSet.getInt(1);
                int date = resultSet.getInt(2);
                int period = resultSet.getInt(3);
                payments.add(new Payment(uID, date, period));
            }
            return payments;
        } catch (SQLException e) {
            System.out.println("There was an error getting the payments!" + e);
        }
        return null;
    }

    @Override
    public List<Payment> createPayment(int userID, int date, int period) throws SQLException {
        try {
            connect();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO payments(userid, date, period) VALUES(?,?,?)");
            statement.setInt(1, userID);
            statement.setInt(2, date);
            statement.execute();
            disconnect();
            return getPayments(userID);
        } catch (SQLException e) {
            System.out.println("There was an error creating the payment!" + e);
        }
        return null;
    }

    @Override
    public boolean deletePayment(Payment payment) throws SQLException {
        return false;
    }
}

