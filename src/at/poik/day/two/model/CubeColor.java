package at.poik.day.two.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CubeColor {
    RED("red"),
    BLUE("blue"),
    GREEN("green");

    private final String color;
}
