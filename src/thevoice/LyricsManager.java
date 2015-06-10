package thevoice;

import java.util.List;

/**
 * Klasa, która zarządza tekstami piosenek. Pozwala na dodawanie 
 * z różnych źrodeł oraz umożliwia wyznaczanie statystyk: zliczanie różnych
 * oraz najczęściej występujacych słów w dodanych tekstach.
 * @author anna
 */
public class LyricsManager {
    private final WordsList lyricsWords;
    private final String name;
    
    LyricsManager(String name) {
        this.lyricsWords = new WordsList();
        this.name = name;
    }
    
    public void addLyricsFromSource(Source source) {
        source.addLyricsToWordsList(name, lyricsWords);
    }
    
    public List topWords(WordsList filters, int wordsNumber) {
        WordsList words = new WordsList(lyricsWords);
        words.removeAll(filters);
        Statistics stats = new Statistics(words);
        return stats.getTheMostCommon(wordsNumber);
    }
    
    public int countDifferentWords() {
        Statistics stats = new Statistics(lyricsWords);
        return stats.countDifferentWords();
    }
    
    String getName() {
        return name;
    }
    
}
