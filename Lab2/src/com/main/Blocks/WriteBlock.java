package com.main.Blocks;

import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteBlock implements BlockInterface {
    private static final Logger logger = Logger.getLogger(ReplaceBlock.class.getName());

    @Override
    public String run(String[] input, String text) {
        try {
            if (input == null || input.length != 1)
                throw new Exception("Invalid number of arguments");

            if (text == null)
                throw new Exception("No text given at write block");

            FileWriter fileWriter = new FileWriter(input[0], false);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
        return null;
    }
    //Input - Text, Output - None
    //1 necessary arg and text
    //Write input text to the <filename>(first argument)
}