package thevoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Obsługa tekstów piosenek z serwisu teksty.org.
 * Umożliwia pobranie tekstów dla danego wykonawcy.
 * @author anna
 */
public class TekstyOrgSource extends NetSource {

    @Override
    public String[] getSongsLinks(String name) {
        String[] subpages = getArtistSubpages(name);
        List<String> results = new ArrayList<>();
                  
        for(String subpage: subpages) {
            try {
                Document page = Jsoup.connect(subpage).get();
                Elements links = page.select("div.artistSongs").select("a.artist");
                links.stream().filter((link) -> (link.attr("href").endsWith("tekst-piosenki"))).forEach((link) -> {
                    results.add(link.attr("href"));
                });
            } catch (IOException ex) {
                System.err.println("Error during downloading songs urls " + subpage);
            }
        }
        return results.toArray(new String[0]);
    }

    private String[] getArtistSubpages(String name) {
        List<String> subpages = new ArrayList<>();
        String link = source + createSubpageFromArtistName(name);
        System.out.println(link);
        String last = "";
        try {
            last = Jsoup.connect(link).get().select("a[title=Ostatnia strona]").text();
        } catch (IOException ex) {
            System.err.println("Error during downloading artist subpages " + link);
        }
        int lastPage = 15;
        //if ("".equals(last)) lastPage = 0;
        //else lastPage = Integer.parseInt(last.substring(4));
                  
        for (int i = 0; i <= lastPage; i++)
            subpages.add(link + i);
        return subpages.toArray(new String[0]);
    }
    
    private String createSubpageFromArtistName(String name) {
        String newName = name.toLowerCase().replace(" ", "-");
        return "/" + newName + ",teksty-piosenek/";
    }
    
    @Override
    protected Song getSongFromLink(String link) {
        try {
            Document page = Jsoup.connect(link).get();
            return new Song(link, page.select("div.originalText").get(0).text());
        } catch (IOException ex) {
            System.err.println("Error during downloading songs lyrics " + link);
            return new Song(link, "");
        }
    }    
}
