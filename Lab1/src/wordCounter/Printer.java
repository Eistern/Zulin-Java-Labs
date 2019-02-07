package wordCounter;
import java.io.IOException;

public interface Printer {
    void Print(String outputString) throws IOException;
    void closePrinter() throws IOException;
}