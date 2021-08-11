package com.example.tier2.presentationTierConnection;

import com.example.tier2.dataTierConnection.PaymentDataTierConnection;
import com.example.tier3.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    private PaymentDataTierConnection paymentDataTierConnection;

    @PostMapping("/payments")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentDataTierConnection.createPayment(payment.getUserID(), payment.getStartDate(), payment.getEndDate());
    }

    @PutMapping("/payments")
    public boolean deletePayment(@RequestParam int userID, @RequestParam String startDate, @RequestParam String endDate) {
        return paymentDataTierConnection.deletePayment(new Payment(userID, startDate, endDate));
    }
}