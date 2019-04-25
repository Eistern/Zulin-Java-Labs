package Services;

import commObjects.BaseForm;
import commObjects.CommandForm;
import commObjects.MessageForm;
import serverMain.ConnectionManager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandService implements ServiceInterface {
    private static final Logger log = Logger.getLogger(CommandService.class.getName());

    @Override
    public void serve(BaseForm infoMessage, ConnectionManager.Client srcClient) {
        CommandForm userCommand = (CommandForm) infoMessage;
        switch (userCommand.getCommandType()) {
            case GET_USER_LIST:
                try {
                    srcClient.sendMessage(new MessageForm(MessageForm.MessageType.PRIVATE, ClientMessageRouter.getInstance().getNickNameList(), "User", "Server"));
                } catch (IOException e) {
                    log.log(Level.SEVERE, "Could not deliver message to client:" + srcClient.toString());
                }
                break;
            default:
                log.log(Level.WARNING, "Unexpected command type");
        }
    }
}
