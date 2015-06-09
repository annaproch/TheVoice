package thevoice;

/**
 *
 * @author anna
 */
public abstract class NetSource extends Source {
    
    abstract String[] getSongsLinks(String name);
    abstract String getSongStringFromLink(String link);

    @Override
    void addTextsToWordList(String artistName, WordsList wordsList) {
        String[] songLinks = getSongsLinks(artistName);
        for (String link: songLinks)        
            wordsList.addFromString(getSongStringFromLink(link));
    }
}
