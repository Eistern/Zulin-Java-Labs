package commObjects.View;

import commObjects.MessageForm;

public interface MessageView {
    void showMessage(MessageForm src);
    void showTechnicalMessage(String src);
}
