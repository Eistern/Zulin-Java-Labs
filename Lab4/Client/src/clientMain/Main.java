package clientMain;

import commObjects.MessageForm;
import commObjects.View.ConsoleMessage;
import commObjects.View.MessageView;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

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
            MessageWriter writer = new MessageWriter(cout, sin, connectionSocket);
            Thread writingThread = new Thread(writer);

            writingThread.start();
            while (!connectionSocket.isClosed() && !userInput.equals("stop")) {
                userInput = cin.readLine();
                MessageForm userMessage = InputParser.parseInput(userInput,userName);
                cout.showMessage(userMessage);

                sout.writeObject(userMessage);
                sout.flush();
            }

            cin.close();
            connectionSocket.shutdownInput();
            connectionSocket.close();

        }
        catch (UnknownHostException | ConnectException e) {
            System.out.println("Host unreachable");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
