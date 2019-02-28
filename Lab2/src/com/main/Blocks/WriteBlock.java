package com.main.Blocks;

import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteBlock implements BlockInterface {
    private static final Logger logger = Logger.getLogger(ReplaceBlock.class.getName());

    @Override
    public String run(String[] input) {
        try {
            if (input == null || input.length != 2)
                throw new Exception("Invalid number of arguments");

            if (input[1] == null)
                throw new Exception("No text given at write block");

            FileWriter fileWriter = new FileWriter(input[0], false);
            fileWriter.write(input[1]);
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