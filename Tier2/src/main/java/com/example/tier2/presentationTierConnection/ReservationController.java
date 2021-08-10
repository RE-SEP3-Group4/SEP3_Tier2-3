package com.example.tier2.presentationTierConnection;

import com.example.tier2.dataTierConnection.ReservationDataTierConnection;
import domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationDataTierConnection reservationDataTierConnection;

    @PostMapping("/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationDataTierConnection.createReservation(reservation.getUserID(), reservation.getDate(), reservation.getHour());
    }

    @PutMapping("/reservations")
    public boolean deleteReservation(@RequestParam int userID, @RequestParam String date, @RequestParam String hour) {
        return reservationDataTierConnection.deleteReservation(new Reservation(userID, date, hour));
    }

}
