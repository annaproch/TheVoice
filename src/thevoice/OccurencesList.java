package thevoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anna
 */
public class OccurencesList {
    private final List list;
    
    OccurencesList(WordsList text) {
    HashMap<String, Integer> counter = new HashMap<>();
    ArrayList<String> words = text.getWords();
    for (String word: words)
        counter.put(word, counter.getOrDefault(word, 0)+1);
    list = new LinkedList<>(counter.entrySet());
    }
    
    private void sort() {
       Collections.sort(list, (Object o1, Object o2) ->
            ((Comparable) ((Map.Entry) (o2)).getValue())
                .compareTo(((Map.Entry) (o1)).getValue()));
    }
    
    public List getTheMostCommon(int resultsNumber) {
        sort();
        return list.subList(0, Math.min(resultsNumber, list.size()));
    }
}
