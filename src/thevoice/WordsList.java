package thevoice;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author anna
 */
public class WordsList {
    private final ArrayList<String> words;
    
    WordsList() {
        words = new ArrayList<>();
    }
    
    WordsList(WordsList list) {
        words = new ArrayList<>(list.getWords());
    }
    
    ArrayList<String> getWords() {
        return new ArrayList<>(words);
    }

    void addFromFile(String path) {
        String text = FilesSupport.textStringFromFile(path);
        addFromString(text);
    }
    
    void addFromString(String text) {
        String[] slowa = text.split("[\\s,.:;?!()-]+");
        for (String word: slowa)
            words.add(word.toLowerCase());
    }
    
    void remove(WordsList toRemove) {
        words.removeAll(toRemove.getWords());
    }
    
    public int countDifferentWords() {
        HashSet<String> wordsSet = new HashSet<>(words);
        return wordsSet.size();
    } 
}
