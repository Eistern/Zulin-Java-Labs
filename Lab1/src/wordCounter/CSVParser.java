package wordCounter;
import java.io.IOException;
import java.util.*;

public class CSVParser {
    private Map<String, Double> _wordMap;
    private double _wordNumber = 0.0;

    public CSVParser() {
        _wordMap = new HashMap<>();
    }

    public void parseFile(StringReader inputListener) throws IOException {
        while (inputListener.isReady()) {
            String inputWord = inputListener.getWord();
            if (!inputWord.isEmpty()) {
                _safeIncrement(inputWord);
                _wordNumber++;
            }
        }
    }

    public void printStats(Printer outputPrinter) {
        Map<Double, List<String>> result = new TreeMap<>(Collections.reverseOrder());
        _wordMap.forEach((word, count) ->
            result.computeIfAbsent(count, (buff) -> new ArrayList<>()).add(word)
        );
        result.forEach((number, wordList) ->
            wordList.forEach((word) -> {
                try {
                    outputPrinter.Print(word + ", " + number + ", " + (number / this._wordNumber * 100) + "%");
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            })
        );
    }

    private void _safeIncrement(String key) {
        double oldValue;
        oldValue = _wordMap.getOrDefault(key, 0.0);
        _wordMap.put(key, oldValue + 1);
    }
}