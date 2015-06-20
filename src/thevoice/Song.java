package thevoice;

/**
 *
 * @author anna
 */
public class Song {
    private final String title;
    private final String lyrics;
    
    Song(String title, String lyrics) {
        this.title = title;
        this.lyrics = lyrics;
    }
    
    String getLyrics() {
        return lyrics;
    }
}
