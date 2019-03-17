package clientMain;

import commObjects.MessageForm;

import java.io.*;
import java.net.Socket;

public class Main {


    public static void main(String[] args) {
        try {
            Socket connectionSocket = new Socket("localhost", 4004);

            BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

            ObjectOutputStream sout = new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream sin = new ObjectInputStream(connectionSocket.getInputStream());

            String userInput = "";
            MessageForm serverResponse;
            while (!connectionSocket.isClosed() && !userInput.equals("stop")) {
                userInput = cin.readLine();
                MessageForm userMessage = new MessageForm(MessageForm.MessageType.BROADCAST, userInput, "");

                sout.writeObject(userMessage);
                sout.flush();
                System.out.println("Message sent");

                serverResponse = (MessageForm) sin.readObject();
                cout.write(serverResponse.getData() + "\n");
                cout.flush();
            }
            cin.close();
            cout.close();
            sin.close();
            sout.close();
            connectionSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
