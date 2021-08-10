package network;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import static network.Response.ResponseStatus.*;

@AllArgsConstructor
public class ClientHandler implements Runnable {

    private final Socket client;
    private RequestDispatcher dispatcher;

    private static Logger logger = Logger.getLogger(ClientHandler.class.getName());


    @Override
    public void run() {
        try (
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                client
        ) {
            while (true) {
                logger.info("Awaiting requests from " + client.getInetAddress());

                Request req = (Request) in.readObject();
                Response resp = null;

                logger.info(String.format("Received request from %s: %s",
                        client.getInetAddress(),
                        req.toString()));

                try {
                    resp = dispatcher.handle(req);
                } catch(RuntimeException e) {
                    resp = new Response(ERROR, e.getMessage());

                    logger.warning(String.format(
                            "Error processing request from %s: %s", req, e));
                }

                out.writeObject(resp);
            }
        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
