package thevoice;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author anna
 */
public class Artist {
    private WordsList textsWords = new WordsList();
    private final String name;
    
    Artist(String name) {
        this.name = name;
    }
    
    public void addTextsFromFolder(String path) throws IOException {
        String artistFolder = path + "/" + name;
        File f = new File(artistFolder);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            for (File file: files) {
                System.out.println(file.toString());
                if (file.toString().endsWith(".txt"))
                    textsWords.addFromFile(file.toString());
            }
        }
    }
    
    public void addTextsFromAZLyrics() throws IOException {
        String newName = name.toLowerCase().replace(" ", "");
        //String link = "http://www.azlyrics.com/" + newName.charAt(0) + "/" + newName + ".html";
        String link = "http://students.mimuw.edu.pl/~ap360585/azlyrics/" + newName.charAt(0) + "/" + newName + ".html";
        
        System.out.println(link);
        Document d = Jsoup.connect(link).get();
            for (Element e : d.select("a[href^=\"../lyrics\"")) {
                //System.out.println(e.attr("href"));
                //String songLink = "http://www.azlyrics.com/" + e.attr("href").substring(2);
                String songLink = "http://students.mimuw.edu.pl/~ap360585/azlyrics/" + e.attr("href").substring(2);
                Document doc = Jsoup.connect(songLink).get();
                Element links = doc.select("div.ringtone ~ div").first();
                textsWords.addFromString(links.text());
            }
    }

    public void addTextsFromTekstyorg() throws IOException {
        String newName = name.toLowerCase().replace(" ", "-");
        //String link = "http://www.azlyrics.com/" + newName.charAt(0) + "/" + newName + ".html";
        String link = "http://teksty.org/" + newName + ",teksty-piosenek/";
        System.out.println(link);
        String last = Jsoup.connect(link).get().select("a[title=Ostatnia strona]").text();
        int lastPage;
        if ("".equals(last))
            lastPage = 0;
        else lastPage = Integer.parseInt(last.substring(4));
        
            
        for (int i = 0; i <= lastPage; i++) {
            String pageLink = link + i;
            //System.out.println(pageLink);
            Document d = Jsoup.connect(pageLink).get();
            Elements links = d.select("div.artistSongs").select("a.artist");
            for (Element el: links) {
                if (el.attr("href").endsWith("tekst-piosenki")) {
                    System.out.println(el.attr("href"));
                    Document page = Jsoup.connect(el.attr("href")).get();
                    String text = page.select("div.originalText").get(0).text();
                    textsWords.addFromString(text);
                }
            }
            //System.out.println(links);
        }
        
    }
    
    public WordsList getTexts() {
        return textsWords;
    }

    String getName() {
        return name;
    }
    
}
