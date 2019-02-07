package wordCounter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVPrinter implements Printer {
    private FileWriter _fileWriter;
    public CSVPrinter() throws IOException{
        _fileWriter = new FileWriter("src\\result.csv", false);
    }

    @Override
    public void Print(String outputString) throws IOException {
        _fileWriter.write(outputString);
        _fileWriter.append('\n');
    }

    @Override
    public void closePrinter() throws IOException {
        _fileWriter.flush();
        _fileWriter.close();
    }
}
