package com.example.tier3.database;

import com.example.tier3.domain.Payment;

import java.util.List;

public interface PaymentRepository {
    List<Payment> getPayments(int userID);
    Payment createPayment(int userID, String startDate, String endDate);
    boolean deletePayment(Payment payment);
}
