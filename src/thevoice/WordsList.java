package thevoice;

import java.util.ArrayList;

/**
 * Lista słów umożliwiająca dodawanie wyrazów ze stringa
 * zawierającego tekst.
 * @author anna
 */
public class WordsList extends ArrayList<String> {
    private final String SPLIT_REGEX = "[\\s,.:;?!()-]+";
    
    WordsList() {}
    
    WordsList(WordsList list) {
        this.addAll(list.getWords());
    }
    
    public ArrayList<String> getWords() {
        return new ArrayList<>(this);
    }
    
    public int addFromString(String text, boolean limitEnabled, int limit) {
        String[] slowa = text.split(SPLIT_REGEX);
        int size;
        if (limitEnabled)
            size = Math.min(limit, slowa.length);
        else size = slowa.length;
        for (int i = 0; i < size; i++)
            this.add(slowa[i].toLowerCase());
        return size;
    } 
}
