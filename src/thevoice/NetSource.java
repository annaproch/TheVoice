package thevoice;

/**
 *
 * @author anna
 */
public abstract class NetSource extends Source {
    
    abstract String[] getSongsLinks(String name);
    abstract String getSongStringFromLink(String link);

    @Override
    void addTextsToWordList(String name, WordsList wordsList) {
        String[] songLinks = getSongsLinks(name);
        for (String link: songLinks)        
            wordsList.addFromString(getSongStringFromLink(link));
    }
}
