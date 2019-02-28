package com.main.Blocks;

import java.io.FileWriter;

public class WriteBlock implements BlockInterface {
    @Override
    public String run(String[] input) throws Exception {
        if (input == null || input.length != 2)
            throw new Exception("Invalid number of arguments");

        if (input[1] == null)
            throw new Exception("No text given at write block");

        FileWriter fileWriter = new FileWriter(input[0], false);
        fileWriter.write(input[1]);
        fileWriter.flush();
        fileWriter.close();

        return null;
    }
    //Input - Text, Output - None
    //1 necessary arg and text
    //Write input text to the <filename>(first argument)
}