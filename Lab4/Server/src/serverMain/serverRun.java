package serverMain;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverRun {
    private static final Logger log = Logger.getLogger(serverRun.class.getName());

    static public void run() throws IOException {
        ServerSocket clientGetter = new ServerSocket(4004);
        while (true) {
            Client newClient;
            try {
                newClient = ConnectionManager.getClient(clientGetter);
                Thread handler = new Thread(new ClientManager(newClient));
                handler.start();

                if (Thread.activeCount() == 1)
                    break;
            } catch (IOException e) {
                log.log(Level.SEVERE, "Could not connect client", e.fillInStackTrace());
            }
        }
    }
}
