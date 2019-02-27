import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class Executor {
    private static final Logger logger = Logger.getLogger(Executor.class.getName());

    Executor(){
        logger.fine("Workflow executor created");
    }

    void processFile(String workflowFile) {
        try {
            WorkflowReader workfolwReader = new WorkflowReader(workflowFile);
            BlockFactory factory = new BlockFactory("src\\blocks.properties");
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
                buffResult = currentBlock.run(currentBlockArgs);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error occurred: ");
        }
    }
}
