package thevoice;

import java.util.List;

/**
 * Procesor odpowiadający za policzenie i wyświetlenie dla każdego artysty
 * słów, które najcześciej pojawiają się w jego piosenkach z pominięciem
 * wyznaczonych słów.
 * @author anna
 */
public class TopWordsProcessor {
    
    public void run(List<Artist> artists, boolean limitEnabled, Filters filters, int wordsNumber) {
        artists.stream().map((artist) -> {
            System.out.println(artist.getName());
            return artist;
        }).forEach((artist) -> {
            System.out.println(processArtist(artist, limitEnabled, filters, wordsNumber));
        });
    }
    
    public static List processArtist(Artist artist, boolean limitEnabled, Filters filters, int wordsNumber) {
        WordsList words = new WordsList();
        artist.songsToWordsList(words, limitEnabled, -1);
        words.removeAll(filters.getWords());
        OccurenceList occurenceList = new OccurenceList(words);
        occurenceList.sort();
        return occurenceList.subList(0, Math.min(wordsNumber, occurenceList.size()));
    }
    
}
