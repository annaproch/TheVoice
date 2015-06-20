package thevoice;

import java.util.List;

/**
 * Klasa abstrakcyjna internetowych źródeł tekstów.
 * @author anna
 */
public abstract class NetSource extends Source {
    private final String PROTOCOL_PREFIX = "http://";
    
    abstract String[] getSongsLinks(String name);
    abstract Song getSongFromLink(String link);

    @Override
    void addSongsForArtist(String artistName, List<Song> songs) {
        String[] songLinks = getSongsLinks(artistName);
        for (String link: songLinks)        
            songs.add(getSongFromLink(link));
    }
    
    @Override
    void setSource(String param) {
        source = PROTOCOL_PREFIX + param;
    }
}
