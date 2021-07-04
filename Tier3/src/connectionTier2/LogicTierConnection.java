package connectionTier2;

import database.DatabaseAccess;
import database.DatabaseAccessInterface;
import domain.SocketMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LogicTierConnection implements Runnable {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private DatabaseAccessInterface databaseAccess;

    public LogicTierConnection() {
        databaseAccess = new DatabaseAccess();
    }

    public void setSocket(Socket socket) throws IOException {
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            SocketMessage message = (SocketMessage) input.readObject();
            switch (message.getCmd()) {
                case "login":
                    output.writeObject(databaseAccess.login(message.getStr1(), message.getStr2()));
                    break;
                default:
                    System.out.println("Error!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
