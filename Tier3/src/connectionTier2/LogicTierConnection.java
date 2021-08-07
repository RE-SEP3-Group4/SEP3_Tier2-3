package connectionTier2;

import database.*;
import domain.SocketMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LogicTierConnection implements Runnable {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private UserRepository userRepository;
    private ReservationRepository reservationRepository;
    private PaymentRepository paymentRepository;
    private Socket client;

    public LogicTierConnection() {
        userRepository = new JDBCUserRepositoryImpl();
        reservationRepository = new JDBCReservationRepositoryImpl();
        paymentRepository = new JDBCPaymentRepositoryImpl();
    }

    public void setSocket(Socket socket) throws IOException {
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            SocketMessage msg = (SocketMessage) input.readObject();
            switch (msg.getCmd()) {
                case "login":
                    output.writeObject(userRepository.findUserByUsernameAndPassword(msg.getStr1(), msg.getStr2()));
                    break;
                case "register":
                    output.writeObject(userRepository.createUser(msg.getStr1(), msg.getStr2()));
                    break;
                case "updateUser":
                    output.writeObject(userRepository.updateUser(msg.getInt1(), msg.getStr1(), msg.getStr2()));
                    break;
                case "deleteUser":
                    output.writeObject(userRepository.deleteUser(msg.getInt1()));
                    break;
                case "getAllUsers":
                    output.writeObject(userRepository.getAllUsers());
                    break;
                case "getReservations":
                    output.writeObject(reservationRepository.getReservations(msg.getInt1()));
                    break;
                case "createReservation":
                    output.writeObject(reservationRepository.createReservation(msg.getInt1(), msg.getStr1(), msg.getStr2()));
                    break;
                case "deleteReservation":
                    output.writeObject(reservationRepository.deleteReservation(msg.getReservation()));
                    break;
                case "getPayments":
                    output.writeObject(paymentRepository.getPayments(msg.getInt1()));
                    break;
                case "createPayment":
                    output.writeObject(paymentRepository.createPayment(msg.getInt1(), msg.getStr1(), msg.getStr2()));
                    break;
                case "deletePayment":
                    output.writeObject(paymentRepository.deletePayment(msg.getPayment()));
                    break;
                default:
                    System.out.println("Wrong command imputed!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
