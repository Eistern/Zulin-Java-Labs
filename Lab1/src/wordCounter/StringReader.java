package wordCounter;
import java.io.IOException;

public interface StringReader {
    String getWord() throws IOException;
    boolean isReady() throws IOException;
    void closeReader() throws IOException;
}
