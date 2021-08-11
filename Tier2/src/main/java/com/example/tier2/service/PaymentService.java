package com.example.tier2.service;

import com.example.tier2.dataTierConnection.PaymentDataTierConnection;
import com.example.tier2.service.dto.CreatePaymentDTO;
import com.example.tier3.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentDataTierConnection connection;

    public Payment createPayment(CreatePaymentDTO dto) {
        return connection.createPayment(dto.getUserID(), dto.getStartDate(), dto.getEndDate());
    }

    public boolean deletePayment(Payment payment) {
        return connection.deletePayment(payment);
    }
}
