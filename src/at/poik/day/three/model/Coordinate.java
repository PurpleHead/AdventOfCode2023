package at.poik.day.three.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinate {
    private int x;
    private int y;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinate other) {
            return other.x == this.x && other.y == this.y;
        }
        return false;
    }
}
