package commObjects;

import java.io.Serializable;

public class CommandForm extends BaseForm {
    public enum CommandType {
        GET_USER_LIST
    }
    final private CommandType commandType;

    public CommandForm(CommandType commandType) {
        super("comm");
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return commandType;
    }
}
