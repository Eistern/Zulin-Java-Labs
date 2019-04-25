package clientMain;

import commObjects.MessageForm;

public class InputParser {
    static MessageForm parseInput(String userInput, String userName) {
        MessageForm.MessageType messageType;
        String destNick = "Server";
        String userData;

        if (userInput.matches("->[A-Za-z0-9]* \\w*")) {
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
