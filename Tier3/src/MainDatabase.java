import connectionTier2.LogicTierConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainDatabase {
    public static void main(String[] args) throws IOException {
        final int PORT = 3000;
        ServerSocket serverSocket = new ServerSocket(PORT);
        LogicTierConnection connection = new LogicTierConnection();
        System.out.println("Database has started!");
        while (true) {
            Socket socket = serverSocket.accept();
            connection.setSocket(socket);
            Thread clientThread = new Thread(connection);
            clientThread.start();
        }
    }
}
