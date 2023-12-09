package at.poik.day.three.p1;

import at.poik.day.three.model.Coordinate;
import at.poik.day.three.model.EnginePart;
import at.poik.io.FileLoader;

import java.net.URISyntaxException;
import java.util.*;

import static at.poik.day.three.EngineService.*;

public class EnginesOne {

    public static void main(String[] args) throws URISyntaxException {
        FileLoader fileLoader = new FileLoader("/three");
        List<String> lines = fileLoader.loadLinesOfFile();
        char[][] engine = parseEngine(lines);
        List<EnginePart> parts = parseParts(lines);
        int sum = parts.stream()
                .map(part -> {
                    int startX = part.getStartIndex().getX();
                    int endX = part.getEndIndex().getX();
                    int startY = part.getStartIndex().getY();
                    int endY = part.getEndIndex().getY();
                    boolean foundSymbol = false;
                    for (int y = startY - 1; y <= endY + 1 && !foundSymbol; y++) {
                        for (int x = startX - 1; x <= endX + 1 && !foundSymbol; x++) {
                            if(!(y < 0 || y >= engine.length)
                                    && !(x < 0 || x >= engine[y].length)) {
                                foundSymbol = isSymbol(engine[y][x]);
                            }
                        }
                    }
                    return foundSymbol ? part.getNumberInt() : 0;
                })
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

}
