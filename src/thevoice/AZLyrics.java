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
 * UWAGA: korzysta z przygotowanej na wzór azlyrics.com atrapy
 * Adama Paszke ze względu na bardzo szybkie blokowanie robotów przez
 * serwis.
 * 
 * @author anna
 */
public class AZLyrics extends NetSource {
    
    private final String FAKE_LINK = "http://students.mimuw.edu.pl/~ap360585/azlyrics/";
    
    String createSubpageFromArtistName(String name) {
        String newName = name.toLowerCase().replace(" ", "");
        return "/" + newName.charAt(0) + "/" + newName + ".html";
    }
    
    @Override
    String[] getSongsLinks(String name) {
        List<String> songsLinks = new ArrayList<>();
        //String link = source + createSubpageFromArtistName(name);
        String link = FAKE_LINK + createSubpageFromArtistName(name);
        try {
            Document page = Jsoup.connect(link).get();
            for (Element e : page.select("a[href^=\"../lyrics\""))
                songsLinks.add(e.attr("href").substring(2));
        } catch (IOException e) {
            System.err.println("Error during downloading songs urls");
        }
        return songsLinks.toArray(new String[0]);
    }

    @Override
    String getSongStringFromLink(String subLink) {
        try {
            //String songLink = "https://www." + source + subLink;
            String songLink = FAKE_LINK + subLink;
            Document page = Jsoup.connect(songLink).get();
            Element lyricsTag = page.select("div.ringtone ~ div").first();
            return lyricsTag.text();
        } catch (IOException ex) {
            System.err.println("Error during downloading song lyrics");
            return "";
        }
    }
}
