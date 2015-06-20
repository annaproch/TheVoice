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
        OccurenceList occurenceList = new OccurenceList(words);
        return occurenceList.size();
    }
}
