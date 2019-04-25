package clientMain;

import commObjects.MessageForm;
import commObjects.View.MessageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class MessageWriter implements Runnable {
    private final MessageView view;
    private final ObjectInputStream sin;
    private final Socket connectionSocket;

    MessageWriter(MessageView view, ObjectInputStream sin, Socket connectionSocket) {
        this.view = view;
        this.sin = sin;
        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        MessageForm serverResponse;
        while (!connectionSocket.isClosed()) {
            try {
                serverResponse = (MessageForm) sin.readObject();
                if (serverResponse != null)
                    view.showMessage(serverResponse);
            }
            catch (SocketException e) {
                view.showTechnicalMessage("You've been disconnected");
                try {
                    connectionSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
