package at.poik.day.three;

import at.poik.day.three.model.Coordinate;
import at.poik.day.three.model.EnginePart;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class EngineService {

    public static boolean isSymbol(char c) {
        String str = String.valueOf(c);
        return !str.matches("[0-9.]");
    }

    public static char[][] parseEngine(List<String> lines) {
        char[][] engine = new char[lines.size()][lines.get(0).length()];

        for(int y = 0; y < engine.length; y++) {
            for(int x = 0; x < engine[y].length; x++) {
                engine[y][x] = lines.get(y).charAt(x);
            }
        }

        return engine;
    }

    public static List<EnginePart> parseParts (List<String> lines) {
        List<EnginePart> parts = new LinkedList<>();

        for(int y = 0; y < lines.size(); y++) {
            for(int x = 0; x < lines.get(y).length(); x++) {
                char c = lines.get(y).charAt(x);
                if(String.valueOf(c).matches("[0-9]")) {
                    int finalX = x;
                    int finalY = y;
                    String currentNumber = String.valueOf(c);
                    Optional<EnginePart> prevPart = parts
                            .stream()
                            .filter(enginePart -> {
                                        Coordinate endIndex = enginePart.getEndIndex();
                                        return endIndex.getX() == finalX - 1 && endIndex.getY() == finalY;
                                    }
                            )
                            .findFirst();
                    if(prevPart.isPresent()) {
                        EnginePart part = prevPart.get();
                        part.setNumber(part.getNumber() + currentNumber);
                        part.setEndIndex(new Coordinate(x, y));
                    } else {
                        parts.add(new EnginePart(new Coordinate(x, y),
                                new Coordinate(x, y),
                                currentNumber));
                    }
                }
            }
        }
        return parts;
    }

    public static List<Coordinate> parseGears(List<String> lines) {
        List<Coordinate> gears = new LinkedList<>();
        for(int y = 0; y < lines.size(); y++) {
            for(int x = 0; x < lines.size(); x++) {
                if(lines.get(y).charAt(x) == '*') {
                    gears.add(new Coordinate(x, y));
                }
            }
        }
        return gears;
    }

    public static Optional<EnginePart> getPartByCoordinate(Coordinate coordinate, List<EnginePart> parts) {
        final int x = coordinate.getX();
        final int y = coordinate.getY();
        return parts.stream().filter(p ->
                    x >= p.getStartIndex().getX() && x <= p.getEndIndex().getX()
                    && y == p.getStartIndex().getY())
                .findFirst();
    }
}
