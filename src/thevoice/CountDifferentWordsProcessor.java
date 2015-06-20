package thevoice;

import java.util.List;

/**
 *
 * @author anna
 */
public class CountDifferentWordsProcessor {
    
    public static int process(List<Song> songs) {
        WordsList words = new WordsList();
        for (Song song: songs)
            words.addFromString(song.getLyrics());
        Statistics stats = new Statistics(words);
        return stats.countDifferentWords();
    }
}
