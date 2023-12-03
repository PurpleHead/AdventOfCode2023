package at.poik.day.one.p2;

import at.poik.io.FileLoader;

import java.net.URISyntaxException;
import java.util.*;

public class TrebutchetTwo {

    private static Map<String, Integer> numbers = new HashMap<>();

    public static void main(String[] args) throws URISyntaxException {
        FileLoader loader = new FileLoader("/one");
        List<String> lines = loader.loadLinesOfFile("input.txt");
        initMap();
        int sum = lines.stream()
                .map(TrebutchetTwo::findNumberIndexes)
                .map(indexesAndValues -> {
                    List<Integer> indexes = new LinkedList<>(indexesAndValues.keySet().stream().toList());
                    Collections.sort(indexes);
                    int first = indexesAndValues.get(indexes.get(0));
                    int last = indexesAndValues.get(indexes.get(indexes.size() - 1));
                    return String.format("%s%s", first, last);
                })
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private static Map<Integer, Integer> findNumberIndexes (String string) {
        Map<Integer, Integer> indexesAndValues = new HashMap<>();
        numbers.keySet().forEach(key -> addIndexIfExists(indexesAndValues, string, key, numbers.get(key)));
        for (int i = 0; i < 9; i++) {
            addIndexIfExists(indexesAndValues, string, String.valueOf(i + 1), i + 1);
        }
        return indexesAndValues;
    }

    private static void addIndexIfExists (Map<Integer, Integer> indexes,
                                          String string,
                                          String match,
                                          Integer value) {
        int index = string.indexOf(match);
        if (index >= 0) {
            indexes.put(index, value);
        }

        while (index >= 0) {
            index = string.toLowerCase().indexOf(match.toLowerCase(), index + 1);
            if (index >= 0) {
                indexes.put(index, value);
            }
        }
    }

    private static void initMap () {
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);
    }

}
