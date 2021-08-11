package com.example.tier2.presentationTierConnection;

import com.example.tier2.dataTierConnection.ReservationDataTierConnection;
import com.example.tier3.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationDataTierConnection reservationDataTierConnection;

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationDataTierConnection.createReservation(reservation.getUserID(), reservation.getDate(), reservation.getHour());
    }

    @DeleteMapping
    public boolean deleteReservation(@RequestParam int userID, @RequestParam String date, @RequestParam String hour) {
        return reservationDataTierConnection.deleteReservation(new Reservation(userID, date, hour));
    }

}
