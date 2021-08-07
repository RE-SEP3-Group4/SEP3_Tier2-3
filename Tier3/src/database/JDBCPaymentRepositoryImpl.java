package database;

import domain.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPaymentRepositoryImpl extends JDBCRepository implements PaymentRepository {
    @Override
    public List<Payment> getPayments(int userID) {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM payments WHERE userID = ?");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                payments.add(new Payment(resultSet.getInt("id"),
                        resultSet.getString("startDate"),
                        resultSet.getString("endDate")));
            }
            return payments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment createPayment(int userID, String startDate, String endDate) {
        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO payments(userID, startDate, endDate) VALUES(?,?,?)");
            statement.setInt(1, userID);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.executeUpdate();
            return new Payment(userID, startDate, endDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deletePayment(Payment payment) {
        return false;
    }
}
