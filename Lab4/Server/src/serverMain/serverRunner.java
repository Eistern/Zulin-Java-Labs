package serverMain;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverRunner {
    private static final Logger log = Logger.getLogger(serverRunner.class.getName());

    static public void run() throws IOException {
        ServerSocket clientGetter = new ServerSocket(4004);
        while (true) {
            Socket newClient;
            try {
                newClient = clientGetter.accept();
                ConnectionManager.connectClient(newClient);

                if (Thread.activeCount() == 1)
                    break;
            } catch (IOException | ClassNotFoundException e) {
                log.log(Level.SEVERE, "Could not connect client", e.fillInStackTrace());
            }
        }
    }

    static void addClient(ConnectionManager.Client newClient) {
        if (!newClient.isFinalized()) {
            log.log(Level.WARNING, "Did not get nickName from a client" + newClient);
            return;
        }
        ClientMessageRouter.getInstance().addClient(newClient.getClientName(), newClient);
        Thread handler = new Thread(new ClientManager(newClient));
        handler.start();
    }
}
