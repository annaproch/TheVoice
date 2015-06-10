package thevoice;

import java.util.ArrayList;

/**
 * Lista słów umożliwiająca dodawanie wyrazów z pliku.
 * @author anna
 */
public class WordsList extends ArrayList<String> {
    private final String SPLIT_REGEX = "[\\s,.:;?!()-]+";
    
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
        String[] slowa = text.split(SPLIT_REGEX);
        for (String word: slowa)
            this.add(word.toLowerCase());
    } 
}
