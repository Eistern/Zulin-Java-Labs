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
                Thread handler = new Thread(new ClientManager(newClient));
                handler.start();

            } catch (IOException e) {
                log.log(Level.SEVERE, "Could not connect client", e.fillInStackTrace());
            }
        }
    }
}
