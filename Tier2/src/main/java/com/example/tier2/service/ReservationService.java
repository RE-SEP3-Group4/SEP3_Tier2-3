package com.example.tier2.service;

import com.example.tier2.dataTierConnection.ReservationDataTierConnection;
import com.example.tier2.service.dto.CreateReservationDTO;
import com.example.tier3.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationDataTierConnection connection;

    public Reservation createReservation(CreateReservationDTO dto) {
        return connection.createReservation(dto.getUserID(), dto.getDate(), dto.getHour());
    }

    public boolean deleteReservation(Reservation reservation) {
        return connection.deleteReservation(reservation);
    }
}
