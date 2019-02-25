import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadBlock implements BlockInterface {
    @Override
    public String run(String[] input) throws Exception {
        if (input == null || (input.length == 2 && input[1] != null) || input.length != 1)
            throw new Exception("Invalid number of arguments");

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(input[0]));
        StringBuilder stringBuilder = new StringBuilder();
        int inputInt;

        while ((inputInt = inputStreamReader.read()) != -1) {
            stringBuilder.append((char) inputInt);
        }
        return stringBuilder.toString();
    }
    //Input - None, Output - Text
    //1 necessary arg
    //Open <filename>(first argument) and return text from it
}
