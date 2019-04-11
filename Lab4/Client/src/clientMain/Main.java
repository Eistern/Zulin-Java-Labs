package clientMain;

import commObjects.MessageForm;
import commObjects.View.ConsoleMessage;
import commObjects.View.MessageView;

import java.io.*;
import java.net.Socket;

public class Main {


    public static void main(String[] args) {
        try {
            Socket connectionSocket = new Socket("localhost", 4004);

            BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
            MessageView cout = new ConsoleMessage();

            ObjectOutputStream sout = new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream sin = new ObjectInputStream(connectionSocket.getInputStream());

            String userName = AuthorizationProcessor.authorize(cin, cout, sout, sin);
            String userInput = "";
            MessageForm serverResponse;
            while (!connectionSocket.isClosed() && !userInput.equals("stop")) {
                userInput = cin.readLine();
                MessageForm userMessage = new MessageForm(MessageForm.MessageType.PRIVATE, userInput, "Server", userName);
                cout.showMessage(userMessage);

                sout.writeObject(userMessage);
                sout.flush();

                serverResponse = (MessageForm) sin.readObject();
                cout.showMessage(serverResponse);
            }
            cin.close();
            sin.close();
            sout.close();
            connectionSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
