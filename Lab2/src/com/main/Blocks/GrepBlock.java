package com.main.Blocks;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GrepBlock implements BlockInterface {
    private static final Logger logger = Logger.getLogger(GrepBlock.class.getName());

    @Override
    public String run(String[] input, String text) {
        try {
            if (input == null || input.length != 1)
                throw new Exception("Invalid number of arguments");

            if (text == null)
                throw new Exception("No text given at grep block");

            String[] buffArray = text.split("\n");
            StringBuilder result = new StringBuilder();

            for (String s : buffArray) {
                if (s.contains(input[0])) {
                    result.append(s);
                    result.append('\n');
                }
            }
            if (!result.toString().isEmpty())
                result.deleteCharAt(result.toString().length() - 1);

            return result.toString();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
        return null;
    }
    //Input - Text, Output - Text
    //1 necessary arg and text
    //Find strings from the original text, containing <word>(first argument) and return them
}
