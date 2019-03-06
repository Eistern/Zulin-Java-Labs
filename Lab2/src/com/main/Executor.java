package com.main;

import com.main.Blocks.BlockFactory;
import com.main.Blocks.BlockInterface;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class Executor {
    private final Logger logger;

    Executor() {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        logger = Logger.getLogger(Executor.class.getName());
        logger.fine("Workflow executor created");
    }

    void processFile(@SuppressWarnings("SameParameterValue") String workflowFile) {
        try {
            WorkflowReader workfolwReader = new WorkflowReader(workflowFile);
            BlockFactory factory = BlockFactory.getInstance();
            String buffResult = null;
            logger.fine("Initialization successful");

            Map<Integer, Block> blockMap = workfolwReader.readBlockDecl();
            List<Integer> conveyor = workfolwReader.readBlockConv();
            logger.fine("Workflow file read");

            for (Integer blockID : conveyor) {
                String currentBlockName = blockMap.get(blockID).getCmdName();
                String[] currentBlockArgs = blockMap.get(blockID).getArgs();
                currentBlockArgs[currentBlockArgs.length - 1] = buffResult;

                BlockInterface currentBlock = factory.getBlock(currentBlockName);
                if (currentBlock != null)
                    buffResult = currentBlock.run(currentBlockArgs);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
    }
}
