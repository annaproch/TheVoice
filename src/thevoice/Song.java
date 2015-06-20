package thevoice;

/**
 * Klasa reprezentująca piosenkę.
 * @author anna
 */
public class Song {
    private final String title;
    private final String lyrics;
    
    Song(String title, String lyrics) {
        this.title = title;
        this.lyrics = lyrics;
    }
    
    public String getLyrics() {
        return lyrics;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int addLyricsToWordsList(WordsList words, boolean limitEnabled, int limit) {
        return words.addFromString(lyrics, limitEnabled, limit);
    }
}
