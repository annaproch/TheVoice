package thevoice;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Klasa do obsługi plików jako źrodła tekstów piosenek.
 * Umożliwia dodanie słów z tekstów na listę ze wszystkich plików
 * z katalogu wykonwacy.
 * @author anna
 */
public class FilesSource extends Source {
    
    File[] TxtFilesFromFolder(String path) {
        File f = new File(path);
        if (f.exists() && f.isDirectory())
            return f.listFiles(
                    (File directory, String fileName) -> fileName.endsWith(".txt")
            );
        return new File[]{};
    }
    
    static String textStringFromFile(String path) {
        File f = new File(path);
        if (f.exists() && f.isFile())
            try {
                byte bytes[] = java.nio.file.Files.readAllBytes(f.toPath());
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.err.println("Error during reading from file");
            }
        return "";
    }

    @Override
    void addSongsForArtist(String name, List<Song> songs) {
        String artistPath = source + "/" + name;
        File[] files = TxtFilesFromFolder(artistPath);
        for (File file: files) {
            songs.add(new Song(file.getName(), textStringFromFile(file.getPath())));
        }
    }
}
