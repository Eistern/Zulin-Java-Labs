import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringFileReader {
    private InputStreamReader _inputStreamReader = null;
    private StringBuilder _stringBuilder = null;

    StringFileReader(String fileName) throws FileNotFoundException {
        this._inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
        this._stringBuilder = new StringBuilder();
    }

    public String getWord() {
        return "TODO";
    }

    public boolean isReady() throws IOException {
        return _inputStreamReader.ready();
    }
}
