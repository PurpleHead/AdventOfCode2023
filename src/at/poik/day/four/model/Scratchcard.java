package at.poik.day.four.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Scratchcard {
    private int cardId;
    private List<Integer> winningNumbers;
    private List<Integer> actualNumbers;

    public int calculateValue() {
        int winningAmount = this.calculateWinningAmount();
        if (winningAmount > 0) {
            int sum = 1;
            for(int i = 0; i < winningAmount - 1; i++) {
                sum *= 2;
            }
            return sum;
        }
        return winningAmount;
    }

    public int calculateWinningAmount() {
        return actualNumbers.stream()
                .map(i -> winningNumbers.contains(i) ? 1 : 0)
                .reduce(0, Integer::sum);
    }
}
