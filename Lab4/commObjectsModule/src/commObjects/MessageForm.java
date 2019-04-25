package commObjects;

public class MessageForm extends BaseForm {
    public enum MessageType {
        BROADCAST, PRIVATE
    }

    private final MessageType type;
    private final String data;
    private final String dest;
    private final String src;

    public MessageForm() {
        super("e_msg");
        this.data = "";
        this.dest = "";
        this.src = "";
        this.type = MessageType.BROADCAST;
    }

    public MessageForm(MessageType type, String data, String dest, String src) {
        super("msg");
        this.dest = dest;
        this.data = data;
        this.type = type;
        this.src = src;
    }

    public MessageType getType() {
        return type;
    }

    public String getDest() {
        return dest;
    }

    public String getSrc() {
        return src;
    }

    public String getData() {
        return data;
    }
}
