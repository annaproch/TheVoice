package thevoice;

import java.util.List;

/**
 *
 * @author anna
 */
public abstract class Source {
    String source;
    
    abstract void addSongsForArtist(String artistName, List<Song> songs);

    void setSource(String param) {
        source = param;
    }
}
