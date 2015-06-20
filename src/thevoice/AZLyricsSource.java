package thevoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Obsługa tekstów piosenek z serwisu azlyrics.com.
 * Umożliwia pobranie tekstów dla danego wykonawcy.
 * 
 * @author anna
 */
public class AZLyricsSource extends NetSource {

    private String createSubpageFromArtistName(String name) {
        String newName = name.toLowerCase().replace(" ", "");
        return "/" + newName.charAt(0) + "/" + newName + ".html";
    }
    
    @Override
    protected String[] getSongsLinks(String name) {
        List<String> songsLinks = new ArrayList<>();
        String link = source + createSubpageFromArtistName(name);
        try {
            Document page = Jsoup.connect(link).get();
            page.select("a[href^=\"../lyrics\"").stream().forEach((e) -> {
                songsLinks.add(e.attr("href").substring(2));
            });
        } catch (IOException e) {
            System.err.println("Error during downloading songs urls from " + link);
        }
        return songsLinks.toArray(new String[0]);
    }

    @Override
    protected Song getSongFromLink(String subLink) {
        String songLink = source + subLink;
        try {
            Document page = Jsoup.connect(songLink).get();
            Element lyricsTag = page.select("div.ringtone ~ div").first();
            return new Song(subLink, lyricsTag.text());
        } catch (IOException ex) {
            System.err.println("Error during downloading song lyrics from " + songLink);
            return new Song(subLink, "");
        }
    }
}
