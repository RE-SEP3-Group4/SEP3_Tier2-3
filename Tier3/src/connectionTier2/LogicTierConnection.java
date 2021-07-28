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
        databaseAccess = new DatabaseAccess();
    }

    @Override
    public void run() {
        try {
            SocketMessage msg = (SocketMessage) input.readObject();
            switch (msg.getCmd()) {
                case "login":
                    output.writeObject(databaseAccess.login(msg.getStr1(), msg.getStr2()));
                    break;
                case "register":
                    output.writeObject(databaseAccess.register(msg.getStr1(), msg.getStr2()));
                    break;
                case "updateUser":
                    output.writeObject(databaseAccess.updateUser(msg.getInt1(), msg.getStr1(), msg.getStr2()));
                    break;
                case "deleteUser":
                    //Write the delete user method
                    break;
                case "getAllUsers":
                    //Write the get all user method
                    break;
                case "getReservations":
                    output.writeObject(databaseAccess.getReservations(msg.getInt1()));
                    break;
                case "createReservation":
                    output.writeObject(databaseAccess.createReservation(msg.getInt1(), msg.getInt2()));
                    break;
                case "deleteReservation":
                    //Write the delete reservation method
                    break;
                case "getPayments":
                    output.writeObject(databaseAccess.getPayments(msg.getInt1()));
                    break;
                case "createPayment":
                    output.writeObject(databaseAccess.createPayment(msg.getInt1(), msg.getInt2(), msg.getInt3()));
                    break;
                case "deletePayment":
                    //Write the delete payment method
                    break;
                default:
                    System.out.println("Wrong command imputed!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
