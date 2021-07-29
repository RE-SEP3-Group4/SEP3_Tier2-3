package com.example.tier2.dataTierConnection;

import domain.Reservation;
import domain.SocketMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ReservationDataTierConnection {
    private final String HOST = "localhost";
    private final int PORT = 3000;

    private Socket socket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    public ReservationDataTierConnection() {}

    private void createSocket() {
        try {
            socket = new Socket(HOST, PORT);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> getReservations(int userID) {
        SocketMessage socketMessage = new SocketMessage("getReservations");
        socketMessage.setInt1(userID);
        createSocket();
        try {
            output.writeObject(socketMessage);
            List<Reservation> reservations = (List<Reservation>) input.readObject();
            socket.close();
            return reservations;
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

    public boolean createReservation(int userID, String date, String hour) {
        SocketMessage socketMessage = new SocketMessage("createReservation");
        socketMessage.setInt1(userID);
        socketMessage.setStr1(date);
        socketMessage.setStr2(hour);
        createSocket();
        try {
            output.writeObject(socketMessage);
            List<Reservation> reservations = (List<Reservation>) input.readObject();
            socket.close();
            if (reservations == null) {
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

    public boolean deleteReservation(Reservation reservation) {
        SocketMessage socketMessage = new SocketMessage("deleteReservation");
        socketMessage.setReservation(reservation);
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
