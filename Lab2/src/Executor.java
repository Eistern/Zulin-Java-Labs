import java.util.Map;

public class Executor {
    public void processFile(String workflowFile) throws Exception {
        FileReader workfolwReader = new FileReader(workflowFile);
        PropertiesParser parser = new PropertiesParser();
        Map<String, Class> classQuals = parser.parseFile("blocks.properties");


    }
}
