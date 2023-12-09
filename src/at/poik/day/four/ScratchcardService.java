package at.poik.day.four;

import at.poik.day.four.model.Scratchcard;
import at.poik.io.FileLoader;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ScratchcardService {
    public static List<Scratchcard> loadScratchcards() throws URISyntaxException {
        var fileLoader = new FileLoader("/four");
        var lines = fileLoader.loadLinesOfFile();
        return lines.stream()
                .map(l -> l.split(":")[1].trim()) // Remove "Card xxx"
                .map(l -> l.split("\\|")) // Split winning and actual numbers
                .map(array -> Scratchcard.builder()
                        .winningNumbers(convertToIntegerList(array[0].trim().split(" ")))
                        .actualNumbers(convertToIntegerList(array[1].trim().split(" ")))
                        .build()
                )
                .toList();
    }

    public static List<Integer> convertToIntegerList(String... list) {
        return Arrays.stream(list)
                .filter(s -> !Objects.equals(s, ""))
                .map(Integer::parseInt)
                .toList();
    }
}
