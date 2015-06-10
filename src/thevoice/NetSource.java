package thevoice;

/**
 * Klasa abstrakcyjna internetowych źródeł tekstów.
 * @author anna
 */
public abstract class NetSource extends Source {
    private final String PROTOCOL_PREFIX = "https://";
    
    abstract String[] getSongsLinks(String name);
    abstract String getSongStringFromLink(String link);

    @Override
    void addLyricsToWordsList(String artistName, WordsList wordsList) {
        String[] songLinks = getSongsLinks(artistName);
        for (String link: songLinks)        
            wordsList.addFromString(getSongStringFromLink(link));
    }
    
    @Override
    void setSource(String param) {
        source = PROTOCOL_PREFIX + param;
    }
}
