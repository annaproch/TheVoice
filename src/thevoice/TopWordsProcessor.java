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
        Statistics stats = new Statistics(words);
        return stats.getTheMostCommon(wordsNumber);
    }
    
    
}
