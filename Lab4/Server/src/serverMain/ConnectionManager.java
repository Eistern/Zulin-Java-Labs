package serverMain;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ConnectionManager {
    static Socket getClient(ServerSocket serverPort) throws IOException {
        return serverPort.accept();
    }
}
