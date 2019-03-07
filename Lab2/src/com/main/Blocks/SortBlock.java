package com.main.Blocks;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortBlock implements BlockInterface {
    private static final Logger logger = Logger.getLogger(SortBlock.class.getName());

    @Override
    public String run(String[] input, String text) {
        try {
            if (input == null || input.length != 0)
                throw new Exception("Invalid number of arguments");

            if (text == null)
                throw new Exception("No text given at sort block");

            String[] buffArray = text.split("\n");
            Arrays.sort(buffArray);
            StringBuilder result = new StringBuilder();

            for (String s : buffArray) {
                result.append(s);
                result.append('\n');
            }
            result.deleteCharAt(result.toString().length() - 1);

            return result.toString();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
        return null;
    }
    //Input - Text, Output - Text
    //0 necessary args and text
    //Sorts text in lexicographical order and return it
}
