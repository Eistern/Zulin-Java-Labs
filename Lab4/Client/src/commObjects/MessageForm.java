package commObjects;

import java.io.Serializable;

public class MessageForm implements Serializable {
    public enum MessageType {
        BROADCAST, PRIVATE
    }

    private final MessageType type;
    private final String data;
    private final String addr;

    public MessageForm() {
        this.data = "";
        this.addr = "";
        this.type = MessageType.BROADCAST;
    }

    public MessageForm(MessageType type, String data, String addr) {
        this.addr = addr;
        this.data = data;
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public String getAddr() {
        return addr;
    }

    public String getData() {
        return data;
    }
}
