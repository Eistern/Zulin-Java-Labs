import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class PropertiesParser {
    Map<String, Class> parseFile(String fileName) throws Exception {
        Map<String, Class> output = new HashMap<>();
        Properties properties = new Properties();
        InputStream fin = new FileInputStream(fileName);

        properties.load(fin);
        properties.forEach((action, className) -> {
            try {
                Class classQual = Class.forName(className.toString());
                output.put(action.toString(), classQual);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });

        return output;
    }
}
