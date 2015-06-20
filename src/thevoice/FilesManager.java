package thevoice;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Klasa umożliwiająca operacje na plikach i folderach.
 * Obsługuje zwracanie wszytskich plików z katalogu o danym rozszerzeniu oraz
 * zwracanie zawartości pliku jako String.
 * @author anna
 */
public class FilesManager {
    
    public static File[] FilesFromFolder(String path, String extension) {
        File f = new File(path);
        if (f.exists() && f.isDirectory())
            return f.listFiles(
                    (File directory, String fileName) -> fileName.endsWith("." + extension)
            );
        else System.err.println("Directory " + path + " doesn't exist");
        return new File[]{};
    }
    
    public static String textStringFromFile(String path) {
        File f = new File(path);
        if (f.exists() && f.isFile())
            try {
                byte bytes[] = java.nio.file.Files.readAllBytes(f.toPath());
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.err.println("Error during reading from file");
            }
        return "";
    }
}
