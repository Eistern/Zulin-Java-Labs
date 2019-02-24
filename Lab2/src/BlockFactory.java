import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class BlockFactory {
    private Map<String, Class<BlockInterface>> _classMap;

    BlockFactory(String fileName) throws Exception {
        _classMap = new HashMap<>();
        Properties properties = new Properties();
        InputStream fin = new FileInputStream(fileName);

        properties.load(fin);
        properties.forEach((action, className) -> {
            try {
                Class classQual = Class.forName(className.toString());
                _classMap.put(action.toString(), classQual);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    BlockInterface getBlock(String blockName) throws Exception{
        return _classMap.get(blockName).getDeclaredConstructor().newInstance();
    }
}
