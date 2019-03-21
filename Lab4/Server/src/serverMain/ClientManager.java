package serverMain;

import commObjects.MessageForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManager implements Runnable {
    private static final Logger log = Logger.getLogger(ClientManager.class.getName());
    private final Socket clientSocket;

    ClientManager(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            log.fine("Client connected. From: " + Thread.currentThread().getName());

            ObjectOutputStream sout = new ObjectOutputStream(clientSocket.getOutputStream());
            log.fine("Output stream for client generated. From:" + Thread.currentThread().getName());

            ObjectInputStream sin = new ObjectInputStream(clientSocket.getInputStream());
            log.fine("Input stream for client generated. From:" + Thread.currentThread().getName());

            MessageForm clientMessage = new MessageForm();
            while (!clientSocket.isClosed() && !clientMessage.getData().equals("stop")) {
                Object clientInput = sin.readObject();
                if (!(clientInput instanceof MessageForm))
                    continue;

                clientMessage = (MessageForm) clientInput;
                MessageForm serverResponse = new MessageForm(MessageForm.MessageType.PRIVATE, "Server got: " + clientMessage.getData(), "Client", "Server");
                sout.writeObject(serverResponse);
                sout.flush();

                log.fine("Message from " + clientMessage.getSrc() + ": " + clientMessage.getData() + ". From:" + Thread.currentThread().getName());
            }
            sin.close();
            sout.close();
            clientSocket.close();
            log.fine("Client disconnected. From" + Thread.currentThread().getName());

        } catch (IOException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Error while handling client: ", e.fillInStackTrace());
        }
    }
}
