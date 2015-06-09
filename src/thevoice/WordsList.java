package thevoice;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author anna
 */
public class WordsList extends ArrayList {

    WordsList() {}
    
    WordsList(WordsList list) {
        this.addAll(list.getWords());
    }
    
    ArrayList<String> getWords() {
        return new ArrayList<>(this);
    }

    void addFromFile(String path) {
        String text = FilesSource.textStringFromFile(path);
        addFromString(text);
    }
    
    void addFromString(String text) {
        String[] slowa = text.split("[\\s,.:;?!()-]+");
        for (String word: slowa)
            this.add(word.toLowerCase());
    }
    
    public int countDifferentWords() {
        HashSet<String> wordsSet = new HashSet<>(this);
        return wordsSet.size();
    } 
}
