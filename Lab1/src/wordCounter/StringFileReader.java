package wordCounter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringFileReader implements StringReader {
    private InputStreamReader _inputStreamReader;
    private StringBuilder _stringBuilder;
    private boolean _eofFlag = false;

    public StringFileReader(String fileName) throws FileNotFoundException {
        this._inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
        this._stringBuilder = new StringBuilder();
    }

    @Override
    public String getWord() throws IOException {
        _stringBuilder.delete(0, _stringBuilder.toString().length());
        int inputInt;
        while (this.isReady()) {
            inputInt = _inputStreamReader.read();
            if (inputInt == -1)
                _eofFlag = true;

            if (Character.isLetterOrDigit(inputInt))
                _stringBuilder.append((char) inputInt);
            else if (_stringBuilder.toString().length() != 0)
                return _stringBuilder.toString().toLowerCase();
        }
        return "";
    }

    @Override
    public boolean isReady() {
        return !_eofFlag;
    }

    @Override
    public void closeReader() throws IOException {
        _inputStreamReader.close();
    }
}
