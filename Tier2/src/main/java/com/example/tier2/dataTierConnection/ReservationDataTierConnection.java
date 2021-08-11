package com.example.tier2.dataTierConnection;

import com.example.tier3.network.Request;
import com.example.tier3.network.Response;
import com.google.gson.Gson;
import com.example.tier3.domain.Reservation;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.example.tier3.network.Request.RequestOperation.*;

@Component
public class ReservationDataTierConnection {
    private final String HOST = "localhost";
    private final int PORT = 3000;

    private Socket socket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    private String addr = "reservations";
    private Gson serializer = new Gson();

    public ReservationDataTierConnection() {createSocket();}

    private void createSocket() {
        try {
            socket = new Socket(HOST, PORT);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservation createReservation(int userID, String date, String hour) {
        try {
            Reservation payload = new Reservation(userID, date, hour);
            Request req = new Request(addr, CREATE, serializer.toJson(payload));
            output.writeObject(req);
            Response resp = (Response) input.readObject();

            return serializer.fromJson(resp.getPayload(), Reservation.class);

        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteReservation(Reservation reservation) {
        try {
            Request req = new Request(addr, DELETE, serializer.toJson(reservation));
            output.writeObject(req);
            Response resp = (Response) input.readObject();

            return serializer.fromJson(resp.getPayload(), boolean.class);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
