package thevoice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anna
 */
public class Filters {

    List<String> words = new ArrayList<>();
    
    void addFromFile(String path) {
        String text = FilesSource.textStringFromFile(path);
        String[] slowa = text.split("\\s+");
        for (String word: slowa)
            words.add(word.toLowerCase());
    }    
    
    List<String> getWords() {
        return new ArrayList<>(words);
    }
    
}
