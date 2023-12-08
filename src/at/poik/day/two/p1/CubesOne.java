package at.poik.day.two.p1;

import at.poik.day.two.model.CubeColor;
import at.poik.day.two.model.Game;
import at.poik.day.two.model.GameSet;
import at.poik.io.FileLoader;

import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CubesOne {

    // Available cubes
    private static final Map<CubeColor, Integer> availableCubes = new HashMap<>();

    public static void main(String[] args) throws URISyntaxException {
        FileLoader fileLoader = new FileLoader("/two");
        List<String> lines = fileLoader.loadLinesOfFile("input.txt");
        List<Game> games = parseGames(lines);

        availableCubes.put(CubeColor.RED, 12);
        availableCubes.put(CubeColor.GREEN, 13);
        availableCubes.put(CubeColor.BLUE, 14);

        int sum = games.stream()
                .map(game -> game.isPossible(availableCubes) ? game.getGameId() : 0)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private static List<Game> parseGames(List<String> lines) {
        return lines.stream()
                .map(l -> {
                    Game game = new Game();
                    String[] split = l.split(":");
                    String g = split[0];
                    int gameId = Integer.parseInt(g.split(" ")[1]);

                    game.setGameId(gameId);
                    game.setSets(parseSets(split[1]));

                    return game;
                })
                .toList();
    }

    private static List<GameSet> parseSets (String string) {
        String[] sets = string.split(";");
        List<GameSet> gameSets = new LinkedList<>();
        Arrays.stream(sets).forEach(set -> {
            GameSet gameSet = new GameSet();
            var cubes = set.split(",");
            Arrays.stream(cubes).forEach(cube -> {
                var cubeSplit = cube.trim().split(" ");
                int amount = Integer.parseInt(cubeSplit[0]);
                CubeColor color = CubeColor.valueOf(cubeSplit[1].toUpperCase());
                gameSet.getColorTotal().put(color, amount);
                gameSets.add(gameSet);
            });
        });
        return gameSets;
    }

}
