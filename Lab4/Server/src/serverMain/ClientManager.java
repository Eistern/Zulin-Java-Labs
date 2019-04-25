package serverMain;

import commObjects.MessageForm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManager implements Runnable {
    private static final Logger log = Logger.getLogger(ClientManager.class.getName());
    private final ConnectionManager.Client currentClient;

    ClientManager(ConnectionManager.Client currentClient) {
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
                log.fine("Message from " + clientMessage.getSrc() + ": " + clientMessage.getData() + ". From:" + Thread.currentThread().getName());

                if (!clientMessage.getData().equals("stop"))
                    ClientMessageRouter.getInstance().sendMessage(clientMessage);
            }
            currentClient.disconnectClient();
            log.fine("Client disconnected. From" + Thread.currentThread().getName());

        } catch (IOException | ClassNotFoundException e) {
            try {
                currentClient.disconnectClient();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            log.log(Level.SEVERE, "Error while handling client: ", e.fillInStackTrace());
        }
    }
}
