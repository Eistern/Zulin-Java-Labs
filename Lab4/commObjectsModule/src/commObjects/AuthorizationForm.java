package commObjects;

import java.io.Serializable;

public class AuthorizationForm implements Serializable {
    public enum Status {
        REQUEST, ANSWER, OK, REJECT
    }

    private final Status packetType;
    private final String nickName;

    public AuthorizationForm() {
        this.nickName = "";
        this.packetType = Status.REQUEST;
    }

    public AuthorizationForm(String nickName, Status packetType) {
        this.nickName = nickName;
        this.packetType = packetType;
    }

    public String getNickName() {
        return nickName;
    }

    public Status getPacketType() {
        return packetType;
    }
}
