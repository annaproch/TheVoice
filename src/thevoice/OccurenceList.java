package thevoice;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * Lista par: słowo i liczby wystąpień tego słowa na liście słów podanej w konstruktorze.
 * @author anna
 */
public class OccurenceList extends LinkedList {

    public OccurenceList(WordsList text) {
        WordsList sortedWords = new WordsList(text);
        Collections.sort(sortedWords);
        if (sortedWords.size() > 0) {
            String currentWord = sortedWords.get(0);
            int currentCounter = 0;
            for (String word : sortedWords) {
                if (word.equals(currentWord)) currentCounter++;
                else {
                    this.add(new OccurencePair(currentWord, currentCounter));
                    currentCounter = 1;
                    currentWord = word;
                }
            }
            this.add(new OccurencePair(currentWord, currentCounter));
        }
    }

    protected void sort() {
        Collections.sort(this, (OccurencePair o1, OccurencePair o2)
                -> (o2.getValue()).compareTo(o1.getValue()));
    }

    /**
     * Klasa reprezentująca parę słowo i liczba jego wystąpień w tekście.
     * Implmentuje interfejs Map.Entry.
     */
    private class OccurencePair implements Entry<String, Integer> {

        private final String word;
        private Integer counter;

        public OccurencePair(final String key, final Integer value) {
            this.word = key;
            this.counter = value;
        }

        @Override
        public String getKey() {
            return word;
        }

        @Override
        public Integer getValue() {
            return counter;
        }

        @Override
        public Integer setValue(final Integer value) {
            final Integer oldValue = this.counter;
            this.counter = value;
            return oldValue;
        }

        @Override
        public String toString() {
            return word + "=" + counter;
        }
    }
}
