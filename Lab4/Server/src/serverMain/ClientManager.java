package serverMain;

import Services.ClientMessageRouter;
import Services.ServerServices;
import commObjects.BaseForm;

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

            while (ClientMessageRouter.getInstance().clientConnected(currentClient)) {
                BaseForm clientInput = (BaseForm) currentClient.getMessage();

                ServerServices.getInstance().getHandler(clientInput.getIdentity()).serve(clientInput, currentClient);
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
