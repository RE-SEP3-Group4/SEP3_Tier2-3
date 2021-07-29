package com.example.tier2.presentationTierConnection;

import com.example.tier2.dataTierConnection.PaymentDataTierConnection;
import com.example.tier2.dataTierConnection.ReservationDataTierConnection;
import com.example.tier2.dataTierConnection.UserDataTierConnection;
import domain.Payment;
import domain.Reservation;
import domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebService {
    private UserDataTierConnection userDataTierConnection;
    private ReservationDataTierConnection reservationDataTierConnection;
    private PaymentDataTierConnection paymentDataTierConnection;

    public WebService() {
        this.userDataTierConnection = new UserDataTierConnection();
        this.reservationDataTierConnection = new ReservationDataTierConnection();
        this.paymentDataTierConnection = new PaymentDataTierConnection();
    }

    // Methods related to the User class.
    @GetMapping("/user")
    public User login(@RequestParam String username, @RequestParam String password) { return userDataTierConnection.login(username,password); }
    @PostMapping("/user")
    public boolean register(@RequestBody User user) { return userDataTierConnection.register(user.getUsername(), user.getPassword()); }
    @PutMapping("/user")
    public boolean updateUser(@RequestParam int id, @RequestBody User user) { return userDataTierConnection.updateUser(id, user.getUsername(), user.getPassword()); }
    @DeleteMapping("/user")
    public boolean deleteUser(@RequestParam int id) { return userDataTierConnection.deleteUser(id); }
    @GetMapping("/users")
    public List<User> getAllUsers() { return userDataTierConnection.getAllUsers(); }

    // Methods related to the Reservation class.
    @GetMapping("/reservation")
    public List<Reservation> getReservations(@RequestParam int userID) { return reservationDataTierConnection.getReservations(userID); }
    @PostMapping("/reservation")
    public boolean createReservation(@RequestBody Reservation reservation) { return reservationDataTierConnection.createReservation(reservation.getUserID(), reservation.getDate(), reservation.getHour()); }
    @PutMapping("/reservation")
    public boolean deleteReservation(@RequestBody Reservation reservation) { return reservationDataTierConnection.deleteReservation(reservation); }

    // Methods related to the Payment class.
    @GetMapping("/payment")
    public List<Payment> getPayments(@RequestParam int userID) { return paymentDataTierConnection.getPayments(userID); }
    @PostMapping("/payment")
    public boolean createPayment(@RequestBody Payment payment) { return paymentDataTierConnection.createPayment(payment.getUserID(), payment.getStartDate(), payment.getEndDate()); }
    @PutMapping("/payment")
    public boolean deletePayment(@RequestBody Payment payment) { return paymentDataTierConnection.deletePayment(payment); }
}
