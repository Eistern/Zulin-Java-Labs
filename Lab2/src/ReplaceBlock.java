public class ReplaceBlock implements BlockInterface {
    @Override
    public String run(String[] input) throws Exception {
        if (input == null || input.length != 3)
            throw new Exception("Invalid number of arguments");

        if (input[2] == null)
            throw new Exception("No text given at replace block");

        return input[2].replaceAll(input[0], input[1]);
    }
    //Input - Text, Output - Text
    //2 necessary args and text
    //Replaces <word1>(first argument) with <word2>(second argument) in text and return it
}
