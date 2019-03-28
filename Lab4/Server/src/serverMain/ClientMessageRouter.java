package serverMain;

import commObjects.MessageForm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMessageRouter {
    private static final Logger log = Logger.getLogger(ClientMessageRouter.class.getName());
    private final Map<String, Client> clientMap = new HashMap<>();

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

    public void addClient(String _newClientName, Client _newClient) {
        clientMap.put(_newClientName, _newClient);
    }

    public void releaseClient(String clientName) {
        clientMap.remove(clientName);
    }

    public void sendMessage(MessageForm message) {
        clientMap.forEach((name, id) -> {
            try {
                switch (message.getType()) {
                    case BROADCAST:
                        id.sendMessage(message);
                        break;
                    case PRIVATE:
                        if (name.equals(message.getDest()))
                            id.sendMessage(message);
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
