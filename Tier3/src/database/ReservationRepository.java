package database;

import domain.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface ReservationRepository {
    Reservation createReservation(int userID, String date, String hour);
    List<Reservation> getReservations(int userID);
    boolean deleteReservation(Reservation reservation);
}
