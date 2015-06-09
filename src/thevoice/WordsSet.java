package thevoice;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;

/**
 *
 * @author anna
 */
public class WordsSet {
    
    HashSet<String> words;

    WordsSet() {
        words = new HashSet<>();
    }
    
    WordsSet(WordsList list) {
        words = new HashSet<>(list.getWords());
    }
    
    void addFromFile(String path) throws IOException {
        File f = new File(path);
        if (f.exists() && f.isFile()) {
            byte bytes[] = Files.readAllBytes(f.toPath());
            String text = new String(bytes, StandardCharsets.UTF_8);
            addFromString(text);
        }
    }
     
    void addFromString(String text) {
        String[] slowa = text.split("[\\s,.:;?!()-]+");
        for (String word: slowa) {
            words.add(word.toLowerCase());
        }
    }
    
    HashSet<String> getWords() {
        return new HashSet<>(words);
    }
    
    int size() {
        return words.size();
    }
    
}
