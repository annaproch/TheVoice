package thevoice;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca słowa, które mają być pomijane przy robieniu statystyk
 * najczęściej występujących słów w tekstach piosenek. Słowa mogą być dodawane
 * z plików.
 * @author anna
 */
public class Filters {

    private final List<String> words = new ArrayList<>();
    
    public void addFromFile(String path) {
        String text = FilesManager.textStringFromFile(path);
        String[] slowa = text.split("\\s+");
        for (String word: slowa)
            words.add(word.toLowerCase());
    }    
    
    public List<String> getWords() {
        return new ArrayList<>(words);
    }
    
}
