package database;

import domain.Payment;
import domain.Reservation;
import domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseAccessInterface {
    Connection connect() throws SQLException;
    void disconnect() throws SQLException;

    User login(String username, String password) throws SQLException;
    User register(String username, String password) throws SQLException;
    User updateUser(int id, String username, String password) throws SQLException;

    List<Reservation> getReservations(int userID) throws SQLException;
    List<Reservation> createReservation(int userID, int date) throws SQLException;

    List<Payment> getPayments(int userID) throws SQLException;
    List<Payment> createPayment(int userID, int date, int period) throws SQLException;
}
