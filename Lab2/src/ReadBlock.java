import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadBlock implements BlockInterface {
    @Override
    public String[] run(String[] input) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(input[0]));
        StringBuilder stringBuilder = new StringBuilder();
        int inputInt;

        while ((inputInt = inputStreamReader.read()) != -1) {
            stringBuilder.append((char) inputInt);
        }
        String[] output = {stringBuilder.toString()};
        return output;
    }
    //Input - None, Output - Text
    //1 necessary arg
    //Open <filename>(first argument) and return text from it
}
