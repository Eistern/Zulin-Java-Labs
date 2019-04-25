package clientMain;

import ViewInterface.MessageView;
import commObjects.AuthorizationForm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class AuthorizationProcessor {
    static String authorize(BufferedReader cin, MessageView cout, ObjectOutputStream sout, ObjectInputStream sin) throws IOException, ClassNotFoundException {
        Object probeMessage = sin.readObject();
        if (!(probeMessage instanceof AuthorizationForm))
            System.exit(1);
        cout.showTechnicalMessage("Input your name");
        String name = cin.readLine();
        sout.writeObject(new AuthorizationForm(name));
        sout.flush();
        cout.showTechnicalMessage("Name set");
        return name;
    }
}
