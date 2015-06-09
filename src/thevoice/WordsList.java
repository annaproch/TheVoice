package thevoice;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author anna
 */
public class WordsList {
    private ArrayList<String> words = new ArrayList<>();
    
    ArrayList<String> getWords() {
        return new ArrayList<>(words);
    }

    void addFromFile(String path) throws IOException {
        File f = new File(path);
        if (f.exists() && f.isFile()) {
            byte bytes[] = Files.readAllBytes(f.toPath());
            String text = new String(bytes, StandardCharsets.UTF_8);
            addFromString(text);
        }
    }
    
        
    void remove(WordsSet toRemove) {
        words.removeAll(toRemove.getWords());
    }
    
    void addFromString(String text) {
        String[] slowa = text.split("[\\s,.:;?!()-]+");
        for (String word: slowa) {
            words.add(word.toLowerCase());
        }
    }
    
}
