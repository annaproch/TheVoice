package thevoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Procesor odpowiadający za policzenie i wyświetlenie różnych słów pojawiających
 * się w tekstach podanych artystów.
 * @author anna
 */
public class DifferentWordsProcessor {

    private final List<Result> results;
    
    public DifferentWordsProcessor() {
        results = new ArrayList<>();
    }
    
    public void run(List<Artist> artists, boolean limitEnabled, int wordsNumber) {
        artists.stream().forEach((artist) -> {
            processArtist(artist, limitEnabled, wordsNumber);
        });
        Collections.sort(results);
                    
        results.stream().forEach((result) -> {
            System.out.println(result);
        });
    }
    
    public void processArtist(Artist artist, boolean limitEnabled, int wordsNumber) {
        WordsList words = new WordsList();
        artist.songsToWordsList(words, limitEnabled, wordsNumber);
        OccurenceList occurenceList = new OccurenceList(words);
        results.add(new Result(artist, occurenceList.size()));
    }
    
    private class Result implements Comparable {

        private final Artist artist;
        private final int counter;
        
        Result(Artist artist, int counter) {
            this.artist = artist;
            this.counter = counter;
        }
        
        @Override
        public int compareTo(Object o) {
            Result other = (Result) o;
            if (this.counter == other.counter) {
                return this.artist.getName().compareTo(other.artist.getName());
            } else {
                return other.counter - this.counter;
            }
        }
        
        @Override
        public String toString() {
            return artist.getName() + " " + counter;
        }
    }
    
}
