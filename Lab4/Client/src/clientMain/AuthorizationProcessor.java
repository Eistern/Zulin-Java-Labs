package clientMain;

import commObjects.AuthorizationForm;
import commObjects.View.MessageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AuthorizationProcessor {
    static String authorize(BufferedReader cin, MessageView cout, ObjectOutputStream sout, ObjectInputStream sin) throws IOException, ClassNotFoundException {
        Object probeMessage = sin.readObject();
        if (!(probeMessage instanceof AuthorizationForm))
            System.exit(1);
        cout.showTechnicalMessage("Input your name");
        String name = cin.readLine();
        sout.writeObject(new AuthorizationForm(name));
        sout.flush();
        return name;
    }
}
