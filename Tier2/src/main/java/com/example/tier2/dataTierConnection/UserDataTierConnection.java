package com.example.tier2.dataTierConnection;

import domain.SocketMessage;
import domain.User;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class UserDataTierConnection {
    private final String HOST = "localhost";
    private final int PORT = 3000;

    private Socket socket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    public UserDataTierConnection() {}

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

    public boolean register(String username, String password) {
        SocketMessage socketMessage = new SocketMessage("register");
        socketMessage.setStr1(username);
        socketMessage.setStr2(password);
        createSocket();
        try {
            output.writeObject(socketMessage);
            User user = (User) input.readObject();
            socket.close();
            if (user == null) {
                return false;
            } else if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
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

    public boolean updateUser(int id, String username, String password) {
        SocketMessage socketMessage = new SocketMessage("updateUser");
        socketMessage.setInt1(id);
        socketMessage.setStr1(username);
        socketMessage.setStr2(password);
        createSocket();
        try {
            output.writeObject(socketMessage);
            User user = (User) input.readObject();
            socket.close();
            if (user == null) {
                return false;
            } else if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
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

    public boolean deleteUser(int id) {
        SocketMessage socketMessage = new SocketMessage("deleteUser");
        socketMessage.setInt1(id);
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

    public List<User> getAllUsers() {
        SocketMessage socketMessage = new SocketMessage("getAllUsers");
        createSocket();
        try {
            output.writeObject(socketMessage);
            List<User> users = (List<User>) input.readObject();
            socket.close();
            return users;
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
}
