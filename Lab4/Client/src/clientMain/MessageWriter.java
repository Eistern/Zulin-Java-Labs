package clientMain;

import commObjects.MessageForm;
import commObjects.View.MessageView;

import java.io.IOException;
import java.io.ObjectInputStream;

public class MessageWriter implements Runnable {
    private final MessageView view;
    private final ObjectInputStream sin;

    MessageWriter(MessageView view, ObjectInputStream sin) {
        this.view = view;
        this.sin = sin;
    }

    @Override
    public void run() {
        MessageForm serverResponse;
        while (true) {
            try {
                serverResponse = (MessageForm) sin.readObject();
                view.showMessage(serverResponse);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
