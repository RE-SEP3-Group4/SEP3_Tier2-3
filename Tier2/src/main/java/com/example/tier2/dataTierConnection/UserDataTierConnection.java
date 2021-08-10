package com.example.tier2.dataTierConnection;

import com.google.gson.Gson;
import domain.SocketMessage;
import domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import static com.example.tier2.dataTierConnection.Request.RequestOperation.*;
import static com.example.tier2.dataTierConnection.Response.ResponseStatus.NOT_FOUND;

@Component
public class UserDataTierConnection {
    private final String HOST = "localhost";
    private final int PORT = 3000;

    private Socket socket;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    private String addr = "users";
    private Gson serializer = new Gson();

    public UserDataTierConnection() {
        createSocket();
    }

    private void createSocket() {
        try {
            socket = new Socket(HOST, PORT);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            User payload = new User(null, username, password, null);
            Request req = new Request(addr, GET, serializer.toJson(payload));
            output.writeObject(req);
            Response resp = (Response) input.readObject();

            if(resp.getStatus() == NOT_FOUND)
                throw new IllegalArgumentException("No such user");

            return serializer.fromJson(resp.getPayload(), User.class);

        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public User createUser(String username, String password) {
        try {
            User payload = new User(null, username, password, null);
            Request req = new Request(addr, CREATE, serializer.toJson(payload));
            output.writeObject(req);
            Response resp = (Response) input.readObject();

            if(resp.getStatus() == NOT_FOUND)
                throw new IllegalArgumentException("No such user");

            return serializer.fromJson(resp.getPayload(), User.class);

        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public User updateUser(int id, String username, String password) {
        try {
            User payload = new User(null, username, password, null);
            Request req = new Request(addr, UPDATE, serializer.toJson(payload));
            output.writeObject(req);
            Response resp = (Response) input.readObject();

            if(resp.getStatus() == NOT_FOUND)
                throw new IllegalArgumentException("No such user");

            return serializer.fromJson(resp.getPayload(), User.class);

        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(int id) {
        try {
            User payload = new User(id, null, null, null);
            Request req = new Request(addr, DELETE, serializer.toJson(payload));
            output.writeObject(req);
            Response resp = (Response) input.readObject();

            if(resp.getStatus() == NOT_FOUND)
                throw new IllegalArgumentException("No such user");

            return true;

        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
