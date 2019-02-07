import java.util.HashMap;
import java.util.Map;

public class CSVParser {
    private Map<String, Integer> _wordMap = null;
    private Integer _wordNumber = 0;

    CSVParser() {
        _wordMap = new HashMap<String, Integer>();
    }

    public void parseFile() {

    }

    private void _safeIncrement(String key) {
        Integer oldValue;
        oldValue = _wordMap.getOrDefault(key, 0);
        _wordMap.put(key, oldValue + 1);
    }
}
