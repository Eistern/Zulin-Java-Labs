package serverMain;

import commObjects.AuthorizationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ConnectionManager {
    static void connectClient(Socket clientPort) throws IOException, ClassNotFoundException {
        Client currentClient = new Client(clientPort);
        currentClient.sendMessage(new AuthorizationForm());
        Object response = currentClient.getMessage();
        if (response instanceof AuthorizationForm) {
            String nickName = ((AuthorizationForm) response).getNickName();
            currentClient.setClientName(nickName);
        }
        serverRunner.addClient(currentClient);
    }

    static class Client {
        private final Socket clientSocket;
        private final ObjectOutputStream sout;
        private final ObjectInputStream sin;
        private String clientName;
        private Boolean finalized = false;

        public Client(Socket connectionSocket) throws IOException {
            clientSocket = connectionSocket;
            sout = new ObjectOutputStream(connectionSocket.getOutputStream());
            sin = new ObjectInputStream(connectionSocket.getInputStream());
        }

        void setClientName(String clientName) {
            if (!finalized) {
                finalized = true;
                this.clientName = clientName;
            }
        }

        String getClientName() {
            return clientName;
        }

        void sendMessage(Object message) throws IOException {
            sout.writeObject(message);
            sout.flush();
        }

        Object getMessage() throws IOException, ClassNotFoundException {
            return sin.readObject();
        }

        void disconnectClient() throws IOException {
            ClientMessageRouter.getInstance().releaseClient(clientName);
            sout.close();
            sin.close();
            clientSocket.close();
        }

        boolean isClosed() {
            return clientSocket.isClosed();
        }

        boolean isFinalized() {
            return finalized;
        }
    }
}
