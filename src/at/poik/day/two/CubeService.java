package at.poik.day.two;

import at.poik.day.two.model.CubeColor;
import at.poik.day.two.model.Game;
import at.poik.day.two.model.GameSet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CubeService {
    public static List<Game> parseGames(List<String> lines) {
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
