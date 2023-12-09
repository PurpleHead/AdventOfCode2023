package at.poik.day.four.p1;

import at.poik.day.four.model.Scratchcard;
import java.net.URISyntaxException;

import static at.poik.day.four.ScratchcardService.*;

public class ScratchcardsOne {

    public static void main(String[] args) throws URISyntaxException {
        var scratchcards = loadScratchcards();
        var sum = scratchcards.stream()
                .map(Scratchcard::calculateValue)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

}
