package thevoice;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author anna
 */
public class Artist {
    private final WordsList textsWords = new WordsList();
    private final String name;
    
    Artist(String name) {
        this.name = name;
    }
    
    public void addTextsFromFolder(String path) throws IOException {
        String artistFolder = path + "/" + name;
        File[] files = FilesSupport.TxtFilesFromFolder(artistFolder);
        for (File file: files)
            textsWords.addFromFile(file.toString());
    }
    
    public void addTextsFromAZLyrics() {
        String[] songLinks = AZLyrics.getSongsLinks(name);
        for (String link: songLinks)        
            textsWords.addFromString(AZLyrics.getSongStringFromLink(link));
    }

    public void addTextsFromTekstyorg() throws IOException {
        String[] songLinks = TekstyOrg.getSongsLinks(name);
        for (String link: songLinks)        
            textsWords.addFromString(TekstyOrg.getSongStringFromLink(link));
    }
    
    public List top5(WordsList filters) {
        WordsList words = new WordsList(textsWords);
        words.remove(filters);
        OccurencesList occurences = new OccurencesList(words);
        return occurences.getTheMostCommon(5);
    }
    
    public int different() {
        return textsWords.countDifferentWords();
    }
    
    String getName() {
        return name;
    }
    
}
