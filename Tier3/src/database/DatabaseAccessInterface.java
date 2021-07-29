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
    boolean deleteUser(int id) throws SQLException;
    List<User> getAllUsers() throws SQLException;

    List<Reservation> getReservations(int userID) throws SQLException;
    List<Reservation> createReservation(int userID, String date, String hour) throws SQLException;
    boolean deleteReservation(Reservation reservation) throws SQLException;

    List<Payment> getPayments(int userID) throws SQLException;
    List<Payment> createPayment(int userID, String startDate, String endDate) throws SQLException;
    boolean deletePayment(Payment payment) throws SQLException;
}
