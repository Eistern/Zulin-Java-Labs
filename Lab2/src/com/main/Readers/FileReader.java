package com.main.Readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {
    private InputStreamReader _inputStreamReader;
    private StringBuilder _stringBuilder;
    private boolean _eofFlag = false;

    public FileReader(String fileName) throws FileNotFoundException {
        this._inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
        this._stringBuilder = new StringBuilder();
    }

    public String nextString() throws IOException {
        _stringBuilder.delete(0, _stringBuilder.toString().length());
        int inputInt;
        while (this.hasNext()) {
            inputInt = _inputStreamReader.read();
            if (inputInt == -1)
                _eofFlag = true;

            if ((char) inputInt != '\n' && (char) inputInt != '\r')
                _stringBuilder.append((char) inputInt);
            else if (_stringBuilder.toString().length() != 0)
                return _stringBuilder.toString().toLowerCase();
            else
                _stringBuilder.delete(0, 1);
        }
        return "";
    }

    public String nextWord() throws IOException {
        _stringBuilder.delete(0, _stringBuilder.toString().length());
        int inputInt;
        while (this.hasNext()) {
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

    public boolean hasNext() {
        return !_eofFlag;
    }

    public void closeReader() throws IOException {
        _inputStreamReader.close();
    }
}
