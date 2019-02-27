import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Block {
    private String cmd;
    private String[] args;

    Block(String cmd, String[] args) {
        this.args = args;
        this.cmd = cmd;
    }

    public String getCmdName() {
        return cmd;
    }

    public String[] getArgs() {
        return args;
    }
}

public class WorkflowReader {
    private FileReader fin;
    WorkflowReader(String fileName) throws Exception {
        this.fin = new FileReader(fileName);
    }

    public Map<Integer, Block> readBlockDecl() throws Exception {
        String buffString;
        if (fin.hasNext())
            buffString = fin.nextString();
        else
            throw new Exception("File ended");

        while (fin.hasNext() && !buffString.equals("desc"))
            buffString = fin.nextString();
        buffString = fin.nextString();

        Map<Integer, Block> result = new HashMap<>();
        while (fin.hasNext() && !buffString.equals("csed")) {
            String[] formatArray = buffString.split(" ");
            String[] argsArray = new String[formatArray.length - 2];
            System.arraycopy(formatArray, 3, argsArray, 0, formatArray.length - 3);
            argsArray[argsArray.length - 1] = null;
            result.put(Integer.parseInt(formatArray[0]), new Block(formatArray[2], argsArray));

            buffString = fin.nextString();
        }
        return result;
    }

    public List<Integer> readBlockConv() throws Exception {
        List<Integer> result = new LinkedList<>();
        String convDesc;

        if (fin.hasNext())
            convDesc = fin.nextString();
        else
            throw new Exception("File ended");

        String[] parsedID = convDesc.split(" -> ");
        for (String s : parsedID)
            result.add(Integer.parseInt(s));

        return result;
    }
}
