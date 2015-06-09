package thevoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author anna
 */
public class AZLyrics extends NetSource {

    @Override
    String[] getSongsLinks(String name) {
        List<String> links = new ArrayList<>();
        String newName = name.toLowerCase().replace(" ", "");
        //String link = "http://www.azlyrics.com/" + newName.charAt(0) + "/" + newName + ".html";
        String link = "http://students.mimuw.edu.pl/~ap360585/azlyrics/" + newName.charAt(0) + "/" + newName + ".html";
        System.out.println(link);
        try {
            Document d = Jsoup.connect(link).get();
            for (Element e : d.select("a[href^=\"../lyrics\""))
                links.add(e.attr("href").substring(2));
        } catch (IOException e) {
            System.err.println("Error during downloading songs urls");
        }
        return links.toArray(new String[0]);
    }

    @Override
    String getSongStringFromLink(String link) {
        try {
            //String songLink = "http://www.azlyrics.com/" + link;
            String songLink = "http://students.mimuw.edu.pl/~ap360585/azlyrics/" + link;
            Document doc = Jsoup.connect(songLink).get();
            Element links = doc.select("div.ringtone ~ div").first();
            return links.text();
        } catch (IOException ex) {
            System.err.println("Error during downloading song lyrics");
            return "";
        }
    }
}
