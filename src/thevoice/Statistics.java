package thevoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anna
 */
public class Statistics {

    public static List top5(WordsList text, WordsSet filters) {
        ArrayList<String> words = text.getWords();
        words.removeAll(filters.getWords());
        HashMap<String, Integer> counter = new HashMap<>();
        for (String word: words)
            if (counter.containsKey(word))
                counter.put(word, counter.get(word)+1);
            else counter.put(word, 1);
            //counter.put(word, counter.getOrDefault(word, 0)+1);
        

        List list = new LinkedList<>(counter.entrySet());
       Collections.sort(list, new Comparator<Object>() {

            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        return list.subList(0, Math.min(5, list.size()));
    }
            
    public static int different(WordsList text) {
        //text.remove(filters);
        return new WordsSet(text).size();
    }
            
    
}
