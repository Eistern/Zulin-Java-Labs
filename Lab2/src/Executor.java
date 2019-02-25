import java.util.List;
import java.util.Map;

class Executor {
    void processFile(String workflowFile) throws Exception {
        WorkflowReader workfolwReader = new WorkflowReader(workflowFile);
        BlockFactory factory = new BlockFactory("src\\blocks.properties");
        String buffResult = null;

        Map<Integer, Block> blockMap = workfolwReader.readBlockDecl();
        List<Integer> conveyor = workfolwReader.readBlockConv();
        for (Integer blockID : conveyor) {
            String currentBlockName = blockMap.get(blockID).getCmdName();
            String[] currentBlockArgs = blockMap.get(blockID).getArgs();
            currentBlockArgs[currentBlockArgs.length - 1] = buffResult;

            BlockInterface currentBlock = factory.getBlock(currentBlockName);
            buffResult = currentBlock.run(currentBlockArgs);
        }
    }
}
