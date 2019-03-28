package serverMain;

import commObjects.MessageForm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManager implements Runnable {
    private static final Logger log = Logger.getLogger(ClientManager.class.getName());
    private final Client currentClient;

    ClientManager(Client currentClient) {
        this.currentClient = currentClient;
    }

    @Override
    public void run() {
        try {
            log.fine("Client connected. From: " + Thread.currentThread().getName());

            MessageForm clientMessage = new MessageForm();
            while (!clientMessage.getData().equals("stop")) {
                Object clientInput = currentClient.getMessage();
                if (!(clientInput instanceof MessageForm))
                    continue;

                clientMessage = (MessageForm) clientInput;
                MessageForm serverResponse = new MessageForm(MessageForm.MessageType.PRIVATE, "Server got: " + clientMessage.getData(), "Client", "Server");
                currentClient.sendMessage(serverResponse);

                log.fine("Message from " + clientMessage.getSrc() + ": " + clientMessage.getData() + ". From:" + Thread.currentThread().getName());
            }
            currentClient.disconnectClient();
            log.fine("Client disconnected. From" + Thread.currentThread().getName());

        } catch (IOException | ClassNotFoundException e) {
            log.log(Level.SEVERE, "Error while handling client: ", e.fillInStackTrace());
        }
    }
}
