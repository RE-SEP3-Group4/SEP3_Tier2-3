package database;

import domain.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentRepository {
    List<Payment> getPayments(int userID);
    Payment createPayment(int userID, String startDate, String endDate);
    boolean deletePayment(Payment payment);
}
