package commObjects;

import java.io.Serializable;

public class AuthorizationForm implements Serializable {
    private final String nickName;

    public AuthorizationForm() {
        this.nickName = "";
    }

    public AuthorizationForm(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
