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
    
    @Override
    void addSongsForArtist(String name, List<Song> songs) {
        String artistPath = source + "/" + name;
        File[] files = FilesManager.TxtFilesFromFolder(artistPath);
        for (File file: files) {
            songs.add(new Song(file.getName(), FilesManager.textStringFromFile(file.getPath())));
        }
    }
}
