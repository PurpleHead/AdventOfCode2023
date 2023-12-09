package at.poik.day.three.p1;

import at.poik.day.three.model.Coordinate;
import at.poik.day.three.model.EnginePart;
import at.poik.io.FileLoader;

import java.net.URISyntaxException;
import java.util.*;

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

    private static boolean isSymbol(char c) {
        String str = String.valueOf(c);
        return !str.matches("[0-9.]");
    }

    private static char[][] parseEngine(List<String> lines) {
        char[][] engine = new char[lines.size()][lines.get(0).length()];

        for(int y = 0; y < engine.length; y++) {
            for(int x = 0; x < engine[y].length; x++) {
                engine[y][x] = lines.get(y).charAt(x);
            }
        }

        return engine;
    }

    private static List<EnginePart> parseParts (List<String> lines) {
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

}
