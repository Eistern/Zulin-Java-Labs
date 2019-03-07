package com.main.Blocks;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ReplaceBlock implements BlockInterface {
    private static final Logger logger = Logger.getLogger(ReplaceBlock.class.getName());

    @Override
    public String run(String[] input, String text) {
        try {
            if (input == null || input.length != 2)
                throw new Exception("Invalid number of arguments");

            if (text == null)
                throw new Exception("No text given at replace block");

            return text.replaceAll(input[0], input[1]);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
        return null;
    }
    //Input - Text, Output - Text
    //2 necessary args and text
    //Replaces <word1>(first argument) with <word2>(second argument) in text and return it
}
