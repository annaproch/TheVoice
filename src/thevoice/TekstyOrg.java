/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thevoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author anna
 */
public class TekstyOrg extends NetSource {

    @Override
    String[] getSongsLinks(String name) {
        List<String> results = new ArrayList<>();
        String newName = name.toLowerCase().replace(" ", "-");
        //String link = "http://www.azlyrics.com/" + newName.charAt(0) + "/" + newName + ".html";
        String link = "http://teksty.org/" + newName + ",teksty-piosenek/";
        String last = "";
        try {
            last = Jsoup.connect(link).get().select("a[title=Ostatnia strona]").text();
        } catch (IOException ex) {}
        int lastPage;
        if ("".equals(last)) lastPage = 0;
        else lastPage = Integer.parseInt(last.substring(4));
                  
        for (int i = 0; i <= lastPage; i++) {
            String pageLink = link + i;
            try {
                Document d = Jsoup.connect(pageLink).get();
                Elements links = d.select("div.artistSongs").select("a.artist");
                for (Element el: links)
                    if (el.attr("href").endsWith("tekst-piosenki"))
                        results.add(el.attr("href"));
            } catch (IOException ex) {}
        }
        return results.toArray(new String[0]);
    }

    @Override
    String getSongStringFromLink(String link) {
        try {
            Document page = Jsoup.connect(link).get();
            return page.select("div.originalText").get(0).text();
        } catch (IOException ex) {
            return "";
        }
    }    
}
