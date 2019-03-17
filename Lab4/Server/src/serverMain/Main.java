package serverMain;

import commObjects.MessageForm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        try {
            ServerSocket clientGetter = new ServerSocket(4004);
            Socket client = ConnectionManager.getClient(clientGetter);
            log.fine("Client connected");


            ObjectOutputStream sout = new ObjectOutputStream(client.getOutputStream());
            log.fine("Output stream for client generated");

            ObjectInputStream sin = new ObjectInputStream(client.getInputStream());
            log.fine("Input stream for client generated");

            MessageForm clientMessage = new MessageForm();
            while (!client.isClosed() && !clientMessage.getData().equals("stop")) {
                clientMessage = (MessageForm) sin.readObject();
                sout.writeChars("Server got: " + clientMessage.getData() + "\n");
                sout.flush();
                log.fine("Message from user: " + clientMessage.getData());
            }
            sin.close();
            sout.close();
            client.close();
            log.fine("Client disconnected");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
