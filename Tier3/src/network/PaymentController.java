package network;

import com.google.gson.Gson;
import database.PaymentRepository;
import domain.Payment;
import lombok.AllArgsConstructor;

import static network.Response.ResponseStatus.ERROR;
import static network.Response.ResponseStatus.OK;

@AllArgsConstructor
public class PaymentController implements Controller{
    private PaymentRepository repo;
    private Gson gson;

    @Override
    public Response handle(Request req) {
        Payment payment = null;
        Object resp = null;

        switch (req.getOperation()) {
            case GET:
                payment = gson.fromJson(req.getPayload(), Payment.class);
                resp = repo.getPayments(payment.getUserID());
                break;

            case CREATE:
                payment = gson.fromJson(req.getPayload(), Payment.class);
                resp = repo.createPayment(payment.getUserID(), payment.getStartDate(),
                        payment.getEndDate());
                break;

            case DELETE:
                payment = gson.fromJson(req.getPayload(), Payment.class);
                resp = repo.deletePayment(payment);
                break;

            default:
                throw new UnsupportedOperationException(req.getOperation() + " not supported");
        }

        return new Response(OK, gson.toJson(resp));
    }
}
