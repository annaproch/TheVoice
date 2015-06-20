package thevoice;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Klasa odpowiedzialna za wyznaczenie statystyk dla danej listy słów z tekstów
 * piosenek.
 * Dla listy słów wyznacza liczbę wystąpień każdego słowa. Zlicza różne słowa
 * i podaje listę najczęściej występujacych.
 * @author anna
 */
public class OccurenceList extends LinkedList{

    public OccurenceList(WordsList text) {
        WordsList sortedWords = new WordsList(text);
        Collections.sort(sortedWords);
        if (sortedWords.size() > 0) {
            String currentWord = sortedWords.get(0);
            int currentCounter = 0;
            for (String word : sortedWords) {
                if (word.equals(currentWord)) currentCounter++;
                else {
                    this.add(new OccurencePair<>(currentWord, currentCounter));
                    currentCounter = 1;
                    currentWord = word;
                }
            }
            this.add(new OccurencePair<>(currentWord, currentCounter));
        }
    }

    protected void sort() {
        Collections.sort(this, (OccurencePair o1, OccurencePair o2)
                -> ((Comparable) o2.getValue()).compareTo(o1.getValue()));
    }


    /**
     * Klasa reprezentująca parę słowo i liczba jego wystąpień w tekście.
     * Implmentuje interfejs Map.Entry.
     */
    private class OccurencePair<K, V> implements Entry<K, V> {

        private final K key;
        private V value;

        public OccurencePair(final K key) {
            this.key = key;
        }

        public OccurencePair(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(final V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}