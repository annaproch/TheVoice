package thevoice;

import java.util.List;

/**
 *
 * @author anna
 */
public class TopWordsProcessor {
    
    public static List process(List<Song> songs, Filters filters, int wordsNumber) {
        WordsList words = new WordsList();
        for (Song song: songs)
            words.addFromString(song.getLyrics());
        words.removeAll(filters.getWords());
        OccurenceList occurenceList = new OccurenceList(words);
        occurenceList.sort();
        return occurenceList.subList(0, Math.min(wordsNumber, occurenceList.size()));
    }
    
    
}
