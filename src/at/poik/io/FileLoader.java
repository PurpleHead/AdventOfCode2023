package at.poik.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class FileLoader {

    private String folder;
    public FileLoader () throws URISyntaxException {
        this("/");
    }

    public FileLoader (String folder) throws URISyntaxException {
        this.folder = folder;
    }

    public List<String> loadLinesOfFile () {
        return this.loadLinesOfFile("/input.txt");
    }

    public List<String> loadLinesOfFile (String filename) {
        try {
            File file = this.getFile(filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            return reader.lines().toList();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private File getFile(String filename) {
        try {
            return new File(Objects.requireNonNull(this.getClass().getResource(String.format("%s/%s", this.folder, filename))).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
