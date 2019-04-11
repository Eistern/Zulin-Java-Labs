package startPack;

import serverMain.serverRunner;

import java.io.*;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        serverRunner.run();
    }
}
