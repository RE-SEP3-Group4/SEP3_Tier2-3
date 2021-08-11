package com.example.tier2.dataTierConnection;

import com.example.tier3.network.Request;
import com.example.tier3.network.Response;
import com.google.gson.Gson;
import com.example.tier3.domain.Payment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.example.tier3.network.Request.RequestOperation.CREATE;
import static com.example.tier3.network.Request.RequestOperation.DELETE;

@Component
public class PaymentDataTierConnection {
    private final String HOST = "localhost";
    private final int PORT = 3000;

    private Socket socket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    private String addr = "payments";
    private Gson serializer = new Gson();

    public PaymentDataTierConnection() {createSocket();}

    private void createSocket() {
        try {
            socket = new Socket(HOST, PORT);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Payment createPayment(int userID, String startDate, String endDate) {
        try {
            Payment payload = new Payment(userID, startDate, endDate);
            Request req = new Request(addr, CREATE, serializer.toJson(payload));
            output.writeObject(req);
            Response resp = (Response) input.readObject();

            return serializer.fromJson(resp.getPayload(), Payment.class);

        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletePayment(Payment payment) {
        try {
            Request req = new Request(addr, DELETE, serializer.toJson(payment));
            output.writeObject(req);
            Response resp = (Response) input.readObject();

            return serializer.fromJson(resp.getPayload(), boolean.class);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
