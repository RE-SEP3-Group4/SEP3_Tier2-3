package network;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

@AllArgsConstructor
public class Server implements Runnable {
    private final ServerSocket serverSocket;
    private RequestDispatcher dispatcher;

    private static Logger logger = Logger.getLogger(Server.class.getName());

    @Override
    public void run() {
        logger.info("Awaiting connections...");
        try (serverSocket) {
            while (true) {
                Socket client = serverSocket.accept();
                logger.info(String.format("Accepted connection from %s", client.getInetAddress()));
                new Thread(new ClientHandler(client, dispatcher)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
