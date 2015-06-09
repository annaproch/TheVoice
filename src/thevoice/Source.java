package thevoice;

/**
 *
 * @author anna
 */
public abstract class Source {
    
    String source;
    
    abstract void addTextsToWordList(String artistName, WordsList wordsList);

    void setSource(String param) {
        source = param;
    }
}
