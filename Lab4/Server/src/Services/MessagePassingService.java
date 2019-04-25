package Services;

import commObjects.BaseForm;
import commObjects.MessageForm;
import serverMain.ConnectionManager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessagePassingService implements ServiceInterface {
    private static final Logger log = Logger.getLogger(MessagePassingService.class.getName());

    @Override
    public void serve(BaseForm infoMessage, ConnectionManager.Client srcClient) {
        MessageForm clientMessage = (MessageForm) infoMessage;
        log.fine("Message from " + clientMessage.getSrc() + " to " + clientMessage.getDest() + ": " + clientMessage.getData() + ". From:" + Thread.currentThread().getName());

        if (!clientMessage.getData().equals("stop"))
            ClientMessageRouter.getInstance().sendMessage(clientMessage);
        else {
            try {
                srcClient.disconnectClient();
            } catch (IOException e) {
                log.log(Level.WARNING, "Could not disconnect client:" + srcClient.toString());
            }
        }
    }
}
