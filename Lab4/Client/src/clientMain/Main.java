package clientMain;

import ViewInterface.ConsoleMessage;
import ViewInterface.MessageView;
import commObjects.BaseForm;
import commObjects.MessageForm;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
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
            while (!userInput.equals("stop")) {
                userInput = cin.readLine();
                BaseForm userMessage = InputParser.parseInput(userInput, userName);

                if (userMessage.getIdentity().equals("msg"))
                    cout.showMessage((MessageForm) userMessage);

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
        catch (SocketException e) {
            System.out.println("Message hasn't been delivered");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
