package thevoice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Główna klasa progamu wyznaczającego statystyki dla tekstów piosenek podanych
 * autorów. 
 * Teksty mogą być pobierane z plików oraz serwisów teksty.org i azlyrics.com.
 * Obsługiwane zliczanie różnych słów w tekstach oraz lista 5 najcześciej
 * występujących słów (w tym możliwe odfiltrowanie słów, których nie chcemy brać
 * pod uwagę).
 * @author anna
 */
public class TheVoice {
    private static final ArrayList<Artist> artists = new ArrayList<>();
    private static Source source;
    private static String[] processors;
    private static final Filters filters = new Filters();
    
    public static void main(String[] args) throws IOException {
        for (String arg: args) {
            if (arg.startsWith("--")) {
                 String[] params = arg.split("=");
                 String param = params[0].substring(2);
                 switch  (param) {
                     case "source-type":
                         switch (params[1]) {
                            case "file": 
                                source = new FilesSource();
                                break;
                            case "azlyrics.com":
                                source = new AZLyrics();
                                break;
                            case "teksty.org":
                                source = new TekstyOrg();
                                break;
                         }
                         break;
                     case "source":
                         source.setSource(params[1]);
                         break;
                     case "processors":
                         processors = params[1].split(",");
                         break;
                     case "filters":
                         String[] paths = params[1].split(File.pathSeparator);
                         for (String filterPath: paths)
                             filters.addFromFile(filterPath);
                         break;
                 }
            } else artists.add(new Artist(arg));
        }
                        
        artists.stream().forEach((artist) -> {
            artist.addSongsFromSource(source);
        });
        
        for (String processor: processors) {
            System.out.println(processor + ":");
            for (Artist artist: artists) {
                System.out.print(artist.getName() + " ");
                switch (processor) {
                    case "top5":
                        System.out.println(TopWordsProcessor.process(artist.getSongs(), filters, 5));
                        break;
                    case "count":
                        System.out.println(CountDifferentWordsProcessor.process(artist.getSongs()));
                        break;
                }
            }
            System.out.println("***");
        }
    }
    
}
