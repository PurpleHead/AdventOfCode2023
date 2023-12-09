package at.poik.day.three.p2;

import at.poik.day.three.model.Coordinate;
import at.poik.day.three.model.EnginePart;
import at.poik.io.FileLoader;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static at.poik.day.three.EngineService.*;

public class EnginesTwo {

    public static void main(String[] args) throws URISyntaxException {
        FileLoader fileLoader = new FileLoader("/three");
        List<String> lines = fileLoader.loadLinesOfFile();
        char[][] engine = parseEngine(lines);
        List<EnginePart> parts = parseParts(lines);
        List<Coordinate> gearCoordinates = parseGears(lines);
        int sum = gearCoordinates.stream()
                .map(g -> {
                    List<EnginePart> foundParts = new LinkedList<>();
                    for (int y = g.getY() - 1; y <= g.getY() + 1; y++) {
                        for (int x = g.getX() - 1; x <= g.getX() + 1; x++) {
                            if(!(y < 0 || y >= engine.length)
                                    && !(x < 0 || x >= engine[y].length)) {
                                Optional<EnginePart> part = getPartByCoordinate(new Coordinate(x, y), parts);
                                if(part.isPresent() && !foundParts.contains(part.get())) {
                                    foundParts.add(part.get());
                                }
                            }
                        }
                    }
                    if(foundParts.size() > 1) {
                        return foundParts.stream()
                                .map(EnginePart::getNumberInt)
                                .reduce(1, (a, b) -> a * b);
                    }
                    return 0;
                })
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

}
