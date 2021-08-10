package network;

import com.google.gson.Gson;
import database.ReservationRepository;
import domain.Reservation;
import lombok.AllArgsConstructor;

import static network.Response.ResponseStatus.ERROR;
import static network.Response.ResponseStatus.OK;

@AllArgsConstructor
public class ReservationController implements Controller {
    private ReservationRepository repo;
    private Gson gson;

    @Override
    public Response handle(Request req) {
        Reservation reservation = null;
        Object resp = null;

        switch (req.getOperation()) {
            case GET:
                reservation = gson.fromJson(req.getPayload(), Reservation.class);
                resp = repo.getReservations(reservation.getUserID());
                break;

            case CREATE:
                reservation = gson.fromJson(req.getPayload(), Reservation.class);
                resp = repo.createReservation(reservation.getUserID(),
                        reservation.getDate(),
                        reservation.getHour());
                break;

            case DELETE:
                reservation = gson.fromJson(req.getPayload(), Reservation.class);
                resp = repo.deleteReservation(reservation);
                break;

            default:
                throw new UnsupportedOperationException(req.getOperation() + " not supported");
        }
        return new Response(OK, gson.toJson(resp));
    }
}
