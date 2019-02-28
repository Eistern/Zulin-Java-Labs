package com.main.Blocks;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadBlock implements BlockInterface {
    private static final Logger logger = Logger.getLogger(ReadBlock.class.getName());

    @Override
    public String run(String[] input) {
        try {
            if (input == null || input.length != 2)
                throw new Exception("Invalid number of arguments");

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(input[0]));
            StringBuilder stringBuilder = new StringBuilder();
            int inputInt;

            while ((inputInt = inputStreamReader.read()) != -1) {
                stringBuilder.append((char) inputInt);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
        return null;
    }
    //Input - None, Output - Text
    //1 necessary arg
    //Open <filename>(first argument) and return text from it
}
