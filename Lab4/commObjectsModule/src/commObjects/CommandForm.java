package commObjects;

public class CommandForm extends BaseForm {
    public enum CommandType {
        GET_USER_LIST, GET_SERVER_TIME, GET_HELP
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
