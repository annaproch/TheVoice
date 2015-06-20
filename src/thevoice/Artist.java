package thevoice;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa, która reprezentuje artystę.
 * @author anna
 */
public class Artist {
    private final String artistName;
    private final List<Song> songs;
    
    Artist(String name) {
        this.artistName = name;
        this.songs = new ArrayList<>();
    }
    
    public void addSongsFromSource(Source source) {
        source.addSongsForArtist(artistName, songs);
    }
    
    public void songsToWordsList(WordsList words, boolean limitEnabled, int limit) {
        for (Song song: songs) {
            if (limitEnabled && limit <= 0)
                break;
            int addedCount = song.addLyricsToWordsList(words, limitEnabled, limit);
            limit -= addedCount;
        }
    }

    public String getName() {
        return artistName;
    }
    
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }
    
}
