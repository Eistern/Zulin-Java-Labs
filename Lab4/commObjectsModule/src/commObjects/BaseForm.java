package commObjects;

import java.io.Serializable;

public class BaseForm implements Serializable {
    private final String identity;

    BaseForm(String id) {
        identity = id;
    }

    public String getIdentity() {
        return identity;
    }
}
