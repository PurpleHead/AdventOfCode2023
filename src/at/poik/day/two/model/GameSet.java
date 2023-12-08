package at.poik.day.two.model;

import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
@NoArgsConstructor
public class GameSet {
    private Map<CubeColor, Integer> colorTotal = new HashMap<>();

    public boolean isPossible(Map<CubeColor, Integer> availableCubes) {
        AtomicBoolean possible = new AtomicBoolean(true);
        colorTotal.forEach((color, amount) ->
                possible.set(possible.get() && availableCubes.get(color) >= amount));
        return possible.get();
    }
}
