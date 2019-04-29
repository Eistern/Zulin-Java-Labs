package serverMain;

import Services.ClientMessageRouter;
import commObjects.AuthorizationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionManager {
    static void connectClient(Socket clientPort) throws IOException, ClassNotFoundException {
        Client currentClient = new Client(clientPort);
        String nickName = "Server";
        currentClient.sendMessage(new AuthorizationForm());

        while (!ClientMessageRouter.getInstance().correctName(nickName)) {
            Object response = currentClient.getMessage();
            nickName = ((AuthorizationForm) response).getNickName();
            currentClient.setClientName(nickName);
            if (ClientMessageRouter.getInstance().correctName(nickName))
                currentClient.sendMessage(new AuthorizationForm("", AuthorizationForm.Status.OK));
            else
                currentClient.sendMessage(new AuthorizationForm("", AuthorizationForm.Status.REJECT));
        }
        serverRunner.addClient(currentClient);
    }

    public static class Client {
        private final Socket clientSocket;
        private final ObjectOutputStream sout;
        private final ObjectInputStream sin;
        private String clientName;
        private Boolean finalized = false;

        Client(Socket connectionSocket) throws IOException {
            clientSocket = connectionSocket;
            sout = new ObjectOutputStream(connectionSocket.getOutputStream());
            sin = new ObjectInputStream(connectionSocket.getInputStream());
        }

        void setClientName(String clientName) {
            this.clientName = clientName;
            if (!finalized)
                finalized = true;
        }

        String getClientName() {
            return clientName;
        }

        public void sendMessage(Object message) throws IOException {
            sout.writeObject(message);
            sout.flush();
        }

        Object getMessage() throws IOException, ClassNotFoundException {
            return sin.readObject();
        }

        public void disconnectClient() throws IOException {
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
