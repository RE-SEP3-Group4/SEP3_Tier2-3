package com.example.tier2.dataTierConnection;

import domain.Payment;
import domain.Reservation;
import domain.SocketMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class PaymentDataTierConnection {
    private final String HOST = "localhost";
    private final int PORT = 3000;

    private Socket socket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    public PaymentDataTierConnection() {}

    private void createSocket() {
        try {
            socket = new Socket(HOST, PORT);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getPayments(int userID) {
        SocketMessage socketMessage = new SocketMessage("getPayments");
        socketMessage.setInt1(userID);
        createSocket();
        try {
            output.writeObject(socketMessage);
            List<Payment> payments = (List<Payment>) input.readObject();
            socket.close();
            return payments;
        } catch (Exception e) {
            System.out.println(e);
            try {
                socket.close();
            } catch (IOException ee) {
                System.out.println(ee);
            }
        }
        return null;
    }

    public boolean createPayment(int userID, int date, int period) {
        SocketMessage socketMessage = new SocketMessage("createPayment");
        socketMessage.setInt1(userID);
        socketMessage.setInt2(date);
        socketMessage.setInt3(period);
        createSocket();
        try {
            output.writeObject(socketMessage);
            List<Payment> payments = (List<Payment>) input.readObject();
            socket.close();
            if (payments == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            try {
                socket.close();
            } catch (IOException ee) {
                System.out.println(ee);
            }
        }
        return false;
    }

    public boolean deletePayment(Payment payment) {
        SocketMessage socketMessage = new SocketMessage("deletePayment");
        socketMessage.setPayment(payment);
        createSocket();
        try {
            output.writeObject(socketMessage);
            socket.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            try {
                socket.close();
            } catch (IOException ee) {
                System.out.println(ee);
            }
        }
        return false;
    }
}
