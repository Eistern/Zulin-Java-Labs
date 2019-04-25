package Services;

import commObjects.MessageForm;
import serverMain.ConnectionManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMessageRouter {
    private static final Logger log = Logger.getLogger(ClientMessageRouter.class.getName());
    private final Map<String, ConnectionManager.Client> clientMap = new HashMap<>();
    private MessageForm sendingMessage;

    private static volatile ClientMessageRouter instance;

    public static ClientMessageRouter getInstance() {
        ClientMessageRouter localInstance = instance;
        if (instance == null)
            synchronized (ClientMessageRouter.class) {
                if (instance == null) {
                    localInstance = new ClientMessageRouter();
                    instance = localInstance;
                }
            }
        return localInstance;
    }

    private ClientMessageRouter() {}

    public void addClient(String _newClientName, ConnectionManager.Client _newClient) {
        clientMap.put(_newClientName, _newClient);
    }

    String getNickNameList() {
        return clientMap.keySet().toString();
    }

    public void releaseClient(String clientName) {
        clientMap.remove(clientName);
    }

    public boolean clientConnected(ConnectionManager.Client testClient) {
        return clientMap.containsValue(testClient);
    }

    void sendMessage(MessageForm message) {
        sendingMessage = message;
        if (sendingMessage.getType() != MessageForm.MessageType.BROADCAST && !clientMap.containsKey(message.getDest()))
            sendingMessage = new MessageForm(MessageForm.MessageType.PRIVATE, "Client does not exist", message.getSrc(), "Server");
        clientMap.forEach((name, id) -> {
            try {
                switch (sendingMessage.getType()) {
                    case BROADCAST:
                        if (!name.equals(sendingMessage.getSrc()))
                            id.sendMessage(sendingMessage);
                        break;
                    case PRIVATE:
                        if (name.equals(sendingMessage.getDest()))
                            id.sendMessage(sendingMessage);
                        break;
                    default:
                        break;

                }
            } catch (IOException e) {
                log.log(Level.SEVERE, "Could not deliver message to: " + name, e.fillInStackTrace());
            }
        });
    }
}
