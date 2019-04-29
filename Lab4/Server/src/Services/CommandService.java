package Services;

import commObjects.BaseForm;
import commObjects.CommandForm;
import commObjects.MessageForm;
import serverMain.ConnectionManager;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandService implements ServiceInterface {
    private static final Logger log = Logger.getLogger(CommandService.class.getName());

    @Override
    public void serve(BaseForm infoMessage, ConnectionManager.Client srcClient) {
        CommandForm userCommand = (CommandForm) infoMessage;
        try {
            switch (userCommand.getCommandType()) {
                case GET_USER_LIST:
                    srcClient.sendMessage(new MessageForm(MessageForm.MessageType.PRIVATE, ClientMessageRouter.getInstance().getNickNameList(), "User", "Server"));
                    break;
                case GET_SERVER_TIME:
                    Date currentTime = new Date();
                    srcClient.sendMessage(new MessageForm(MessageForm.MessageType.PRIVATE, currentTime.toString(), "User", "Server"));
                default:
                    log.log(Level.WARNING, "Unexpected command type");
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, "Could not deliver command response to client:" + srcClient.toString());
        }
    }
}
