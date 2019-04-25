package clientMain;

import commObjects.BaseForm;
import commObjects.CommandForm;
import commObjects.MessageForm;

class InputParser {
    static BaseForm parseInput(String userInput, String userName) {
        if (userInput.equals("->list"))
            return new CommandForm(CommandForm.CommandType.GET_USER_LIST);

        MessageForm.MessageType messageType;
        String destNick = "Server";
        String userData;

        if (userInput.matches("->[A-Za-z0-9_]* \\w*")) {
            userData = userInput.substring(userInput.indexOf(" ") + 1);
            messageType = MessageForm.MessageType.PRIVATE;
            String dest = userInput.substring(0, userInput.indexOf(" "));
            destNick = dest.substring(2);
        }
        else {
            userData = userInput;
            messageType = MessageForm.MessageType.BROADCAST;
        }

        return new MessageForm(messageType, userData, destNick, userName);
    }
}
