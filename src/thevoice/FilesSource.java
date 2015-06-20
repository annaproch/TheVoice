package thevoice;

import java.io.File;
import java.util.List;

/**
 * Klasa do obsługi plików jako źrodła tekstów piosenek.
 * Umożliwia dodanie piosenek ze wszystkich plików z katalogu wykonawcy.
 * @author anna
 */
public class FilesSource extends Source {
    
    @Override
    public void addSongsForArtist(String name, List<Song> songs) {
        String artistPath = source + name;
        File[] files = FilesManager.FilesFromFolder(artistPath, "txt");
        for (File file: files) {
            String title = file.getName().substring(0, file.getName().length()-4);
            String lyrics = FilesManager.textStringFromFile(file.getPath());
            songs.add(new Song(title, lyrics));
        }
    }
}
