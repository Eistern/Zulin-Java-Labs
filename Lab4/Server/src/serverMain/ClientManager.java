package serverMain;

import Services.ClientMessageRouter;
import Services.ServerServices;
import commObjects.BaseForm;
import commObjects.MessageForm;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManager implements Runnable {
    private static final Logger log = Logger.getLogger(ClientManager.class.getName());
    private ConnectionManager.Client currentClient;
    private final Socket clientSocket;

    ClientManager(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            currentClient = ConnectionManager.connectClient(clientSocket);
            if (!currentClient.isFinalized()) {
                log.log(Level.WARNING, "Did not get nickName from a client" + currentClient);
                return;
            }
            ClientMessageRouter.getInstance().addClient(currentClient.getClientName(), currentClient);
            log.fine("Client connected. From: " + Thread.currentThread().getName());

            currentClient.sendMessage(new MessageForm(MessageForm.MessageType.PRIVATE, "List of server commands ->help.\nTo send private message write -><username> text", "User", "Server"));

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
