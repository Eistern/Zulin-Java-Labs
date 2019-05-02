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

        if (clientMessage.getData().equals("")) {
            try {
                srcClient.sendMessage(new MessageForm("Empty messages will not be delivered"));
            } catch (IOException e) {
                log.log(Level.WARNING, "Could not deliver response to client ", e.fillInStackTrace());
            }
            return;
        }

        if (clientMessage.getData().equals("stop")) {
            try {
                srcClient.disconnectClient();
            } catch (IOException e) {
                log.log(Level.WARNING, "Could not disconnect client:" + srcClient.toString());
            }
            return;
        }

        ClientMessageRouter.getInstance().sendMessage(clientMessage);
        log.fine("Message from " + clientMessage.getSrc() + " to " + clientMessage.getDest() + ": " + clientMessage.getData() + ". From:" + Thread.currentThread().getName());
    }
}
