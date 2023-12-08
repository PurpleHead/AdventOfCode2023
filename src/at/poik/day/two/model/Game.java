package at.poik.day.two.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Game {
    private int gameId;
    private List<GameSet> sets;

    public boolean isPossible(Map<CubeColor, Integer> availableCubes) {
        return this.getSets()
                .stream()
                .filter(gameSet -> gameSet.isPossible(availableCubes))
                    .toList()
                    .size() == this.getSets().size();
    }
}
