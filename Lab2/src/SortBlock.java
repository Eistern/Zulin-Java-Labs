import java.util.Arrays;

public class SortBlock implements BlockInterface {
    @Override
    public String[] run(String[] input) {
        String[] buffArray = input[0].split("\n");
        Arrays.sort(buffArray);
        StringBuilder result = new StringBuilder();

        for (String s : buffArray) {
            result.append(s);
            result.append('\n');
        }

        return new String[]{result.toString()};
    }
    //Input - Text, Output - Text
    //0 necessary args and text
    //Sorts text in lexicographical order and return it
}
