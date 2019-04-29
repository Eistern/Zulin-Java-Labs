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
        String name = "";
        AuthorizationForm authorizationForm = (AuthorizationForm) probeMessage;
        cout.showTechnicalMessage("Input your name");
        while (authorizationForm.getPacketType() != AuthorizationForm.Status.OK) {
            name = cin.readLine();
            sout.writeObject(new AuthorizationForm(name, AuthorizationForm.Status.ANSWER));
            sout.flush();
            authorizationForm = (AuthorizationForm) sin.readObject();
            if (authorizationForm.getPacketType() != AuthorizationForm.Status.OK)
                cout.showTechnicalMessage("Incorrect nickname");
        }
        cout.showTechnicalMessage("Name set");
        return name;
    }
}
