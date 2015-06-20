package thevoice;

import java.util.List;

/**
 * Klasa abstrakcyjna internetowych źródeł tekstów.
 * @author anna
 */
public abstract class NetSource extends Source {
    
    protected abstract String[] getSongsLinks(String name);
    protected abstract Song getSongFromLink(String link);

    @Override
    public void addSongsForArtist(String artistName, List<Song> songs) {
        String[] songLinks = getSongsLinks(artistName);
        for (String link: songLinks)        
            songs.add(getSongFromLink(link));
    }
}
