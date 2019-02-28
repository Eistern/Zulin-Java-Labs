package com.main.Blocks;

public class GrepBlock implements BlockInterface {
    @Override
    public String run(String[] input) throws Exception {
        if (input == null || input.length != 2)
            throw new Exception("Invalid number of arguments");

        if (input[1] == null)
            throw new Exception("No text given at grep block");

        String[] buffArray = input[1].split("\n");
        StringBuilder result = new StringBuilder();

        for (String s : buffArray) {
            if (s.contains(input[0])) {
                result.append(s);
                result.append('\n');
            }
        }

        return result.toString();
    }
    //Input - Text, Output - Text
    //1 necessary arg and text
    //Find strings from the original text, containing <word>(first argument) and return them
}
