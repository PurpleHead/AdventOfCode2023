package at.poik.day.one.p1;

import at.poik.io.FileLoader;

import java.net.URISyntaxException;
import java.util.List;
import java.util.OptionalInt;

public class Trebutchet {

    public static void main(String... args) throws URISyntaxException {
        FileLoader loader = new FileLoader("/one/p1");
        List<String> lines = loader.loadLinesOfFile("input.txt");
        int sum = lines.stream()
                .map(s -> {
                    StringBuilder builder = new StringBuilder(s);
                    String reversed = builder.reverse().toString();
                    Integer first = Trebutchet.findFirstIntegerInString(s);
                    Integer last = Trebutchet.findFirstIntegerInString(reversed);
                    // We can assume that these Integers aren't null
                    return String.format("%s%s", first, last);
                })
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    public static Integer findFirstIntegerInString (String string) {
        OptionalInt firstInt = string.chars()
                .filter(c -> {
                    String characterAsString = String.valueOf((char) c);
                    return characterAsString.matches("[0-9]");
                })
                .findFirst();
        if (firstInt.isPresent()) {
            char charValue = (char) firstInt.getAsInt();
            return Integer.parseInt(String.valueOf(charValue));
        }
        return null;
    }
}
