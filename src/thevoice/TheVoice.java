package thevoice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author anna
 */
public class TheVoice {
    private static ArrayList<Artist> artists = new ArrayList<>();
    private static String sourceType;
    private static String source;
    private static String[] processors;
    private static String[] filtersPaths = new String[]{};
    private static WordsSet filters = new WordsSet();
    
    
    public static void main(String[] args) throws IOException {
        for (String arg: args) {
            if (arg.startsWith("--")) {
                 String[] params = arg.split("=");
                 String param = params[0].substring(2);
                 switch (param) {
                     case "source-type":
                         sourceType = params[1];
                         break;
                     case "source":
                         source = params[1];
                         break;
                     case "processors":
                         processors = params[1].split(",");
                         break;
                     case "filters":
                         filtersPaths = params[1].split(File.pathSeparator);
                         break;
                     default:
                         break;
                 }
            } else artists.add(new Artist(arg));
        }
        for (String filterPath: filtersPaths)
            filters.addFromFile(filterPath);
            
        switch (sourceType) {
            case "file": 
                for (Artist artist: artists)
                    artist.addTextsFromFolder(source);
                break;
            case "azlyrics.com":
                for (Artist artist: artists)
                    artist.addTextsFromAZLyrics();
                break;
            case "teksty.org":
                for (Artist artist: artists)
                    artist.addTextsFromTekstyorg();
                break;
        }
        
        
        
        for (String processor: processors) {
            System.out.println(processor);
            for (Artist artist: artists) {
                System.out.println(artist.getName() + " ");
                switch (processor) {
                    case "top5":
                        System.out.println(Statistics.top5(artist.getTexts(), filters));
                        break;
                    case "count":
                        System.out.println(Statistics.different(artist.getTexts()));
                        break;
                }
            }
            System.out.println("***");
        }
    }
    
}
