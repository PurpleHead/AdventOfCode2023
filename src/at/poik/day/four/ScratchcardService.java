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
                .map(l -> {
                    var firstSplit = l.split(":");
                    int cardId = extractCardId(firstSplit[0].trim());
                    var secondSplit = firstSplit[1].trim().split("\\|");
                    return Scratchcard.builder()
                            .cardId(cardId)
                            .winningNumbers(convertToIntegerList(secondSplit[0].trim().split(" ")))
                            .actualNumbers(convertToIntegerList(secondSplit[1].trim().split(" ")))
                            .build();
                })
                .toList();
    }

    private static List<Integer> convertToIntegerList(String... list) {
        return Arrays.stream(list)
                .filter(s -> !Objects.equals(s, ""))
                .map(Integer::parseInt)
                .toList();
    }

    private static int extractCardId(String str) {
        var split = str.split(" ");
        return Arrays.stream(split)
                .filter(s -> !Objects.equals(s, "") && !Objects.equals(s, "Card"))
                .findFirst()
                .map(Integer::parseInt)
                .orElse(-1);
    }
}
