package serverMain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Client {
    private final Socket clientSocket;
    private final ObjectOutputStream sout;
    private final ObjectInputStream sin;

    public Client(Socket connectionSocket) throws IOException {
        clientSocket = connectionSocket;
        sout = new ObjectOutputStream(connectionSocket.getOutputStream());
        sin = new ObjectInputStream(connectionSocket.getInputStream());
    }

    void sendMessage(Object message) throws IOException {
        sout.writeObject(message);
        sout.flush();
    }

    Object getMessage() throws IOException, ClassNotFoundException {
        return sin.readObject();
    }

    void disconnectClient() throws IOException {
        sout.close();
        sin.close();
        clientSocket.close();
    }

    boolean isClosed() {
        return clientSocket.isClosed();
    }
}

class ConnectionManager {
    static Client getClient(ServerSocket serverPort) throws IOException {
        return new Client(serverPort.accept());
    }
}
