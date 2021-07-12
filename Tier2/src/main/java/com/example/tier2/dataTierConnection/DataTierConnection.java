package com.example.tier2.dataTierConnection;

import domain.SocketMessage;
import domain.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DataTierConnection {
    private final String HOST = "localhost";
    private final int PORT = 3000;

    private Socket socket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    public DataTierConnection() {}

    private void createSocket() {
        try {
            socket = new Socket(HOST, PORT);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User login(String username, String password) {
        SocketMessage socketMessage = new SocketMessage("login");
        socketMessage.setStr1(username);
        socketMessage.setStr2(password);
        createSocket();
        try {
            output.writeObject(socketMessage);
            User user = (User) input.readObject();
            socket.close();
            return user;
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

    public void register(String username, String password) {
        SocketMessage socketMessage = new SocketMessage("register");
        socketMessage.setStr1(username);
        socketMessage.setStr2(password);
        createSocket();
        try {
            output.writeObject(socketMessage);
        } catch (Exception e) {
            System.out.println(e);
            try {
                socket.close();
            } catch (IOException ee) {
                System.out.println(ee);
            }
        }
    }

    public void updateUser(int id, String username, String password) {
        SocketMessage socketMessage = new SocketMessage("updateUser");
        socketMessage.setInt1(id);
        socketMessage.setStr1(username);
        socketMessage.setStr2(password);
        createSocket();
        try {
            output.writeObject(socketMessage);
        } catch (Exception e) {
            System.out.println(e);
            try {
                socket.close();
            } catch (IOException ee) {
                System.out.println(ee);
            }
        }
    }
}
