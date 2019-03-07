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
        logger = Logger.getLogger(Executor.class.getName());
        logger.fine("Workflow executor created");
    }

    void processFile(@SuppressWarnings("SameParameterValue") String workflowFile) {
        try {
            WorkflowReader workflowReader = new WorkflowReader(workflowFile);
            BlockFactory factory = BlockFactory.getInstance();
            String buffResult = null;
            logger.fine("Initialization successful");

            Map<Integer, Block> blockMap = workflowReader.readBlockDecl();
            List<Integer> conveyor = workflowReader.readBlockConv();
            logger.fine("Workflow file read");

            for (Integer blockID : conveyor) {
                String currentBlockName = blockMap.get(blockID).getCmdName();
                String[] currentBlockArgs = blockMap.get(blockID).getArgs();

                BlockInterface currentBlock = factory.getBlock(currentBlockName);
                if (currentBlock != null)
                    buffResult = currentBlock.run(currentBlockArgs, buffResult);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
    }
}
