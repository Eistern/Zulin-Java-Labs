package com.main.Blocks;

import java.util.Arrays;

public class SortBlock implements BlockInterface {
    @Override
    public String run(String[] input) throws Exception {
        if (input == null || input.length != 1)
            throw new Exception("Invalid number of arguments");

        if (input[0] == null)
            throw new Exception("No text given at sort block");

        String[] buffArray = input[0].split("\n");
        Arrays.sort(buffArray);
        StringBuilder result = new StringBuilder();

        for (String s : buffArray) {
            result.append(s);
            result.append('\n');
        }

        return result.toString();
    }
    //Input - Text, Output - Text
    //0 necessary args and text
    //Sorts text in lexicographical order and return it
}
