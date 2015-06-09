package thevoice;

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
    
    public void addTextsFromSource(Source source) {
        source.addTextsToWordList(name, textsWords);
    }
    
    public List topWords(WordsList filters, int wordsNumber) {
        WordsList words = new WordsList(textsWords);
        words.remove(filters);
        OccurencesList occurences = new OccurencesList(words);
        return occurences.getTheMostCommon(wordsNumber);
    }
    
    public int different() {
        return textsWords.countDifferentWords();
    }
    
    String getName() {
        return name;
    }
    
}
