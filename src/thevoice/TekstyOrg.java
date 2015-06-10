package thevoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Obsługa tekstów piosenek z serwisu teksty.org.
 * Umożliwia pobranie tekstów dla danego wykonawcy.
 * @author anna
 */
public class TekstyOrg extends NetSource {

    @Override
    String[] getSongsLinks(String name) {
        String[] subpages = getArtistSubpages(name);
        List<String> results = new ArrayList<>();
                  
        for(String subpage: subpages) {
            try {
                Document page = Jsoup.connect(subpage).get();
                Elements links = page.select("div.artistSongs").select("a.artist");
                for (Element link: links)
                    if (link.attr("href").endsWith("tekst-piosenki"))
                        results.add(link.attr("href"));
            } catch (IOException ex) {
                System.err.println("Error during downloading songs urls");
            }
        }
        return results.toArray(new String[0]);
    }

    String[] getArtistSubpages(String name) {
        List<String> subpages = new ArrayList<>();
        String link = source + createSubpageFromArtistName(name);
        String last = "";
        try {
            last = Jsoup.connect(link).get().select("a[title=Ostatnia strona]").text();
        } catch (IOException ex) {
            System.err.println("Error during downloading artist subpages");
        }
        int lastPage;
        if ("".equals(last)) lastPage = 0;
        else lastPage = Integer.parseInt(last.substring(4));
                  
        for (int i = 0; i <= lastPage; i++)
            subpages.add(link + i);
        return subpages.toArray(new String[0]);
    }
    
    String createSubpageFromArtistName(String name) {
        String newName = name.toLowerCase().replace(" ", "-");
        return "/" + newName + ",teksty-piosenek/";
    }
    
    @Override
    String getSongStringFromLink(String link) {
        try {
            Document page = Jsoup.connect(link).get();
            return page.select("div.originalText").get(0).text();
        } catch (IOException ex) {
            System.err.println("Error during downloading songs lyrics");
            return "";
        }
    }    
}
