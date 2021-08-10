import com.google.gson.Gson;
import database.JDBCPaymentRepositoryImpl;
import database.JDBCReservationRepositoryImpl;
import database.JDBCUserRepositoryImpl;
import network.*;

import java.io.IOException;
import java.net.ServerSocket;

public class MainDatabase {

    public static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        Gson gson = new Gson();

        RequestDispatcher dispatcher = new RequestDispatcher();
        dispatcher.register("users", new UserController(new JDBCUserRepositoryImpl(), gson));
        dispatcher.register("reservations", new ReservationController(new JDBCReservationRepositoryImpl(),
                gson));
        dispatcher.register("payments", new PaymentController(new JDBCPaymentRepositoryImpl(),
                gson));

        Server server = new Server(serverSocket, dispatcher);
        new Thread(server).start();
    }
}
