package at.poik.day.two.p1;

import at.poik.day.two.CubeService;
import at.poik.day.two.model.CubeColor;
import at.poik.day.two.model.Game;
import at.poik.io.FileLoader;

import java.net.URISyntaxException;
import java.util.*;

public class CubesOne {

    private static final Map<CubeColor, Integer> availableCubes = new HashMap<>();

    public static void main(String[] args) throws URISyntaxException {
        FileLoader fileLoader = new FileLoader("/two");
        List<String> lines = fileLoader.loadLinesOfFile();
        List<Game> games = CubeService.parseGames(lines);

        availableCubes.put(CubeColor.RED, 12);
        availableCubes.put(CubeColor.GREEN, 13);
        availableCubes.put(CubeColor.BLUE, 14);

        int sum = games.stream()
                .map(game -> game.isPossible(availableCubes) ? game.getGameId() : 0)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

}
