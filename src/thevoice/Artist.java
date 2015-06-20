package thevoice;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa, która zarządza tekstami piosenek. Pozwala na dodawanie 
 * z różnych źrodeł oraz umożliwia wyznaczanie statystyk: zliczanie różnych
 * oraz najczęściej występujacych słów w dodanych tekstach.
 * @author anna
 */
public class Artist {
    private final String name;
    private final List<Song> songs;
    
    Artist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }
    
    public void addSongsFromSource(Source source) {
        source.addSongsForArtist(name, songs);
    }

    String getName() {
        return name;
    }
    
    List<Song> getSongs() {
        return new ArrayList<>(songs);
    }
    
}
