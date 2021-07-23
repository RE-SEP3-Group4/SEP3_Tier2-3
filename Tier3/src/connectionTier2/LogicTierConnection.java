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
                case "register":
                    output.writeObject(databaseAccess.register(message.getStr1(), message.getStr2()));
                    break;
                case "updateUser":
                    output.writeObject(databaseAccess.updateUser(message.getInt1(), message.getStr1(), message.getStr2()));
                    break;
                case "getReservations":
                    break;
                case "createReservation":
                    break;
                case "getPayments":
                    break;
                case "createPayment":
                    break;
                default:
                    System.out.println("Error!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
