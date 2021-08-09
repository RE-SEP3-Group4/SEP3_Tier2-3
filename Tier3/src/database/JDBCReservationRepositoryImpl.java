package database;

import domain.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCReservationRepositoryImpl extends JDBCRepository implements ReservationRepository {
    @Override
    public Reservation createReservation(int userID, String date, String hour) {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reservations(userid, date, hour) VALUES(?,?,?)");
            statement.setInt(1, userID);
            statement.setString(2, date);
            statement.setString(3, hour);
            statement.execute();
            return new Reservation(userID, date, hour);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reservation> getReservations(int userID) {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations WHERE userid = ?");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservations.add(new Reservation(resultSet.getInt("userID"),
                        resultSet.getString("date"),
                        resultSet.getString("hour")));
            }
            return reservations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteReservation(Reservation reservation) {
        try(Connection connection = connect()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM reservations WHERE userid = ? AND date = ? AND hour = ?");
            statement.setInt(1, reservation.getUserID());
            statement.setString(2, reservation.getDate());
            statement.setString(3, reservation.getHour());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
