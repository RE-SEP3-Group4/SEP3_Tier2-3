package com.example.tier3.database;

import com.example.tier3.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    Reservation createReservation(int userID, String date, String hour);
    List<Reservation> getReservations(int userID);
    boolean deleteReservation(Reservation reservation);
}
