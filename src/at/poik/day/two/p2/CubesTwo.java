package at.poik.day.two.p2;

import at.poik.day.two.CubeService;
import at.poik.day.two.model.CubeColor;
import at.poik.day.two.model.Game;
import at.poik.day.two.model.GameSet;
import at.poik.io.FileLoader;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CubesTwo {

    public static void main(String[] args) throws URISyntaxException {
        FileLoader fileLoader = new FileLoader("/two");
        List<String> lines = fileLoader.loadLinesOfFile();
        List<Game> games = CubeService.parseGames(lines);

        int sum = games.stream()
                .map(game -> getFewestCubesMultiplied(game.getSets()))
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private static int getFewestCubesMultiplied(List<GameSet> gameSets) {
        Map<CubeColor, Integer> minimumColor = new HashMap<>();
        minimumColor.put(CubeColor.RED, 1);
        minimumColor.put(CubeColor.BLUE, 1);
        minimumColor.put(CubeColor.GREEN, 1);
        gameSets.forEach(gameSet ->
                        gameSet.getColorTotal()
                        .keySet()
                        .forEach(color -> {
                            if(minimumColor.get(color) < gameSet.getColorTotal().get(color)) {
                                minimumColor.put(color, gameSet.getColorTotal().get(color));
                            }
                        }));
        return minimumColor
                .values()
                .stream()
                .reduce(1, (a, b) -> a * b);
    }

}
