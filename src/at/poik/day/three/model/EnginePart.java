package at.poik.day.three.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnginePart {
    private Coordinate startIndex;
    private Coordinate endIndex;
    private String number;

    public int getNumberInt() {
        return Integer.parseInt(number);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EnginePart other) {
            return other.startIndex.equals(this.startIndex) &&
                    other.endIndex.equals(this.endIndex);
        }
        return false;
    }
}
