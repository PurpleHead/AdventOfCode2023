package at.poik.day.four.p2;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static at.poik.day.four.ScratchcardService.*;

public class ScratchcardsTwo {
    public static void main(String[] args) throws URISyntaxException {
        var scratchcards = loadScratchcards();
        var occurrences = new HashMap<Integer, Integer>();

        // Init map
        scratchcards.forEach(c -> occurrences.put(c.getCardId(), 1));

        // Count wins of each card
        scratchcards.forEach(card -> {
            var amount = card.calculateWinningAmount();
            for(int i = card.getCardId() + 1; i <= amount + card.getCardId(); i++) {
                occurrences.put(i, occurrences.get(i) + (occurrences.get(card.getCardId())));
            }
        });

        var totalCards = occurrences.values()
                .stream()
                .reduce(0, Integer::sum);

        System.out.println(totalCards);
    }
}
