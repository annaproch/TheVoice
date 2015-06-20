package thevoice;

import java.util.List;

/**
 * Klasa reprezentująca źródło, z które czerpane są teksty piosenek.
 * @author anna
 */
public abstract class Source {
    protected String source;
    
    public abstract void addSongsForArtist(String artistName, List<Song> songs);

    public void setSource(String param) {
        source = param;
    }
}
